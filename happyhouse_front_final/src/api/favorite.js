import { apiInstance } from "./index.js";

const api = apiInstance();

function post_favorite(bookmark, success, fail) {
  api
    .post("/bookmark/upload", JSON.stringify(bookmark))
    .then(success)
    .catch(fail);
}

function get_favorite(userid, success, fail) {
  api
    .get(`/bookmark/getbookmark/${userid}`)
    .then(success)
    .catch(fail);
}

function delete_like(aptCode, userid, success, fail) {
  api
    .delete(`/bookmark/delete/${aptCode}/${userid}`)
    .then(success)
    .catch(fail);
}

export { post_favorite, get_favorite, delete_like };
