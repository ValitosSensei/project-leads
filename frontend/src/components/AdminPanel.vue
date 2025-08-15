<script setup lang="ts">
import { onMounted, ref } from 'vue';
import { useLeadStore, type Lead } from '../stores/leadStore';

const leadStore = useLeadStore();
const searchName = ref('');
const searchType = ref('');

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

// Пошук через бекенд
const searchLeads = () => {
  leadStore.searchLeads({
    name: searchName.value,
    typeOfWork: searchType.value
  });
}

const resetSearch = () => {
  searchName.value = '';
  searchType.value = '';
  leadStore.fetchLeads();
}

const updateLeadStatus = (lead: Lead) => {
  leadStore.updateLead(lead.id, lead.status, lead.adminComment || '');
};
</script>

<template>
  <div>
    <h2>Адмінпанель лідів</h2>

    <input v-model="searchName" placeholder="Пошук по імені" />
    <input v-model="searchType" placeholder="Пошук по типу роботи" />
    <button @click="searchLeads">Пошук</button>
    <button @click="resetSearch">Скинути</button>

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
          <td>{{ lead.createdAt }}</td>
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
      <button @click="nextPage" :disabled="leadStore.currentPage +1 === leadStore.totalPages">Наступна</button>
    </div>
  </div>
</template>
