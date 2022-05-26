import { apiInstance, houseInstance } from "./index.js";

const api = apiInstance();
const house = houseInstance();

function sidoList(success, fail) {
  api
    .get(`/map/sido`)
    .then(success)
    .catch(fail);
}

function gugunList(params, success, fail) {
  api
    .get(`/map/gugun`, { params: params })
    .then(success)
    .catch(fail);
}

function houseList(params, success, fail) {
  house
    .get(``, { params: params })
    .then(success)
    .catch(fail);
}

function getAll(success, fail) {
  api
    .get("/map")
    .then(success)
    .catch(fail);
}

function count(success, fail) {
  api
    .get("/map/count")
    .then(success)
    .catch(fail);
}

export { sidoList, gugunList, houseList, getAll, count };
