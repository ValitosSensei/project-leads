import axios from "axios";
import { defineStore } from "pinia";

export interface Lead {
    id: number;
    name: string;
    phone: string;
    typeOfWork?: string;
    comment?: string;
    createdAt: string;
    status: string;
    adminComment?: string;
}

export const useLeadStore = defineStore('leadStore', {
    state: () => ({
        leads: [] as Lead[],
        currentPage: 0,
        totalPages: 0,
        filters: {
            name: '',
            typeOfWork: '',
            callStatus: '',
            sortBy: 'createdAt',
            sortDirection: 'DESC'
        }
    }),
    actions: {
        async fetchLeads(page = 0) {
            try {
                // Будемо відправляти тільки ті параметри, які реально заповнені
                const params: any = { page, size: 10 };
                
                if (this.filters.name?.trim()) params.name = this.filters.name.trim();
                if (this.filters.typeOfWork?.trim()) params.typeOfWork = this.filters.typeOfWork.trim();
                if (this.filters.callStatus?.trim()) params.callStatus = this.filters.callStatus.trim();
                
                params.sortBy = this.filters.sortBy;
                params.sortDirection = this.filters.sortDirection;

                const response = await axios.get('http://localhost:8080/api/leads/search', { params });

                this.leads = response.data.content;
                this.currentPage = response.data.number;
                this.totalPages = response.data.totalPages;
            } catch (error) {
                console.error('Помилка при отриманні лідів', error);
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
        setFilter(field: string, value: string) {
            (this.filters as any)[field] = value;
        },
        resetFilters() {
            this.filters = {
                name: '',
                typeOfWork: '',
                callStatus: '',
                sortBy: 'createdAt',
                sortDirection: 'DESC'
            };
        },
        async updateLead(id: number, status: string, adminComment: string) {
            try {
                const response = await axios.patch(
                    `http://localhost:8080/api/leads/${id}/status`,
                    null,
                    {
                        params: { status, adminComment }
                    }
                );

                const index = this.leads.findIndex(lead => lead.id === id);
                if (index !== -1) this.leads[index] = response.data;
            } catch (error) {
                console.error('Помилка при оновленні ліда', error);
            }
        }
    }
});
