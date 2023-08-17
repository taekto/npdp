<template>
  <div>
    <!-- 상단 nav -->
    <nav class="header_container">
      <!-- 로고 이미지 & 타이틀 -->
      <div id="title" @click="goToMainPage">
        <img id="logo" src="./assets/Shape.png" alt="">
        <p id="brand">냉파대파</p>
      </div>

      <!-- 기본 메뉴(메인, 검색, 설명) -->
      <div class="centered-section">
        <div id="pageLink">
          <router-link to="/" class="nav_page_link">Main</router-link> |
          <!-- <router-link to="/search" class="nav_page_link ">Search</router-link> | -->
          <router-link :to="{ name: 'searchKeyword', params: { keyword: '' } }" class="nav_page_link ">Search</router-link> |
          <router-link to="/about" class="nav_page_link">About Us</router-link>
        </div>
      </div>
      

      <!-- 유저 관련 메뉴 -->
      <div>
        <div v-if="!isLoggedIn" id="user">
          <router-link to="/signup" class="nav_user_link">회원가입</router-link>
          <router-link to="/login" class="nav_user_link login">로그인</router-link>
        </div>
        <div v-else id="user">
          <!-- <p>안녕하세요 {{ $state }}</p> -->
          <div class="nickName">{{ member.nickname }}님 :)</div>
          <div>
          <router-link to="/mypage/refrigerator" class="nav_user_link">마이페이지</router-link>
          <a class="logout nav_user_link" @click="logout">로그아웃</a>
        </div>
        </div>
      </div>
    </nav>

    <!-- 내용이 변경될 곳 -->
    <router-view/>

    <!-- 하단 설명 -->
    <footer class="footer">
      <!-- <p>@2023.panda. All rights reserved</p> -->
      <router-link to="#" class="footer_text">이용약관</router-link>
      <router-link to="#" class="footer_text">개인정보 처리방침</router-link>
      <router-link to="#" class="footer_text">문의</router-link>
      <div class="footer_css">
        <div>&copy; 2023 All Rights Reserved.</div>
        <div>문의: ssafyIdle 개발팀</div>
      </div>
    </footer>
  </div>
</template>

<script>
import {mapGetters, mapActions} from 'vuex'

export default {
  data(){
    return{
      memberId:null
    }
  },
  computed: {
    ...mapGetters(['isLoggedIn','member']),
  },
  methods: {
    ...mapActions(['logout','fetchMember']),
    goToMainPage() {
      this.$router.push({name: 'main'})
    }
  },
  created(){
    this.memberId = parseInt(sessionStorage.getItem('memberId'))
    this.fetchMember(this.memberId)
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
  /* justify-content: flex-start; */
  justify-content: space-between;
  border-bottom: solid #e8e6e6;
  margin: 0 auto;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #FD7E14;
}

#pageLink {
  font-family: 'LINESeedKR-Bd';
  display: flex;
  justify-content: center;
}

.centered-section {
  flex: 1; /* 남은 공간을 차지 */
  display: flex;
  justify-content: center;
  align-items: center; /* 세로 가운데 정렬 */
}

nav #pageLink {
  margin-top: auto;
  margin-bottom: auto;
  margin-left: 2rem;
  justify-content: center;
}

.nav_page_link {
  text-decoration-line: none;
  margin: 0 20px;
  font-size: 18px;

}

.nav_user_link {
  font-family: 'LINESeedKR-Bd';
  text-decoration-line: none;
  margin: 0 .2rem;
  padding: 10px;
  border: 1px solid #fd7e14;
  border-radius: 5px;
  color: #fd7e14;
}

.nav_user_link:hover {
  background-color: #fd7e14;
  color: #f2f2f2;
}

.nav_user_link.login {
  background-color: #fd7e14;
  color: #f2f2f2;
}
.nav_user_link.login:hover {
  background-color: white;
  color: #fd7e14;
}

.nav_user_link.logout {
  background-color: #fd7e14;
  color: #f2f2f2;
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
  font-family: 'LINESeedKR-Bd';
  margin-top: auto;
  margin-bottom: auto;
  font-size: 22px;
  font-weight: bold;
  color: #FD7E14;
}

/* 로그인 관련 CSS */
#logIn {
  margin-right: .5rem;
}


nav #user {
  display: flex;
  margin-top: auto;
  margin-bottom: auto;
  margin-left: auto;
  /* display: flex; */
}

.nickName {
  font-family: 'LINESeedKR-Rg';
  font-size: 1rem;
  margin-top: .2rem;
  margin-right: .5rem;
}

/* 하단 footer CSS */
footer {
  padding-top: 3rem;
  padding-bottom: 2rem;
  background-color: #FD7E14;
  color: white;
}

/* 라디오 버튼 공통 */
.radioButton {
  font-family: 'GangwonEdu_OTFBoldA';
  border: 1.6px solid #FD7E14;
  border-radius: .5rem;
  padding: .3rem .1rem;
  margin: .5rem;
  width: 6rem;
  font-size: 1.25rem;
  display: flex;
  align-items: center;
  justify-content: center;
  min-width: 5rem;
}


.radioButton2 {
  font-family: 'GangwonEdu_OTFBoldA';
  border: solid #FD7E14;
  background-color: #FD7E14;
  color: white;
  border-radius: .5rem;
  padding: .3rem .1rem;
  margin: .5rem;
  width: 6rem;
  font-size: 1.25rem;
  display: flex;
  align-items: center;
  justify-content: center;
  min-width: 5rem;
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
    min-width: 530px;
}

#searchForm {
    height: 3rem;
    border-radius: .5rem;
    margin-right: 1rem;
}

#submitButton {
  font-family: 'LINESeedKR-Rg';
    width: 5rem;
    border-radius: .5rem;
    background-color: #f2f2f2;
    border: solid rgb(244, 244, 244);
    font-family: 'LINESeedKR-Bd';
}

.logout {
  font-family: 'LINESeedKR-Bd';
  cursor: pointer;
  text-decoration: underline;
  text-decoration-line: none;
}

.footer_text {
  font-family: 'LINESeedKR-Rg';
  text-decoration: none;
  font-size: 15px;
  color: #f2f2f2;
}
.footer_text+.footer_text::before {
  margin: 0 25px;
  content: " | ";
  color: #f2f2f2;
}

.footer {
  margin-top: 3rem;
}

.footer_css {
  font-family: 'LINESeedKR-Rg';
  font-size: .9rem;
  margin-top: 1rem;
}

</style>

