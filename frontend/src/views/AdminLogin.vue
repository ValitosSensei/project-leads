<script setup lang="ts">
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { useAuthStore } from '../stores/authStore';


const username = ref('')
const password = ref('')
const errorMessage = ref('')
const router = useRouter();
const authStore = useAuthStore();

const submitLogin = async () =>{
    try{
        await authStore.login(username.value, password.value);
        router.push('/admin')
    }catch(error: any){
        errorMessage.value = error.message
    }
}
</script>
<template>
    <div>
        <h2>Логін для адміна</h2>
        <form @submit.prevent="submitLogin">
            <input v-model="username" placeholder="Username" required></input>
            <input type="password" v-model="password" placeholder="Password" required></input>
            <button type="submit">Увійти</button>
        </form>
        <div v-if="errorMessage" style="color: red;">{{ errorMessage }}</div>
    </div>
</template>
<style scoped></style>