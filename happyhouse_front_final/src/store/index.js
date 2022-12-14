import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex);

import houseStore from "@/store/modules/houseStore.js";
import memberStore from "@/store/modules/memberStore.js";
import h_commentStore from "@/store/modules/house_comment.js";

const store = new Vuex.Store({
  modules: {
    houseStore,
    memberStore,
    h_commentStore,
  },
  plugins: [
    createPersistedState({
      // 브라우저 종료시 제거하기 위해 localStorage가 아닌 sessionStorage로 변경. (default: localStorage)
      storage: sessionStorage,
    }),
  ],
});

export default store;
