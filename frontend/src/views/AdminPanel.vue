<script setup lang="ts">
import { onMounted, ref } from 'vue';
import { useLeadStore, type Lead } from '../stores/leadStore';
import { WORK_TYPES } from '../constants/workTypes';

const leadStore = useLeadStore();
const searchName = ref('');
const searchType = ref('');
const searchStatus = ref('');
const sortBy = ref('createdAt');
const sortDirection = ref('DESC');

const applyFilters = () => {
  leadStore.setFilter('name', searchName.value);
  leadStore.setFilter('typeOfWork', searchType.value);
  leadStore.setFilter('callStatus', searchStatus.value);
  leadStore.fetchLeads(0);
}

const resetAllFilters = () => {
  searchName.value = '';
  searchType.value = '';
  searchStatus.value = '';
  leadStore.resetFilters();
  leadStore.fetchLeads(0);
}

const updateSorting = () => {
  leadStore.setFilter('sortBy', sortBy.value);
  leadStore.setFilter('sortDirection', sortDirection.value);
  leadStore.fetchLeads(0);
}

onMounted(() => {
  leadStore.fetchLeads();
})

const nextPage = () => {
  if (leadStore.currentPage + 1 < leadStore.totalPages) {
    leadStore.fetchLeads(leadStore.currentPage + 1);
  }
}

const prevPage = () => {
  if (leadStore.currentPage > 0) {
    leadStore.fetchLeads(leadStore.currentPage - 1);
  }
}

const updateLeadStatus = (lead: Lead) => {
  leadStore.updateLead(lead.id, lead.status, lead.adminComment || '');
};

const formData = (isoString: string) => {
  return new Date(isoString).toLocaleDateString('uk-UA', {
    year: 'numeric',
    month: '2-digit',
    day: '2-digit',
    hour: '2-digit',
    minute: '2-digit',
  })
}

const CONTACT_ICONS: Record<string, string> = {
  PHONE: '📞',
  TELEGRAM: '✈️',
  VIBER: '📱'
};

const selectedLead = ref<Lead | null>(null);
const showPopup = ref(false);

const openLeadDetails = (lead: Lead) => {
  selectedLead.value = { ...lead };
  showPopup.value = true;
};
const deleteLead = (id: number) => { leadStore.deleteLead(id); closePopup(); };

const closePopup = () => {
  showPopup.value = false;
  selectedLead.value = null;
};

const saveLeadDetails = () => {
  if (selectedLead.value) {
    leadStore.updateLead(
      selectedLead.value.id,
      selectedLead.value.status,
      selectedLead.value.adminComment || ''
    );
  }
  closePopup();
};
</script>

<template>
  <div class="admin-container">
    <h2>Адмінпанель лідів</h2>

    <div class="filters">
      <input v-model="searchName" placeholder="Пошук по імені" />
      <select v-model="searchType">
        <option value="">Всі типи роботи</option>
        <option v-for="type in WORK_TYPES" :key="type" :value="type">{{ type }}</option>
      </select>
      <select v-model="searchStatus">
        <option value="">Всі статуси</option>
        <option value="NEW">NEW</option>
        <option value="CALLED">CALLED</option>
        <option value="NO_ANSWER">NO_ANSWER</option>
        <option value="INVALID_DATA">INVALID_DATA</option>
      </select>
      <button @click="applyFilters">Застосувати</button>
      <button @click="resetAllFilters">Скинути</button>
    </div>

    <div class="sorting">
      <label>Сортування:</label>
      <select v-model="sortBy" @change="updateSorting">
        <option value="createdAt">Дата створення</option>
        <option value="name">Ім’я</option>
      </select>
      <select v-model="sortDirection" @change="updateSorting">
        <option value="ASC">Зростання</option>
        <option value="DESC">Спадання</option>
      </select>
    </div>

    <table class="leads-table">
      <thead>
        <tr>
          <th>Ім’я</th>
          <th>Телефон</th>
          <th>Тип роботи</th>
          <th>Статус</th>
          <th>Дата створення</th>
          <th>Деталі</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="lead in leadStore.leads" :key="lead.id">
          <td>{{ lead.name }}</td>
          <td>{{ lead.phone }}</td>
          <td>{{ lead.typeOfWork }}</td>
          <td>
            <select v-model="lead.status" @change="updateLeadStatus(lead)">
              <option value="NEW">NEW</option>
              <option value="CALLED">CALLED</option>
              <option value="NO_ANSWER">NO_ANSWER</option>
              <option value="INVALID_DATA">INVALID_DATA</option>
            </select>
          </td>
         
          <td>{{ formData(lead.createdAt) }}</td>
          <td><button class="details-btn" @click="openLeadDetails(lead)">Деталі</button></td>
        </tr>
      </tbody>
    </table>

    <div class="pagination">
      <button @click="prevPage" :disabled="leadStore.currentPage === 0">Попередня</button>
      <span>Сторінка {{ leadStore.currentPage + 1 }} з {{ leadStore.totalPages }}</span>
      <button @click="nextPage" :disabled="leadStore.currentPage + 1 === leadStore.totalPages">Наступна</button>
    </div>
  </div>

  <!-- Попап -->
  <div v-if="showPopup" class="modal-overlay">
    <div class="modal-content">
      <h3>Деталі ліда</h3>
      <p><b>Ім’я:</b> {{ selectedLead?.name }}</p>
      <p><b>Телефон:</b> {{ selectedLead?.phone }}</p>
      <p><b>Тип роботи:</b> {{ selectedLead?.typeOfWork }}</p>
      <p><b>Коментар користувача:</b> {{ selectedLead?.comment }}</p>
      <p><b>Дата створення:</b> {{ selectedLead ? formData(selectedLead.createdAt) : '' }}</p>

      <div>
        <label>Статус:</label>
        <select v-model="selectedLead!.status">
          <option value="NEW">NEW</option>
          <option value="CALLED">CALLED</option>
          <option value="NO_ANSWER">NO_ANSWER</option>
          <option value="INVALID_DATA">INVALID_DATA</option>
        </select>
      </div>

      <div>
        <label>Коментар адміна:</label>
        <textarea v-model="selectedLead!.adminComment"></textarea>
      </div>

      <div>
        <label>Методи контакту:</label>
        <label><input type="checkbox" value="PHONE" v-model="selectedLead!.contactMethods" /> Дзвінок</label>
        <label><input type="checkbox" value="TELEGRAM" v-model="selectedLead!.contactMethods" /> Telegram</label>
        <label><input type="checkbox" value="VIBER" v-model="selectedLead!.contactMethods" /> Viber</label>
      </div>

      <div class="modal-actions">
        <button @click="saveLeadDetails">Зберегти</button>
        <button @click="() => selectedLead && deleteLead(selectedLead.id)">Видалити</button>
        <button @click="closePopup">Закрити</button>
      </div>
    </div>
  </div>
