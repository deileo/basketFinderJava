import axios from "axios";
import {ACCESS_TOKEN, API_URL} from "../config";

const config = {
  headers: {
    'X-Requested-With': 'XMLHttpRequest',
    'Content-Type': 'application/json'
  }
};

export function sendPermissionRequest(requestData) {
  if(localStorage.getItem(ACCESS_TOKEN)) {
    config.headers['Authorization'] = 'Bearer ' + localStorage.getItem(ACCESS_TOKEN);
  }
  config.headers['Content-Type'] = 'multipart/form-data';

  const formData = new FormData();
  formData.append('file', requestData.file);
  formData.append('message', requestData.message);
  formData.append('gymCourt', requestData.gymCourt);

  let url = API_URL + '/permission/new';

  return axios.post(url, formData, config);
}

export function sendPermissionRequestApproval(permissionId, approveData) {
  if(localStorage.getItem(ACCESS_TOKEN)) {
    config.headers['Authorization'] = 'Bearer ' + localStorage.getItem(ACCESS_TOKEN);
  }

  let url = API_URL + '/permission/approve/' + permissionId;

  return axios.post(url, approveData, config);
}

export function deletePermission(permissionId) {
  if(localStorage.getItem(ACCESS_TOKEN)) {
    config.headers['Authorization'] = 'Bearer ' + localStorage.getItem(ACCESS_TOKEN);
  }

  let url = API_URL + '/permission/delete/' + permissionId;

  return axios.post(url, {}, config);
}

export function getGymCourtPermission(gymCourtId) {
  if(localStorage.getItem(ACCESS_TOKEN)) {
    config.headers['Authorization'] = 'Bearer ' + localStorage.getItem(ACCESS_TOKEN);
  }

  let url = API_URL + '/permission/gym-court/' + gymCourtId;

  return axios.post(url, {}, config);
}

export function getPermissions() {
  if(localStorage.getItem(ACCESS_TOKEN)) {
    config.headers['Authorization'] = 'Bearer ' + localStorage.getItem(ACCESS_TOKEN);
  }

  let url = API_URL + '/permission/all';

  return axios.post(url, {}, config);
}
