import {
  ACCEPT_PARTICIPANT,
  CANCEL_PARTICIPANT,
  FLASH_MESSAGE,
  GET_PARTICIPANTS_UNCONFIRMED,
  RELOAD_EVENTS
} from "./types";
import {
  acceptParticipant,
  cancelParticipant,
  getUnconfirmedParticipants
} from '../services/participantService';

export const getUnconfirmedParticipantsAction = () => {
  return function(dispatch) {
    return getUnconfirmedParticipants()
      .then(response => {
        return dispatch({ type: GET_PARTICIPANTS_UNCONFIRMED, payload: response.data });
      })
      .catch(error => {
        if (error) {
          console.error(error);
        }
        return Promise.reject({});
      })
  };
};

export const acceptParticipantAction = (participant) => {
  return function(dispatch) {
    return acceptParticipant(participant)
      .then(response => {
        dispatch({type: FLASH_MESSAGE, payload: {isOpen: true, message: 'Dalyvavimas patvirtintas!', variant: 'success'}});
        dispatch({type: RELOAD_EVENTS});

        return dispatch({ type: ACCEPT_PARTICIPANT, payload: response.data });
      })
      .catch(error => {
        if (error) {
          console.error(error);
        }
        return Promise.reject({});
      });
  };
};

export const cancelParticipantAction = (participant) => {
  return function(dispatch) {
    return cancelParticipant(participant)
      .then(response => {
        dispatch({type: FLASH_MESSAGE, payload: {isOpen: true, message: 'Dalyvavimas atmestas!', variant: 'success'}});
        dispatch({type: RELOAD_EVENTS});

        return dispatch({ type: CANCEL_PARTICIPANT, payload: response.data });
      })
      .catch(error => {
        if (error) {
          console.error(error);
        }
        return Promise.reject({});
      })
  };
};
