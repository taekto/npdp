<template>
  <div>
    <h2>search</h2>
    <form @submit.prevent="goToSearchwithKeyword">
        <div class="input-group">
            <span class="input-group">검색어</span>
            <input class="form-control" type="text" v-model.trim="searchKeyword">
            <input type="submit" value="검색">
        </div>
    </form>
    <div id="hashTagkeyword">
        <router-link id="hash" :to="{
            name: 'searchKeyword',
            params: {
                keyword: tag
            }
        }" v-for="(tag, index) in hashTag" :key="index">
            # {{ tag }}
        </router-link>
    </div>
  </div>
</template>

<script lang="ts">
export default {
    data() {
        return {
            keyWord : null,
            searchKeyword: "",
            hashTag : ["김치", "돼지", "소", "닭", "된장", "빵"],
        }
    },
    methods: {
        goToSearchwithKeyword() {
            this.keyWord = ""
            const tempKeyword: String = this.searchKeyword
            tempKeyword.toLowerCase()
            console.log(tempKeyword)
            if(tempKeyword == "" || tempKeyword == "null") {
                this.$router.push('search')
            }
            else {
                this.$router.push({
                name: "searchKeyword",
                params: { keyword : tempKeyword }
                })
            }

            
            this.keyWord = this.searchKeyword
            this.searchKeyword = ""
        }
    }
    
}
</script>

<style>
#hashTagkeyword {
    display: flex;
    justify-content: center;
}
#hash {
    margin: 1rem;
}

</style>