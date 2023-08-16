<template>
  <div class="row justify-content-around allergy_container">
    <div class="col-5 add_form">
    <div class="allergy_list">
      <h3 class="allergy_list_title">알러지 재료 체크</h3>
        <!-- 알러지 재료 리스트 체크박스 한줄에 5개씩 만든다 -->
      <div class="col-5 member_allergy">
        <div class="allergyBox row">
          <div v-for="(name, idx) in allergyList" :key="idx" class="col-4 checkAllergy">
            <label>
              <input type="checkbox" name="allergy" @change="toggleAllergy(name)" :checked="isAllergySelected(name)">{{ name }}
            </label>
          </div>
        </div>
        <button @click="memberDislikeAllergy({type:'allergyPost', memberId:this.memberId})">저장</button>
      </div>
    </div>
      
    </div>
      <div class="col-5 member_allergy_list">
        <h3 class="list_title">비선호 재료 리스트</h3>
          <div class="member_check_list">
            <span v-for="(item,idx) in memberAllergy" :key="idx">
              {{ item.ingredientName }}
            <button @click="deleteItem({type: 'dislike', delData: item.ingredientId})">삭제</button>  
            </span>
          </div>
        <button @click="memberDislikeAllergy({type:'allergyPost', memberId:this.memberId})">저장</button>
      </div>
  </div>

</template>

<script>
import { mapGetters, mapActions } from 'vuex';

export default {
  data() {
    return {
      allergyList:[
        '게',
        '고등어',
        '굴',
        '난류(가금류)',
        '닭고기',
        '대두',
        '돼지고기',
        '땅콩',
        '메밀',
        '밀',
        '복숭아',
        '새우',
        '쇠고기',
        '아황산 포함식품',
        '오징어',
        '우유',
        '전복',
        '조개류',
        '토마토',
        '호두',
        '홍합',
      ],
      allergyNameToPk:{
        '게': [1910, 1911, 1912, 1913, 1914, 1915, 1916, 1918, 1925, 2815, 2825, 2896, 2902],
        '고등어':[1717, 1718, 2837], 
        '굴': [1930, 2315, 2817],
        '난류(가금류)': [1520, 1584, 1572, 1584, 1594, 1595, 1596, 1597, 1598, 1599, 1600 ,1601, 1602, 1603, 1604, 1605, 2775, 2783, 2784, 2785, 2786, 2787, 2788, 2789, 2790, ],
        '닭고기': [1521, 1522, 1523, 1524, 1525, 1526, 1527, 1528, 1529, 1530, 1531, 1532, 1533, 1534, 1535, 1536, 2921, 2922,],
        '대두': [86, 87, 2142, 2580],
        '돼지고기': [1346, 1347, 1348, 1374, 1375, 1376, 1377, 1378, 1379, 1380, 1381, 1382, 1383, 1384, 1385, 1386, 1387, 1388, 1389],
        '땅콩': [116, 126, 127, 2159],
        '메밀': [22, 463, 841, 867, 2137, 2182],
        '밀': [20, 21, 44, 2515],
        '복숭아': [204, 2232],
        '새우': [1897, 1898, 1899, 1900, 1903, 1904, 1905, 1906, 1907, 1908, 1909, 2904, 2994],
        '쇠고기': [1278, 1279, 1280, 1329, 1330, 1331, 1332, 1333, 1334, 1335, 1336, 1337, 1338, 1339, 1340, 1341, 1342, 1343, 1344, 1345, 2923, 2924],
        '아황산 포함식품': [290, 291, 369, 404, 435, 436, 460, 471, 472, 474, 475, 477, 478, 479, 480, 493, 522, 2222, 2474, 2475, 2476, 2477, 2478, 2479, 2756, 2969,],
        '오징어': [1969, 1970, 1972, 2830, 2853, 2869, 2870, 2899, 2900],
        '우유': [2596, 2597, 2598, 2599, 2600, 2603, 2619, 2620, 2692],
        '전복': [974, 1943, 1944, 1946, 2832],
        '조개류': [1269, 1935, 1956, 1957, 1958, 1959, 1961, 2826],
        '토마토': [560, 561, 562, 563, 564, 565, 829, 1881, 2237, 2328, 2814],
        '호두': [118, 119, 1012, 1033],
        '홍합': [1931, 2836],
      },
      allergyData: [],
      memberId: null,
    };
  },
  computed: {
    ...mapGetters(['memberAllergy']),
  },
  methods: {
    ...mapActions(['memberDislikeAllergy','deleteItem', 'appendItem' ]),
    isAllergySelected(name) {
      return this.memberAllergy.some(item => item.allergyName === name)
    },

    toggleAllergy(name) {
      const pkArray = this.allergyNameToPk[name]
      if (pkArray.some(pk => this.memberAllergy.includes(pk))) {
        this.deleteItem({type:'allergy', delData: pkArray})
        console.log('배열에서 제거', this.memberAllergy)
      } else {
        const inputData = pkArray.map(pk => ({ allergyId: pk, allergyName: name }))
        this.appendItem({ type: 'allergy', inputData })
        
      }
    },
  },

  created() {
    this.memberId = parseInt(sessionStorage.getItem('memberId'))
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
