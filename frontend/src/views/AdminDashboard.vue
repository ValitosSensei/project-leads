<script setup lang="ts">

import AdminPanel from './AdminPanel.vue';
import { useAuthStore } from '../stores/authStore';
import { useRouter } from 'vue-router';

const authStore = useAuthStore();
const router = useRouter();



const logout = () => {
  authStore.logout();
  router.push('/admin-login');
};
</script>
<template>
  <div class="admin-wrapper">
    <header class="admin-header">
      <h2>Адмінка лідів</h2>
      <button class="logout-btn" @click="logout">Вийти</button>
    </header>

    <AdminPanel />
  </div>
</template>
<style scoped>
body{
  background-color: #2c2c2c;
}

.admin-wrapper {
  background-color: #2c2c2c;
  min-height: 100vh;
  color: #fff;
  font-family: system-ui, sans-serif;
}

.admin-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 20px;
  background: #3b3b3b;
  border-bottom: 1px solid #555;
}

.admin-header h2 {
  margin: 0;
}

.logout-btn {
  background: #FF6B6B;
  border: none;
  padding: 8px 16px;
  border-radius: 6px;
  color: white;
  cursor: pointer;
  font-weight: bold;
}

.logout-btn:hover {
  background: #ff4b4b;
}
</style>
