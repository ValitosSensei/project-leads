<script setup lang="ts">

import AdminPanel from '../components/AdminPanel.vue';
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
  <div>
    <h2>Адмінка лідів</h2>
    <button @click="logout">Вийти</button>
    <AdminPanel />
  </div>
</template>
