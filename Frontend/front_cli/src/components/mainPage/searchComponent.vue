<template>
    <!-- 검색창 컴포넌트 -->
    <div>
<<<<<<< HEAD
        <form @submit.prevent="recipeSpecificSearch(content)">
=======
        <form @submit.prevent="recipeSpecific(content)" @keydown.enter="recipeSpecific(content)">
>>>>>>> 409a58e886623d95589e61841785a6e26a3025c5
            <div class="input-group">
                <!-- <input id="submitButton" type="submit" value="검색"> -->
                <i class="bi bi-search" @click="recipeSpecific(content)"></i>
                <input id="searchForm" class="form-control" type="text" v-model.trim="content">
            </div>
            <!-- 해시태그 -->
            <div id="hashTagkeyword">
                <div @click="recipeSpecific(tag)" id="hash" v-for="(tag, index) in hashTag" :key="index">
                    # {{ tag }}
                </div>
            </div>
        </form>
    </div>
</template>

<script>
import { mapActions } from 'vuex'

export default {
    data() {
        return {
            content : "",    
            keyWord : "",
            hashTag : ["김치", "돼지", "소", "닭", "된장", "빵"],
        }
    },
    methods: {
        ...mapActions(['recipeSpecificSearch']),
        goToSearchwithKeyword() {
            this.keyWord = ""
            const tempKeyword = this.word.content
            tempKeyword.toLowerCase()
            console.log(tempKeyword)
            if(tempKeyword == "" || tempKeyword == "null") {
                this.$router.push({
                    name: 'search',
                })
            }
            else {
                this.$router.push({
                name: "content",
                params: { keyword : tempKeyword }
                })
            }

            
            this.keyWord = this.content
            this.content = ""
        }
    }
    
}
</script>

<style scoped>
/* 해시태그 */
#hashTagkeyword {
    display: flex;
    justify-content: center;
}

#hash {
    margin: 1rem;
    background-color: #FD7E14;
    color: white;
    width: 5rem;
    padding: 0.6rem;
    border-radius: .5rem;
    text-decoration-line: none;
}

.bi-search {
    width: 10%;
    margin: auto;
    cursor: pointer;
}

/* 검색창 */
.input-group {
    width: 60%;
    height: 2.5rem;
    margin: auto;
    margin-top: 3.5rem;
<<<<<<< HEAD
    
=======
    border: solid #FD7E14 1px;
    border-radius: .5rem;
>>>>>>> 409a58e886623d95589e61841785a6e26a3025c5
}

#searchForm {
    border: none;
    margin-right: 1rem;
    
}

#submitButton {
    width: 5rem;
    border-radius: .5rem;
    background-color: #f2f2f2;
    border: solid rgb(244, 244, 244);
}

@media screen and (max-width: 992px){
    .input-group {
        width: 80%;
        margin: auto;
        margin-top: 3.5rem;
    }
}

@import url("https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.0/font/bootstrap-icons.css");
</style>