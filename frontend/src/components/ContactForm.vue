<script setup lang="ts">
import axios from 'axios';
import { reactive, ref } from 'vue';

interface LeadDTO{
  name:string;
  phone:string;
  typeOfWork?:string;
  comment?:string;
}

const lead = reactive<LeadDTO>({
  name:'',
  phone:'',
  typeOfWork:'',
  comment:'',
})

const errors = reactive<{name?: string; phone?:string}>({})
const message  =ref('');

const validate = ()  =>{
  errors.name = lead.name.trim() ? '':'Ім’я обов’язкове';
  errors.phone = /^\d{5,30}$/.test(lead.phone) ? '': 'Телефон повинен містити від 5 до 30 цифр';
  return !errors.name && !errors.phone;
}

const submitLead = async () =>{
  if(!validate()) return;
  try{
    const response = await axios.post('http://localhost:8080/api/leads', lead)
    message.value = 'Заявка успішно надіслана!'
     lead.name = '';
    lead.phone = '';
    lead.typeOfWork = '';
    lead.comment = '';
  }catch(error: any){
    message.value = 'Сталась помилка: ' + error.response?.data?.message || error.message;
  }
}
</script>
<template>
  <form @submit.prevent="submitLead">
    <div>
      <label for="name">Ім'я</label>
      <input v-model="lead.name" id="name" required></input>
      <div v-if="errors.name" style="color:red">{{ errors.name }}</div>
    </div>
    <div>
      <label for="phone">Телефон</label>
      <input v-model="lead.phone" id="phone" required></input>
      <div v-if="errors.phone" style="color:red">{{ errors.phone }}</div>
    </div>
    <div>
      <label>Тип роботи:</label>
      <input v-model="lead.typeOfWork" id="TypeOfWork"></input>
    </div>
    <div>
      <label>Коментар</label>
      <textarea v-model="lead.comment" id="comment" required></textarea>
    </div>

    <button type="submit">Відправити</button>
  </form>
  <div v-if="message">
{{ message }}
  </div>
</template>
<style scoped></style>