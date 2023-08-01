<template>
  <div class="row justify-content-around dislike_container">
    <div class="col-5 add_form">
    <div class="dislike_input_form">
      <h3 class="list_title">비선호 재료 검색</h3>
        <input v-model="searchKeyword" class="dislike_search_form" style="width:85%;" type="text" @input="searchDislikeIngredient">
        <button @click="searchDislikeIngredient" class="add_btn">추가</button>
    </div>
      
      <div class="search_kwd">
        <span v-for="ingredient in searchResults" :key="ingredient.ingredient_id">
          {{ ingredient.title }}
          <button @click="addToDislikeList(ingredient)">추가</button>
        </span>
      </div>
    </div>

      <div class="col-5 member_dislike_list">
        <h3 class="list_title">비선호 재료 리스트</h3>
          <div class="member_check_list" v-if="memberDislikeIngredient.length > 0 || tmplst.length > 0" >
            <span v-for="ingredient in memberDislikeIngredient" :key="ingredient.ingredient_id">
              {{ ingredient.title }} |
            </span>
            <!-- 사용자가 search_kwd에서 재료를 선택하면 임시로 추가해놓고 저장버튼을 누르면 리스트를 들고 사용자 비선호 테이블에 저장 요청 -->
            <span v-for="addedIngredient in tmplst" :key="addedIngredient.ingredient_id" @click="removeFromTmplst(addedIngredient)"
            >
              {{ addedIngredient.title }} |
            </span>
            </div>
          <button @click="saveMemberDislikeList">저장</button>
          <button @click="deleteMemberDislikeList">삭제</button>

        
      </div>
  </div>


  
</template>

<script>
import { mapGetters, mapActions } from 'vuex';

export default {
  data() {
    return {
      searchKeyword: '',
      searchResults: [],
      tmplst:[],
      deleteMode: false,
    };
  },
  computed: {
    ...mapGetters(['ingredient', 'memberDislikeIngredient']),
  },
  methods: {
    ...mapActions(['memberDislikeIngredient', 'fetchIngredient']),
  
    searchDislikeIngredient() {
      if (!this.searchKeyword) {
        this.searchResults = [];
        return;
      }
      const tmplstIngredientIds = this.tmplst.map((item) => item.ingredient_id);
      // 사용자가 입력한 검색어와 유사한 재료 서치
      this.searchResults = this.ingredient.filter((ingredient) =>
      !tmplstIngredientIds.includes(ingredient.ingredient_id) && ingredient.title.includes(this.searchKeyword)
      ).slice(0, 5);
    },
    memberDislikeList(ingredient) {
      if (!this.memberDislikeList.some((item) => item.ingredient_id === ingredient.ingredient_id)) {  
        this.memberDislikeList.push(ingredient);
      }
    },

    addToDislikeList(ingredient) {
    // 비선호 재료 리스트에 추가, 저장을 눌러야 요청을 보내 회원 비선호 재료 테이블에 추가
    if (!this.tmplst.some((item) => item.id === ingredient.ingredient_id)) {
    // 비선호 재료 리스트에 추가
    this.tmplst.push(ingredient);
    // 새로 추가한 재료를 searchResults에서 제외하여 보여지지 않도록 처리
    this.searchResults = this.searchResults.filter((item) => item.ingredient_id !== ingredient.ingredient_id);
    }
    },

    saveMemberDislikeList() {
        // 저장기능
    },

    deleteMemberDislikeList() {
      // 삭제 버튼을 누르면 삭제모드 삭제모드는 리스트에 재료들을 클릭 시 리스트안에서 삭제모드 상태에서 삭제버튼을 다시 클릭하면 삭제모드 해제
       this.deleteMode = !this.deleteMode;
    },

    toggleDeleteMode() {
      if (this.deleteMode) {
        this.deleteMode = false; // 삭제모드 해제
      }
    },

    removeFromTmplst(addedIngredient) {
      // 삭제모드일 때 재료를 tmplst에서 제거합니다.
      if (this.deleteMode) {
        const index = this.tmplst.findIndex((item) => item.ingredient_id === addedIngredient.ingredient_id);
        if (index !== -1) {
          this.tmplst.splice(index, 1);
          this.searchResults.push(addedIngredient); // 삭제한 후 바로 재정렬
        }
      }
    },
  },

  created() {
    this.fetchIngredient();
  },
};
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
