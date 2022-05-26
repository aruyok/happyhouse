<template>
  <div class="container">
    <section id="index_section">
      <div class="card col-sm-12 mt-1" style="min-height: 850px">
        <div class="card-body">
          <div class="form-group form-inline justify-content-center">
            <label class="mr-2" for="sido">시도 : </label>
            <select
              class="selectpicker"
              id="sido"
              v-model="select_sido"
              @change="gugun()"
            >
              <option value="" disabled hidden>선택</option>
              <option
                v-for="sido in sido_list"
                :key="sido.sidoCode"
                :value="sido.sidoCode"
              >
                {{ sido.sidoName }}
              </option>
            </select>
            <label class="mr-2 ml-3" for="gugun">구군 : </label>
            <select
              class="selectpicker"
              v-model="select_gugun"
              id="gugun"
              @change="dong()"
            >
              <option value="" disabled hidden>선택</option>
              <option
                v-for="gugun in gugun_list"
                :key="gugun.gugunCode"
                :value="gugun.gugunCode"
              >
                {{ gugun.gugunName }}
              </option>
            </select>
            <label class="mr-2 ml-3" for="dong">읍면동 : </label>
            <select
              class="selectpicker"
              id="dong"
              v-model="select_dong"
              @change="dong()"
            >
              <option value="" disabled hidden>선택</option>
              <option
                v-for="dong in this.dong_list"
                :key="dong.dongCode"
                :value="dong.dongCode"
              >
                {{ dong.dongName }}
              </option>
            </select>
            <label class="mr-2 ml-3" for="dong">원하는 옵션 : </label>
            <select
              v-model="select_wanted"
              class="selectpicker"
              id="interesting"
            >
              <option value="" disabled hidden>선택</option>
              <option value="1">편의점</option>
              <option value="2">은행</option>
              <option value="3">대형마트</option>
              <option value="4">지하철역</option>
              <option value="5">문화시설</option>
              <option value="6">음식점</option>
              <option value="7">카페</option>
              <option value="0">선택 안함</option>
            </select>
            <button
              type="button"
              class="btn btn-light mr-2 ml-3"
              id="search-btn"
              @click="list(), reload()"
            >
              검색
            </button>
            <div><br /></div>
            <map-info
              id="map"
              style="width: 100%; height: 500px"
              :select_wanted="this.select_wanted"
              :info="info_list"
              :key="info_reload"
            ></map-info>
          </div>
          <b-table-simple hover responsive>
            <b-thead head-variant="dark">
              <b-tr>
                <b-th>번호</b-th>
                <b-th>아파트이름</b-th>
                <b-th>주소</b-th>
                <b-th>건축연도</b-th>
                <b-th>최근거래금액</b-th>
                <b-th>조회수</b-th>
              </b-tr>
            </b-thead>
            <b-tbody v-if="info_list">
              <b-tr
                v-for="info in info_list"
                :key="info.aptCode"
                @click="detail(info.aptCode), plus_hit(info)"
              >
                <b-td> {{ info.aptCode }} </b-td>
                <b-td> {{ info.aptName }} </b-td>
                <b-td>
                  {{ info.sidoName }} {{ info.gugunName }} {{ info.dongName }}
                  {{ info.jibun }}</b-td
                >
                <b-td> {{ info.buildYear }}</b-td>
                <b-td> {{ info.recentPrice }}</b-td>
                <b-td> {{ info.hit }}</b-td>
              </b-tr>
            </b-tbody>
            <!-- <b-tbody v-if="houses">
              <b-tr
                v-for="info in houses"
                :key="info.aptCode"
                @click="detail(info.aptCode), plus_hit(info)"
              >
                <b-td> {{ info.일련번호 }} </b-td>
                <b-td> {{ info.아파트 }} </b-td>
                <b-td>
                  {{ info.법정동 }}
                </b-td>
                <b-td> {{ info.층 }}</b-td>
                <b-td> {{ info.거래금액 }}원</b-td>
                <b-td> {{ info.hit }}</b-td>
              </b-tr>
            </b-tbody> -->
          </b-table-simple>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import http from "@/api/http.js";
import MapInfo from "@/pages/map/MapInfo.vue";
import { mapState, mapActions, mapMutations } from "vuex";
const houseStore = "houseStore";
// import MapInfo2 from "@/components/maps/MapInfo2.vue";
// import MapInfo2 from "@/components/maps/MapInfo2.vue";
export default {
  components: {
    MapInfo,
    // MapInfo2,
    // MapInfo2,
  },

  data() {
    return {
      sido_list: [],
      gugun_list: [],
      dong_list: [],
      select_sido: "",
      select_gugun: "",
      select_dong: "",
      times: 0,
      info_reload: 0,
      select_wanted: "",
      // info_list: [],
    };
  },
  computed: {
    // info_list() {
    //   //   console.log("info_list: " + this.$store.state.info_list);
    //   return this.$store.state.info_list;

    // },
    ...mapState(houseStore, ["info_list", "houses"]),
  },
  methods: {
    ...mapActions(houseStore, ["get_Info", "getHouseList"]),
    ...mapMutations(houseStore, ["INIT"]),
    reload() {
      console.log("hi");
      this.info_reload++;
      this.times + 1;
    },
    gugun() {
      // console.log(parseInt(event.target.value));
      console.log(this.select_sido);

      http.get(`/map/gugun/${this.select_sido}`).then(({ data }) => {
        // console.log(data);
        this.gugun_list = data;
      });
    },
    dong() {
      console.log(this.select_gugun);
      http.get(`/map/dong/${this.select_gugun}`).then(({ data }) => {
        console.log(data);
        this.dong_list = data;
      });
    },

    list() {
      if (this.select_dong != "") {
        // http.get(`map/s_dong/${this.select_dong}`).then(({ data }) => {
        //   this.info_list = data;
        // console.log("list 1: " + this.select_dong);
        // this.$store.dispatch("get_Info", this.select_dong);
        this.get_Info(this.select_dong);
      } else if (this.select_gugun != "") {
        // http.get(`map/s_gugun/${this.select_gugun}`).then(({ data }) => {
        //   this.info_list = data;
        // this.$store.dispatch("get_Info", this.select_gugun);
        this.get_Info(this.select_gugun);
        this.getHouseList(this.select_gugun);
      } else {
        // http.get(`map/s_sido/${this.select_sido}`).then(({ data }) => {
        //   this.info_list = data;
        // this.$store.dispatch("get_Info", this.select_sido);
        this.get_Info(this.select_sido);
      }
    },
    detail(aptCode) {
      this.INIT;
      this.$router.push({
        name: "DashboardDetail",
        params: { aptCode: aptCode },
      });
    },
    plus_hit(info) {
      console.log("hit: " + info.hit); // this.lists[num].hit= this.list[num].hit+1;
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
  },
  created() {
    http.get("/map/sido").then(({ data }) => {
      this.sido_list = data;
    });
  },
  destroyed() {
    // this.$store.commit("INIT");
    this.INIT();
  },
};
</script>

<style>
select {
  width: auto;
  margin: auto;
  background-color: lightblue;
}
</style>
