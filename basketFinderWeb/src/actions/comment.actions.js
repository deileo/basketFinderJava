import {
  NEW_COMMENT,
  GET_COMMENTS,
  RESET_COMMENT_RELOAD,
  LOADING_COMMENTS_ENDED,
  LOADING_COMMENTS_STARTED
} from "./types";
import {createComment, getCourtComments, getEventComments} from '../services/commentService';

export const createCommentAction = (commentData) => {
  return function (dispatch) {
    return createComment(commentData)
      .then(response => {
        return dispatch({type: NEW_COMMENT})
      })
      .catch(error => {
        return showConsoleError(error);
      });
  }
};

export const getCourtCommentsAction = (court) => {
  return function (dispatch) {
    dispatch({ type: LOADING_COMMENTS_STARTED });

    return getCourtComments(court)
      .then(response => {
        return dispatch({type: GET_COMMENTS, payload: response.data})
      })
      .catch(error => {
        return showConsoleError(error);
      })
      .finally(() => {
        dispatch({ type: LOADING_COMMENTS_ENDED });
      });
  }
};

export const getEventCommentsAction = (event) => {
  return function (dispatch) {
    dispatch({ type: LOADING_COMMENTS_STARTED });

    return getEventComments(event)
      .then(response => {
        return dispatch({type: GET_COMMENTS, payload: response.data})
      })
      .catch(error => {
        return showConsoleError(error);
      })
      .finally(() => {
        dispatch({ type: LOADING_COMMENTS_ENDED });
      });
  }
};

export const resetCourtCommentsAction = () => {
  return function (dispatch) {
    return dispatch({type: RESET_COMMENT_RELOAD})
  }
};

const showConsoleError = (error) => {
  if (error) {
    console.error(error);
  }

  return Promise.reject({});
};
