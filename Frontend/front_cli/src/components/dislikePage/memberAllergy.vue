<template>  
  <div class="myPage">
    <!-- 좌측 마이페이지 메뉴 컴포넌트 -->
    <CategoryComponent />
    <div id="myPageView">
      <div class="menuTitle_allergy">알러지 재료 등록</div>
        <!-- 알러지 재료 리스트 체크박스 한줄에 5개씩 만든다 -->
      <div class="allergy_container">
        <div class="row member_allergy_container">
          <div class="col-1 col_Box"></div>
          <div class="col-4 membe6r_allergy">
            <div class="list_title"></div>
            <div class="allergyBox row">
              <div v-for="(item, idx) in allergyList" :key="idx" class="col-4 checkAllergy">
                <label>
                  <input type="checkbox" name="allergy" @change="toggleAllergy(item)" :checked="isAllergySelected(item.allergyId)">{{ item.allergyName }}
                </label>
              </div>
            </div>
          </div>
        </div>        
      </div>
        
        <div class="row save_btn_container">
          <button class="save_btn" @click="memberDislikeAllergy({type:'allergyPost', memberId:this.memberId})">저장</button>
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

<style scoped>
.allergy_container {
  border: .1rem solid #a7a7a7;
  border-radius: 0.5rem;
  width: 80%;
  margin: auto;
  padding: 1rem;
  margin-bottom: 3rem;
  height: 60vh;
  font-family: 'GangwonEdu_OTFBoldA';
}

.menuTitle_allergy{
  font-family: 'LINESeedKR-Rg';
    text-align: start;
    margin: 1rem auto;
    /* margin-left: 1rem; */
    font-size: 1.5rem;
    font-weight: bold;
    width: 80%;
}

.checkAllergy {
  margin-top: 1rem;
  margin-bottom: 1rem;
  padding: 1rem;
}

.list_title {
  font-family: 'LINESeedKR-Rg';
}

.member_check_list {
  border: 1px solid black;
  border-radius: 10px;
  overflow-x: hidden; 
}

.allergy_list_item{
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 8px 0;
  border-bottom: 1px solid #ccc;
  overflow-y: auto; 
}

.delete_button {
  background-color: transparent;
  border: none;
  cursor: pointer;
  padding-right: 1rem;
}

.allergy_name {
  padding-left: 2rem;
}

.save_btn_container{
  display: flex;
  justify-content: center;
  margin-top: 1rem;
}

.save_btn {
  background-color: #FD7E14;
  border-radius: 0.5rem;
  width: 5rem;
  height: 3rem;
  color: white;
  border: none;
}

</style>
