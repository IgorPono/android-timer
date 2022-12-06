package edu.luc.etl.cs313.android.simplestopwatch.model.state;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import edu.luc.etl.cs313.android.simplestopwatch.R;

import java.io.IOException;

class AlarmState implements StopwatchState {

    public AlarmState(final StopwatchSMStateView sm) {
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
        sm.playNotification();
        //playDefaultNotification();
        //sm.toAlarmState();
        //play alarm on tick
        //throw new UnsupportedOperationException("onTick");
    }



    @Override
    public void updateView() {
        sm.updateUIRuntime();
    }

    @Override
    public int getId() {
        return R.string.ALARM;
    }
}
