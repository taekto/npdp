<template>
  <div class="myPage">
    <!-- 좌측 마이페이지 메뉴 컴포넌트 -->
    <CategoryComponent />
    <div id="myPageView">
      <div class="menuTitle_dislike">비선호 재료 등록</div>
      <div class="dislike_container">
        <div class="row member_dislike_search">
          <div class="col-1 col_Box"></div>
          <div class="col-4">
            <h3 class="list_title_dislike">재료 검색</h3>
            <div class="col-2 col_box"></div>
            <form class="col-10 search_form" @submit.prevent="specificSearch({ type: 'ingredient', name: this.ingredientName })">
              <div class="input-group">
                <input id="searchForm ingredientText" class="form-control" type="text" v-model.trim="ingredientName">
                <input id="submitBtn" type="submit" value="검색">
              </div>
            </form>
            

            <div class="result_box" >
              <li v-for="item in ingredientSearchData" :key="item.id" @click="appendItem({type:'dislike', memberId: this.memberId, inputData:item})">
                {{ item.name }}
              </li>
            </div>
            <div v-if="ingredientSearchData.length === 0" class="no_results">
              검색 결과가 없습니다.
            </div>
          </div>

          <div class="col-1 col_Box"></div>
          <div class="col-5 member_dislike_list">
            <h3 class="list_title_dislike list_title">비선호 재료 리스트</h3>
            <div class="col-12 member_check_list">
              <div class="row">
                <div v-for="(item,idx) in memberDislikeIngredient" :key="idx">
                  <div class="ingredient_list_item">
                    <div class="ingredient_name">{{ item.ingredientName }}</div>
                    <i class="bi bi-trash deleteButton" @click="deleteItem({type: 'dislike', delData: item.ingredientId})"></i>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        
        <div class="row save_btn_container">
            <div class="col-9"></div>
            <div class="col-3">
              <button class="save_btn" @click="memberDislikeAllergy({type:'dislikePost', memberId:this.memberId})">저장</button>
            </div>
        </div>
          
      </div>
    </div>
  </div>


  
</template>

<script>
import CategoryComponent from '../myPage/categoryComponent.vue'
import { mapGetters, mapActions } from 'vuex';

export default {
  name:'disLike',
  components: {
    CategoryComponent
  },

  data() {
    return {
      memberId:null,
      ingredientName: '',
      recognizedIngredients: "", // 음성으로 인식된 재료를 저장할 변수
    }
  },
  computed: {
    ...mapGetters(['memberDislikeIngredient','ingredientSearchData']),
  },
  methods: {
    ...mapActions(['appendItem', 'memberDislikeAllergy', 'specificSearch','deleteItem']),
    
    selectedItem(result) {
      this.ingredientName = result.name
    },
    
    ///
    startSpeechRecognition() {
      const recognition = new window.webkitSpeechRecognition();
      recognition.lang = "ko-KR"
      recognition.interimResults = false
      recognition.continuous = false
      recognition.maxAlternatives = 1

      recognition.onresult = (event) => {
        const speechResult = event.results[0][0].transcript.toLowerCase()
        console.log("Confidence: " + event.results[0][0].confidence)
        console.log("Speech Result: " + speechResult)
        this.recognizedIngredients = speechResult
      };

      recognition.onend = () => {
        console.log("SpeechRecognition.onend")
      }

      recognition.start()
    },
  },
  
  created() {
    this.memberId = parseInt(sessionStorage.getItem('memberId'))
    this.memberDislikeAllergy({ type: 'dislikeGet', memberId: this.memberId })
  },
}
</script>


<style >
.menuTitle_dislike{
    font-family: 'LINESeedKR-Rg';
    text-align: start;
    margin: 1rem auto;
    /* margin-left: 1rem; */
    font-size: 1.5rem;
    font-weight: bold;
    width: 80%;
}

.member_dislike_list {
  max-height: 76vh;
  overflow-y: auto;

}

.result_box {
  max-height: 55vh;
  max-width: 28vh;
  overflow: auto;
}

.result_box li {
  font-family: 'LINESeedKR-Rg';
  text-align: left;
}

.dislike_container {
  border: .1rem solid #a7a7a7;
  border-radius: 0.5rem;
  width: 80%;
  margin: auto;
  padding: 1rem;
  margin-bottom: 3rem;
  height: 90vh;
}

.ingredient_list_item{
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 8px 0;
  border-bottom: 1px solid #ccc;
}

.list_title_dislike{
  font-family: 'LINESeedKR-Bd';
  font-size: 1.5rem;
  margin-top: 2.5rem;
  margin-bottom: 2.5rem;
}
.ingredient_list_item{
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 8px 0;
  border-bottom: 1px solid #ccc;
}
.ingredient_name {
  font-family: 'LINESeedKR-Rg';
}
#submitBtn {
  font-family: 'LINESeedKR-Rg';
  border: .1rem solid #FD7E14;
  border-radius: .5rem;
  background-color: #FD7E14;
  color: #fff;
  margin-left: .3rem;
  align-items: end;
}
.save_btn {
  font-family: 'LINESeedKR-Rg';
  color: #fff;
  font-size: 1.2rem;
  background-color: #FD7E14;
  border: .1rem solid #FD7E14;
  border-radius: .5rem;
  padding: .3rem .5rem;
  margin-top: .7rem;
}
</style>
