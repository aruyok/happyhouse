<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>글보기</h3></b-alert>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col align="left" class="text-left">
        <b-button class="mr-2" variant="outline-primary" @click="listArticle"
          >목록</b-button
        >
      </b-col>
      <b-col align="right" class="text-right">
        <b-button variant="outline-info" class="mr-2" @click="modifylist"
          >글수정</b-button
        >
        <b-button variant="outline-danger" @click="deletelist">글삭제</b-button>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col>
        <b-card
          :header-html="
            `<h3>${list.num}.
          ${list.title} [${list.hit}]</h3><div><h6>${list.writername}</div><div>${list.createtime}</h6></div>`
          "
          class="mb-2"
          border-variant="dark"
          no-body
        >
          <b-card-body class="text-left">
            <div v-html="message"></div>
          </b-card-body>
        </b-card>
      </b-col>
    </b-row>
    <!-- <comment-view :num="list.num"></comment-view> -->
  </b-container>
</template>

<script>
import http from "@/api/http.js";
// import CommentView from "@/components/comment/CommentView.vue";
export default {
  components: {
    // CommentView,
  },
  data() {
    return {
      list: {},
    };
  },
  created() {
    // console.log("hi");
    http.get(`${this.$route.path}`).then(({ data }) => {
      // console.log(data);
      console.log("data: " + data);
      this.list = data;
    });
  },
  methods: {
    listArticle() {
      this.$router.push({ name: `DashboardNotice` });
    },
    deletelist() {
      if (confirm("정말로 삭제하시겠습니까?")) {
        this.$router.replace({
          name: "delete",
          params: { num: this.list.num },
        });
      }
    },
    modifylist() {
      this.$router.push({
        name: "modify",
      });
    },
  },

  computed: {
    message() {
      if (this.list.content) return this.list.content.split("\n").join("<br>");
      return "";
    },
  },
};
</script>

<style></style>
