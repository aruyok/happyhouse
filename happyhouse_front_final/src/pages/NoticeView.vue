<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show class="text-center"><h3>공지사항</h3></b-alert>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-right">
        <b-button
          variant="outline-primary"
          @click="moveWrite()"
          v-if="role == 1"
          >글쓰기</b-button
        >
      </b-col>
    </b-row>
    <b-row>
      <b-col v-if="lists.length">
        <b-table-simple hover responsive>
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
            <b-tr v-for="list in lists" :key="list.num">
              <b-td @click="ViewList(list.num), plus_hit(list)">
                {{ list.num }}
              </b-td>
              <b-td @click="ViewList(list.num), plus_hit(list)">
                {{ list.title }}
              </b-td>
              <b-td @click="ViewList(list.num), plus_hit(list)">
                {{ list.hit }}
              </b-td>
              <!-- <b-td>{{ list.content }}</b-td> -->
              <!-- <b-td> {{ list.writername }} </b-td> -->
              <!-- <b-td> {{ list.createtime }} </b-td> -->
              <div>
                <b-button
                  variant="danger"
                  @click="delete_comment(list.num)"
                  v-if="role == 1"
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
import http from "@/api/http.js";
import { getLogin } from "@/api/member.js";
import { mapState } from "vuex";
const memberStore = "memberStore";
export default {
  data() {
    return {
      lists: [],
      role: "",
    };
  },

  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },

  created() {
    http.get(`/board/list`).then(({ data }) => {
      // console.log(data);
      this.lists = data;
    });
    // console.log("gd");
    // console.log(this.userInfo);
    getLogin(this.userInfo.userid, ({ data }) => {
      console.log(data);
      this.role = data.role;
    });
  },
  methods: {
    ViewList(num) {
      // console.log(num);
      this.$router.push({
        name: "detail",
        params: { num: num },
      });
    },
    moveWrite() {
      this.$router.push({
        name: "regist",
      });
    },
    plus_hit(list) {
      // console.log("hit: " + list.hit); // this.lists[num].hit= this.list[num].hit+1;
      http
        .put(`/board/hit/${list.num}`, {
          num: list.num,
          hit: list.hit + 1,
        })
        .then(({ data }) => {
          console.log(data);
          // console.log("hi");
        });
    },
    delete_comment(num) {
      // const params = { num: num }
      http.delete(`/board/delete/${num}`, { num: num }).then(({ data }) => {
        console.log(data);
        this.$router.go();
      });
    },
  },
};
</script>

<style></style>
