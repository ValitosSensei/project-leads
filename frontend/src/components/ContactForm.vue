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
    errors.contactMethods = 'Виберіть як з вами звязатись';
    isValid = false;
  }

  return isValid;
}

const submitLead = async () => {
  if (!validateForm()) return;

  try {
    await axios.post('http://localhost:8080/api/leads', form);
    successMessage.value = 'Дані успішно відправлено!';
    form.name = '';
    form.phone = '';
    form.typeOfWork = '';
    form.comment = '';
    form.contactMethods = [];
    setTimeout(() => emit('close'), 2000);
  } catch (error: any) {
    successMessage.value = 'Сталася помилка: ' + (error.response?.data?.message || error.message);
  }
}

import { onMounted, onUnmounted } from 'vue';
onMounted(() => { document.body.style.overflow = 'hidden'; });
onUnmounted(() => { document.body.style.overflow = ''; });

const toggleCheckbox = (method: string) => {
  const index = form.contactMethods.indexOf(method);
  if (index === -1) {
    form.contactMethods.push(method);
  } else {
    form.contactMethods.splice(index, 1);
  }
}
</script>

<template>
 
  <div class="contact-form">
    <button class="close-btn" @click="emit('close')">×</button>
    <h2>Залиште ваші контакти</h2>

    <div class="form-body">
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
          <option v-for="type in WORK_TYPES" :key="type" :value="type">{{ type }}</option>
        </select>
        <p v-if="errors.typeOfWork" class="error">{{ errors.typeOfWork }}</p>
      </div>

      <div class="form-group contact-methods">
        <label>Як з вами зв’язатися?</label>
        <div class="methods-grid">
          <div v-for="method in CONTACT_METHODS" :key="method" class="method-item" @click="toggleCheckbox(method)">
            <input type="checkbox" :id="method" :value="method" v-model="form.contactMethods" />
            <label :for="method" class="method-label">{{ method }}</label>
          </div>
        </div>
        <p v-if="errors.contactMethods" class="error">{{ errors.contactMethods }}</p>
      </div>
    </div>

    <div class="comment-footer">
      <textarea v-model="form.comment" placeholder="Ваш коментар..."></textarea>
      <button type="button" class="submit-btn" @click="submitLead">Відправити</button>
    </div>

    <div v-if="successMessage" class="success-message">{{ successMessage }}</div>
  </div>
  
</template>

<style scoped>

.contact-form {
  position: relative;
  display: flex;
  flex-direction: column;
  background: linear-gradient(135deg, #2e2e2e, #1a1a1a);
  border-radius: 20px;
  width: 100%;
  max-width: 500px;
  color: #fff;
  animation: slideUp 0.4s ease-out;
  overflow: hidden;
}

.close-btn {
  position: absolute;
  top: 12px;
  right: 15px;
  font-size: 1.8rem;
  background: transparent;
  border: none;
  color: #fff;
  cursor: pointer;
}

.contact-form h2 {
  text-align: center;
  margin: 20px 0;
  font-size: 1.5rem;
  font-weight: bold;
}

.form-body {
  flex: 1;
  padding: 20px;
  max-height: 60vh;
  overflow-y: auto; /* scroll замість auto */
  -webkit-overflow-scrolling: touch; /* плавний скрол на мобільних */
  
  scrollbar-width: none; /* Firefox */
  -ms-overflow-style: none; /* IE 10+ */
}
.form-body::-webkit-scrollbar {
  width: 0px;
  background: transparent; /* Chrome/Safari/Edge */
}

.form-group {
  display: flex;
  flex-direction: column;
  margin-bottom: 15px;
}

.form-group input,
.form-group select,
.comment-footer textarea {
  padding: 12px;
  border: none;
  border-radius: 10px;
  font-size: 1rem;
  margin-top: 5px;
  background: #2d2d2d;
  color: #fff;
}

.comment-footer textarea {
  resize: none;
  min-height: 45px;
  max-height: 80px;
}

.contact-methods .methods-grid {
  display: flex;
  flex-direction: column;
  gap: 10px;
  margin-top: 5px;
}

.method-item {
  display: flex;
  align-items: center;
  gap: 10px;
  padding: 8px 12px;
  border-radius: 10px;
  background: #3a3a3a;
}

.method-label {
  font-size: 0.95rem;
  cursor: pointer;
  /* для кліку по тексту */
}

.comment-footer {
  display: flex;
  flex-direction: column;
  gap: 10px;
  padding: 12px 20px;
  background: #222;
  border-top: 1px solid #333;
  position: sticky;
  bottom: 0;
}

.submit-btn {
  background: #ff6b6b;
  color: white;
  padding: 12px 18px;
  border-radius: 50px;
  border: none;
  font-size: 1rem;
  cursor: pointer;
  transition: 0.3s;
}

.success-message {
  margin: 10px;
  padding: 12px;
  background-color: #1e7e34;
  color: #fff;
  border-radius: 10px;
  text-align: center;
  font-weight: bold;
}

@media (max-width: 600px) {
  .contact-form {
    width: 95%;
    border-radius: 15px;
  }

  .form-body {
    max-height: 50vh;
  }

  .submit-btn {
    padding: 10px;
    font-size: 0.9rem;
  }

  .comment-footer {
    flex-direction: column;
  }
}

@keyframes slideUp {
  from {
    transform: translateY(100px);
    opacity: 0;
    
  }

  to {
    transform: translateY(0);
    opacity: 1;
  }
}
</style>
