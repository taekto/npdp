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
        tools: ["후라이팬", "에어프라이어", "전자레인지", "거품기", "국자", "냄비", "오븐", "기", "타", "등1", "등2"],
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