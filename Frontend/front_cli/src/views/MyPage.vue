<template>
    <!-- 마이 페이지 들어갈시 비밀번호 확인 -->
    <div>
        <div class="searchWindow">
            <h2 class="mypage_pw">비밀번호 입력</h2>
            <div>
                <form @submit.prevent="confirmPassword">
                    <div class="input-group">
                        <input id="searchForm" class="form-control" type="password" v-model.trim="creditials.password">
                        <input id="submitButton" type="submit" value="확인">
                    </div>
                </form>
            </div>
        </div>
    </div>
</template>

<script>
import axios from 'axios'

export default {
    name: 'MyPage',
    data() {
        return{
            creditials: {
                email: '',
                password : "",
            }
            
        }
    },
    created() {
        this.creditials.email = sessionStorage.getItem("email")
    },
    methods: {
        // password가 해당 회원의 password가 맞는지 확인해야 함
        confirmPassword() {
            axios({
                url: 'https://i9b202.p.ssafy.io/api/members/checkPassword',
                method: 'post',
                data: this.creditials,
            })
            .then(() => {
                this.$router.push({name: 'edit'})
            })
            .catch(err => {
                console.log(err)
                alert("비밀번호가 일치하지 않습니다.")
                this.$router.go(0)
            })
            // 
        }
    }
}
</script>

<style>
.myPage {
    border-radius: 1rem;
    display: flex;
    margin: 3rem;
}

#myPageMenu {
    width: 15%;
}

#myPageView {
    width: 85%;
    border-radius: 1rem;
    margin-top: 2rem;
    margin-bottom: 3rem;
    /* border: solid black; */
    min-height: 100vh;
}

.menuTitle {
    font-family: 'LINESeedKR-Bd';
    text-align: start;
    margin-top: 2rem;
    margin-bottom: 2rem;
    margin-left: 7rem;
    font-size: 2.5rem;
}
</style>

<style scoped>
.mypage_pw {
    font-family: 'LINESeedKR-Bd';
}
.searchWindow {
    width: 60%;
    height: 15rem;
    margin: auto;
    margin-top: 10rem;
    margin-bottom: 10rem;
}
.input-group {
    margin-top: 3rem;
}
</style>