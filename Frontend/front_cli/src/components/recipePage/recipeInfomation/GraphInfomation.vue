<template>
    <div>
        <Doughnut :data="data" :options="options" />
    </div>
</template>

<script>
import { Chart as ChartJS, ArcElement, Tooltip, Legend } from 'chart.js'
import { Doughnut } from 'vue-chartjs'
// import * as chartConfig from './Graph/graph'

ChartJS.register(ArcElement, Tooltip, Legend)



export default {
    name: 'GraphInfomation',
    components: { Doughnut, },
    props: {
        similarity : Number
    },
    data() {
        return /*chartConfig*/ {
            data : {
                labels: ['Differ', 'Similarity', ],
                datasets: [
                {
                    backgroundColor: ['#EEEEEE', '#FD7E14'],
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

<style>

</style>