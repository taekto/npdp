<template>
  <div>
    {{ keyword }}
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

}
</script>

<style>

</style>