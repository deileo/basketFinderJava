import axios from "axios";
import {API_URL} from "../config";

const config = {
  headers: {
    'X-Requested-With': 'XMLHttpRequest',
    'Content-Type': 'application/json'
  }
};

export function getUser(token) {
  if(token) {
    config.headers['Authorization'] = 'Bearer ' + token;
  }

  return axios.get(API_URL + '/user/me',
    config
  );
}

export function getAllUsers() {
  return axios.get(API_URL + '/user/all',
    config
  );
}

export function disableUser(user) {
  return axios.get(API_URL + '/user/' + user.id + '/disable',
    config
  );
}

export function enableUser(user) {
  return axios.get(API_URL + '/user/' + user.id + '/enable',
    config
  );
}
