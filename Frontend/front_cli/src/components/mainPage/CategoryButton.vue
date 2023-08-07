<template>
  <!-- 검색 결과 필터링 컴포넌트 -->
  <div class="categoryList">
    <label v-if="selectCategory === '전체'" class="categotyButton"><input type="radio" name="category" value="전체" v-model="selectCategory">전체</label>
    <label v-else class="categotyButton2"><input type="radio" name="category" value="전체" v-model="selectCategory">전체</label>
    <label v-if="selectCategory === '밥'" class="categotyButton"><input type="radio" name="category" value="밥" v-model="selectCategory">밥</label>
    <label v-else class="categotyButton2"><input type="radio" name="category" value="밥" v-model="selectCategory">밥</label>
    <label v-if="selectCategory === '국/찌개'" class="categotyButton"><input type="radio" name="category" value="국/찌개" v-model="selectCategory">국/찌개</label>
    <label v-else class="categotyButton2"><input type="radio" name="category" value="국/찌개" v-model="selectCategory">국/찌개</label>
    <label v-if="selectCategory === '반찬'" class="categotyButton"><input type="radio" name="category" value="반찬" v-model="selectCategory">반찬</label>
    <label v-else class="categotyButton2"><input type="radio" name="category" value="반찬" v-model="selectCategory">반찬</label>
    <label v-if="selectCategory === '일품'" class="categotyButton"><input type="radio" name="category" value="일품" v-model="selectCategory">일품</label>
    <label v-else class="categotyButton2"><input type="radio" name="category" value="일품" v-model="selectCategory">일품</label>
    <label v-if="selectCategory === '후식'" class="categotyButton"><input type="radio" name="category" value="후식" v-model="selectCategory">후식</label>
    <label v-else class="categotyButton2"><input type="radio" name="category" value="후식" v-model="selectCategory">후식</label>
  </div>
</template>

<script>
export default {
  data() {
    return {
      selectCategory : "전체"
    }
  }
}
</script>

<style scoped>
/* 검색결과 필터링 */
.categoryList {
    padding-top: 2.5rem;
    padding-bottom: 2.5rem;
    display: flex;
    justify-content: center;
    margin: auto;
    background-color: #EEEEEE;
    font-weight: bold;
    font-size: 1.75rem;
    margin-top: 1.5rem;
    margin-bottom: 2.5rem;
}

.categoryList label {
    margin-left: 1rem;
    margin-right: 1rem;
}

.categotyButton{
  border: solid #FD7E14;
  cursor: pointer;
  background-color: #FD7E14;
  color: white;
  width: 9rem;
  height: 3rem;
  border-radius: .5rem;
}

.categotyButton2{
  border: solid #FD7E14;
  cursor: pointer;
  color: #FD7E14;
  width: 9rem;
  height: 3rem;
  border-radius: .5rem;
}
</style>
