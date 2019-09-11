import axios from 'axios';
import {TYPE_COURT} from "../actions/types";
import {ACCESS_TOKEN, API_URL} from "../config";

const config = {
  headers: {
    'X-Requested-With': 'XMLHttpRequest',
    'Content-Type': 'application/json',
  }
};

export function fetchCourts(type) {
  let url = type === TYPE_COURT ? API_URL + '/courts/public' : API_URL + '/courts/private';
  if(localStorage.getItem(ACCESS_TOKEN)) {
    config.headers['Authorization'] = 'Bearer ' + localStorage.getItem(ACCESS_TOKEN);
  }

  return axios.get(url, config);
}

export function getCourt(type, courtId) {
  if(localStorage.getItem(ACCESS_TOKEN)) {
    config.headers['Authorization'] = 'Bearer ' + localStorage.getItem(ACCESS_TOKEN);
  }

  return axios.get(API_URL + '/courts/' + courtId, config);
}

export function getAllAdminCourts()
{
  return axios.get(API_URL + '/courts/all/court/admin', config);
}

export function getAllAdminGymCourts()
{
  return axios.get(API_URL + '/courts/all/gym-court/admin', config);
}

export function enableCourt(type, courtId) {
  return axios.get(API_URL + '/courts/'+ type +'/enable/' + courtId, config);
}

export function disableCourt(type, courtId) {
  return axios.get(API_URL + '/courts/'+ type +'/disable/' + courtId, config);
}

export function deleteCourt(type, courtId) {
  return axios.get(API_URL + '/courts/'+ type +'/delete/' + courtId, config);
}

export function getNewCourts() {
  return axios.get(API_URL + '/courts/admin/new', config);
}

export function createCourt(courtData, type = TYPE_COURT) {
  if(localStorage.getItem(ACCESS_TOKEN)) {
    config.headers['Authorization'] = 'Bearer ' + localStorage.getItem(ACCESS_TOKEN);
  }
  let url = API_URL + '/courts/' + type +'/new';

  return axios.post(url, courtData, config);
}
