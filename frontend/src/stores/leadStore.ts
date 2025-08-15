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


export const useLeadStore = defineStore('leadStore', {
    state: () => ({
        leads: [] as Lead[],
        currentPage:0,
        totalPages:0
    }),
    actions: {
        async fetchLeads(page = 0, size = 10) {
            try {
                     const response = await axios.get(`http://localhost:8080/api/leads?page=${page}&size=${size}`);
                this.leads = response.data.content;
                this.totalPages = response.data.totalPages;
                this.currentPage = response.data.number
            } catch (error) {
                console.error('Помилка при отримані лідів', error);
            }
        },
        async deleteLead(id: number) {
            try {
                await axios.delete(`http://localhost:8080/api/leads/${id}`);
                this.leads = this.leads.filter(lead => lead.id !== id);
            } catch (error) {
                console.error('Помилка при видаленні ліда:', error);
            }
        },
        async searchLeads(params: { name?: string; typeOfWork?: string }) {
            try {
                let query = new URLSearchParams();
                if (params.name) query.append('name', params.name);
                if (params.typeOfWork) query.append('typeOfWork', params.typeOfWork);

                const response = await axios.get(`http://localhost:8080/api/leads/search?${query.toString()}`);
                this.leads = response.data.content;

                this.currentPage = 0;
                this.totalPages = 1;
            } catch (error) {
                console.error('Помилка при пошуку лідів:', error);
            }
        }
    }
});
