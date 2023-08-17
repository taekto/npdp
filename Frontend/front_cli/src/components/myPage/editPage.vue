<template>
  <!-- 마이페이지 회원정보 수정 페이지 -->
  <div class="myPage">
    <!-- 좌측 마이페이지 메뉴 컴포넌트 -->
    <CategoryComponent />

    <!-- 우측 회원정보 컴포넌트 -->
    <div id="myPageView">
      <p class="menuTitle">회원정보수정</p>
      <div class="editComponent">
        
        <div>
          <!-- 닉네임 변경 -->
          <div class="searchWindow">
            <div class="editCategoryTitle">
              <p>닉네임 변경</p>
            </div>
            <div>
              <form class="edit_form" @submit.prevent="memberUpdate(memberId, userData.nickname)" >
                <div class="input-group">
                  <input id="searchForm" class="form-control" type="text" v-model.trim="member.nickname">
                </div>
                <button class="btn_update" @click="asd">수정</button>
              </form>
            </div>
          </div>

          <!-- 이메일 변경 -->
          <div class="searchWindow">
            <div class="editCategoryTitle">
              <p>이메일 변경</p>
            </div>
            <div>
              <form class="edit_form">
                <div class="input-group">
                  <input id="searchForm" class="form-control" type="email" v-model.trim="member.email" readonly>
                </div>
              </form>
            </div>
          </div>

          <!-- 비밀번호 변경 -->
          <div class="searchWindow">
            <div class="editCategoryTitle">
              <p>비밀번호 변경</p>
            </div>
            <div>
              <form class="edit_form" @submit.prevent="confirmPassword">
                <div class="input-group">
                    <input id="searchForm" class="form-control" type="password" v-model.trim="userData.password">
                </div>
                <button class="btn_update">수정</button>
              </form>
            </div>
          </div>

          <!-- 생일 & 성별 변경 -->
          <div class="birthAndgender">

            <!-- 생일 변경 -->
            <div class="birthDate" >
              <div class="editCategoryTitle">
                <p>생일 변경</p>
              </div>
              <VDatePicker v-model="member.birth" :max-date="new Date()" @click="whatDate(member.birth)"/>
            </div>

            <!-- 성별 변경 -->
            <div class="genderSelect">
              <div class="editCategoryTitle">
                <p>성별 변경</p>
              </div>
              <div class="storageRadio">
                <label v-if="userData.gender !== 'MALE'" class="radioButton">
                  <input type="radio" name="MALE" value="MALE" v-model="userData.gender" @click="changeClassification">남자
                </label>
                <label v-else class="radioButton2">
                  <input type="radio" name="MALE" value="MALE" v-model="userData.gender" @click="changeClassification">남자
                </label>
                <label v-if="userData.gender !== 'FEMALE'" class="radioButton">
                  <input type="radio" name="FEMALE" value="FEMALE" v-model="userData.gender" @click="changeClassification">여자
                </label>
                <label v-else class="radioButton2">
                  <input type="radio" name="FEMALE" value="FEMALE" v-model="userData.gender" @click="changeClassification">여자
                </label>
                <label v-if="userData.gender !== 'NONE'" class="radioButton">
                  <input type="radio" name="NONE" value="NONE" v-model="userData.gender" @click="changeClassification">선택 안함
                </label>            
                <label v-else class="radioButton2">
                  <input type="radio" name="NONE" value="NONE" v-model="userData.gender" @click="changeClassification">선택 안함
                </label>            
              </div>
            </div>
          </div>
          <button class="saveButton" @click="memberUpdate({type:'all', memberId:this.memberId, updateData: userData})">저장</button>
        </div>
      </div>
      </div>
  </div>
</template>

<script>
import CategoryComponent from './categoryComponent.vue'
import { mapActions, mapGetters } from 'vuex';

export default {
    name: "UserInfoEditPage",
    components: {
        CategoryComponent,
    },
    // 임시 데이터
    data() {
      return {
        userData:{
          nickname:"",
          email : "",
          birthDate : "",
          password : "",
          gender : "",
        },
        memberId: null,
      }
    },
    computed: {
      ...mapGetters(['member']),
    },

    methods: {
      ...mapActions(['fetchMember','memberUpdate']),
      
      setGender() {
        this.userData.gender = this.member.gender
      },

      whatDate(birthDate, delimiter = '-') {
        const year = birthDate.getFullYear()
        const month = birthDate.getMonth() + 1
        const date = birthDate.getDate()
        if(month < 10) {
          this.birthDate = year + delimiter + '0' + month + delimiter + date
        }
        else {
          this.birthDate = year + delimiter + month + delimiter + date
        }
      },
    },
    watch: {
      'member': {
        handler(updatedMember) {
          // member 게터의 데이터 출력
          console.log('Updated member data');
          
          // 만약 updatedMember 안에 nickname 프로퍼티가 있다면 userData.nickname에 할당
          if (updatedMember && updatedMember.nickname) {
            this.userData.nickname = updatedMember.nickname;
          }
        },
        deep: true // 객체 내부의 프로퍼티까지 감시
      }
    },
    async created() {
      this.memberId = parseInt(sessionStorage.getItem('memberId'))
      this.fetchMember(this.memberId)
      this.setGender()
    },

}
</script>

<style scoped>
.editTitle {
  font-family: 'LINESeedKR-Bd';
  margin: auto;
  margin-top: 2rem;
  font-size: 2rem;
  font-weight: bold;
  /* text-align: start; */
  width: 75%;
}

.storageRadio {
  display: flex;
}

.editCategoryTitle {
    margin: auto;
    margin-top: 2rem;
    margin-left: .75rem;
    font-size: 1.2rem;
    font-weight: bold;
    text-align: start;
}

.editComponent {
    width: 80%;
    margin: auto;
    border: solid grey;
  border-radius: .5rem;
  /* margin-left: 2rem;
  margin-right: 2rem;
  margin-bottom: 5rem; */
}


.searchWindow {
    width: 75%;
    margin: auto;
}

.birthAndgender {
  display: flex;
  justify-content: space-around;
  margin: auto;
  margin-bottom: 3rem;
  width: 75%;
}

.birthInput {
  justify-content: center;
  border-radius: .5rem;
  width: 100%;
  height: 2.5rem;
  text-align: center;
  margin-top: 1rem;
}

.birthDate{
  margin: auto;
}

.genderSelect {
  margin: auto;
  
  margin-left: 3rem;
}


/* #myPageView {
  border: solid grey;
  border-radius: .5rem;
  margin-left: 2rem;
  margin-right: 2rem;
  margin-bottom: 5rem;
} */

.btn_update {
  border-radius: .5rem;
  font-family: 'LINESeedKR-Rg';
  border: 1.6px solid #FD7E14;
  background-color: #FD7E14;
  color: white;
  border-radius: .5rem;
  padding: .3rem .2rem;
  margin-right: 1rem;
  width: 4rem;
  font-size: 1.25rem;
}

.edit_form {
  display: flex;
}

.saveButton {
  font-family: 'LINESeedKR-Rg';
  border: 1.6px solid #FD7E14;
  background-color: #FD7E14;
  color: white;
  border-radius: .5rem;
  padding: .3rem .2rem;
  margin: .5rem;
  width: 6rem;
  font-size: 1.25rem;
  margin-bottom: 3rem;
  /* display: flex;
  align-items: center;
  justify-content: center; */
}

</style>