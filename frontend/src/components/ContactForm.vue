<script setup lang="ts">
import { ref, watch } from 'vue';
import { userFormStore } from '../stores/formStore';

const formStore = userFormStore();

const name = ref(formStore.name);
const email = ref(formStore.email);
const message = ref(formStore.message);

watch(name, (newVal) => formStore.setName(newVal));
watch(email, (newVal) => formStore.setEmail(newVal));
watch(message, (newVal) => formStore.setMessage(newVal));

const submitForm = () =>{
    alert(`Дякуємо, ${formStore.name}! Ваше повідомлення отримано.`)
    formStore.resetForm();

    name.value ='';
    email.value = '';
    message.value ='';
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