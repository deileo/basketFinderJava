import React, { Component } from 'react';
import { ACCESS_TOKEN } from '../config';
import { Redirect } from 'react-router-dom'
import {connect} from "react-redux";
import * as actions from "../actions";
import {withStyles} from "@material-ui/core";
import {courtStyles} from "../components/styles";

class OAuth2RedirectHandler extends Component {
  getUrlParameter(name) {
    name = name.replace(/[\[]/, '\\[').replace(/[\]]/, '\\]');
    var regex = new RegExp('[\\?&]' + name + '=([^&#]*)');

    var results = regex.exec(this.props.location.search);
    return results === null ? '' : decodeURIComponent(results[1].replace(/\+/g, ' '));
  };

  render() {
    const token = this.getUrlParameter('token');
    const error = this.getUrlParameter('error');

    if(token) {
      this.props.getUserAction(token);
      localStorage.setItem(ACCESS_TOKEN, token);
      return <Redirect to={{
        pathname: "/",
        state: { from: this.props.location }
      }}/>;
    } else {
      return <Redirect to={{
        pathname: "/",
        state: {
          from: this.props.location,
          error: error
        }
      }}/>;
    }
  }
}

const mapStateToProps = state => {
  return {
    userReducer: state.userReducer,
  };
};

export default connect(mapStateToProps, actions)(withStyles(courtStyles)(OAuth2RedirectHandler));
