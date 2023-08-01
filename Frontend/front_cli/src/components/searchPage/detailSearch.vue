<template>
    <div>
        <div class="accordion" id="accordionPanelsStayOpenExample">
            <div class="accordion-item">
                <h2 class="accordion-header">
                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#panelsStayOpen-collapseOne" aria-expanded="false" aria-controls="panelsStayOpen-collapseOne">
                        <strong>Classification</strong>
                    </button>
                </h2>
                <div id="panelsStayOpen-collapseOne" class="accordion-collapse collapse">
                    <div class="accordion-body">
                        <label class="radioButton">
                            <input type="radio" name="classification" value="전체" v-model="selectClassification" @click="changeClassification">전체
                        </label>
                        <label class="radioButton">
                            <input type="radio" name="classification" value="레시피명" v-model="selectClassification" @click="changeClassification">레시피명
                        </label>
                        <label class="radioButton">
                            <input type="radio" name="classification" value="주재료" v-model="selectClassification" @click="changeClassification">주재료
                        </label>
                        <label class="radioButton">
                            <input type="radio" name="classification" value="보조재료" v-model="selectClassification" @click="changeClassification">보조재료
                        </label>
                        <label class="radioButton">
                            <input type="radio" name="classification" value="양념" v-model="selectClassification" @click="changeClassification">양념
                        </label>
                    </div>
                </div>
            </div>
            <div class="accordion-item">
                <h2 class="accordion-header">
                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#panelsStayOpen-collapseTwo" aria-expanded="false" aria-controls="panelsStayOpen-collapseTwo">
                        <strong>Category</strong>
                    </button>
                </h2>
                <div id="panelsStayOpen-collapseTwo" class="accordion-collapse collapse">
                    <div class="accordion-body">
                        <label class="radioButton">
                            <input type="radio" name="category" value="전체" v-model="selectCategory" @click="changeCategory">전체
                        </label>
                        <label class="radioButton">
                            <input type="radio" name="category" value="밥" v-model="selectCategory" @click="changeCategory">밥
                        </label>
                        <label class="radioButton">
                            <input type="radio" name="category" value="국/찌개" v-model="selectCategory" @click="changeCategory">국/찌개
                        </label>
                        <label class="radioButton">
                            <input type="radio" name="category" value="반찬" v-model="selectCategory" @click="changeCategory">반찬
                        </label>
                        <label class="radioButton">
                            <input type="radio" name="category" value="일품" v-model="selectCategory" @click="changeCategory">일품
                        </label>
                        <label class="radioButton">
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
        changeClassification() {
            setTimeout(() => {console.log(this.selectClassification)}, 250)
        },
        changeCategory() {
            setTimeout(() => {console.log(this.selectCategory)}, 250)
        }
    }
}
</script>

<style scoped>
.classification {
    display: flex;
    margin: auto;
    justify-content: center;
}

.accordion-item {
    width: 60%;
    margin: auto;
}

.radioButton {
    border: solid #FD7E14;
    border-radius: .5rem;
    padding: .5rem;
    margin: .5rem;
    width: 6rem;
}

[type="radio"] {
    appearance: none;
}



.checkedRadio {
    appearance: none;
    background-color: #FD7E14;
    color: white;
}

</style>