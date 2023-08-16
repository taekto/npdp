<template>
<div class="allergyPage">
      <!-- 좌측 마이페이지 메뉴 컴포넌트 -->
    <CategoryComponent />
  <div class="row justify-content-around allergy_container">
    <div class="col-5 add_form">
    <div class="allergy_list">
      <h3 class="allergy_list_title">알러지 재료 체크</h3>
        <!-- 알러지 재료 리스트 체크박스 한줄에 5개씩 만든다 -->
      <div class="col-5 member_allergy">
        <div class="allergyBox row">
          <div v-for="(item, idx) in allergyList" :key="idx" class="col-4 checkAllergy">
            <label>
              <input type="checkbox" name="allergy" @change="toggleAllergy(item)" :checked="isAllergySelected(item.allergyId)">{{ item.allergyName }}
            </label>
          </div>
        </div>
        <button @click="memberDislikeAllergy({type:'allergyPost', memberId:this.memberId})">저장</button>
      </div>
    </div>
      
    </div>
      <div class="col-5 member_allergy_list">
        <h3 class="list_title">알러지 재료 리스트</h3>
          <div class="member_check_list">
            <span v-for="(item,idx) in memberAllergy" :key="idx">
              {{ item.allergyName }}
            <button @click="deleteItem({type: 'allergy', delData: item.allergyId})">삭제</button>  
            </span>
          </div>
        <button @click="memberDislikeAllergy({type:'allergyPost', memberId:this.memberId})">저장</button>
      </div>
  </div>
</div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex';
import CategoryComponent from '../myPage/categoryComponent.vue'
export default {
  name:'allergyPage',
  components: {
    CategoryComponent
  },
  data() {
    return {
      allergyData: [],
      memberId: null,
    };
  },
  computed: {
    ...mapGetters(['memberAllergy','allergyList']),
  },
  methods: {
    ...mapActions(['memberDislikeAllergy','deleteItem', 'appendItem','fetchAllergyList' ]),
    isAllergySelected(allergyId) {
      return this.memberAllergy.some(item => item.allergyId === allergyId)
    },

    toggleAllergy(item) {
      const pk = item.allergyId
      if (this.memberAllergy.some(memberItem => memberItem.allergyId === pk)) {
        this.deleteItem({ type: 'allergy', delData: pk })
        console.log('배열에서 제거', this.memberAllergy)
      } else {
        const inputData = { allergyId: pk, allergyName: item.allergyName };
        this.appendItem({ type: 'allergy', inputData })
      }
    },
  },

  created() {
    this.memberId = parseInt(sessionStorage.getItem('memberId'))
    this.fetchAllergyList()
    this.memberDislikeAllergy({ type: 'allergyGet', memberId: this.memberId })
  },
}
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
