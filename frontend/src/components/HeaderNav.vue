<script setup lang="ts">
import { ref } from "vue";

const isMenuOpen = ref(false);
</script>

<template>
  <header class="header">
    <div class="header__container">
      <div class="header__logo">
        <span>
          <img src="../assets/vue.svg" alt="logo" class="logo" />
        </span>
      </div>

      <!-- Бургер -->
      <button class="burger" @click="isMenuOpen = !isMenuOpen">
        <span></span>
        <span></span>
        <span></span>
      </button>

      <!-- Навігація -->
      <nav :class="['header__nav-menu', { open: isMenuOpen }]">
        <ul class="header__nav-list">
          <li class="header__nav-item"><a href="#" class="header__nav-link">Про нас</a></li>
          <li class="header__nav-item"><a href="#" class="header__nav-link">Подарунки</a></li>
          <li class="header__nav-item"><a href="#" class="header__nav-link">Як це працює</a></li>
        </ul>
      </nav>
    </div>
  </header>
</template>

<style scoped>
.header__container {
  display: flex;
  justify-content: space-between;
  align-items: center;
  height: 80px;
  padding: 10px 145px; /* Відступи зліва і справа на десктопі */
  background-color: #b9fbdb;
  border-end-start-radius: 30px;
  border-end-end-radius: 30px;
  box-shadow: 1px 4px 6px rgba(0, 0, 0, 0.1);
  position: relative;
  z-index: 1000; /* завжди поверх херой */
}

.logo {
  width: 50px;
  height: auto;
}

/* --- Навігація --- */
.header__nav-list {
  display: flex;
  gap: 40px;
}

.header__nav-item {
  list-style: none;
  font-weight: 500;
  font-size: 18px;
}

.header__nav-link {
  text-decoration: none;
  color: #000;
  cursor: pointer;
}


/* --- Бургер --- */
.burger {
  display: none;
  flex-direction: column;
  justify-content: space-between;
  width: 35px;
  height: 25px;
  background: none;
  border: none;
  cursor: pointer;
  position: absolute;
  top: 50%;
  right: 20px;
  transform: translateY(-50%);
  z-index: 1001;
}

.burger span {
  display: block;
  height: 4px;        /* трохи товстіше для iPhone */
  width: 100%;        /* займати всю ширину кнопки */
  background-color: #000;
  border-radius: 2px;
}

/* --- Мобільна адаптація --- */
@media (max-width: 1024px) {
  .header__container {
    padding: 10px 40px; /* трохи менші відступи на планшеті */
  }
}

@media (max-width: 768px) {
  .burger {
    display: flex;
    position: absolute; /* ставимо бургер поверх контейнера */
    top: 50%;           /* вертикальне центрування */
    right: 20px;        /* відступ від правого краю */
    transform: translateY(-50%);
    z-index: 1001;      /* завжди поверх */
  }

  .header__nav-menu {
    position: absolute;
    top: 100%;
    right: 0;
    width: 220px;
    background: #b9fbdb;
    border-radius: 0 0 20px 20px;
    box-shadow: 1px 4px 6px rgba(0, 0, 0, 0.1);
    max-height: 0;
    overflow: hidden;
    transition: max-height 0.4s ease;
    z-index: 1000;
  }

  .header__nav-menu.open {
    max-height: 500px;
  }

  .header__nav-list {
    flex-direction: column;
    padding: 20px;
    gap: 20px;
  }

  .header__nav-item {
    font-size: 16px;
  }
}
/* --- Дуже маленькі екрани --- */
@media (max-width: 480px) {
  .header__container {
    padding: 10px 20px;
  }

  .header__nav-menu {
    width: 180px;
  }

  .header__nav-item {
    font-size: 14px;
  }
}

</style>
