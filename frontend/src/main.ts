import { createApp } from 'vue'
import './style.css'
import App from './App.vue'
import { createPinia } from 'pinia';
import router from './routers';
import axios from 'axios';
import { useAuthStore } from './stores/authStore';

// const token = localStorage.getItem('token');
// if (token) {
//   axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
// }

const app = createApp(App)
const pinia = createPinia()
app.use(pinia)
app.use(router)

// Доступ до authStore
const authStore = useAuthStore()

// Встановлюємо токен для всіх запитів при старті, якщо він є
if (authStore.token) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${authStore.token}`
}

// Інтерсептор для обробки 401/403
axios.interceptors.response.use(
  response => response,
  error => {
    if (error.response?.status === 401 || error.response?.status === 403) {
      authStore.logout()          // очищаємо токен
      router.push('/login')       // редірект на логін
    }
    return Promise.reject(error)
  }
)

app.mount('#app')
