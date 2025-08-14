

import { createRouter, createWebHistory } from 'vue-router';
import Home from '../views/Home.vue';
import AdminLogin from '../views/AdminLogin.vue';
import AdminDashboard from '../views/AdminDashboard.vue';
import { useAuthStore } from '../stores/authStore';

const routes = [
  { path: '/', component: Home },
  { path: '/admin-login', component: AdminLogin },
  { path: '/admin', component: AdminDashboard, meta: { requiresAuth: true } },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

// **Навігаційний guard**
router.beforeEach((to, from, next) => {
  const authStore = useAuthStore();
  if (to.meta.requiresAuth && !authStore.isAuthenticated) {
    next('/admin-login'); // редірект на логін, якщо не авторизований
  } else {
    next(); // дозволяємо доступ
  }
});

export default router;