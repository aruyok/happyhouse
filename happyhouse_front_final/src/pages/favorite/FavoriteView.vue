<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show class="text-center"><h3>즐겨찾기 목록</h3></b-alert>
      </b-col>
    </b-row>

    <b-row>
      <b-col>
        <b-table-simple hover>
          <b-thead head-variant="dark">
            <b-tr>
              <b-th>글번호</b-th>
              <b-th>제목</b-th>
              <b-th>조회수</b-th>

              <!-- <b-th>작성자</b-th> -->
              <!-- <b-th>작성일</b-th> -->
            </b-tr>
          </b-thead>
          <b-tbody>
            <b-tr v-for="list in user_favorite" :key="list.aptCode">
              <b-td @click="detail(list.aptCode), plus_hit(list)">
                {{ list.aptCode }}
              </b-td>
              <b-td @click="detail(list.aptCode), plus_hit(list)">
                {{ list.aptName }}
              </b-td>
              <b-td @click="detail(list.aptCode), plus_hit(list)">
                {{ list.hit }}
              </b-td>
              <!-- <b-td>{{ list.content }}</b-td> -->
              <!-- <b-td> {{ list.writername }} </b-td> -->
              <!-- <b-td> {{ list.createtime }} </b-td> -->
              <div>
                <b-button
                  variant="danger"
                  @click="delete_favo(list.aptCode, userInfo.userid)"
                  >삭제</b-button
                >
              </div>
            </b-tr>
          </b-tbody>
        </b-table-simple>
      </b-col>
      <!-- <b-col v-else class="text-center">도서 목록이 없습니다.</b-col> -->
    </b-row>
  </b-container>
</template>

<script>
import { get_favorite } from "@/api/favorite.js";
import { mapState } from "vuex";
import http from "@/api/http.js";
const memberStore = "memberStore";
import { delete_like } from "@/api/favorite.js";
export default {
  data() {
    return {
      user_favorite: [],
    };
  },

  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },

  created() {
    // console.log("안녕하세요");
    get_favorite(this.userInfo.userid, ({ data }) => {
      console.log(JSON.stringify(data));
      this.user_favorite = data;
    });
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
      // console.log("hit: " + info.hit); // this.lists[num].hit= this.list[num].hit+1;
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
    delete_favo(aptCode, userid) {
      console.log("안녕하세요");
      console.log(aptCode + ":" + userid);
      delete_like(aptCode, userid, ({ data }) => {
        console.log(data);
        this.user_favorite.pop();
      });
    },
  },
};
</script>
