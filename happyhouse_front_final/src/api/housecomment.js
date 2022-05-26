import { apiInstance } from "./index.js";

const api = apiInstance();

function comment_regist(comment, success, fail) {
  api
    .post("/h_comment/regist", JSON.stringify(comment))
    .then(success)
    .catch(fail);
}

async function comment_get(comment, success, fail) {
  await api
    .get(`/h_comment/list/${comment}`)
    .then(success)
    .catch(fail);
}

async function delete_comment(num, success, fail) {
  await api
    .delete(`/h_comment/delete/${num}`)
    .then(success)
    .catch(fail);
}

async function modify_comment(num, success, fail) {
  await api
    .delete(`/h_comment/modify/${num}`)
    .then(success)
    .catch(fail);
}
// function get_favorite(userid, success, fail) {
//   api
//     .get(`/bookmark/getbookmark/${userid}`)
//     .then(success)
//     .catch(fail);
// }

export { comment_regist, comment_get, delete_comment, modify_comment };
