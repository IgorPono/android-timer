package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import edu.luc.etl.cs313.android.simplestopwatch.R;

class InitialState implements StopwatchState {

    public InitialState(final StopwatchSMStateView sm) {
        this.sm = sm;
    }

    private final StopwatchSMStateView sm;

    @Override
    public void onStartStop() {
        sm.actionStart();
        sm.actionInc();
        sm.toIncrementingState();
    }

    @Override
    public void onTick() {
        throw new UnsupportedOperationException("onTick");
    }

    @Override
    public void updateView() {
        sm.updateUIRuntime();
    }

    @Override
    public int getId() {
        return R.string.INITIAL;//how to change ID?
    }
}
