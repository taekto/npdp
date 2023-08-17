<template>  
  <div class="myPage">
    <!-- 좌측 마이페이지 메뉴 컴포넌트 -->
    <CategoryComponent />
    <div id="myPageView">
      <p class="menuTitle">알러지 재료 등록</p>
        <!-- 알러지 재료 리스트 체크박스 한줄에 5개씩 만든다 -->
      <div class="allergy_container">
        <div class="row member_allergy_container">
        <div class="col-1 col_Box"></div>
          <div class="col-4 membe6r_allergy">
            <h3 class="list_title">알러지 체크</h3>
            <div class="allergyBox row">
              <div v-for="(item, idx) in allergyList" :key="idx" class="col-4 checkAllergy">
                <label>
                  <input type="checkbox" name="allergy" @change="toggleAllergy(item)" :checked="isAllergySelected(item.allergyId)">{{ item.allergyName }}
                </label>
              </div>
            </div>
            <button @click="memberDislikeAllergy({type:'allergyPost', memberId:this.memberId})">저장</button>
          </div>

          <div class="col-1 col_Box"></div>
          <div class="col-5 member_allergy_list">
            <h3 class="list_title">알러지 재료 리스트</h3>
            <div class="col-12 member_check_list">
              <div class="row">
                <div v-for="(item,idx) in memberAllergy" :key="idx">
                  <div class="allergy_list_item">
                    {{ item.allergyName }}
                    <button @click="deleteItem({type: 'allergy', delData: item.allergyId})" class="delete_button">
                      X
                    </button>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <button @click="memberDislikeAllergy({ type: 'allergyPost', memberId: this.memberId })">
            저장
          </button>
        </div>
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
        console.log('배열에서 제거')
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
  margin: auto;
  border: solid grey;
  border-radius: .5rem;
  width: 75%;
  margin-bottom: 3rem;
  margin-top: 3rem;
  min-height: 35rem;
}
.allergy_check_container {
  border: 1px solid black;
  border-radius: 10px;
}

.member_check_list {
  border: 1px solid black;
  border-radius: 10px;
}

.allergy_list_item{
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 8px 0;
  border-bottom: 1px solid #ccc;
}

</style>
