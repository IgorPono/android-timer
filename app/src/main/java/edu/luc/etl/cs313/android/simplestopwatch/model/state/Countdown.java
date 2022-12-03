package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import edu.luc.etl.cs313.android.simplestopwatch.R;

class Countdown implements StopwatchState {

    public Countdown(final StopwatchSMStateView sm) {
        this.sm = sm;
    }

    private final StopwatchSMStateView sm;

    @Override
    public void onStartStop() {
        sm.actionStop();
        sm.actionInit();
    }


    @Override
    public void onTick() {
        sm.actionDec();
        if(sm.getRuntime() <= 0){
            sm.toAlarmState();
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
