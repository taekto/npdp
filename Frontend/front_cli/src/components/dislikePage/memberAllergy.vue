<template>  
  <div class="myPage">
    <!-- 좌측 마이페이지 메뉴 컴포넌트 -->
    <CategoryComponent />
    <div id="myPageView">
      <p class="menuTitle_allergy">알러지 재료 등록</p>
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
          </div>

          <div class="col-1 col_Box"></div>
          <div class="col-5 member_allergy_list">
            <h3 class="list_title">알러지 재료 리스트</h3>
            <div class="col-12 member_check_list">
              <div class="row">
                <div v-for="(item,idx) in memberAllergy" :key="idx">
                  <div class="allergy_list_item">
                      <div class="allergy_name">{{ item.allergyName }}</div>
                    <button @click="deleteItem({type: 'allergy', delData: item.allergyId})" class="delete_button">
                      X
                    </button>
                  </div>
                </div>
              </div>
            </div>
          </div>
          
        </div>
        <div class="row save_btn_container">
            <div class="col-9"></div>
            <div class="col-3">
              <button class="save_btn" @click="memberDislikeAllergy({type:'allergyPost', memberId:this.memberId})">저장</button>
            </div>

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
  border: solid #a7a7a7;
  border-radius: 0.5rem;
  width: 80%;
  margin: auto;
  padding: 1rem;
  margin-bottom: 3rem;
  height: 55vh;
  font-family: 'GangwonEdu_OTFBoldA';
}

.menuTitle_allergy{
    font-family: 'LINESeedKR-Rg';
    text-align: start;
    margin-top: 3rem;
    margin-bottom: 1rem;
    /* margin-left: 1rem; */
    font-size: 1.5rem;
    font-weight: bold;
    width: 80%;
    margin: auto;
}
.checkAllergy {
  height: 4rem;
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
  justify-content: flex-end;
  margin-top: 1rem;
}

.save_btn {
  margin-top: 1.5rem;
  background-color: #FD7E14;
  border-radius: 0.5rem;
  width: 5rem;
  height: 3rem;
  color: white;
  border: none;
}

</style>
