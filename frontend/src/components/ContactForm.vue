<script setup lang="ts">
import axios from 'axios';
import { reactive, ref } from 'vue';
import { CONTACT_METHODS, WORK_TYPES } from '../constants/workTypes';



interface LeadDTO {
  name: string;
  phone: string;
  typeOfWork?: string;
  comment?: string;
  contactMethods: string[]; // ✅ масив
}

const form = reactive<LeadDTO>({
  name: '',
  phone: '',
  typeOfWork: '',
  comment: '',
  contactMethods: [] // масив, а не рядок
})

const errors = reactive({
  name: '',
  phone: '',
  typeOfWork: '',
  comment: '',
  contactMethods: '',
});

const validateForm = () => {
  let isValid = true
  errors.name = ''
  errors.phone = ''
  errors.typeOfWork = ''

  if (!form.name || form.name.length < 2) {
    errors.name = 'Ім’я повинно містити мінімум 2 символи'
    isValid = false
  }

  if (!form.phone || !/^\d{10,}$/.test(form.phone)) {
    errors.phone = 'Введіть коректний номер (мін. 10 цифр)'
    isValid = false
  }
  if (!form.typeOfWork) {
    errors.typeOfWork = 'Виберіть тип роботи'
    isValid = false
  }
  return isValid
}

const submitLead = async () => {

  if (!validateForm()) return;
  try {
    const response = await axios.post('http://localhost:8080/api/leads', form)
    alert('Форма відправлена')
    form.name = '';
    form.phone = '';
    form.typeOfWork = '';
    form.comment = '';
    form.contactMethods = [];
  } catch (error: any) {
    alert('Сталась помилка: ' + error.response?.data?.message || error.message);
  }
}
</script>
<template>
  <form @submit.prevent="submitLead">
    <div>
      <label for="name">Ім'я</label>
      <input v-model="form.name" id="name" required></input>
      <p v-if="errors.name" style="color: red;">{{ errors.name }}</p>
    </div>
    <div>
      <label for="phone">Телефон</label>
      <input v-model="form.phone" id="phone" required></input>
      <p v-if="errors.phone" style="color: red;">{{ errors.phone }}</p>
    </div>
    <div>
      <label>Тип роботи:</label>
      <select v-model="form.typeOfWork">
        <option disabled value="">Виберіть тип роботи</option>
        <option v-for="type in WORK_TYPES" :key="type" :value="type">
          {{ type }}
        </option>

      </select>
      <p v-if="errors.typeOfWork" style="color: red;">{{ errors.typeOfWork }}</p>
    </div>
    <div>
      <label>Коментар</label>
      <textarea v-model="form.comment" id="comment" required></textarea>
    </div>
    <div>
      <label>Як з вами зв’язатися?</label>
      <div v-for="method in CONTACT_METHODS" :key="method">
        <input type="checkbox" :value="method" v-model="form.contactMethods" /> {{ method }}
      </div>
    </div>
    <button type="submit">Відправити</button>
  </form>

</template>
<style scoped></style>