import Vue from "vue";
import Vuex from "vuex";
import http from "@/api/http";
import { houseList } from "@/api/house.js";
Vue.use(Vuex);

const houseStore = {
  namespaced: true,
  state: {
    sideBarOpen: false,
    info_list: [],
    houses: [],
  },
  getters: {
    sideBarOpen: (state) => {
      return state.sideBarOpen;
    },
  },
  mutations: {
    toggleSidebar(state) {
      state.sideBarOpen = !state.sideBarOpen;
    },
    GET_INFO(state, info) {
      console.log("mutations: " + info);
      state.info_list = info;
    },
    INIT(state) {
      state.info_list = [];
    },
    SET_HOUSE_LIST(state, houses) {
      state.houses = houses;
    },
  },
  actions: {
    toggleSidebar(context) {
      context.commit("toggleSidebar");
    },
    get_Info({ commit }, payload) {
      console.log("payload: " + payload);
      if (payload.toString().length == 10) {
        // const params = { dong: payload };
        http.get(`map/apt/${payload}`).then(({ data }) => {
          console.log("actions: " + data);
          commit("GET_INFO", data);
        });
      }
      // else if (payload.toString().length == 5) {
      //   http.get(`map/s_gugun/${payload}`).then(({ data }) => {
      //     // console.log("actions: " + data);
      //     commit("GET_INFO", data);
      //   });}
      else {
        http.get(`map/s_sido/${payload}`).then(({ data }) => {
          // console.log("actions: " + data);
          commit("GET_INFO", data);
        });
      }
    },
    getHouseList: ({ commit }, gugunCode) => {
      const SERVICE_KEY = process.env.VUE_APP_APT_DEAL_API_KEY;
      console.log("gugunCode: " + gugunCode);
      const params = {
        LAWD_CD: gugunCode,
        DEAL_YMD: "202110",
        serviceKey: decodeURIComponent(
          "hMlb8qO%2FdgkfNg5%2BiWHftA4WzS0Jj0dSbzyx5o4A3%2BM59BudkGG0DnEPK9aCbbay%2BrHppszUbsesl6hRLZ8N6Q%3D%3D"
        ),
      };
      houseList(
        params,
        (response) => {
          console.log("actions2: " + response);
          console.log(SERVICE_KEY);
          commit("SET_HOUSE_LIST", response.data.response.body.items.item);
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};

export default houseStore;
