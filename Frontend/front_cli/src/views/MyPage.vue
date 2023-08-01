<template>
    <!-- 마이 페이지 들어갈시 비밀번호 확인 -->
    <div>
        <div class="searchWindow">
            <h2>Password</h2>
            <div>
                <form @submit.prevent="confirmPassword">
                    <div class="input-group">
                        <input id="searchForm" class="form-control" type="password" v-model.trim="password">
                        <input id="submitButton" type="submit" value="확인">
                    </div>
                </form>
            </div>
            
        </div>
    </div>
</template>

<script>
export default {
    name: 'MyPage',
    data() {
        return{
            password : "",
        }
    },
    methods: {
        confirmPassword() {
            this.$router.push({name: 'refrigerator'})
        }
    }
}
</script>

<style>
.myPage {
    display: flex;
}

#myPageMenu {
    width: 15%;
}

#myPageView {
    width: 85%;
    margin-top: 3rem;
}
</style>

<style scoped>
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