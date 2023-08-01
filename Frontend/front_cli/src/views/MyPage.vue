<template>
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
            console.log(this.password)
            this.$router.push({name: 'refrigerator'})
        }
    }
}
</script>

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

.amount {
  display: flex;
}
</style>