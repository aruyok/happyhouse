<template>
  <b-table-simple hover responsive>
    <b-thead head-variant="dark">
      <b-tr>
        <b-th>작성자</b-th>
        <b-th>제목</b-th>
        <b-th></b-th>

        <!-- <b-th>작성자</b-th> -->
        <!-- <b-th>작성일</b-th> -->
      </b-tr>
    </b-thead>
    <b-tbody>
      <b-tr v-for="info in house_comment" :key="info.num">
        <b-td><b-avatar variant="danger"></b-avatar>{{ info.username }}</b-td>
        <b-td @click="reply()">{{ info.comment }}</b-td>
        <b-td v-if="!show && number == info.board_num">
          <b-form-input v-model="info.comment"></b-form-input
        ></b-td>
        <!-- <b-td v-else>
          <b-form-input
            v-model="user_comment"
            placeholder="댓글을 입력해주세요!"
          ></b-form-input>
        </b-td> -->
        <b-td> {{ info.createtime }}</b-td>

        <div v-if="info.userid === userInfo.userid || role == 1">
          <b-button
            variant="primary"
            @click="trigger(info.board_num, info.comment, info.aptCode)"
            >수정</b-button
          >
          <b-button variant="danger" @click="delete_comment(info.board_num)"
            >삭제</b-button
          >
        </div>
      </b-tr>
      <!-- <reply-view v-if="info.board.num"></reply-view> -->
      <!-- <div v-if="number === info.board_num">
        <div>
          <b-form-input
            v-model="user_comment"
            placeholder="댓글을 입력해주세요!"
          ></b-form-input>
        </div>
        <div v-if="number === info.board_num">
          <div>└ re:<b-avatar variant="danger"></b-avatar>김싸피</div>
          <div>
            rrrrㅇㅇㅇㅇㅇㅇㅇㅇ ㄹㅇㅇㄹㅇㅇㄹㅇㄹㅇㄹㅇㄹㅇㅇfdfdafsfdfff
          </div>
        </div>
      </div> -->
    </b-tbody>
  </b-table-simple>
</template>

<script>
import { delete_comment } from "../../api/housecomment";
import http from "../../api/http";
// import ReplyView from "../../pages/detail/ReplyVIew.vue";
export default {
  components: {
    // ReplyView,
  },
  data() {
    return {
      show: true,
      number: 0,
      click_num: 0,
    };
  },
  props: {
    house_comment: Array,
    userInfo: Object,
    aptCode: Number,
    role: String,
  },
  methods: {
    async delete_comment(num) {
      console.log("들어왔다!");
      //   setTimeout(() => {
      delete_comment(num, ({ data }) => {
        console.log(data);
        this.house_comment.pop();
        //   this.house_comment.slice(1);
        // this.$router.go();
      });
      //   }, 10);
    },

    // async modify_comment(num) {
    //   const params = {
    //     board_num: num,
    //     comment: this.info.comment,
    //   };
    //   modify_comment(params, ({ data }) => {
    //     console.log(data);
    //   });
    // },
    trigger(num, comment) {
      console.log(this.show);

      this.show = !this.show;
      this.number = num;
      this.click_num++;
      // const params = { comment: comment };
      console.log("num:" + num);
      if (this.click_num == 2) {
        console.log("ㅎㅇ");
        http
          .put(`/h_comment/modify/${num}`, {
            board_num: num,
            comment: comment,
          })
          .then(({ data }) => {
            console.log("ㅎㅇ2");
            console.log(data);
          });
      }
      //   this.num = this.userInfo.board_num;
    },
  },
};
</script>

<style></style>
