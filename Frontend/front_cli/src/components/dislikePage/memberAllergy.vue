<template>
  <div class="row justify-content-around allergy_container">
    <div class="col-5 add_form">
    <div class="allergy_list">
      <h3 class="allergy_list_title">알러지 재료 체크</h3>
        <!-- 알러지 재료 리스트 체크박스 한줄에 5개씩 만든다 -->
        <div class="allergy_check_container">
          <label v-for="tmp in ingredient" :key="tmp.ingredient_id">
            <input type="checkbox" @change="addToTmpLst(tmp)" /> 
            {{ tmp.name }}
          </label>
        </div>
    </div>
      
    </div>
      <div class="col-5 member_allergy">
        <h3 class="member_allergy_title">알러지 있는 재료</h3>
        <div class="member_check_list">
          <span v-for="allergyingredient in memberAllergy" :key="allergyingredient.ingredient_id">
            {{ allergyingredient.ingredient_id }} |
          </span>
          <!-- 사용자가 알러지를 체크하면 tmplst리스트에 추가했다가 저장버튼을 눌러야 api에 요청 -->
          <span v-for="addedallergyIngredient in tmplst" :key="addedallergyIngredient.ingredient_id">
            {{ addedallergyIngredient.name }} |
          </span>
      </div>
        <button @click="saveMemberallergy">저장</button>
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
      tmplst:[]
    };
  },
  computed: {
    ...mapGetters(['ingredient', 'memberAllergy']),
  },
  methods: {
    ...mapActions(['fetchIngredient', 'fetchMemberAllergy']),

addToTmpLst(tmp) {
    const index = this.tmplst.findIndex(item => item.ingredient_id === tmp.ingredient_id);
    if (index > -1) {
      // 이미 체크된 재료라면 리스트에서 제거
      this.tmplst.splice(index, 1);
    } else {
      // 체크된 알러지 재료를 tmplst 배열에 추가
      this.tmplst.push(tmp);
    }
  },
  },

  created() {
    this.fetchIngredient();
    // 재료 리스트가 불러와 지는지 확인
    console.log(this.ingredient)
  },
};
</script>


<style>
.allergy_container {
  padding: 30px 0px 250px 0px;
  max-height: 350px;
}
.allergy_check_container {
  border: 1px solid black;
  border-radius: 10px;
}

.member_check_list {
  border: 1px solid black;
  border-radius: 10px;
}

</style>
