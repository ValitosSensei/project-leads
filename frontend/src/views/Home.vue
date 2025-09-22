<script setup lang="ts">
import { ref, watch } from 'vue';
import ContactForm from '../components/ContactForm.vue';
import HeaderNav from '../components/HeaderNav.vue';
import HeroSection from '../components/HeroSection.vue';
import GiftSection from '../components/GiftSection.vue';
import HowItWorksSection from '../components/HowItWorksSection.vue';

const isModalOpen = ref(false);

const openOrderModal = () => { isModalOpen.value = true; };
const closeModal = () => { isModalOpen.value = false; };

// Блокування скролу body при відкритій модалці
watch(isModalOpen, (val) => {
  document.body.style.overflow = val ? 'hidden' : '';
});
</script>

<template>
  <HeaderNav />
  <HeroSection @order="openOrderModal" />
  <GiftSection/>
  <HowItWorksSection/>

  <div v-if="isModalOpen" class="modal-overlay" @click.self="closeModal">
    <div class="modal-content">
      <ContactForm @close="closeModal" />
    </div>
  </div>
</template>

<style scoped>

.modal-overlay {
  position: fixed;
  inset: 0;
  background: rgba(65,65,65,0.6);
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 15px; /* рівні відступи з усіх сторін */
  overflow-y: auto; /* вертикальний скрол, якщо форма більша за екран */
  z-index: 1000;
  padding-right:0px ;
}

.modal-content {
  width: 100%;
  max-width: 500px;
  max-height: 90vh;
  overflow-y: auto;
  border-radius: 15px;
  padding: 0px;
  box-sizing: border-box;
  /* background: #2e2e2e; */
  color: #fff;
  animation: scaleIn 0.3s ease;
  -webkit-overflow-scrolling: touch; /* плавний скрол на мобільних */
}

/* кастомний скролбар для ПК */
.modal-content::-webkit-scrollbar {
  width: 6px;
}

.modal-content::-webkit-scrollbar-thumb {
  background: rgba(255,255,255,0.3);
  border-radius: 3px;
}

@keyframes scaleIn {
  from { opacity: 0; transform: scale(0.8); }
  to   { opacity: 1; transform: scale(1); }
}

@media (max-width: 600px) {
  .modal-content {
    max-width: 95%;
    max-height: 80vh;
   
  }
}
</style>
