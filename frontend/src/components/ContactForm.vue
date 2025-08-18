<script setup lang="ts">
import axios from 'axios';
import { reactive, ref, defineEmits } from 'vue';
import { CONTACT_METHODS, WORK_TYPES } from '../constants/workTypes';

interface LeadDTO {
  name: string;
  phone: string;
  typeOfWork?: string;
  comment?: string;
  contactMethods: string[];
}

// Передаємо подію закриття модалки батьку
const emit = defineEmits<{
  (e: 'close'): void
}>();

const form = reactive<LeadDTO>({
  name: '',
  phone: '',
  typeOfWork: '',
  comment: '',
  contactMethods: []
})

const errors = reactive({
  name: '',
  phone: '',
  typeOfWork: '',
  contactMethods: ''
});

const successMessage = ref('');

const validateForm = () => {
  let isValid = true;
  errors.name = '';
  errors.phone = '';
  errors.typeOfWork = '';
  errors.contactMethods = '';

  if (!form.name || form.name.length < 2) {
    errors.name = 'Ім’я повинно містити мінімум 2 символи';
    isValid = false;
  }

  if (!form.phone || !/^\d{10,}$/.test(form.phone)) {
    errors.phone = 'Введіть коректний номер (мін. 10 цифр)';
    isValid = false;
  }

  if (!form.typeOfWork) {
    errors.typeOfWork = 'Виберіть тип роботи';
    isValid = false;
  }

  if (form.contactMethods.length === 0) {
    errors.contactMethods = 'Виберіть як з вами звязатись'
    isValid = false
  }

  return isValid;
}

const submitLead = async () => {
  if (!validateForm()) return;

  try {
    await axios.post('http://localhost:8080/api/leads', form);

    // Показуємо гарне повідомлення
    successMessage.value = 'Дані успішно відправлено!';

    // Очищення форми
    form.name = '';
    form.phone = '';
    form.typeOfWork = '';
    form.comment = '';
    form.contactMethods = [];

    // Закриваємо модалку через 2 секунди
    setTimeout(() => emit('close'), 2000);

  } catch (error: any) {
    successMessage.value = 'Сталася помилка: ' + (error.response?.data?.message || error.message);
  }
}
</script>

<template>
  <div class="contact-form">
    <button class="close-btn" @click="emit('close')">×</button>
    <h2>Залиште ваші контакти</h2>

    <div class="form-group">
      <label for="name">Ім'я</label>
      <input v-model="form.name" id="name" placeholder="Ваше ім’я" />
      <p v-if="errors.name" class="error">{{ errors.name }}</p>
    </div>

    <div class="form-group">
      <label for="phone">Телефон</label>
      <input v-model="form.phone" id="phone" placeholder="Наприклад: 380501234567" />
      <p v-if="errors.phone" class="error">{{ errors.phone }}</p>
    </div>

    <div class="form-group">
      <label>Тип роботи</label>
      <select v-model="form.typeOfWork">
        <option disabled value="">Оберіть тип роботи</option>
        <option v-for="type in WORK_TYPES" :key="type" :value="type">
          {{ type }}
        </option>
      </select>
      <p v-if="errors.typeOfWork" class="error">{{ errors.typeOfWork }}</p>
    </div>

    <div class="form-group">
      <label>Коментар</label>
      <textarea v-model="form.comment" placeholder="Ваш коментар або побажання"></textarea>
    </div>

    <div class="form-group contact-methods">
      <label>Як з вами зв’язатися?</label>
      <div class="methods-grid">
        <div v-for="method in CONTACT_METHODS" :key="method" class="method-item">
          <input type="checkbox" :value="method" v-model="form.contactMethods" />
          <span class="method-label">{{ method }}</span>
        </div>
      </div>
      <p v-if="errors.contactMethods" class="error">{{ errors.contactMethods }}</p>
    </div>

    <button type="button" class="submit-btn" @click="submitLead">Відправити</button>

    <div v-if="successMessage" class="success-message">{{ successMessage }}</div>
  </div>
</template>

<style scoped>
.contact-form {
  position: relative;
  display: flex;
  flex-direction: column;
  gap: 15px;
  padding: 20px;
  background: #696969;
  border-radius: 15px;
  width: 100%;
  max-width: 500px;
}

.close-btn {
  position: absolute;
  top: 10px;
  right: 10px;
  font-size: 1.5rem;
  background: transparent;
  border: none;
  cursor: pointer;
}

.contact-form h2 {
  text-align: center;
  margin-bottom: 20px;
}

.form-group {
  display: flex;
  flex-direction: column;
}

.form-group input,
.form-group textarea,
.form-group select {
  padding: 10px;
  border: 1px solid #c0c0c0;
  border-radius: 10px;
  font-size: 1rem;
  margin-top: 5px;
}

.form-group textarea {
  resize: vertical;
  min-height: 80px;
}

.error {
  color: red;
  font-size: 0.9rem;
  margin-top: 5px;
}

.contact-methods .methods-grid {
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
  margin-top: 5px;
}

.method-item {
  display: flex;
  align-items: center;
  gap: 5px;
  background: #5a5a5a;
  padding: 5px 10px;
  border-radius: 8px;
}

.method-label {
  font-size: 0.9rem;
}

.submit-btn {
  background: #FF6B6B;
  color: white;
  padding: 12px;
  border-radius: 50px;
  border: none;
  font-size: 1.1rem;
  cursor: pointer;
  transition: background 0.3s;
}

.submit-btn:hover {
  background: #ff4b4b;
}

.success-message {
  margin-top: 10px;
  padding: 10px;
  background-color: #d4edda;
  color: #155724;
  border-radius: 10px;
  text-align: center;
  font-weight: bold;
  animation: fadeIn 0.3s;
}

@keyframes fadeIn {
  from {
    opacity: 0;
  }

  to {
    opacity: 1;
  }
}
</style>
