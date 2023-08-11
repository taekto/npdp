<template>
  <div>
    <form class="signup_form" id="signup_form" @submit.prevent="tempPassword">
        <h1 class="form_title">회원가입</h1>
          <label for="email" class="input_label" 
          :class="{ 'title-danger': emailHasError }">
          이메일
          </label>
          <div class="email_box">
          <input
            type="email"
            id="email"
            placeholder="이메일을 입력해주세요"
            class="input-item"
            :class="{ 'input-danger': emailHasError }"
            name="email" 
            v-model="credentials.email"
          />
        </div>
        <!-- 에러 메시지 표시 -->
          <p v-show="valid.email" class="input-error">
            이메일 주소가 올바르지 않습니다. 다시 확인해주세요!
          </p>
        <button class="signup_btn" style="width: 100%;" @click="tempPassword">임시 비밀번호 발송</button>
      

      </form>
  </div>
</template>

<script>
import axios from 'axios'

export default {
    name: 'FindPassword',
    data() {
        return {
            credentials: {
                email: '',
            },
            // 이메일, 패스워드 검증
            valid: {
                email: false,
                password: false,
            },
            emailHasError: false,
            passwordHasError: false,
        }
    },
    watch: {
        'credentials.email': function() {
        this.checkEmail()
    },
    },

    methods: {
        // 이메일 형식 검사
        checkEmail() {
        // 이메일 형식 검사
        const validateEmail = /^[A-Za-z0-9_\\.\\-]+@[A-Za-z0-9\\-]+\.[A-Za-z0-9\\-]+/

        if (!validateEmail.test(this.email) || !this.email) {
            this.valid.email = true
            this.emailHasError = true
            return
        } this.valid.email = false
            this.emailHasError = false
        },
        tempPassword() {
            axios({
                url: 'https://i9b202.p.ssafy.io//api/mail/password',
                method: 'post',
                data: this.credentials,
            })
            .then (res => {
                console.log(res)
                alert('비밀번호를 발급했습니다.')
            })
            .catch(err => {
                console.log(err)
                alert('잘못된 이메일입니다.')
            })
        }
    }
    
}
</script>

<style>

</style>