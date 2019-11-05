import axios from 'axios';
import {ACCESS_TOKEN, API_URL} from "../config";

const config = {
  headers: {
    'X-Requested-With': 'XMLHttpRequest',
    'Content-Type': 'application/json'
  }
};

export function createComment(commentData) {
  if(localStorage.getItem(ACCESS_TOKEN)) {
    config.headers['Authorization'] = 'Bearer ' + localStorage.getItem(ACCESS_TOKEN);
  }

  let url = API_URL + '/comments/new';

  return axios.post(url, commentData, config);
}

export function getCourtComments(court) {
  let url = API_URL + '/comments/court/' + court.id;

  return axios.get(url, config);
}

export function getEventComments(event) {
  let url = API_URL + '/comments/event/' + event.id;

  return axios.get(url, config);
}

