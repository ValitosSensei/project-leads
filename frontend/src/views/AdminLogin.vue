<script setup lang="ts">
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { useAuthStore } from '../stores/authStore';

const username = ref('');
const password = ref('');
const errorMessage = ref('');
const router = useRouter();
const authStore = useAuthStore();

const submitLogin = async () => {
    try {
        await authStore.login(username.value, password.value);
        router.push('/admin');
    } catch (error: any) {
        errorMessage.value = error.message;
    }
};
</script>

<template>
  <div class="login-wrapper">
    <h2 class="login-title">Логін для адміна</h2>
    <form @submit.prevent="submitLogin" class="login-form">
      <input v-model="username" placeholder="Username" required class="login-input"/>
      <input type="password" v-model="password" placeholder="Password" required class="login-input"/>
      <button type="submit" class="login-button">Увійти</button>
    </form>
    <div v-if="errorMessage" class="error-message">{{ errorMessage }}</div>
  </div>
</template>

<style scoped>
.login-wrapper {
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding: 20px;
  background: linear-gradient(to bottom right, #636363, #3a3b3b);
  color: #fff;
  text-align: center;
}

.login-title {
  margin-bottom: 20px;
  font-size: 2rem;
}

.login-form {
  display: flex;
  flex-direction: column;
  width: 100%;
  max-width: 350px;
  gap: 15px;
}

.login-input {
  padding: 12px 15px;
  border-radius: 8px;
  border: none;
  font-size: 1rem;
  outline: none;
}

.login-input:focus {
  box-shadow: 0 0 0 3px rgba(255,255,255,0.3);
}

.login-button {
  padding: 12px;
  border: none;
  border-radius: 8px;
  background-color: #ff6b6b;
  color: white;
  font-size: 1rem;
  cursor: pointer;
  transition: background-color 0.3s;
}

.login-button:hover {
  background-color: #ff4b4b;
}

.error-message {
  margin-top: 10px;
  color: #ffbaba;
  font-weight: bold;
}

/* Адаптивність під телефони */
@media (max-width: 480px) {
  .login-wrapper {
    padding: 10px;
  }

  .login-title {
    font-size: 1.5rem;
  }

  .login-form {
    gap: 10px;
  }

  .login-input, .login-button {
    font-size: 0.9rem;
    padding: 10px;
  }
}
</style>
