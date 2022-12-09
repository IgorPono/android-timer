package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import edu.luc.etl.cs313.android.simplestopwatch.R;

class Countdown implements StopwatchState {

    public Countdown(final StopwatchSMStateView sm) {
        this.sm = sm;
    }

    private final StopwatchSMStateView sm;

    @Override
    public void onStartStop() { //goes to the initial state if the button is pressed
        sm.actionStop();
        sm.actionInit();
    }


    @Override
    public void onTick() { //on second
        sm.actionDec();
        if(sm.getRuntime() <= 0){ //checks if the runtime is 0
            sm.toAlarmState(); //goes to the alarm state if the timer runs out
        }
    }

    @Override
    public void updateView() {
        sm.updateUIRuntime();
    }

    @Override
    public int getId() {
        return R.string.COUNTDOWN;
    }
}
