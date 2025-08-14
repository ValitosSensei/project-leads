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

const message  =ref('');

const submitLead = async () =>{
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
    </div>
    <div>
      <label for="phone">Телефон</label>
      <input v-model="lead.phone" id="phone" required></input>
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