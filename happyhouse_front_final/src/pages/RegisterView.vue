<template>
  <div>
    <b-container class="bv-example-row mt-3">
      <b-row>
        <b-col>
          <b-alert variant="secondary" show><h3>회원가입</h3></b-alert>
        </b-col>
      </b-row>
      <b-row>
        <b-col></b-col>
        <b-col cols="8">
          <b-card
            class="text-center mt-3"
            style="max-width: 40rem"
            align="left"
          >
            <b-form class="text-left">
              <b-alert show variant="danger">아이디가 이미 존재합니다.</b-alert>
              <b-form-group label="아이디:" label-for="userid">
                <b-form-input
                  id="userid"
                  v-model="userid"
                  required
                  placeholder="아이디 입력...."
                ></b-form-input>
              </b-form-group>
              <b-form-group label="비밀번호:" label-for="userpwd">
                <b-form-input
                  type="password"
                  id="userpwd"
                  v-model="userpwd"
                  required
                  placeholder="비밀번호 입력...."
                ></b-form-input>
              </b-form-group>
              <b-form-group label="이름:" label-for="username">
                <b-form-input
                  type="text"
                  id="username"
                  required
                  v-model="username"
                  placeholder="이름 입력...."
                ></b-form-input>
              </b-form-group>
              <b-form-group label="이메일:" label-for="email">
                <b-form-input
                  type="email"
                  v-model="email"
                  id="email"
                  required
                  placeholder="이메일 입력...."
                ></b-form-input>
              </b-form-group>

              <b-button
                type="button"
                variant="success"
                class="m-1"
                @click="movePage"
                >Join</b-button
              >
            </b-form>
          </b-card>
        </b-col>
        <b-col></b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script>
import { regist } from "@/api/member.js";
export default {
  data() {
    return {
      userid: "",
      userpwd: "",
      email: "",
      username: "",
    };
  },

  methods: {
    movePage() {
      const params = {
        userid: this.userid,
        userpwd: this.userpwd,
        email: this.email,
        username: this.username,
      };
      regist(params, ({ data }) => {
        console.log(data);
        this.$router.push({ name: "login" });
      });
    },
  },
};
</script>

<style></style>
