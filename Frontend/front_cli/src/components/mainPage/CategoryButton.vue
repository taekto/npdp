<template>
  <!-- 검색 결과 필터링 컴포넌트 -->
  <!-- <div class="categoryList">
    <label><input type="radio" name="category" value="전체">전체</label>
    <label><input type="radio" name="category" value="밥">밥</label>
    <label><input type="radio" name="category" value="국/찌개">국/찌개</label>
    <label><input type="radio" name="category" value="반찬">반찬</label>
    <label><input type="radio" name="category" value="일품">일품</label>
    <label><input type="radio" name="category"  value="후식">후식</label>
  </div> -->
  <!-- 링크 바인딩 a태그 없애고 라우팅? -->
<div class="mainPageCategory">
  <div class="cate_cont" style="height:100px;">
    <li style="display:table-cell;"><a href=""><img src="@/assets/mainPageCategory/all.png"><span>전체</span></a></li>
    <li style="display:table-cell;"><a href="#"><img src="@/assets/mainPageCategory/bob.png"><span>밥</span></a></li>
    <li style="display:table-cell;"><a href="#"><img src="@/assets/mainPageCategory/gook.png"><span>국/찌개</span></a></li>
    <li style="display:table-cell;"><a href="#"><img src="@/assets/mainPageCategory/banchan.png"><span>반찬</span></a></li>
    <li style="display:table-cell;"><a href="#"><img src="@/assets/mainPageCategory/ilfoom.png"><span>일품</span></a></li>
    <li style="display:table-cell;"><a href="#"><img src="@/assets/mainPageCategory/hoosik.png"><span>후식</span></a></li>
  </div>
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
/* .categoryList {
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
} */


.mainPageCategory{
  padding: 2.5rem 0rem 2.5rem 0rem;
  background-color: #EEEEEE;
  margin-top: 1.5rem;
  margin-bottom: 2.5rem; 
  font-family: 'Quicksand', sans-serif; /* Quicksand 글꼴을 사용 */
}

.cate_cont {
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 3rem; 
  
}
.cate_cont span {
  display: flex;
  flex-direction: column;
  margin-top: 0.5rem; /* 이미지와의 간격 조정 */
  font-size: 1.1rem;
  text-decoration: none !important;
  
}

.cate_cont img {
  gap: 1rem; 
  width: 100px; 
  height: auto; 
  vertical-align: middle;
}

</style>
