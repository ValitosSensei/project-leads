<script setup lang="ts">
import { ref, watch } from 'vue';
import { userFormStore } from '../stores/formStore';
import axios from 'axios';

const formStore = userFormStore();

const name = ref(formStore.name);
const email = ref(formStore.email);
const message = ref(formStore.message);

watch(name, (newVal) => formStore.setName(newVal));
watch(email, (newVal) => formStore.setEmail(newVal));
watch(message, (newVal) => formStore.setMessage(newVal));

const submitForm = async() =>{
    try{
        const response = await axios.post('http://localhost:8080/api/contact',{
            name: formStore.name,
            email: formStore.email,
            message: formStore.message,
        });

        alert(`Дякуємо, ${formStore.name}! Ваше повідомлення отримано.`);
        formStore.resetForm();
        name.value ='';
        email.value ='';
        message.value ='';
    }catch (error){
        alert('Виникла помилка при відправці форми. Спробуйте пізніше');
        console.log(error);
    }
}
</script>
<template>
    <form @submit.prevent="submitForm">
        <input v-model="name" type="text" placeholder="Ваше імя" />
        <input v-model="email" type="email" placeholder="Ваш email" />
        <textarea v-model="message" placeholder="Ваше повідомлення"></textarea>
        <button type="submit">Відправити</button>
    </form>
</template>