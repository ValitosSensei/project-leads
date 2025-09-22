<script setup lang="ts">
</script>

<template>
  <section class="steps-section">
    <h2 class="steps-section__title">Все просто, як піца з коробки</h2>
    <p class="steps-section__subtitle">
      Ми зробили процес замовлення максимально легким - без стресу, як має бути у студента!
    </p>

    <div class="steps-section__content">
      <!-- Кроки -->
      <div class="steps-section__steps">
        <div class="step1">
          <img src="../assets/step1.svg" alt="Тип роботи" class="step__icon" />
          <p class="step__text">Обери тип роботи<br/> (курсова, дипломна, реферат)</p>
        </div>

        <div class="step2">
          <img src="../assets/step2.svg" alt="Опис завдання" class="step__icon" />
          <p class="step__text">Опиши завдання<br/> або завантаж файли</p>
        </div>

        <div class="step3">
          <img src="../assets/step3.svg" alt="Оплата" class="step__icon" />
          <p class="step__text">Оплати і вибери<br/> подарунок</p>
        </div>
      </div>

      <!-- Результат -->
      <div class="steps-section__result">
        <div class="result__student">
          <img src="../assets/student-pizza.svg" alt="Студент з піцою" class="result__image" />
          <p class="result__text">Отримай роботу та смачний бонус</p>
        </div>

        <img src="../assets/arrow-right.svg" alt="Стрілка" class="result__arrow" />
      </div>
    </div>
  </section>
</template>

<style scoped>
.steps-section {
  text-align: center;
  padding: 50px 20px;
  position: relative;
}

.step2 {
  margin-top: 100px;
}
.step3 {
  margin-top: 200px;
}

.steps-section__title {
  font-size: 40px;
  font-weight: 700;
  margin-bottom: 10px;
}

.steps-section__subtitle {
  font-size: 24px;
  margin-bottom: 40px;
  color: #333;
}

.steps-section__content {
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 30px;
  flex-wrap: wrap;
}

.steps-section__steps {
  display: flex;
  gap: 40px;
}

.step__icon {
  width: 300px;
  height: auto;
  margin-bottom: 10px;
}

.step__text {
  font-size: 32px;
  line-height: 1.3;
}

.steps-section__result {
  display: flex;
  align-items: center;
  gap: 20px;
  position: relative;
}

.result__arrow {
  position: absolute;
  left: -150px;
  top: 100px;
  width: 180px;
  height: auto;
  pointer-events: none;
}

.result__student {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.result__image {
  width: 400px;
  margin-bottom: 10px;
}

.result__text {
  font-size: 32px;
  max-width: 400px;
  line-height: 1.3;
}

/* 📱 Адаптив під планшети (768–1024px) */
@media (max-width: 1024px) {
  .step__icon {
    width: 220px;
  }

  .step__text {
    font-size: 24px;
  }

  .result__image {
    width: 300px;
  }

  .result__text {
    font-size: 24px;
    max-width: 300px;
  }

  .step2 {
    margin-top: 70px;
  }

  .step3 {
    margin-top: 140px;
  }

  .result__arrow {
    width: 140px;
    left: -120px;
    top: 80px;
  }
}

/* 📱 Адаптив під телефони (<768px) */
@media (max-width: 768px) {
  .steps-section {
    padding: 30px 15px;
  }

  .steps-section__title {
    font-size: 28px;
  }

  .steps-section__subtitle {
    font-size: 18px;
    margin-bottom: 25px;
  }

  .steps-section__content {
    flex-direction: column;
    gap: 40px;
  }

  .steps-section__steps {
    flex-direction: column;
    gap: 30px;
  }

  .step2, .step3 {
    margin-top: 0;
  }

  .step__icon {
    width: 180px;
  }

  .step__text {
    font-size: 18px;
  }

  .result__image {
    width: 220px;
  }

  .result__text {
    font-size: 18px;
    max-width: 90%;
  }

  .result__arrow {
    display: none;
  }
}
</style>
