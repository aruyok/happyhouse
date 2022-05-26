<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show class="text-center"><h3>News</h3></b-alert>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-right"> </b-col>
    </b-row>
    <b-row>
      <b-col v-if="lists.length">
        <b-table-simple hover responsive>
          <b-thead head-variant="dark">
            <b-tr>
              <b-th>제목</b-th>
              <!-- <b-th>링크</b-th> -->
              <!-- <b-th>내용</b-th> -->
              <b-th>등록일자</b-th>
              <!-- <b-th>작성자</b-th> -->
              <!-- <b-th>작성일</b-th> -->
            </b-tr>
          </b-thead>
          <b-tbody>
            <b-tr
              v-for="list in lists"
              :key="list.num"
              @click="ViewList(list.num), plus_hit(list)"
            >
              <b-td>
                <a :href="list.originallink">{{ list.title }} </a></b-td
              >
              <!-- <b-td> {{ list.link }} </b-td> -->
              <!-- <b-td> {{ list.description }} </b-td> -->
              <b-td> {{ list.pubDate }}</b-td>
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
</template>

<script>
// import Chart from "chart.js";
import http from "@/api/http.js";
export default {
  data() {
    return {
      title: "",
      link: "",
      description: "",
      pubDate: "",
      originallink: "",
      lists: [],
    };
  },
  name: "DashboardHome",
  created() {
    http.get("/api/news").then(({ data }) => {
      console.log("크롤링데이터: " + data);
      this.lists = data.items;
      this.originallink = data.items.originallink;
    });
  },
};
</script>

<style scoped>
a {
  text-decoration: none;
  color: inherit;
}
</style>
