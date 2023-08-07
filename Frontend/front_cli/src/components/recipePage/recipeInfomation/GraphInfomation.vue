<template>
    <!-- 도넛 그래프 -->
    <div>
        <Doughnut :data="data" :options="options" />
    </div>
</template>

<script>
import { Chart as ChartJS, ArcElement, Tooltip, Legend } from 'chart.js'
import { Doughnut } from 'vue-chartjs'

ChartJS.register(ArcElement, Tooltip, Legend)

export default {
    name: 'GraphInfomation',
    components: { Doughnut, },
    // 유사도를 받아옴
    props: {
        similarity : Number
    },
    data() {
        return {
            data : {
                labels: ['Differ', 'Similarity', ],
                datasets: [
                {
                    backgroundColor: ['#EEEEEE', '#FD7E14'],
                    // 유사도를 통해 그래프 값을 변경
                    data: [100-this.$props.similarity, this.$props.similarity]
                }
                ]
            },
            options : {
                responsive: true,
                maintainAspectRatio: false
            }
        }
    }
}
</script>