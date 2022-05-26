<template>
  <div>
    <b-container class="bv-example-row mt-3">
      <b-row>
        <b-col>
          <h3 class="underline-steelblue">
            <b-icon icon="house"></b-icon>랭킹
          </h3>
        </b-col>
      </b-row>
      <bar-chart :lists="house_list"></bar-chart>
      <!-- <chart-view></chart-view> -->
      <b-row>
        <b-col>
          <b-table-simple hover>
            <b-thead head-variant="dark">
              <b-tr>
                <!-- <b-th>글번호</b-th> -->
                <b-th>제목</b-th>
                <b-th>조회수</b-th>

                <!-- <b-th>작성자</b-th> -->
                <!-- <b-th>작성일</b-th> -->
              </b-tr>
            </b-thead>
            <b-tbody>
              <b-tr
                v-for="list in house_list"
                :key="list.aptCode"
                @click="detail(list.aptCode), plus_hit(list)"
              >
                <!-- <b-td> {{ list.aptCode }} </b-td> -->
                <b-td> {{ list.aptName }} </b-td>
                <b-td> {{ list.hit }} </b-td>
                <!-- <b-td>{{ list.content }}</b-td> -->
                <!-- <b-td> {{ list.writername }} </b-td> -->
                <!-- <b-td> {{ list.createtime }} </b-td> -->
              </b-tr>
            </b-tbody>
          </b-table-simple>
        </b-col>
        <!-- <b-col v-else class="text-center">도서 목록이 없습니다.</b-col> -->
      </b-row>
    </b-container>
  </div>
</template>

<script>
import { mapState } from "vuex";
import { getAll } from "@/api/house.js";
import BarChart from "@/pages/detail/BarChart.vue";
import http from "@/api/http.js";
// import ChartView from "@/pages/detail/ChartView.vue";
const memberStore = "memberStore";
export default {
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  components: {
    BarChart,
    // ChartView,
  },
  data() {
    return {
      house_list: [],
    };
  },

  created() {
    getAll(({ data }) => {
      console.log(data);
      this.house_list = data;
    });
    // count(({ data }) => {
    //   console.log(data);
    // });
  },
  methods: {
    detail(aptCode) {
      // this.INIT;
      this.$router.push({
        name: "DashboardDetail",
        params: { aptCode: aptCode },
      });
    },
    plus_hit(info) {
      console.log("hit: " + info.hit); // this.lists[num].hit= this.list[num].hit+1;
      http
        .put(`/map/hit/${info.aptCode}`, {
          aptCode: info.aptCode,
          hit: info.hit + 1,
        })
        .then(({ data }) => {
          console.log("hit: " + data);
          // console.log("hi");
        });
    },
  },
};
</script>

<style></style>
