<template>
  <div>
    <!-- 상단 nav -->
    <nav>
      <!-- 로고 이미지 & 타이틀 -->
      <div id="title" @click="goToMainPage">
        <img id="logo" src="./assets/Shape.png" alt="">
        <p id="brand">냉파대파</p>
      </div>

      <!-- 기본 메뉴(메인, 검색, 설명) -->
      <div id="pageLink">
        <router-link to="/">Main</router-link> |
        <router-link to="/search">Search</router-link> |
        <router-link to="/about">About Us</router-link>
      </div>

      <!-- 유저 관련 메뉴 -->
      <div v-if="!isLoggedIn" id="user">
        <router-link to="/signup">Signup</router-link> | 
        <router-link to="/login">Login</router-link>
      </div>
      <div v-else id="user">
        <router-link to="/mypage">MyPage</router-link> |
        <a class="logout" @click="logout">Logout</a>
      </div>
    </nav>

    <!-- 내용이 변경될 곳 -->
    <router-view/>

    <!-- 하단 설명 -->
    <footer>
      <a href="#">Facebook</a> |
      <a href="#">Twitter</a> |
      <a href="#">Instagram</a>
      <p>@2023.panda. All rights reserved</p>
    </footer>
  </div>
</template>

<script>
import {mapGetters, mapActions} from 'vuex'

export default {
  computed: {
    ...mapGetters(['isLoggedIn'])
  },
  methods: {
    ...mapActions(['logout']),
    goToMainPage() {
      this.$router.push({name: 'main'})
    }
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

/* 상단 nav CSS */
nav {
  padding: 25px;
  padding-top: 1.5rem;
  display: flex;
  justify-content: flex-start;
  border-bottom: solid #e8e6e6;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}

nav #pageLink {
  margin-top: auto;
  margin-bottom: auto;
  margin-left: 2rem;
}

nav #title {
  display: flex;
  justify-content: left;
  margin-left: 0;
  cursor: pointer;
}

#logo {
  width: 25px;
  height: 25px;
  margin-top: auto;
  margin-bottom: auto;
  margin-right: .5rem;
}

#brand {
  margin-top: auto;
  margin-bottom: auto;
  font-size: 20px;
  font-weight: bold;
  color: #FD7E14;
}

/* 로그인 관련 CSS */
#logIn {
  margin-right: .5rem;
}

nav #user {
  margin-top: auto;
  margin-bottom: auto;
  margin-left: auto;
}

/* 하단 footer CSS */
footer {
  padding-top: 2rem;
  padding-bottom: 2rem;
  background-color: #FD7E14;
  color: white;
}

/* 라디오 버튼 공통 */
.radioButton {
    border: solid #FD7E14;
    border-radius: .5rem;
    padding: .5rem;
    margin: .5rem;
    width: 6rem;
}

[type="radio"] {
    appearance: none;
}

/* 검색창 & 비밀번호 확인 & 회원정보 수정 공통 */
.searchWindow {
    width: 60%;
    /* height: 2.5rem; */
    margin: auto;
    margin-top: 3.5rem;
    margin-bottom: 5rem;
}

#searchForm {
    border-radius: .5rem;
    margin-right: 1rem;
}

#submitButton {
    width: 5rem;
    border-radius: .5rem;
    background-color: #f2f2f2;
    border: solid rgb(244, 244, 244);
}

.logout {
  cursor: pointer;
  text-decoration: underline;
}

</style>

