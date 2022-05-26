import Vue from "vue";
import App from "./App.vue";
// import Router from "vue-router";
import router from "./router";

// import Dashboard from "@/components/Dashboard";
// import DashboardHome from "@/pages/Home";

import store from "./store";

import "@/assets/css/tailwind.css";

import { BootstrapVue, BootstrapVueIcons } from "bootstrap-vue";

import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";
// import Chart from "chart.js"
Vue.use(BootstrapVue);
Vue.use(BootstrapVueIcons);
// Vue.use(IconsPlugin);

Vue.config.productionTip = false;

// Vue.use(Router);

// const routes = [
//   { path: "/", redirect: { name: "DashboardHome" } },
//   {
//     path: "/dashboard",
//     component: Dashboard,
//     children: [
//       { path: "/", redirect: { name: "DashboardHome" } },
//       { path: "home", name: "DashboardHome", component: DashboardHome },
//     ],
//   },
// ];

// const router = new Router({
//   mode: "history",
//   routes,
// });

new Vue({
  render: (h) => h(App),
  router,
  store,
}).$mount("#app");