</template>

<style scoped>
.admin-container {
  padding: 15px;
  font-family: system-ui, sans-serif;
  color: #fff;
  background-color: #333;
}

.filters, .sorting {
  display: flex;
  gap: 10px;
  margin-bottom: 15px;
  flex-wrap: wrap;
}

.filters input, .filters select, .sorting select {
  padding: 8px;
  border-radius: 5px;
  border: none;
  
}

.leads-table {
  width: 100%;
  border-collapse: collapse;
  margin-bottom: 15px;
  font-size: 14px;
}

.leads-table th, .leads-table td {
  border: 1px solid #555;
  padding: 6px 8px;
  text-align: left;
}

.leads-table th {
  background-color: #444;
}

.leads-table tr:nth-child(even) {
  background-color: #3b3b3b;
}

.leads-table tr:hover {
  background-color: #555;
}

textarea {
  width: 100%;
  resize: vertical;
  border-radius: 5px;
  margin-top: 5px;
}

.details-btn {
  margin-top: 5px;
  background-color: #FF6B6B;
  border: none;
  color: white;
  padding: 6px 12px;
  border-radius: 5px;
  cursor: pointer;
}

.details-btn:hover {
  background-color: #ff4b4b;
}

.pagination {
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 8px;
  margin-top: 10px;
  flex-wrap: wrap;
}

.modal-overlay {
  position: fixed;
  top:0; left:0; right:0; bottom:0;
  background-color: rgba(0,0,0,0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 10px;
}

.modal-content {
  background: #444;
  padding: 15px;
  border-radius: 8px;
  width: 100%;
  max-width: 480px;
}

.modal-actions {
  display: flex;
  justify-content: flex-end;
  gap: 10px;
  margin-top: 15px;
  flex-wrap: wrap;
}

.modal-content button {
  cursor: pointer;
  padding: 6px 12px;
  border-radius: 5px;
  border: none;
  flex: 1 1 auto;
}

/* Адаптація під мобільні */
@media (max-width: 768px) {
  .leads-table th:nth-child(3),
  .leads-table th:nth-child(4),
  .leads-table th:nth-child(5),
  .leads-table td:nth-child(3),
  .leads-table td:nth-child(4),
  .leads-table td:nth-child(5) {
    display: none;
  }

  .filters, .sorting {
    flex-direction: column;
  }

  .details-btn {
    width: 100%;
  }

  .modal-content {
    padding: 10px;
  }

  .modal-actions {
    flex-direction: column;
  }
}

.pagination {
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 8px;
  margin-top: 10px;
  flex-wrap: wrap;
}

@media (max-width: 768px) {
  .pagination {
    flex-direction: column;
    gap: 5px;
  }

  .pagination button {
    width: 100%;
    padding: 8px 0;
    font-size: 14px;
  }

  .pagination span {
    text-align: center;
    width: 100%;
  }
}
</style>
