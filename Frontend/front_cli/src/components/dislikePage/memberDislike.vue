<template>
  <div class="myPage">
    <!-- 좌측 마이페이지 메뉴 컴포넌트 -->
    <CategoryComponent />
    <div id="myPageView">
      <p class="menuTitle">비선호 재료 등록</p>
      <div class="dislike_container">
        <div class="row member_dislike_search">
          <div class="col-4 searchbox">
            <h3 class="list_title">비선호 재료 검색</h3>
            <div class="col-2 col_box"></div>
            <form class="col-10 search_form" @submit.prevent="specificSearch({ type: 'ingredient', name: this.ingredientName })">
              <div class="input-group">
                <input id="searchForm ingredientText" class="form-control" type="text" v-model.trim="ingredientName">
                <input id="submitButton" type="submit" value="검색">
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

          <div class="col-8 member_dislike_list">
            <h3 class="list_title">비선호 리스트</h3>
              <div class="member_check_list">
                <span v-for="(dislikeItem,idx) in memberDislikeIngredient" :key="idx">
                  {{ dislikeItem.ingredientName }}
                <button @click="deleteItem({type: 'dislike', delData: dislikeItem.ingredientId})">삭제</button>  
                </span>
              </div>
            <button class="saveButton" @click="memberDislikeAllergy({type:'dislikePost', memberId:this.memberId})">저장</button>
          </div>
        </div>
      <div>
        <button @click="startSpeechRecognition">음성으로 재료 입력</button>
        <p class="output">인식된 재료: {{ recognizedIngredients }}</p>
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
      console.log(result)
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


<style>
.dislike_container {
  margin: auto;
  border: solid grey;
  border-radius: .5rem;
  width: 75%;
  margin-bottom: 3rem;
  margin-top: 3rem;
  min-height: 35rem;
}

.menu
.input-group{
  margin-left: 2rem;
  margin-top: 2rem;
  margin-bottom: 2rem;
}

.list_title{
  margin-top: 2rem;
  margin-bottom: 2rem;
}

.saveButton {
  margin-top: 1rem;
    margin-bottom: 5rem;
    background-color: #FD7E14;
    border-radius: .5rem;
    width: 5rem;
    height: 3rem;
    color: white;
    border: none;
}

.result_box{
  text-align: center;
  max-height: 20rem;
  overflow-y: auto;
  position: relative;
}

.no_results{
  margin-left: 2rem;
}

.result_box li {
  margin-top: .1rem;
}

.member_check_list{
  margin-left: 1rem;
  margin-right: 2rem;
  height: 20rem;
  overflow-y: auto;
}

</style>
