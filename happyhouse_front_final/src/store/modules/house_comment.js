import { comment_get } from "@/api/housecomment.js";

const h_commentStore = {
  namespaced: true,
  state: {
    house_comment: [],
  },
  mutations: {
    GET_COMMENT(state, data) {
      state.house_comment = data;
    },
  },
  actions: {
    async get_Comment({ commit }, comment) {
      setTimeout(() => {
        comment_get(comment, ({ data }) => {
          console.log("comment: " + comment);
          console.log("actions: " + data);
          commit("GET_COMMENT", data);
        });
      }, 500);
    },
  },
};

export default h_commentStore;
