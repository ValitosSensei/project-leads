import axios from "axios";
import { defineStore } from "pinia";

export const useAuthStore = defineStore('authStore', {
    state: () => ({
        token: localStorage.getItem('token') || '',
        isAuthenticated: !!localStorage.getItem('token')
    }),
    actions: {
        async login(username: string, password: string) {
            try {
                const responce = await axios.post('http://localhost:8080/auth/login', { username, password });
                this.token = responce.data.token
                this.isAuthenticated = true
                localStorage.setItem('token', this.token)
                // Додаємо заголовок Authorization для всіх майбутніх запитів
                axios.defaults.headers.common['Authorization'] = `Bearer ${this.token}`;
            }catch(error){
                throw new Error('Невірний логін або пароль')
            }
        },
        logout(){
            this.token = '';
            this.isAuthenticated= false;
            localStorage.removeItem('token');
            delete axios.defaults.headers.common['Authorization'];
        },
        
    }
})