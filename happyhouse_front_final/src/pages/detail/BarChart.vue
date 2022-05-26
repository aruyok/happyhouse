<template>
  <div>
    <canvas class="mb-2 mt-1" id="planet-chart"></canvas>
  </div>
</template>

<script>
import Chart from "chart.js";
import planetChartData from "../../chart-data.js";
export default {
  name: "PlanetChart",
  props: {
    lists: Array,
  },
  data() {
    return {
      planetChartData: planetChartData,
    };
  },
  mounted() {
    setTimeout(() => {
      const ctx = document.getElementById("planet-chart");
      console.log("list: " + this.lists);
      for (var i = 0; i < 5; i++) {
        this.planetChartData.data.labels[i] = this.lists[i].aptName;
        this.planetChartData.data.datasets[0].data[i] = this.lists[i].hit;
        // console.log(this.planetChartData.data.datasets[0].data);
      }

      new Chart(ctx, this.planetChartData);
      console.log(planetChartData);
    }, 100);
  },
};
</script>
