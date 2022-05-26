<template>
  <div>
    <b-container class="mt-4" v-if="userInfo">
      <b-row>
        <b-col>
          <b-alert variant="secondary" show class="text-center"
            ><h3>내정보</h3></b-alert
          >
          <img src="@/assets/goback.png" @click="goback()" />
        </b-col>
      </b-row>
      <b-row>
        <b-col></b-col>
        <b-col cols="8">
          <b-jumbotron>
            <template #header>My Page</template>

            <template #lead> 내 정보 확인페이지입니다. </template>

            <hr class="my-4" />

            <b-container class="mt-4">
              <b-row>
                <b-col cols="2"></b-col>
                <b-col cols="2" align-self="end">아이디</b-col
                ><b-col cols="4" align-self="start">{{
                  userInfo.userid
                }}</b-col>
                <b-col cols="2"></b-col>
              </b-row>
              <b-row>
                <b-col cols="2"></b-col>
                <b-col cols="2" align-self="end">이름</b-col
                ><b-col cols="4" align-self="start">{{
                  userInfo.username
                }}</b-col>
                <b-col cols="2"></b-col>
              </b-row>
              <b-row>
                <b-col cols="2"></b-col>
                <b-col cols="2" align-self="end">이메일</b-col
                ><b-col cols="4" align-self="start">{{ userInfo.email }}</b-col>
                <b-col cols="2"></b-col>
              </b-row>
              <b-row>
                <b-col cols="2"></b-col>
                <b-col cols="2" align-self="end">가입일</b-col
                ><b-col cols="4" align-self="start">{{
                  userInfo.joinDate
                }}</b-col>
                <b-col cols="2"></b-col>
              </b-row>
            </b-container>

            <hr class="my-4" />

            <b-button variant="primary" href="#" class="mr-1" @click="modify"
              >정보수정</b-button
            >

            <b-button variant="danger" href="#" @click="delete_userid()"
              >회원탈퇴</b-button
            >
          </b-jumbotron>
        </b-col>
        <b-col></b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
import { delete_user } from "@/api/member.js";
import { mapState, mapMutations } from "vuex";

const memberStore = "memberStore";

export default {
  name: "MemberMyPage",

  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),

    delete_userid() {
      delete_user(this.userInfo.userid, ({ data }) => {
        // console.log(this.userInfo.userid);
        console.log("delete:" + data);
        // this.$router.push("/");

        this.SET_IS_LOGIN(false);
        this.SET_USER_INFO(null);
        sessionStorage.removeItem("access-token");

        this.$router.push({ name: "DashboardHome" });
      });
    },
    modify() {
      this.$router.push("/modify");
    },
    goback() {
      this.$router.push({ name: "DashboardHome" });
    },
  },
};
</script>

<style>
img {
  width: 85px;
}
</style>
