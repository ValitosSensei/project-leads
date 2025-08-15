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
  leadStore.fetchLeads(0); // починаємо з першої сторінки
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

const deleteLead = (id: number) => {
  if (confirm('Видалити цей лід?')) {
    leadStore.deleteLead(id)
  }
}




const updateLeadStatus = (lead: Lead) => {
  leadStore.updateLead(lead.id, lead.status, lead.adminComment || '');
};

const formData = (isoString: string) =>{
  return new Date(isoString).toLocaleDateString('uk-UA',{
       year: 'numeric',
    month: '2-digit',
    day: '2-digit',
    hour: '2-digit',
    minute: '2-digit',
    second: '2-digit',
  })
}
</script>

<template>
  <div>
    <h2>Адмінпанель лідів</h2>

    <div>
      <input v-model="searchName" placeholder="Пошук по імені" />
      <select v-model="searchType">
        <option value="">Всі типи роботи</option>
        <option v-for="type in WORK_TYPES" :key="type" :value="type">{{ type }}</option>
      </select>
      <select v-model="searchStatus">
        <option value="">Всі статуси</option>
        <option value="NEW">NEW</option>
        <option value="CALLED">CALLED</option>
        <option value="NO_ANSWER"> NO_ANSWER</option>
        <option value="INVALID_DATA">INVALID_DATA</option>
      </select>
      <button @click="applyFilters">Застосувати</button>
      <button @click="resetAllFilters">Скинути</button>
      <div>
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
    </div>

    <table border="1">
      <thead>
        <tr>
          <th>Ім’я</th>
          <th>Телефон</th>
          <th>Тип роботи</th>
          <th>Коментар</th>
          <th>Дата створення</th>
          <th>Дія</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="lead in leadStore.leads" :key="lead.id">
          <td>{{ lead.name }}</td>
          <td>{{ lead.phone }}</td>
          <td>{{ lead.typeOfWork }}</td>
          <td>{{ lead.comment }}</td>
          <td>{{ formData(lead.createdAt) }}</td>
          <td>
            <select v-model="lead.status" @change="updateLeadStatus(lead)">
              <option value="NEW">NEW</option>
              <option value="CALLED">CALLED</option>
              <option value="NO_ANSWER"> NO_ANSWER</option>
              <option value="INVALID_DATA">INVALID_DATA</option>
            </select>
            <textarea v-model="lead.adminComment" @blur="updateLeadStatus(lead)"></textarea>
            <button @click="deleteLead(lead.id)">Видалити</button>
          </td>
        </tr>
      </tbody>
    </table>

    <div>
      <button @click="prevPage" :disabled="leadStore.currentPage === 0">Попередня</button>
      <span>Сторінка {{ leadStore.currentPage + 1 }} з {{ leadStore.totalPages }}</span>
      <button @click="nextPage" :disabled="leadStore.currentPage + 1 === leadStore.totalPages">Наступна</button>
    </div>
  </div>
</template>
