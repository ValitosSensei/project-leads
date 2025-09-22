<script setup lang="ts">

</script>

<template>
  <section class="gift-section">
    <h2 class="gift-section__title">Навчання ще ніколи не було таким смачним!</h2>
    <p class="gift-section__title-description">
      Обери, що до душі: піца, суші, бургер або навіть кава з донатом!
    </p>

    <div class="gift-section__gifts">
      <!-- Курсова -->
      <div class="gift-section__gift gift-yellow">
        <h3 class="gift-section__top-title">Курсова</h3>
        <img src="../assets/pizza.svg" alt="Піца" class="gift-section__image" />
        <div class="gift-section__gift-free">В<br/>пода-рунок</div>
        <div class="gift-section__gift-bootom bootom-yellow">
          <p class="gift-section__bottom-text">Піца на вибір</p>
        </div>
        
      </div>

      <!-- Диплом -->
      <div class="gift-section__gift gift-green">
        <h3 class="gift-section__top-title">Диплом</h3>
        <img src="../assets/sushi.svg" alt="Суші" class="gift-section__image" />
        <div class="gift-section__gift-free">В<br/>пода-рунок</div>
        <div class="gift-section__gift-bootom bootom-green">
          <p class="gift-section__bottom-text">Сет суши</p>
        </div>
      </div>

      <!-- Реферат -->
      <div class="gift-section__gift gift-pink">
        <h3 class="gift-section__top-title">Реферат</h3>
        <img src="../assets/burger.svg" alt="Бургер" class="gift-section__image" />
        <div class="gift-section__gift-free">В<br/>пода-рунок</div>
        <div class="gift-section__gift-bootom bootom-pink">
          <p class="gift-section__bottom-text">Бургер + кола</p>
        </div>
      </div>

      <!-- Контрольна -->
      <div class="gift-section__gift gift-teal">
        <h3 class="gift-section__top-title">Контрольна</h3>
        <img src="../assets/coffe-donut.svg" alt="Кава з донатом" class="gift-section__image" />
        <div class="gift-section__gift-free">В<br/>пода-рунок</div>
        <div class="gift-section__gift-bootom bootom-teal">
          <p class="gift-section__bottom-text">Кава + пончик</p>
        </div>
      </div>
    </div>
  </section>
</template>

<style scoped>
.gift-section {
  text-align: center;
  padding: 40px 20px;
}
.gift-section__gift-bootom{
  border-bottom-left-radius: 12px;
  border-bottom-right-radius: 12px;
  width: 100%;
  height: 100%;
}
.bootom-yellow{background-color: #FFE9AC;}
.bootom-green{background-color: #EEFFE8;}
.bootom-pink{background-color: #FFDDD5;}
.bootom-teal{background-color: #B6F6DC;}

.gift-section__title {
  font-size: 48px;
  font-weight: 700;
  margin-bottom: 10px;
}

.gift-section__title-description {
  font-size: 32px;
  margin-bottom: 30px;
  color: #333;
}

.gift-section__gifts {
  display: flex;
  gap: 20px;
  justify-content: center;
  flex-wrap: wrap;
}

.gift-section__gift {
  position: relative;
  width: 400px;
  height: 500px;
  border-radius: 12px;
  display: flex;
  flex-direction: column;
  align-items: center;
  /* padding: 15px; */
  box-sizing: border-box;
}

.gift-yellow { background-color: #FED37D; }
.gift-green  { background-color: #A2EACB; }
.gift-pink   { background-color: #FEB7B0; }
.gift-teal   { background-color: #8FE6E3; }

.gift-section__top-title {
  font-size: 24px;
  font-weight: 700;
  margin-bottom: 10px;
}

.gift-section__image {
  width: 300px;
  height: 250px;
  margin-bottom: 15px;
}

.gift-section__gift-free {
  background-color: #FF6B6B;
  color: white;
  font-weight: bold;
  padding: 20px;
  border-radius: 30px;
  font-size: 24px;
  line-height: 1.2;
  text-align: center;
  width: 70px;
  height: 70px;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-bottom: 15px;
  position: absolute;
  top: 270px;
  right: 15px;
}

.gift-section__bottom-text {
  font-size: 32px;
  font-weight: 500;
  width: 220px;
  margin-top: 30px;
  padding: 20px;
}
/* ===== Планшети ===== */
@media (max-width: 1024px) {
  .gift-section__title {
    font-size: 40px;
  }
  .gift-section__title-description {
    font-size: 26px;
  }
  .gift-section__gift {
    width: 300px;
    height: 380px;
  }
  .gift-section__top-title {
    font-size: 20px;
  }
  .gift-section__image {
    width: 220px;
    height: 180px;
  }
  .gift-section__gift-free {
    width: 60px;
    height: 60px;
    font-size: 20px;
    top: 200px;
    right: 10px;
  }
  .gift-section__bottom-text {
    font-size: 24px;
    width: 180px;
    padding: 15px;
    margin-top: 20px;
  }
}

/* ===== Мобільні ===== */
@media (max-width: 768px) {
  .gift-section__title {
    font-size: 36px;
  }
  .gift-section__title-description {
    font-size: 22px;
  }
  .gift-section__gift {
    width: 260px;
    height: 330px;
  }
  .gift-section__top-title {
    font-size: 18px;
  }
  .gift-section__image {
    width: 180px;
    height: 140px;
  }
  .gift-section__gift-free {
    width: 50px;
    height: 50px;
    font-size: 18px;
    top: 160px;
    right: 8px;
  }
  .gift-section__bottom-text {
    font-size: 20px;
    width: 160px;
    padding: 12px;
    margin-top: 15px;
  }
}

/* ===== Дуже маленькі екрани ===== */
@media (max-width: 480px) {
  .gift-section__title {
    font-size: 28px;
  }
  .gift-section__title-description {
    font-size: 18px;
  }
  .gift-section__gift {
    width: 220px;
    height: 280px;
  }
  .gift-section__top-title {
    font-size: 16px;
  }
  .gift-section__image {
    width: 140px;
    height: 120px;
  }
  .gift-section__gift-free {
    width: 45px;
    height: 45px;
    font-size: 16px;
    top: 120px;
    right: 6px;
  }
  .gift-section__bottom-text {
    font-size: 16px;
    width: 140px;
    padding: 10px;
    margin-top: 12px;
  }
}
</style>
