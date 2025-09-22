<script setup lang="ts"> 
const emit = defineEmits(['order']);
</script>

<template>
   <section class="hero">
  <!-- Заголовок та підзаголовок -->
  <div class="hero__text">
    <h1 class="hero__title">Твоя дипломна + піца = ❤️</h1>
    <p class="hero__subtitle">Замов студентську роботу з вкусним бонусом!</p>
  </div>

  <!-- Фонова картинка -->
  <img class="hero__bg" src="../assets/Group 23.svg" alt="Фонова картинка">

  <!-- Кнопка -->
  <div class="hero__button-wrapper">
    <button class="hero__button" @click="emit('order')">ЗАМОВИТИ</button>
  </div>
</section>
</template>

<style scoped>
.hero {
  margin-top: 70px;
  position: relative;
  width: 100%;
  overflow: hidden;
  text-align: left;
  color: black;
}

/* Текст */
.hero__text {
  position: absolute;
  top: 1%;
  left: 145px;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
}

.hero__title {
  font-size: 48px;
  font-weight: bold;
  margin-bottom: 20px;
}

.hero__subtitle {
  font-size: 24px;
  margin-bottom: 40px;
}

/* Фото */
.hero__bg {
  width: 1230px;
  max-width: 100%;
  height: auto;
  display: block;
  margin: 0 auto;
}

/* Кнопка */
.hero__button-wrapper {
  position: absolute;
  top: 30%;
  left: 145px;
}

.hero__button {
  background-color: #FF6B6B;
  color: white;
  border: none;
  padding: 15px 30px; /* стандартний розмір на десктопі */
  font-size: 18px;
  border-radius: 8px;
  cursor: pointer;
}

/* ===== Адаптація ===== */
@media (max-width: 1024px) {
  .hero {
    display: flex;
    flex-direction: column;
    align-items: center;
  }

  .hero__text,
  .hero__button-wrapper {
    position: relative;
    top: 0;
    left: 0;
    text-align: center;
    margin: 10px auto;
    align-items: center;
  }

  .hero__title { font-size: 36px; }
  .hero__subtitle { font-size: 22px; margin-bottom: 15px; }
  .hero__bg { width: 90%; margin-bottom: 20px; }
  .hero__button { padding: 18px 40px; font-size: 18px; } /* трохи більша кнопка */
}

@media (max-width: 768px) {
  .hero__title { font-size: 34px; }
  .hero__subtitle { font-size: 20px; margin-bottom: 12px; }
  .hero__bg { width: 95%; }
  .hero__button { padding: 20px 45px; font-size: 16px; } /* ще більша кнопка */
}

@media (max-width: 480px) {
  .hero__title { font-size: 32px; }
  .hero__subtitle { font-size: 18px; margin-bottom: 10px; }
  .hero__button { padding: 18px 40px; font-size: 14px; } /* кнопка велика і зручна для пальців */
}
</style>
