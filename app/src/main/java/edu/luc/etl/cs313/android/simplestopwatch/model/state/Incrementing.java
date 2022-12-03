package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import edu.luc.etl.cs313.android.simplestopwatch.R;

class Incrementing implements StopwatchState {


    int counter = 0;
    public Incrementing(final StopwatchSMStateView sm) {
        this.sm = sm;
    }

    private final StopwatchSMStateView sm;

    @Override
    public void onStartStop() {
        sm.actionInc();
        counter = 0;
    }



    @Override
    public void onTick() {
        counter++;
        if (counter >= 3){
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
