import React, {Component} from 'react';
import EventLoader from "../EventLoader";
import Paper from '@material-ui/core/Paper';
import Event from "./Event";
import Typography from "@material-ui/core/Typography/Typography";
import {withStyles} from "@material-ui/core";
import {eventListStyles} from "../styles";
import {connect} from "react-redux";
import * as actions from '../../actions';
import {ACCESS_TOKEN} from "../../config";

class EventList extends Component {

  componentDidMount() {
    this.props.getEventsAction(this.props.courtsReducer.type);
  }

  componentDidUpdate(prevProps, prevState, snapshot) {
    const {eventReducer, courtsReducer} = this.props;

    if (!eventReducer || !prevProps.eventReducer || !courtsReducer) {
      return;
    }

    if (!prevProps.eventReducer.reload && eventReducer.reload) {
      let court = courtsReducer ? courtsReducer.court : null;
      this.props.getUserAction(localStorage.getItem(ACCESS_TOKEN));
      if (court) {
        this.props.fetchCourtById(this.props.courtsReducer.type, court.id);
        this.props.getCourtEventsAction(court.id);
      } else {
        this.props.getEventsAction(this.props.courtsReducer.type);
      }
      this.props.resetEventCreationAction();
    }
  }

  renderEvents = (events) => {
    return (
      <div>
        {events.map(event => {
          return (
            <Event
              key={event.id}
              event={event}
              type={this.props.courtsReducer.type}
            />
          )
        })}
      </div>
    )
  };

  renderCourtEvents = (court, events, classes) => {
    return (
      <div>
        <Paper className={classes.paper} elevation={1}>
          <Typography variant="h5">
            {court.address}
          </Typography>
          <Typography variant="body1">
            Kiekis: {events.length}
          </Typography>
          <Typography variant="body1">
            Rajonas: {court.location}
          </Typography>
        </Paper>
        {events.length ? this.renderEvents(events) :
          <Typography className={classes.textCenter} variant="h5">Nėra paskelbtų varžybų</Typography>
        }
      </div>
    )
  };

  renderAllEvents = (events, classes) => {
    return (
      <div>
        <Paper className={classes.paper} elevation={1}>
          <Typography variant="h5">
            Artimiausios varžybos
          </Typography>
          <Typography variant="body1">
            Kiekis: {events.length}
          </Typography>
        </Paper>
        {events.length ? this.renderEvents(events) :
          <Typography className={classes.textCenter} variant="h5">Nėra paskelbtų varžybų</Typography>
        }
      </div>
    )
  };

  render() {
    const {eventReducer, courtsReducer, loaderReducer, classes} = this.props;

    if (loaderReducer.isEventsLoading) {
      return (<div className={classes.root}><EventLoader/></div>)
    }

    if (!eventReducer || !courtsReducer) {
      return null
    }

    let court = courtsReducer ? courtsReducer.court : null;
    let events = eventReducer && eventReducer.events ? eventReducer.events : [];

    return (
      <div className={classes.root}>
        {court ? this.renderCourtEvents(court, events, classes) : this.renderAllEvents(events, classes)}
      </div>
    );
  }
}

const mapStateToProps = state => {
  return {
    eventReducer: state.eventReducer,
    courtsReducer: state.courtsReducer,
    loaderReducer: state.loaderReducer,
  };
};

export default connect(mapStateToProps, actions)(withStyles(eventListStyles)(EventList));
