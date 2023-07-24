<template>
  <div class="categoryList">
    <label><input type="radio" name="category" value="전체">전체</label>
    <label><input type="radio" name="category" value="밥">밥</label>
    <label><input type="radio" name="category" value="국/찌개">국/찌개</label>
    <label><input type="radio" name="category" value="반찬">반찬</label>
    <label><input type="radio" name="category" value="일품">일품</label>
    <label><input type="radio" name="category" value="후식">후식</label>
  </div>
</template>

<script>
export default {

}
</script>

<style>
.categoryList {
    display: flex;
    justify-content: center;
    margin: auto;
}

.categoryList label {
    margin-left: 1rem;
    margin-right: 1rem;
}
</style>
