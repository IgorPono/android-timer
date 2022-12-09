package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import edu.luc.etl.cs313.android.simplestopwatch.R;

class InitialState implements StopwatchState {

    public InitialState(final StopwatchSMStateView sm) {
        this.sm = sm;
    }

    private final StopwatchSMStateView sm;

    @Override
    public void onStartStop() { // button press initializes the program
        sm.actionStart();
        sm.actionInc(); //increments the runtime once
        sm.toIncrementingState(); //goes to incrementing state
    }

    @Override
    public void onTick() { //Nothing occurs on tick on initial state
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
