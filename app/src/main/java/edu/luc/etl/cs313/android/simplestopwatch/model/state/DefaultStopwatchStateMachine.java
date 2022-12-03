package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import edu.luc.etl.cs313.android.simplestopwatch.common.StopwatchModelListener;
import edu.luc.etl.cs313.android.simplestopwatch.model.clock.ClockModel;
import edu.luc.etl.cs313.android.simplestopwatch.model.time.TimeModel;

/**
 * An implementation of the state machine for the stopwatch.
 *
 * @author laufer
 */
public class DefaultStopwatchStateMachine implements StopwatchStateMachine {

    public DefaultStopwatchStateMachine(final TimeModel timeModel, final ClockModel clockModel) {
        this.timeModel = timeModel;
        this.clockModel = clockModel;
    }

    private final TimeModel timeModel;

    private final ClockModel clockModel;

    /**
     * The internal state of this adapter component. Required for the State pattern.
     */
    private StopwatchState state;

    protected void setState(final StopwatchState state) {
        this.state = state;
        listener.onStateUpdate(state.getId());
    }

    private StopwatchModelListener listener;

    @Override
    public void setModelListener(final StopwatchModelListener listener) {
        this.listener = listener;
    }

    // forward event uiUpdateListener methods to the current state
    // these must be synchronized because events can come from the
    // UI thread or the timer thread
    @Override public synchronized void onStartStop() { state.onStartStop(); }
    @Override public synchronized void onTick()      { state.onTick(); }

    @Override public void updateUIRuntime() { listener.onTimeUpdate(timeModel.getRuntime()); }
    //@Override public void updateUILaptime() { listener.onTimeUpdate(timeModel.getLaptime()); }//what is the listener

    // known states
    private final StopwatchState ALARM_STATE    = new AlarmState(this); //Stopped state
    private final StopwatchState INCREMENTING     = new Incrementing(this); //Running State
    private final StopwatchState COUNTDOWN = new Countdown(this); // ToLapRunning State
    private final StopwatchState INITIAL_STATE = new InitialState(this); //ToLapStopped State

    // transitions
    @Override public void toIncrementingState()    { setState(INCREMENTING); }
    @Override public void toAlarmState()    { setState(ALARM_STATE); }
    @Override public void toCountdownState() { setState(COUNTDOWN); }
    @Override public void toInitialState() { setState(INITIAL_STATE); }

    // actions
    @Override public void actionInit()       { toInitialState(); actionReset(); } //previously had toStoppedState()
    @Override public void actionReset()      { timeModel.resetRuntime(); actionUpdateView(); }
    @Override public void actionStart()      { clockModel.start(); }
    @Override public void actionStop()       { clockModel.stop(); }
   // @Override public void actionLap()        { timeModel.setLaptime(); }
    @Override public void actionInc()        { timeModel.incRuntime(); actionUpdateView(); }
    @Override public void actionDec()        { timeModel.decRuntime(); actionUpdateView();}
    @Override public void actionUpdateView() { state.updateView(); }
    public int getRuntime()                  { return timeModel.getRuntime();}
}
