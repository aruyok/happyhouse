import "../src/global.js";
import { Bar } from "chart.js";

export default {
  extends: Bar,
  props: ["chartData", "options"],
  watch: {
    chartData: {
      handler() {
        this.renderChart(this.chartData, this.options);
      },
      deep: true,
    },
  },
  mounted() {
    this.renderChart(this.chartData, this.options);
  },
};
