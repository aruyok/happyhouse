<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>글작성</h3></b-alert>
      </b-col>
    </b-row>

    <b-row class="mb-1">
      <b-col style="text-align: left">
        <b-form>
          <b-form-group
            id="subject-group"
            label="제목:"
            label-for="subject"
            description="제목을 입력하세요."
          >
            <b-form-input
              id="subject"
              type="text"
              required
              v-model="title"
              placeholder="제목 입력..."
            ></b-form-input>
          </b-form-group>

          <b-form-group id="content-group" label="내용:" label-for="content">
            <b-form-textarea
              id="content"
              placeholder="내용 입력..."
              rows="10"
              v-model="content"
              max-rows="15"
            ></b-form-textarea>
          </b-form-group>

          <b-button
            variant="primary"
            class="m-1"
            @click="regist(title, content)"
            >글작성</b-button
          >
        </b-form>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import http from "@/api/http.js";
export default {
  data() {
    return {
      title: "",
      content: "",
    };
  },
  methods: {
    regist(title, content) {
      const params = { title: title, content: content };
      http.post("/board/writeBoard", params).then(({ data }) => {
        console.log(data);
        this.$router.push({ name: "DashboardNotice" });
      });
    },
  },
};
</script>

<style></style>
