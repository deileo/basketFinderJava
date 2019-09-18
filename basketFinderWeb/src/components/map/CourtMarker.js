import React, {Component} from "react";
import {InfoWindow, Marker} from "react-google-maps";
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';
import Modal from '@material-ui/core/Modal';
import { withStyles } from '@material-ui/core/styles';
import CreateEventForm from "../form/CreateEventForm";
import connect from "react-redux/es/connect/connect";
import * as actions from "../../actions";
import icon  from "../../icon-56.png";
import {TYPE_COURT} from "../../actions/types";
import CreateGymEventForm from "../form/CreateGymEventForm";
import {courtStyles, modalStyles} from "../styles";
import CreatePermissionRequestForm from "../form/CreatePermissionRequestForm";
import Comments from "../comment/Comments";

class CourtMarker extends Component {
  state = {
    permissionModalOpen: false,
    commentModalOpen: false,
  };

  componentDidUpdate(prevProps, prevState, snapshot) {
    const {permissionReducer} = this.props;
    if (!permissionReducer || !prevProps.permissionReducer) {
      return;
    }

    if (permissionReducer.created && !prevProps.permissionReducer.created) {
      this.setState({permissionModalOpen: false});
    }
  }

  handleOpen = () => {
    this.props.openCreateEventModalAction();
  };

  handleRequestOpen = () => {
    this.setState({permissionModalOpen: true});
  };

  handleRequestClose = () => {
    this.setState({permissionModalOpen: false});
  };

  handleCommentOpen = () => {
    this.setState({commentModalOpen: true});
  };

  handleCommentClose = () => {
    this.setState({commentModalOpen: false});
  };

  handleClose = () => {
    this.props.closeCreateEventModalAction();
    this.props.removeEventErrorsAction();
  };

  renderInfoWindow = (court, classes, activeMarker, modalReducer, courtReducer, userReducer) => {
    if (activeMarker === court.id) {
      return (
        <InfoWindow>
          <div className={classes.container}>
            {courtReducer.type === TYPE_COURT ?
              this.renderCourtWindow(court, userReducer, classes) :
              this.renderGymCourtWindow(court, userReducer, classes)
            }
            <Modal
              open={modalReducer.isCreateEventOpen}
              onClose={this.handleClose}
            >
              <div style={modalStyles} className={classes.paper}>
                {courtReducer.type === TYPE_COURT ?
                  <CreateEventForm court={court} handleClose={this.handleClose}/> :
                  <CreateGymEventForm court={court} handleClose={this.handleClose}/>
                }
              </div>
            </Modal>

            <Modal
              open={this.state.permissionModalOpen}
              onClose={this.handleRequestClose}
            >
              <div style={modalStyles} className={classes.paper}>
                <CreatePermissionRequestForm gymCourt={court} handleClose={this.handleRequestClose}/>
              </div>
            </Modal>

            <Modal
              open={this.state.commentModalOpen}
              onClose={this.handleCommentClose}
            >
              <div style={modalStyles} className={classes.paper}>
                <Comments court={court} type={courtReducer.type} handleClose={this.handleCommentClose}/>
              </div>
            </Modal>
          </div>
        </InfoWindow>
      )
    }
  };

  renderCourtWindow(court, userReducer, classes) {
    return (
      <div>
        <CardContent className={classes.content}>
          <Typography gutterBottom variant="h5" component="h4" className={classes.title}>
            {court.address}
          </Typography>
          <hr/>
          <Typography component="p">
            Rajonas: {court.location}
          </Typography>
          <Typography component="p">
            Informacija: {court.description}
          </Typography>
        </CardContent>
        {this.renderCourtActions(userReducer)}
      </div>
    )
  }

  renderGymCourtWindow(court, userReducer, classes) {
    return (
      <div>
        <CardContent className={classes.content}>
          <Typography gutterBottom variant="h5" component="h4" className={classes.title}>
            {court.name}
          </Typography>
          <hr/>
          <Typography component="p">
            Adresas: {court.address} ({court.location})
          </Typography>
          <Typography component="p">
            Būklė: {court.condition}
          </Typography>
        </CardContent>
        {this.renderCourtActions(userReducer)}
      </div>
    )
  }

  renderCourtActions(userReducer) {
    return (
      <CardActions>
        {userReducer.isAuthenticated ?
          <Button size="small" variant="contained" color="primary" onClick={this.handleOpen}>
            Skelbti varžybas
          </Button> : ''
        }
        <Button size="small" variant="outlined" color="primary" onClick={() => this.handleCommentOpen()}>
          Komentarai {this.props.commentsCount > 0 ? ' (' + this.props.commentsCount + ')' : null}
        </Button>
      </CardActions>
    )
  }

  render() {
    const {court, classes, handleMarkerClick, activeMarker, modalReducer, courtsReducer, userReducer} = this.props;
    return (
      <Marker
        key={court.id}
        position={{lat: court.lat, lng: court.lng}}
        title={court.address}
        onClick={() => handleMarkerClick(court.id)}
        options={{icon: icon}}
      >
        {this.renderInfoWindow(court, classes, activeMarker, modalReducer, courtsReducer, userReducer)}
      </Marker>
    );
  }
}

const mapStateToProps = state => {
  return {
    modalReducer: state.modalReducer,
    courtsReducer: state.courtsReducer,
    userReducer: state.userReducer,
    permissionReducer: state.permissionReducer,
  };
};

export default connect(mapStateToProps, actions)(withStyles(courtStyles)(CourtMarker));
