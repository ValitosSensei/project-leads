import axios from "axios";
import { defineStore } from "pinia";

export interface Lead {
    id: number;
    name: string;
    phone: string;
    typeOfWork?: string;
    comment?: string;
    createdAt: string;
}

const token = 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJWYWxpdG9zIiwicm9sZXMiOlsiUk9MRV9BRE1JTiJdLCJpYXQiOjE3NTUxNzYzMzcsImV4cCI6MTc1NTE3OTkzN30.zQc0Am7Vt80WpYngRo76hYH1Xs5C8YXSfneKJl8mwMSq5_eWWt_ESgWHZg2HK8IRWY7WTJycF7WjZfdsISHQVA'

export const useLeadStore = defineStore('leadStore', {
    state: () => ({
        leads: [] as Lead[],
    }),
    actions: {
        async fetchLeads() {
            try {
                const response = await axios.get('http://localhost:8080/api/leads', {
                    headers: { Authorization: `Bearer ${token}` }
                });
                this.leads = response.data.content;
            } catch (error) {
                console.error('Помилка при отримані лідів', error);
            }
        },
        async deleteLead(id: number) {
            try {
                await axios.delete(`http://localhost:8080/api/leads/${id}`, {
                    headers: { Authorization: `Bearer ${token}` }
                });
                this.leads = this.leads.filter(lead => lead.id !== id);
            } catch (error) {
                console.error('Помилка при видаленні ліда:', error);
            }
        },
        async searchLeads(params: { name?: string; typeOfWork?: string }) {
            try {
                let url = 'http://localhost:8080/api/leads/search?';
                if (params.name) url += `name=${params.name}&`;
                if (params.typeOfWork) url += `typeOfWork=${params.typeOfWork}&`;

                const response = await axios.get(url, {
                    headers: { Authorization: `Bearer ${token}` }
                });
                this.leads = response.data.content;
            } catch (error) {
                console.error('Помилка при пошуку лідів:', error);
            }
        }
    }
});
