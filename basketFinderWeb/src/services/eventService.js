import axios from "axios";
import {TYPE_COURT, TYPE_GYM_COURT} from "../actions/types";
import {ACCESS_TOKEN, API_URL} from "../config";

const config = {
  headers: {
    'X-Requested-With': 'XMLHttpRequest',
    'Content-Type': 'application/json'
  }
};


export function createEvent(eventData) {
  if(localStorage.getItem(ACCESS_TOKEN)) {
    config.headers['Authorization'] = 'Bearer ' + localStorage.getItem(ACCESS_TOKEN);
  }

  let url = API_URL + '/events/new';

  return axios.post(url, eventData, config);
}

export function editEvent(eventData, eventId, type = TYPE_COURT) {
  if(localStorage.getItem(ACCESS_TOKEN)) {
    config.headers['Authorization'] = 'Bearer ' + localStorage.getItem(ACCESS_TOKEN);
  }

  let url = API_URL + '/events/' + type + '/' + eventId + '/edit';

  return axios.post(url, eventData, config);
}

export function joinEvent(eventId) {
  if(localStorage.getItem(ACCESS_TOKEN)) {
    config.headers['Authorization'] = 'Bearer ' + localStorage.getItem(ACCESS_TOKEN);
  }

  return axios.post(API_URL + '/events/join/' + eventId, {}, config);
}

export function leaveEvent(eventId) {
  if(localStorage.getItem(ACCESS_TOKEN)) {
    config.headers['Authorization'] = 'Bearer ' + localStorage.getItem(ACCESS_TOKEN);
  }

  return axios.post(API_URL + '/events/leave/' + eventId, {}, config);
}

export function getEvents(type) {
  if (!type) {
    type = TYPE_COURT;
  }

  if(localStorage.getItem(ACCESS_TOKEN)) {
    config.headers['Authorization'] = 'Bearer ' + localStorage.getItem(ACCESS_TOKEN);
  }

  return axios.get(API_URL + '/events/' + type, config);
}

export function getCourtEvents(courtId) {
  if(localStorage.getItem(ACCESS_TOKEN)) {
    config.headers['Authorization'] = 'Bearer ' + localStorage.getItem(ACCESS_TOKEN);
  }

  return axios.get(API_URL + '/events/court/' + courtId, config);
}

export function getAllEvents() {
  return axios.get(API_URL + '/events/admin/all/events', config);
}

export function getUserCreatedEvents() {
  if(localStorage.getItem(ACCESS_TOKEN)) {
    config.headers['Authorization'] = 'Bearer ' + localStorage.getItem(ACCESS_TOKEN);
  }

  let url = API_URL + '/events/user';

  return axios.get(url, config);
}

export function getUserJoinedEvents() {
  if(localStorage.getItem(ACCESS_TOKEN)) {
    config.headers['Authorization'] = 'Bearer ' + localStorage.getItem(ACCESS_TOKEN);
  }

  let url = API_URL + '/events/user/joined/events';

  return axios.get(url, config);
}

export function deleteEvent(eventId, type) {
  if(localStorage.getItem(ACCESS_TOKEN)) {
    config.headers['Authorization'] = 'Bearer ' + localStorage.getItem(ACCESS_TOKEN);
  }

  let url = API_URL + '/events/' + type + '/' + eventId + '/delete';

  return axios.post(url, {}, config);
}

export const getEventTime = (event, type) => {
  let eventTime = event.date + ' ' + event.startTime;

  if (type === TYPE_GYM_COURT && event.endTime) {
    eventTime += ' - ' + event.endTime;
  }

  return eventTime;
};

export const getConfirmedParticipantsCount = (event) => {
  let confirmedParticipants = event.participants.filter(function(participant) {
    return participant.isConfirmed === true;
  });

  return confirmedParticipants.length;
};

export const isArrayNotEmpty = (collection) => {
  return collection && collection.length > 0;
};
