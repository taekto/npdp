<template>
    <!-- 라디오 버튼을 통해 검색 결과를 필터링하는 컴포넌트 -->
    <div>
        <!-- 분류(키워드가 레시피명/주재료/보조재료/양념 중 무엇을 검색한 것인지 분류하는 컴포넌트) -->
        <div class="accordion" id="accordionPanelsStayOpenExample">
            <div class="accordion-item">
                <h2 class="accordion-header">
                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#panelsStayOpen-collapseOne" aria-expanded="false" aria-controls="panelsStayOpen-collapseOne">
                        <strong>Classification</strong>
                    </button>
                </h2>
                <div id="panelsStayOpen-collapseOne" class="accordion-collapse collapse">
                    <div class="accordion-body">
                        <label v-if="selectClassification === '전체'" class="radioButton2">
                            <input type="radio" name="classification" value="전체" v-model="selectClassification" @click="changeClassification">전체
                        </label>
                        <label v-else class="radioButton">
                            <input type="radio" name="classification" value="전체" v-model="selectClassification" @click="changeClassification">전체
                        </label>
                        <label v-if="selectClassification === '레시피명'" class="radioButton2">
                            <input type="radio" name="classification" value="레시피명" v-model="selectClassification" @click="changeClassification">레시피명
                        </label>
                        <label v-else class="radioButton">
                            <input type="radio" name="classification" value="레시피명" v-model="selectClassification" @click="changeClassification">레시피명
                        </label>
                        <label v-if="selectClassification === '주재료'" class="radioButton2">
                            <input type="radio" name="classification" value="주재료" v-model="selectClassification" @click="changeClassification">주재료
                        </label>
                        <label v-else class="radioButton">
                            <input type="radio" name="classification" value="주재료" v-model="selectClassification" @click="changeClassification">주재료
                        </label>
                        <label v-if="selectClassification === '보조재료'" class="radioButton2">
                            <input type="radio" name="classification" value="보조재료" v-model="selectClassification" @click="changeClassification">보조재료
                        </label>
                        <label v-else class="radioButton">
                            <input type="radio" name="classification" value="보조재료" v-model="selectClassification" @click="changeClassification">보조재료
                        </label>
                        <label v-if="selectClassification === '양념'" class="radioButton2">
                            <input type="radio" name="classification" value="양념" v-model="selectClassification" @click="changeClassification">양념
                        </label>
                        <label v-else class="radioButton">
                            <input type="radio" name="classification" value="양념" v-model="selectClassification" @click="changeClassification">양념
                        </label>
                    </div>
                </div>
            </div>

            <!-- 검색하고자 하는 레시피가 밥/국/반찬 등 카테고리 가운데 어디에 속하는지 파악하는 컴포넌트 -->
            <div class="accordion-item">
                <h2 class="accordion-header">
                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#panelsStayOpen-collapseTwo" aria-expanded="false" aria-controls="panelsStayOpen-collapseTwo">
                        <strong>Category</strong>
                    </button>
                </h2>
                <div id="panelsStayOpen-collapseTwo" class="accordion-collapse collapse">
                    <div class="accordion-body">
                        <label v-if="selectCategory !== '전체'" class="radioButton">
                            <input type="radio" name="category" value="전체" v-model="selectCategory" @click="changeCategory">전체
                        </label>
                        <label v-else class="radioButton2">
                            <input type="radio" name="category" value="전체" v-model="selectCategory" @click="changeCategory">전체
                        </label>
                        <label v-if="selectCategory !== '밥'" class="radioButton">
                            <input type="radio" name="category" value="밥" v-model="selectCategory" @click="changeCategory">밥
                        </label>
                        <label v-else class="radioButton2">
                            <input type="radio" name="category" value="밥" v-model="selectCategory" @click="changeCategory">밥
                        </label>
                        <label v-if="selectCategory !== '국/찌개'" class="radioButton">
                            <input type="radio" name="category" value="국/찌개" v-model="selectCategory" @click="changeCategory">국/찌개
                        </label>
                        <label v-else class="radioButton2">
                            <input type="radio" name="category" value="국/찌개" v-model="selectCategory" @click="changeCategory">국/찌개
                        </label>
                        <label v-if="selectCategory !== '반찬'" class="radioButton">
                            <input type="radio" name="category" value="반찬" v-model="selectCategory" @click="changeCategory">반찬
                        </label>
                        <label v-else class="radioButton2">
                            <input type="radio" name="category" value="반찬" v-model="selectCategory" @click="changeCategory">반찬
                        </label>
                        <label v-if="selectCategory !== '일품'" class="radioButton">
                            <input type="radio" name="category" value="일품" v-model="selectCategory" @click="changeCategory">일품
                        </label>
                        <label v-else class="radioButton2">
                            <input type="radio" name="category" value="일품" v-model="selectCategory" @click="changeCategory">일품
                        </label>
                        <label v-if="selectCategory !== '후식'" class="radioButton">
                            <input type="radio" name="category" value="후식" v-model="selectCategory" @click="changeCategory">후식
                        </label>
                        <label v-else class="radioButton2">
                            <input type="radio" name="category" value="후식" v-model="selectCategory" @click="changeCategory">후식
                        </label>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: "DetailSearch",
    data() {
        return {
            selectClassification : '전체',
            selectCategory : '전체',
        }
    },
    methods: {
        // 분류를 바꿔주는 함수
        changeClassification() {
            setTimeout(() => {console.log(this.selectClassification)}, 250)
        },

        // 카테고리를 바꿔주는 함수
        changeCategory() {
            setTimeout(() => {console.log(this.selectCategory)}, 250)
        }
    }
}
</script>

<style scoped>

/* 상세 검색 */
.detailSearch {
  margin-top: 2rem;
  margin-bottom: 2rem;
}

.accordion-item {
    width: 60%;
    margin: auto;
}

.checkedRadio {
    appearance: none;
    background-color: #FD7E14;
    color: white;
}


</style>