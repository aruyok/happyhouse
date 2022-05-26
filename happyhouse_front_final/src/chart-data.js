export const planetChartData = {
  type: "bar",
  data: {
    labels: ["1", "2", "3", "4", "5"],
    datasets: [
      //   {
      //     label: "Number of Moons",
      //     data: [0, 0, 1, 2, 79, 82, 27, 14],
      //     backgroundColor: "rgba(54,73,93,.5)",
      //     borderColor: "#36495d",
      //     borderWidth: 3,
      //   },
      {
        label: "사람들이 많이 찾아본 매물",
        data: [0, 0, 0, 0, 0],
        backgroundColor: "rgba(71, 183,132,.5)",
        borderColor: "#47b784",
        borderWidth: 3,
      },
    ],
  },
  options: {
    responsive: true,
    lineTension: 1,
    scales: {
      yAxes: [
        {
          ticks: {
            beginAtZero: true,
            padding: 25,
          },
        },
      ],
    },
  },
};

export default planetChartData;
