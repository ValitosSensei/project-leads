<script setup lang="ts">
import { ref } from 'vue';
import ContactForm from '../components/ContactForm.vue';

const bannerImage = '/mnt/data/b8048b4d-7477-4c11-8b91-0c9532db712c.png';
const menuImages = [
    { src: '/mnt/data/467587f3-f039-46a7-9f3b-7f8fce340c06.png', title: 'Курсова', description: 'Піца на вибір', gift: true },
    { src: '/mnt/data/467587f3-f039-46a7-9f3b-7f8fce340c06.png', title: 'Диплом', description: 'Сет суші', gift: true },
    { src: '/mnt/data/467587f3-f039-46a7-9f3b-7f8fce340c06.png', title: 'Реферат', description: 'Бургер + кола', gift: true },
    { src: '/mnt/data/467587f3-f039-46a7-9f3b-7f8fce340c06.png', title: 'Контрольна', description: 'Кава + пончик', gift: true },
];

const isModalOpen = ref(false);

const navigateTo = (section: string) => {
    if (section === 'Контакти') {
        isModalOpen.value = true;
    } else {
        console.log(`Перехід до: ${section}`);
    }
};

const openOrderModal = () => {
    isModalOpen.value = true;
};

const closeModal = () => {
    isModalOpen.value = false;
};
</script>

<template>
    <div class="page-container">
        <header class="header">
            <nav class="navigation">
                <button v-for="item in ['Про нас', 'Як це працює', 'Подарунки', 'Контакти']" :key="item"
                    @click="navigateTo(item)" class="nav-btn">
                    {{ item }}
                </button>
            </nav>
        </header>

        <!-- Банер -->
        <section class="banner" :style="{ backgroundImage: `url(${bannerImage})` }">
            <div class="banner-content">
                <h1 class="banner-title">
                    Твоя дипломна + піца = ❤️
                </h1>
                <p class="banner-subtitle">Замов студентську роботу з вкусним бонусом!</p>
                <button @click="openOrderModal" class="order-btn">ЗАМОВИТИ</button>
            </div>
        </section>

        <!-- Меню -->
        <section class="menu-section">
            <div class="menu-header">
                <h2>Навчання ще ніколи не було таким смачним!</h2>
                <p>Обери, що до душі: піца, суші, бургер або навіть кава з донатами!</p>
            </div>

            <div class="menu-grid">
                <div v-for="(item, index) in menuImages" :key="index" class="menu-item">
                    <img :src="item.src" alt="Menu Image" class="menu-img" />
                    <h3>{{ item.title }}</h3>
                    <p>{{ item.description }}</p>
                    <div v-if="item.gift" class="gift-label">В ПОДАРУНОК</div>
                </div>
            </div>
        </section>

        <!-- Модальне вікно -->
        <div v-if="isModalOpen" class="modal-overlay" @click.self="closeModal">
            <div class="modal-content">
                
                <ContactForm v-if="isModalOpen" @close="isModalOpen = false" />
            </div>
        </div>
    </div>
</template>

<style scoped>
.page-container {
    max-width: 1200px;
    margin: 0 auto;
    padding: 20px;
}

/* Банер */
.banner {
    background-size: cover;
    background-position: center;
    border-radius: 20px;
    padding: 60px 20px;
    margin: 40px 0;
    color: white;
    text-align: center;
}

.banner-title {
    font-size: 3rem;
    font-weight: 700;
}

.banner-subtitle {
    font-size: 1.5rem;
    margin: 20px 0;
}

.order-btn {
    background-color: #FF6B6B;
    color: white;
    padding: 15px 40px;
    font-size: 1.5rem;
    border-radius: 50px;
    cursor: pointer;
    font-weight: bold;
}

.menu-section {
    margin-top: 60px;
}

.menu-header h2 {
    font-size: 2rem;
    margin-bottom: 10px;
}

.menu-header p {
    font-size: 1.2rem;
    color: #555;
}

.menu-grid {
    display: grid;
    grid-template-columns: repeat(4, 1fr);
    gap: 20px;
    margin-top: 20px;
}

.menu-item {
    border-radius: 15px;
    overflow: hidden;
    text-align: center;
    padding: 10px;
    position: relative;
    background: #353535;
}

.menu-img {
    width: 100%;
    border-radius: 10px;
    margin-bottom: 10px;
}

.gift-label {
    position: absolute;
    top: 10px;
    right: 10px;
    background: #FF6B6B;
    color: white;
    padding: 5px 10px;
    border-radius: 10px;
    font-weight: bold;
    font-size: 0.9rem;
}
.modal-overlay {
  position: fixed;
  top: 0; left: 0; right: 0; bottom: 0;
  background-color: rgba(0,0,0,0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
}




/* Мобільна адаптація */
@media (max-width: 900px) {
    .menu-grid {
        grid-template-columns: repeat(2, 1fr);
    }
}

@media (max-width: 600px) {
    .menu-grid {
        grid-template-columns: 1fr;
    }
}
</style>
