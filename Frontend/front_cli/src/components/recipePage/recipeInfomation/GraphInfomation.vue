<template>
    <!-- 도넛 그래프 -->
    <div>
        <Doughnut :data="chartData" :options="options" />
    </div>
</template>

<script>
import { Chart as ChartJS, ArcElement, Tooltip, Legend } from 'chart.js'
import { Doughnut } from 'vue-chartjs'
import{mapGetters} from 'vuex'

ChartJS.register(ArcElement, Tooltip, Legend)

export default {
    name: 'GraphInfomation',
    components: { Doughnut, },
    computed: {
        ...mapGetters(['recipeDetail']),
        similarity() {
            return (this.recipeDetail.similarity * 100).toFixed(2);
        },
        chartData() {
            return {
                labels: ['Differ', 'Similarity'],
                datasets: [
                    {
                        backgroundColor: ['#EEEEEE', '#FD7E14'],
                        data: [100 - this.similarity, this.similarity],
                    },
                ],
            };
        },
    },
    data() {
        return {
            // data : {
            //     labels: ['Differ', 'Similarity', ],
            //     datasets: [
            //     {
            //         backgroundColor: ['#EEEEEE', '#FD7E14'],
            //         // 유사도를 통해 그래프 값을 변경
            //         // 유사도 입력 받은 후 수정
            //         // data: [100-this.similarity, this.similarity]
            //         data: [100-this.similarity, this.similarity]
            //         // data: [25, 75]
            //     }
            //     ]
            // },
            options : {
                responsive: true,
                maintainAspectRatio: false
            }
        }
    },
}
</script>