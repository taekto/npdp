<template>
  <!-- 마이페이지 조리도구 페이지 -->
  <div class="myPage">
    <!-- 좌측 마이페이지 메뉴 컴포넌트 -->
    <CategoryComponent />
    <div>
      <div class="row">
        <fieldset>
          <legend>가열취사도구</legend>
            <div v-for="(name, idx) in tools" :key="idx" class="col-3 checkTool">
              <label>
                <input type="checkbox" name="tool" @change="toggleUtensil(name)" :checked="isUtensilSelected(name)">{{ name }}
              </label>
          </div>
        </fieldset>

      </div>

      <div class="row">
        <fieldset>그 외</fieldset>
        <div v-for="(name, idx) in tools" :key="idx" class="col-4 checkTool">
            <label>
              <input type="checkbox" name="tool" @change="toggleUtensil(name)" :checked="isUtensilSelected(name)">{{ name }}
            </label>
          </div>
      </div>

      <button class="saveButton" @click="saveTool">저장</button>
    </div>
    <!-- 우측 조리도구 컴포넌트 -->
    <!-- <div id="myPageView"> -->
    <div id="myPageView">
      <!-- <p class="menuTitle">내 조리도구</p> -->
      <!-- 조리도구 체크박스 -->
      <!-- <div class="toolBox row">
        <div v-for="(name, idx) in tools" :key="idx" class="col-4 checkTool">
          <label class="each_box">
          <input class="check_box" type="checkbox" name="tool" @change="toggleUtensil(name)" :checked="isUtensilSelected(name)">
           {{ name }}
          </label>
        </div>
      </div>
      <button class="saveButton" @click="saveTool">저장</button>
    </div> -->

      <button class="saveBtn" @click="saveTool">저장</button>
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
        cooking_utensil:[],
        etc_utensil:[],
        tools: [  
          "가스레인지",
          // "믹서",
          // "반죽기",
          // "빙수기",
          // "수비드머신",
          "에어프라이어",
          "와플기",
          "전기레인지",
          "전기밥솥",
          "전기오븐",
          "전기주전자",
          "전기찜솥",
          // "전자레인지",
          // "커피머신",
          "토스터",
          "튀김기",
          // "제빙기",
          // "제빵기",
          // "제면기",
        ],
        utensilData: [],
        memberId:null,
        utensilNameToPK: {
          '가스레인지': [1, 16, 12, 10, 7],
          // '믹서': [21],
          // '반죽기': [27],
          // '빙수기': [28],
          // '수비드머신': [30],
          '에어프라이어': [4, 19],
          '와플기': [5],
          '전기레인지': [6, 8, 11, 13, 18],
          '전기밥솥': [14],
          '전기오븐': [2],
          '전기주전자': [9],
          '전기찜솥': [15],
          '토스터': [3],
          '튀김기': [17],
          // '전자레인지': [22],
          // '커피머신': [24],
          // '제빙기': [29],
          // '제빵기': [26],
          // '제면기': [25]
        },
        resData:[],
      }
    },
    methods: {
      ...mapActions(['memberUtensil']),
      saveTool() {
        this.memberUtensil({ type: 'post', memberId: this.memberId, utensilData: this.utensilData });
      },
      toggleUtensil(name) {
        const pkArray = this.utensilNameToPK[name]
        if (pkArray.some(pk => this.utensilData.includes(pk))) {
          this.utensilData = this.utensilData.filter(pk => !pkArray.includes(pk));
          console.log('배열에서 제거');
        } else {
          this.utensilData.push(...pkArray);
          console.log('배열에 추가');
        }

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
      this.memberUtensil({ type: 'get', memberId: this.memberId })
        .then(() => {
          this.utensilData = this.memberUtensilList.reduce((data, item) => {
            const pkArray = this.utensilNameToPK[item.utensilName];
            if (pkArray) {
              data.push(...pkArray);
            }
            return data;
          }, []);
        });
  },
}

</script>

<style scoped>
/* 조리도구 체크박스 */
.toolBox {
  font-family: 'LINESeedKR-Rg';
  text-align: left;
    /* margin: 0 auto; */
    border: .1rem solid #d7d6d6;
    border-radius: .5rem;
    width: 75%;
    margin: 7rem auto 2.5rem;
    padding: 2rem;
}
.check_box {
  font-size: 1rem;
}
.each_box {
  margin-left: 2rem;
  transform : scale(1.2);
}
.checkTool {
    margin-top: 1rem;
    margin-bottom: 1rem;
    padding: .5rem;
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
    font-family: 'GangwonEdu_OTFBoldA';
}

.toolTitle {
  text-align: start;
  margin-left: 10rem;
  font-size: 2rem;
  font-weight: bold;
}

.saveBtn {
  font-family: 'LINESeedKR-Rg';
  border: none;
  height: 2.5rem;
  width: 3.5rem;
  color: white;
  background-color: #FD7E14;
  border-radius: .5rem;
  margin-bottom: .5rem;
  font-size: 1.1rem;
}
</style>