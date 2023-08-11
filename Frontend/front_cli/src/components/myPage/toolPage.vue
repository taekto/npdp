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
        <div v-for="(name, idx) in tools" :key="idx" class="col-4 checkTool">
          <label>
             <input type="checkbox" name="tool" @change="toggleUtensil(name)" :checked="isUtensilSelected(name)">{{ name }}
          </label>
        </div>
      </div>
      <button class="saveButton" @click="saveTool">저장</button>
    </div>
  </div>
</template>

<script>
import CategoryComponent from './categoryComponent.vue'
import {mapGetters, mapActions} from 'vuex'

export default {
    name: 'ToolPage',
    components: {
        CategoryComponent,
    },
    // 임시 데이터
    data() {
      return {
        tools: [  
          "가스레인지",
          "믹서",
          "반죽기",
          "빙수기",
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
          "제면기",
        ],
        utensilData: [],
        memberId:null,
        utensilNameToPK: {
          '가스레인지': [1, 7, 10, 12, 16, 20],
          '믹서': [21],
          '반죽기': [27],
          '빙수기': [28],
          '수비드머신': [30],
          '식기건조대': [],
          '전기레인지': [6, 8, 11, 13, 18, 23],
          '전기밥솥': [14],
          '전기찜솥': [15],
          '전기오븐': [2],
          '전기주전자': [9],
          '전자레인지': [22],
          '커피머신': [24],
          '토스터': [3],
          '튀김기': [17],
          '에어프라이어': [4, 19],
          '와플기': [5],
          '제빙기': [29],
          '제빵기': [26],
          '제면기': [25]
        } 
      }
    },
    methods: {
      ...mapActions(['memberUtensil']),
      toggleUtensil(name) {
        const pkArray = this.utensilNameToPK[name]
        if (!pkArray) {
          console.error('Invalid utensil name:', name);
          return;
        }
        if (this.utensilData.some(pk => pkArray.includes(pk))) {
          // 이미 선택된 조리도구인 경우 배열에서 제거
          this.utensilData = this.utensilData.filter(pk => !pkArray.includes(pk));
        } else {
          // 선택되지 않은 조리도구인 경우 배열에 추가
          this.utensilData.push(...pkArray);
        }
      },
      saveTool() {
        // utensilData에 있는 조리도구의 이름을 pk 값으로 매핑한 배열을 생성
        console.log(this.memberId)
        this.memberUtensil({ type: 'post', memberId: this.memberId, utensilData: this.utensilData });
      },
      isUtensilSelected(name) {
        return this.memberUtensilList.some(item => item.utensilName === name)
      },
    },
    computed: {
      ...mapGetters(['memberUtensilList'])
    },

    created() {
      this.memberId = parseInt(sessionStorage.getItem('memberId'))
      console.log(this.memberId)
      this.memberUtensil({type:'get', memberId: this.memberId})
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