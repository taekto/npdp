<template>
  <div>
    <h1>{{similarity}}</h1>
  </div>
</template>

<script>
import { Chart as ChartJS, ArcElement, Tooltip, Legend } from 'chart.js'
import { Doughnut } from 'vue-chartjs'

ChartJS.register(ArcElement, Tooltip, Legend)



export default {
    name: 'GraphInfomation',
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
