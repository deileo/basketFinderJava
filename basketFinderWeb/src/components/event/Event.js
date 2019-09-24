import React, {Component} from "react";
import CardContent from "@material-ui/core/CardContent/CardContent";
import Typography from "@material-ui/core/Typography/Typography";
import CardActions from "@material-ui/core/CardActions/CardActions";
import Button from "@material-ui/core/Button/Button";
import Card from "@material-ui/core/Card/Card";
import {withStyles} from '@material-ui/core/styles';
import InfoModal from "./InfoModal";
import Modal from "@material-ui/core/Modal/Modal";
import {TYPE_GYM_COURT} from "../../actions/types";
import {eventStyles, modalStyles} from "../styles";
import {getEventTime} from "../../services/eventService";
import {connect} from "react-redux";
import * as actions from './../../actions';
import Comments from "../comment/Comments";

class Event extends Component {

  state = {
    infoModalOpen: false,
    commentModalOpen: false,
  };

  handleOpenInfoModalClick = () => {
    this.setState({infoModalOpen: true});
  };

  handleCloseInfoModalClick = () => {
    this.setState({infoModalOpen: false});
  };

  handleCommentOpen = () => {
    this.setState({commentModalOpen: true});
  };

  handleCommentClose = () => {
    this.setState({commentModalOpen: false});
  };

  handleJoin = () => {
    const {event} = this.props;
    this.props.joinEventAction(event.id);
  };

  handleLeave = () => {
    const {event} = this.props;
    this.props.leaveEventAction(event.id);
  };

  renderEventJoinActions = (userReducer, event) => {
    if (!userReducer.isAuthenticated) {
      return null;
    }
    let joined = event.participants.filter(function (participant) {
      return participant.id === userReducer.user.id;
    });

    let unconfirmed = event.unconfirmedParticipants.filter(function (participant) {
      return participant.id === userReducer.user.id;
    });

    if (joined.length) {
      return (
        <Button size="small" variant="contained" color="secondary" onClick={this.handleLeave}>Išeiti</Button>
      )
    }

    if (unconfirmed.length) {
      return (
        <Button size="small" variant="contained" color="secondary" disabled={true}>Prašymas išsiųstas</Button>
      )
    }

    return (
      <Button size="small" variant="contained" color="primary" onClick={this.handleJoin}>Prisijungti</Button>
    );
  };

  render() {
    const {type, event, classes} = this.props;

    return (
      <Card className={classes.card}>
        <CardContent className={classes.cardContent}>
          <Typography variant="h5">
            {event.name}
          </Typography>
          <hr/>
          <Typography variant="body1" gutterBottom className={classes.eventContent}>
            Laikas: {getEventTime(event, type)}
          </Typography>
          <Typography variant="body1" gutterBottom className={classes.eventContent}>
            Adresas: {event.court.address}
          </Typography>
          <Typography variant="body1" gutterBottom className={classes.eventContent}>
            Žaidėjai: {event.joinedPlayers}/{event.neededPlayers}
          </Typography>
          {type === TYPE_GYM_COURT && event.price > 0 ?
            <Typography variant="body1" gutterBottom className={classes.eventContent}>
              Kaina: {event.price} €
            </Typography> : ''
          }
          {event.comment ?
            <Typography variant="body1" gutterBottom style={{color: 'rgba(0, 0, 0, 0.54)'}}>
              Aprašymas: {event.comment.substring(0, 100)}{event.comment.length > 100 ? '...' : ''}
            </Typography> : ''
          }
          <CardActions>
            {this.renderEventJoinActions(this.props.userReducer, event)}
            <Button size="small" variant="outlined" color="primary" onClick={this.handleOpenInfoModalClick}>
              Žaidėjai
            </Button>

            <Button size="small" variant="outlined" color="primary" onClick={() => this.handleCommentOpen()}>
              Komentarai {event.commentsCount > 0 ? ' (' + event.commentsCount + ')' : null}
            </Button>

            <Modal
              open={this.state.infoModalOpen}
              onClose={this.handleClose}
            >
              <div style={modalStyles} className={classes.paper}>
                <InfoModal
                  event={event}
                  onClose={this.handleCloseInfoModalClick}
                  open={this.state.infoModalOpen}
                  type={type}
                />
              </div>
            </Modal>

            <Modal
              open={this.state.commentModalOpen}
              onClose={this.handleCommentClose}
            >
              <div style={modalStyles} className={classes.paper}>
                <Comments event={event} type={type} handleClose={this.handleCommentClose}/>
              </div>
            </Modal>
          </CardActions>
        </CardContent>
      </Card>
    )
  }
}

const mapStateToProps = state => {
  return {
    userReducer: state.userReducer,
  };
};

export default connect(mapStateToProps, actions)(withStyles(eventStyles)(Event));
