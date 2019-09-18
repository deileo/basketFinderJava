import axios from "axios";
import {ACCESS_TOKEN, API_URL} from "../config";

const config = {
  headers: {
    'X-Requested-With': 'XMLHttpRequest',
    'Content-Type': 'application/json'
  }
};

export function getUnconfirmedParticipants() {
  if(localStorage.getItem(ACCESS_TOKEN)) {
    config.headers['Authorization'] = 'Bearer ' + localStorage.getItem(ACCESS_TOKEN);
  }

  let url = API_URL + '/participants/unconfirmed';

  return axios.get(url, config);
}

export function getEventParticipants(event) {
  let url = API_URL + '/participants/' + event.id;

  return axios.get(url, config);
}

export function acceptParticipant(participant) {
  if(localStorage.getItem(ACCESS_TOKEN)) {
    config.headers['Authorization'] = 'Bearer ' + localStorage.getItem(ACCESS_TOKEN);
  }

  let url = API_URL + '/participants/accept/' + participant.id;

  return axios.post(url, {}, config);
}

export function cancelParticipant(participant) {
  if(localStorage.getItem(ACCESS_TOKEN)) {
    config.headers['Authorization'] = 'Bearer ' + localStorage.getItem(ACCESS_TOKEN);
  }

  let url = API_URL + '/participants/cancel/' + participant.id;

  return axios.post(url, {}, config);
}
