<template>
  <div class="login-card">
    <form class="form" id="form1">
      <h2 class="form__title">Sign Up</h2>
      <span>Start your journey!</span>

      <div class="form">
        <label for="nickname">NickName</label><br>
        <input
          type="text"
          id="nickname"
          placeholder="Enter your NickName"
          class="input"
          name="name"
        />
      </div>
      <div class="input-container">
        <label for="email">Email</label><br>
        <input
          type="email"
          id="email"
          placeholder="Enter your Email"
          class="input"
          name="email"
        />
      </div>
      <div class="input-container">
        <label for="password">Password</label><br>
        <input
          type="password"
          id="password"
          placeholder="Password"
          class="input"
          name="password"
        />
      </div>
      <div class="input-container">
        <label for="birthdate">생년월일</label><br>
        <select id="birthdate" class="input" name="birthdate">
          <option value="">년</option>
          <option v-for="year in years" :key="year" :value="year">{{ year }}</option>
        </select>
        <select class="input" name="birthmonth">
          <option value="">월</option>
          <option v-for="month in months" :key="month" :value="month">{{ month }}</option>
        </select>
        <select class="input" name="birthday">
          <option value="">일</option>
          <option v-for="day in days" :key="day" :value="day">{{ day }}</option>
        </select>
      </div>
      <div class="gender-container">
        <label for="gender">성별</label><br>
        <select class="gender-male" name="gender">
          <option value="">남</option>
          <option value="">여</option>
        </select>
      </div>
      <button class="btn">Sign Up</button>
    </form>
  </div>
</template>

<script lang="ts">
import { Options, Vue } from 'vue-class-component';

@Options({})
export default class SignupView extends Vue {
  credentials = {
    name: '',
    email: '',
    password: '',
    gender: '',
  };

  birthdate = {
    year: '',
    month: '',
    day: '',
  };

  get years() {
    const currentYear = new Date().getFullYear();
    const startYear = 1900;
    const years = [];

    for (let year = currentYear; year >= startYear; year--) {
      years.push(year.toString());
    }

    return years;
  }

  get months() {
    const months = [];

    for (let month = 1; month <= 12; month++) {
      months.push(month.toString());
    }

    return months;
  }

  get days() {
    const selectedMonth = Number(this.birthdate.month);
    const selectedYear = Number(this.birthdate.year);

    if (!selectedMonth || !selectedYear) {
      return [];
    }

    const daysInMonth = new Date(selectedYear, selectedMonth, 0).getDate();
    const days = [];

    for (let day = 1; day <= daysInMonth; day++) {
      days.push(day.toString());
    }

    return days;
  }
}
</script>

<style scoped>
.login-card {
  margin: 15px 0;
}

.form {
  margin-top: 25px;
}
.input-label {
  text-align: left;
  margin-bottom: 5px;
}

.form__title {
  margin: 0 0 15px;
}

.input-container {
  margin-bottom: 15px;
}

.birthdate-container {
  display: flex; /* Use flexbox to display the birthdate inputs in a row */
  gap: 5px; /* Add some gap between the inputs for spacing */
}


.gender-container {
  margin-top: 15px;
}

.btn {
  margin-top: 15px;
  border: none; 
  background-color: #FD7E14; 
  color: #FFFFFF; 
  padding: 5px 50px; 
  cursor: pointer; 
  border-radius: 5px; 
}
</style>
