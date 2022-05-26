import { apiInstance } from "./index.js";

const api = apiInstance();

async function login(user, success, fail) {
  await api
    .post(`/user/login`, JSON.stringify(user))
    .then(success)
    .catch(fail);
}

async function findById(userid, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api
    .get(`/user/info/${userid}`)
    .then(success)
    .catch(fail);
}

function regist(user, success, fail) {
  api
    .post("/user/regist", JSON.stringify(user))
    .then(success)
    .catch(fail);
}

function delete_user(userid, success, fail) {
  api
    .delete(`/user/delete/${userid}`)
    .then(success)
    .catch(fail);
}

function modify_user(userid, success, fail) {
  api
    .put(`/user/modify/${userid.userid}`, JSON.stringify(userid))
    .then(success)
    .catch(fail);
}

function find_password(params, success, fail) {
  api
    .get(`/user/find/${params.userid}/${params.username}/${params.email}`)
    .then(success)
    .catch(fail);
}

function getLogin(userid, success, fail) {
  api
    .get(`/user/login/${userid}`)
    .then(success)
    .catch(fail);
}
// function logout(success, fail)

export {
  login,
  findById,
  regist,
  delete_user,
  modify_user,
  find_password,
  getLogin,
};
