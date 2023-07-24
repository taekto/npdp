<template>
  <div>
    <h1>{{ keyword }}</h1>
    <form @submit.prevent="goToSearchwithKeyword">
        <div class="input-group">
            <span class="input-group">검색어</span>
            <input class="form-control" type="text" v-model.trim="searchKeyword">
            <input type="submit" value="검색">
        </div>
    </form>
  </div>
</template>

<script>
export default {
    data() {
        return {
            keyword: this.$route.params.keyword,
        }
    }
}
</script>

<style>

</style>