<template>
  <div>
    <b-container v-if="apt_detail" class="w-50 bv-example-row">
      <b-container class="bv-example-row">
        <b-row>
          <b-col
            ><h3>
              {{ apt_detail.aptName }}
            </h3>
          </b-col>
          <b-col>
            <b-form-checkbox
              size="lg"
              v-model="YN"
              true-value="Y"
              false-value="N"
              @change="toggle()"
              >즐겨찾기</b-form-checkbox
            >
          </b-col>
        </b-row>
      </b-container>

      <b-row class="mb-2 mt-1">
        <b-col
          ><b-img :src="require('@/assets/apt.png')" fluid-grow></b-img>
        </b-col>
      </b-row>
      <b-row>
        <b-col>
          <b-alert show variant="secondary"
            >아파트 이름 : {{ apt_detail.aptName }}</b-alert
          >
        </b-col>
      </b-row>
      <b-row>
        <b-col>
          <b-alert show variant="secondary"
            >주소 : {{ apt_detail.sidoName }} {{ apt_detail.gugunName }}
            {{ apt_detail.dongName }} {{ apt_detail.jibun }}</b-alert
          >
        </b-col>
      </b-row>
      <b-row>
        <b-col>
          <b-alert show variant="secondary"
            >건축연도 : {{ apt_detail.buildYear }}</b-alert
          >
        </b-col>
      </b-row>
      <b-row>
        <b-col>
          <b-alert show variant="secondary"
            >최근가격 : {{ apt_detail.recentPrice }}원</b-alert
          >
        </b-col>
      </b-row>

      <!-- <b-row>
        <b-col>
          <b-alert show variant="primary"
            >아파트 이름 : {{ house.아파트 }}
          </b-alert>
        </b-col>
      </b-row>
      <b-row>
        <b-col>
          <b-alert show variant="info">법정동 : {{ house.법정동 }} </b-alert>
        </b-col>
      </b-row>
      <b-row>
        <b-col>
          <b-alert show variant="warning">층수 : {{ house.층 }}층</b-alert>
        </b-col>
      </b-row>
      <b-row>
        <b-col>
          <b-alert show variant="danger"
            >거래금액 :
            {{
              (parseInt(house.거래금액.replace(",", "")) * 10000) | price
            }}원</b-alert
          >
        </b-col>
      </b-row> -->
    </b-container>

    <!-- -------------------------------------------------------------------------- -->
    <!-- <div>
      <b-form-checkbox
        size="lg"
        v-model="YN"
        true-value="Y"
        false-value="N"
        @change="toggle()"
        >즐겨찾기</b-form-checkbox
      >
    </div> -->

    <div align="left">
      <b-input-group prepend="" class="mt-3">
        <b-avatar variant="danger"></b-avatar>
        <b-form-input
          v-model="user_comment"
          placeholder="댓글을 입력해주세요!"
          @keypress.enter="upload"
        ></b-form-input>
        <b-input-group-append>
          <b-button variant="outline-success" @click="upload(), reload()"
            >댓글달기</b-button
          >
        </b-input-group-append>
      </b-input-group>
    </div>
    <br />
    <house-comment-view
      :house_comment="house_comment"
      :userInfo="userInfo"
      :key="reload_num"
      :aptCode="apt_detail.aptCode"
      :role="role"
    ></house-comment-view>
    <!-- 이렇게 id와 for에 같은 값을 할당하여 서로 연결할 수 있다. -->
  </div>
</template>

<script>
import { post_favorite } from "@/api/favorite.js";
import { mapState, mapActions } from "vuex";
import { comment_regist } from "../../api/housecomment";
import http from "@/api/http.js";
import HouseCommentView from "../../pages/detail/HouseComment.vue";
import { getLogin } from "@/api/member.js";
// import { post } from "jquery";
const memberStore = "memberStore";
const h_commentStore = "h_commentStore";

export default {
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    ...mapState(h_commentStore, ["house_comment"]),
  },
  components: {
    HouseCommentView,
  },
  data() {
    return {
      user_comment: "",
      apt_detail: {},
      YN: "false",
      slide: 0,
      sliding: null,
      reload_num: 0,
      role: "",
    };
  },
  created() {
    console.log("create 들어왔다!");
    http.get(`map/${this.$route.params.aptCode}`).then(({ data }) => {
      console.log("apt_detail: " + JSON.stringify(data));
      this.apt_detail = data;
    });
    setTimeout(() => {
      this.get_Comment(this.apt_detail.aptCode);
    }, 500);

    // console.log("hi");
    // console.log(this.userInfo);
    getLogin(this.userInfo.userid, ({ data }) => {
      console.log("hi");
      console.log(data);
      this.role = data.role;
    });
  },
  methods: {
    // ...mapMutations(h_commentStore, ["GET_COMMENT"]),
    ...mapActions(h_commentStore, ["get_Comment"]),
    toggle() {
      console.log(this.YN);
      if (this.YN === true) {
        console.log(this.userInfo.userid);
        const params = {
          aptCode: this.apt_detail.aptCode,
          userid: this.userInfo.userid,
        };

        post_favorite(
          params,
          ({ data }) => {
            console.log(data);
          },
          (error) => {
            console.log(error);
          }
        );
      }
    },
    onSlideStart() {
      this.sliding = true;
    },
    onSlideEnd() {
      this.sliding = false;
    },
    upload() {
      const params = {
        username: this.userInfo.username,
        comment: this.user_comment,
        userid: this.userInfo.userid,
        aptCode: this.apt_detail.aptCode,
      };
      comment_regist(
        params,
        ({ data }) => {
          console.log(data);
          this.user_comment = "";
          // this.$router.push(`/dashboard/detail/${this.apt_detail.aptCode}`);
        },
        (error) => {
          console.log(error);
        }
      );
      // console.log(this.apt_detail.aptCode);
      this.get_Comment(this.apt_detail.aptCode);
      console.log("housecomment: " + this.house_comment);
      // console.log("안녕하세요" + this.house_comment[0].comment);
      // comment_get(this.apt_detail.aptCode, ({ data }) => {
      //   console.log(data);
      // });
    },
    reload() {
      this.reload_num++;
    },
    // async delete_comment(num) {
    //   console.log("들어왔다!");
    //   setTimeout(() => {
    //     delete_comment(num, ({ data }) => {
    //       console.log(data);
    //       // this.$router.push(`/dashboard/detail/${this.apt_detail.aptCode}`);
    //       this.house_comment.slice(1);
    //       this.$router.go();
    //     });
    //   }, 10);
    // },
  },
};
</script>

<style lang="postcss">
.food1 img {
  width: 50%;
  height: 230px;
}
</style>
