<template>
  <div class="row justify-content-around dislike_container">
    <div class="col-5 add_form">
      <div class="dislike_input_form">
        <h3 class="list_title">비선호 재료 검색</h3>
          <form @submit.prevent="specificSearch({ type: 'ingredient', name: this.ingredientName })">
            <div class="input-group">
                <input id="searchForm ingredientText" class="form-control" type="text" v-model.trim="ingredientName">
                <input id="submitButton" type="submit" value="검색">
            </div>
          </form>
          <div class="search_results_container">
            <ul>
              <li v-for="item in ingredientSearchData" :key="item.id" @click="appendItem({type:'dislike', memberId: this.memberId, inputData:item})">
                {{ item.name }}
              </li>
            </ul>
          </div>
      </div>    
    </div>

      <div class="col-5 member_dislike_list">
        <h3 class="list_title">비선호 재료 리스트</h3>
          <div class="member_check_list">
            <span v-for="(dislikeItem,idx) in memberDislikeIngredient" :key="idx">
              {{ dislikeItem.ingredientName }}
            <button @click="deleteItem({type: 'dislike', delData: dislikeItem.ingredientId})">삭제</button>  
            </span>
          </div>
        <button @click="memberDislikeAllergy({type:'dislikePost', memberId:this.memberId})">저장</button>
      </div>
  <div>
    <button @click="startSpeechRecognition">음성으로 재료 입력</button>
    <p class="output">인식된 재료: {{ recognizedIngredients }}</p>
  </div>  
      
  </div>


  
</template>

<script>
import { mapGetters, mapActions } from 'vuex';

export default {
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
    
    /////
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
  border: 1px solid black;
  padding: 30px 0px 250px 0px;
  max-height: 350px;
  /* overflow-y: auto; 스크롤바 */ 
}
.member_check_list {
  border: 1px solid black;
  border-radius: 10px;
}


.search_kwd {
    display: flex;
    flex-direction: column;
    text-align: left;
}

.add_btn {
  margin-left: 5px;
}

.dislike_search_form {
  border-radius: .5rem;
  margin-right: 1rem;
}
</style>
