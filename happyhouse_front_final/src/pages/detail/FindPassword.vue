<template>
  <div>
    <b-container class="bv-example-row mt-3">
      <b-row>
        <b-col>
          <b-alert variant="secondary" show><h3>비밀번호찾기</h3></b-alert>
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
              <b-form-group label="아이디:" label-for="userid">
                <b-form-input
                  id="userid"
                  required
                  v-model="userid"
                  placeholder="아이디 입력...."
                ></b-form-input>
              </b-form-group>
              <b-form-group label="이름:" label-for="username">
                <b-form-input
                  type="text"
                  id="username"
                  v-model="username"
                  required
                  placeholder="이름 입력..."
                ></b-form-input>
              </b-form-group>
              <b-form-group label="이메일:" label-for="email">
                <b-form-input
                  type="email"
                  id="email"
                  v-model="email"
                  required
                  placeholder="이메일 입력..."
                ></b-form-input>
              </b-form-group>

              <!-- <b-button
                type="button"
                variant="danger"
                class="m-1"
                @click="find_password"
                >비밀번호찾기</b-button
              > -->
              <!-- <div>
                <b-button
                  v-b-modal.modal-1
                  variant="danger"
                  class="m-1"
                  @click="find_password"
                  >비밀번호찾기</b-button
                >

                <b-modal id="modal-1" title="비밀번호">
                  <p class="my-4" v-if="password">
                    회원님의 비밀번호는 : {{ password }} 입니다.
                  </p>
                  <p class="my-4" v-else>
                    잘못된 정보입니다. 다시 확인해주세요.
                  </p>
                </b-modal>
              </div> -->

              <b-button
                id="show-btn"
                variant="danger"
                class="m-1"
                @click="$bvModal.show('bv-modal-example'), find_password()"
                >비밀번호찾기</b-button
              >

              <b-modal id="bv-modal-example" hide-footer>
                <template #modal-title>
                  비밀번호확인
                </template>
                <div class="d-block text-center">
                  <h3 v-if="password">
                    회원님의 비밀번호는 : {{ password }} 입니다.
                  </h3>
                  <h3 v-else>잘못된 정보입니다. 다시 확인해주세요.</h3>
                </div>
                <b-button
                  v-if="password"
                  class="mt-3"
                  block
                  @click="$bvModal.hide('bv-modal-example'), goback(1)"
                  >Close</b-button
                >
                <b-button
                  v-else
                  class="mt-3"
                  block
                  @click="$bvModal.hide('bv-modal-example')"
                  >Close</b-button
                >
              </b-modal>
            </b-form>
          </b-card>
        </b-col>
        <b-col></b-col>
      </b-row>
    </b-container>
    <!-- <b-modal v-if="password" id="modal-1" title="BootstrapVue">
      <p class="my-4">Hello from modal!</p>
    </b-modal> -->
  </div>
</template>

<script>
import { find_password } from "@/api/member.js";
export default {
  data() {
    return {
      userid: "",
      username: "",
      email: "",
      password: null,
    };
  },
  methods: {
    find_password() {
      const params = {
        userid: this.userid,
        username: this.username,
        email: this.email,
      };

      find_password(params, ({ data }) => {
        console.log(data);
        this.password = data;
        this.userid = "";
        this.username = "";
        this.email = "";

        // this.$router.push({ name: "resultmodal" });
      });
    },
    goback(num) {
      if (num == 1) {
        this.$router.push({ name: "login" });
      } else {
        this.$router.push({ name: "findpassword" });
      }
    },
  },
};
</script>

<style></style>
