package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import edu.luc.etl.cs313.android.simplestopwatch.R;

class Incrementing implements StopwatchState {


    int counter = 0;
    public Incrementing(final StopwatchSMStateView sm) {
        this.sm = sm;
    }

    private final StopwatchSMStateView sm;

    @Override
    public void onStartStop() { //button press increments the timer
        sm.actionInc();
        counter = 0;
    }



    @Override
    public void onTick() {
        counter++;
        if (sm.getRuntime() >= 99) { //if the timer exceeds 99 seconds a notification is played and the countdown starts
            sm.playNotification();
            sm.toCountdownState();
        }
        if (counter >= 3){ // if the timer isn't incremented for three seconds the countdown starts
            sm.toCountdownState();
        }

    }

    @Override
    public void updateView() {
        sm.updateUIRuntime();
    }

    @Override
    public int getId() {
        return R.string.INCREMENTING;
    }
}
