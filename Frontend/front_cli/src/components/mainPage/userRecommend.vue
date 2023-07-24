<template>
  <div>
    <h2>userRecommned</h2>
    <Carousel v-bind="settings" :breakpoints="breakpoints">
        <Slide v-for="slide in 12" :key="slide">
            <div class="carousel_item">{{ slide }}</div>
        </Slide>

        <template #addons>
            <Navigation />
        </template>
    </Carousel>
  </div>
</template>

<script setup>
    import {Carousel, Navigation, Slide} from 'vue3-carousel'
</script>

<script>
import 'vue3-carousel/dist/carousel.css'

export default {
    name: 'UserRecommend',
    components: {
        Carousel,
        Slide,
        Navigation,
    },
    data() {
        return {
            settings: {
                itemToShow: 1,
                snapAlign: 'center',
            },
            breakpoints: {
                
            }
        }
        
    }
}
</script>

<style>

</style>