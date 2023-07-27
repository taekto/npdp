<template>
  <div class="signup_container">
    <div class="signup_card">
      <form class="signup_form" id="signup_form">
        <h1 class="form_title">Craete an account</h1>
        <div class="form_title_message">
          <span>Start your journey</span>
        </div>
          <label for="nickname" class="input_label">NickName</label>
          <input
            type="text"
            id="nickname"
            placeholder="Enter your NickName"
            class="input"
            name="name"
            style="width: 100%; height: 33.6px" 
          />
          <label for="email" class="input_label">Email</label>
          <input
            type="email"
            id="email"
            placeholder="Enter your Email"
            class="input"
            name="email"
            style="width: 100%; height: 33.6px" 
          />
          <label for="password" class="input_label">Password</label>
          <input
            type="password"
            id="password"
            placeholder="Password"
            class="input"
            name="password"
            style="width: 100%; height: 33.6px" 
          />
          
  <div class="birthdate_container">
    <label for="birthdate">생년월일</label>
    <div class="birthdate_select_container">
      <select class="birthdate_select" name="birthdate" v-model="birthdate.year">
        <option value="">년</option>
        <option v-for="year in years" :key="year" :value="year">{{ year }}</option>
      </select>
      <select class="birthdate_select" name="birthmonth" v-model="birthdate.month">
        <option value="">월</option>
        <option v-for="month in months" :key="month" :value="month">{{ month }}</option>
      </select>
      <select class="birthdate_select" name="birthday" v-model="birthdate.day">
        <option value="">일</option>
        <option v-for="day in days" :key="day" :value="day">{{ day }}</option>
      </select>
    </div>
  </div>

        <div class="gender_container">
          <div class="gender">
            <label for="gender">성별</label>
          </div>

          <select class="gender_male" name="gender">
            <option value="">선택</option>
            <option value="">남</option>
            <option value="">여</option>
          </select>
        </div>
        <button class="signup_btn" style="width: 100%;">Get started</button>

      <!-- 소셜 로그인 -->
      <div class="signup_sns">
          <a href="http://localhost:8080/signup" class="btn_sns btn-google btn-block">
          Google 계정으로 시작</a>
          <a href="http://localhost:8080/signup" class="btn_sns btn-naver btn-block"><i class="fab fab-naver-alt"></i> 
          네이버 계정으로 시작</a>
          <a href="http://localhost:8080/signup" class="btn_sns btn-kakao btn-block">
          KaKao 계정으로 시작</a>
      </div>

      </form>
    </div>
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
    const daysInMonth = new Date(selectedYear, selectedMonth, 0).getDate();

    if (!selectedMonth || !selectedYear) {
      return [];
    }

    const days = [];

    for (let day = 1; day <= daysInMonth; day++) {
      days.push(day.toString());
    }

    return days;
  }
}
</script>

<style scoped>
.signup_card {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin: 15px 0;
  font-family: 'Inter', sans-serif;
}

.form_title {
  text-align: center;
  margin-bottom: 15px;
}

.form_title_message {
  text-align: center;
  margin-bottom: 25px;
}

.input_label {
  display: flex;
  padding: 15px 0px 5px 0px;
}

.birthdate_container {
  text-align: left; 
  margin-top: 15px;
}

.birthdate_select_container {
  display: flex;
  padding: 5px 0px 0px 0px;
  gap: 2px;
}
.gender_container {
  text-align: left;
  
}
.gender {
  padding: 15px 0px 5px 0px;
}

.signup_btn {
  margin-top: 25px;
  font-size: 14px;
  border: none; 
  background-color: #FD7E14; 
  color: #FFFFFF;
  padding: 7.5px;
  cursor: pointer; 
  border-radius: 4px; 
}

/* 소셜 로그인 */
.signup_sns {
  margin-top: 35px;
}

.btn_sns {
    font-weight: normal;
    text-align: center;
    white-space: nowrap;
    vertical-align: middle;
    cursor: pointer;
    background-image: none;
    border: 1px solid transparent;
    padding: 6px 16px;
    font-size: 14px;
    line-height: 1.42857143;
    border-radius: 4px;
    -webkit-user-select: none;
    -moz-user-select: none;
    -ms-user-select: none;
    user-select: none;
    text-decoration: none;
    margin: 10px 0px 10px 0px;
}

.btn-google {
    color: #000;
    background-color: #ffffff;
    border-color: #ccc;
    background-image: url(../assets/google_icon.png);
    background-size: 20px;
    background-position: 15px 50%;
    background-repeat: no-repeat;
    display: block;
    position: relative;
    font-family: Sans-Serif !important;
    min-width: 280px;
    
}

.btn-naver {
    background: #01c73c;
    border-color: #01c73c;
    color: #fff;
    font-weight: 500;
    font-family: 'Nexon Gothic', 'Malgun Gothic', Sans-Serif;
    background-image: url(../assets/naver_login2x.png);
    background-size: 17px;
    background-position: 17px 50%;
    background-repeat: no-repeat;
    display: block;
    position: relative;
    min-width: 280px;
}

.btn-kakao {
    color: #000000;
    background-color: #FEE500;
    border-color: #ccc;
    background-size: 20px;
    background-position: 15px 50%;
    background-repeat: no-repeat;
    display: block;
    position: relative;
    font-family: Sans-Serif !important;
    min-width: 280px;
    background-image: url(../assets/Kakao.svg);
}

</style>
