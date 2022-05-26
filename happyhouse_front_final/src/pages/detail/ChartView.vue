<template>
  <div class="bar-box">
    <div class="bar">
      <bar-chart
        ref="barChart"
        :chartData="chart.data"
        :options="chart.options"
        id="chart"
      ></bar-chart>
    </div>
  </div>
</template>

<script>
import BarChart from "../../barChart.js";

export default {
  name: "bar-chart",
  components: { BarChart },
  data() {
    return {
      chart: {
        data: {
          labels: [1, 2, 3, 4, 5, 6, 7, 8],
          datasets: [
            {
              backgroundColor: "pink",
              pointBackgroundColor: "white",
              borderWidth: 1,
              borderColor: "hotpink",
              fill: true,
              tension: 0.1,
              barPercentage: 0.55,
              data: [1200, 2000, 2500, 2200, 4000, 3000, 5000, 2000],
            },
          ],
        },
        options: {
          plugins: {
            legend: {
              display: false,
            },
            datalabels: {
              display: false,
            },
            tooltip: {
              boxWidth: 15,
            },
          },
          scales: {
            x: {
              grid: {
                display: false,
              },
              ticks: {
                padding: 3,
              },
            },
            y: {
              grid: {
                drawBorder: false,
                color: "black",
                lineWidth: 1,
              },
              min: 0,
              max: 5000,
              ticks: {
                stepSize: 1000,
                padding: 10,
                callback: (val, index) => {
                  return val !== 0
                    ? val.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",")
                    : "";
                },
              },
            },
          },
          responsive: true,
          maintainAspectRatio: false,
          animation: {
            duration: 5000,
          },
        },
      },
    };
  },
};
</script>

<style></style>
