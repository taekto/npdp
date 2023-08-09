<template>
  <!-- 마이페이지 조리도구 페이지 -->
  <div class="myPage">
    <!-- 좌측 마이페이지 메뉴 컴포넌트 -->
    <CategoryComponent />

    <!-- 우측 조리도구 컴포넌트 -->
    <div id="myPageView">
      <p class="toolTitle">내 조리도구</p>
      <!-- 조리도구 체크박스 -->
      <div class="toolBox row">
        <div v-for="(tool, index) in tools" :key="index" class="col-4 checkTool">
          <label>
            <input type="checkbox" name="tool" v-model="checkedTool" :value=tool>{{tool}}
          </label>
        </div>
      </div>
      <button class="saveButton" @click="saveTool">저장</button>
    </div>
  </div>
</template>

<script>
import CategoryComponent from './categoryComponent.vue'

export default {
    name: 'ToolPage',
    components: {
        CategoryComponent,
    },
    // 임시 데이터
    data() {
      return {
        tools: [  "가스레인지",
        "과일칼",
        "믹서",
        "반죽기",
        "빙수기",
        "빵틀",
        "수비드머신",
        "식기건조대",
        "전기레인지",
        "전기밥솥",
        "전기찜솥",
        "전기오븐",
        "전기주전자",
        "전자레인지",
        "커피머신",
        "토스터",
        "튀김기",
        "에어프라이어",
        "와플기",
        "제빙기",
        "제빵기",
        "제면기"
        ,],
        checkedTool: [],
      }
    },
    methods: {
      saveTool() {
        console.log(this.checkedTool)
      }
    }
}
</script>

<style scoped>
/* 조리도구 체크박스 */
.toolBox {
    margin: auto;
    border: solid grey;
    border-radius: .5rem;
    width: 75%;
    margin-bottom: 3rem;
}

.checkTool {
    margin-top: 1rem;
    margin-bottom: 1rem;
    padding: 1rem;
}

/* 저장버튼 */
.saveButton {
    margin-bottom: 5rem;
    background-color: #FD7E14;
    border-radius: .5rem;
    width: 5rem;
    height: 3rem;
    color: white;
    border: none;
}

.toolTitle {
    text-align: start;
    margin-left: 10rem;
    font-size: 2rem;
    font-weight: bold;
}
</style>