import Vue from "vue";
import VueRouter from "vue-router";
// import MainView from "@/pages/Home.vue";
import Dashboard from "@/components/Dashboard";
import DashboardHome from "@/pages/Home";
import store from "@/store/index.js";
// import LoginView from "@/components/users/LoginView.vue";
// import UserInfo from "@/views/UserInfo.vue";
// import BoardView from "@/views/BoardView.vue";
// import BoardList from "@/components/board/BoardList.vue";
Vue.use(VueRouter);
const onlyAuthUser = async (to, from, next) => {
  // console.log(store);
  const checkUserInfo = store.getters["memberStore/checkUserInfo"];
  const getUserInfo = store._actions["memberStore/getUserInfo"];
  let token = sessionStorage.getItem("access-token");
  if (checkUserInfo == null && token) {
    await getUserInfo(token);
  }
  if (checkUserInfo === null) {
    alert("로그인이 필요한 페이지입니다..");
    next({ name: "login" });
    // router.push({ name: "signIn" });
  } else {
    // console.log("로그인 했다.");
    next();
  }
};

const routes = [
  { path: "/", name: DashboardHome, redirect: { name: "DashboardHome" } },
  {
    path: "/dashboard",
    component: Dashboard,
    children: [
      { path: "/", redirect: { name: "DashboardHome" } },
      { path: "home", name: "DashboardHome", component: DashboardHome },
      {
        path: "map",
        name: "DashboardMap",
        beforeEnter: onlyAuthUser,
        component: () => import("@/pages/SelectBox"),
      },
      {
        path: "detail/:aptCode",
        name: "DashboardDetail",
        component: () => import("@/pages/detail/ApartDetail.vue"),
      },
      {
        path: "favorite",
        name: "DashboardFavorite",
        beforeEnter: onlyAuthUser,
        component: () => import("@/pages/favorite/FavoriteView.vue"),
      },
      {
        path: "notice",
        name: "DashboardNotice",
        component: () => import("@/pages/NoticeView.vue"),
      },
      {
        path: "rank",
        name: "rank",
        beforeEnter: onlyAuthUser,
        component: () => import("@/pages/RankView.vue"),
      },
    ],
  },
  {
    path: "/login",
    name: "login",
    component: () => import("@/pages/LoginView.vue"),
  },
  {
    path: "/signup",
    name: "signup",
    component: () => import("@/pages/RegisterView.vue"),
  },
  {
    path: "/mypage",
    name: "mypage",
    component: () => import("@/pages/MemberMyPage.vue"),
  },
  {
    path: "/modify",
    name: "modify",
    component: () => import("@/pages/MemberModify.vue"),
  },
  {
    path: "/board/boarddetail/:num",
    name: "detail",
    component: () => import("@/pages/detail/BoardDetail.vue"),
  },
  {
    path: "/password",
    name: "findpassword",
    component: () => import("@/pages/detail/FindPassword.vue"),
  },
  {
    path: "/resultmodal",
    name: "resultmodal",
    component: () => import("@/pages/detail/ResultModal.vue"),
  },
  {
    path: "/regist",
    name: "regist",
    component: () => import("@/pages/BoardRegist.vue"),
  },

  // {
  //   path: "/map",
  //   name: "map",
  //   component: () => import("@/pages/SelectBox.vue"),
  // },

  // {
  //   path: "/user",
  //   name: "user",
  //   component: UserInfo,
  //   // redirect: "/user",
  //   children: [
  //     {
  //       path: "login",
  //       name: "login",
  //       component: LoginView,
  //     },
  //   ],
  // },
  // {
  //   path: "/board",
  //   name: "board",
  //   component: BoardView,
  //   redirect: "/board/list",
  //   children: [
  //     {
  //       path: "list",
  //       name: "list",
  //       component: BoardList,
  //     },
  //     {
  //       path: "detail/:num",
  //       name: "detail",
  //       component: () => import("@/components/board/BoardDetail.vue"),
  //     },
  //     {
  //       path: "regist",
  //       name: "regist",
  //       component: () => import("@/components/board/BoardRegist_Modify.vue"),
  //     },
  //     {
  //       path: "delete/:num",
  //       name: "delete",
  //       component: () => import("@/components/board/BoardDelete.vue"),
  //     },
  //     {
  //       path: "modify/:num",
  //       name: "modify",
  //       component: () => import("@/components/board/BoardRegist_Modify.vue"),
  //     },
  //   ],
  // },
  // {
  //   path: "/about",
  //   name: "about",
  //   // route level code-splitting
  //   // this generates a separate chunk (about.[hash].js) for this route
  //   // which is lazy-loaded when the route is visited.
  //   component: () =>
  //     import(/* webpackChunkName: "about" */ "../views/AboutView.vue"),
  // },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
