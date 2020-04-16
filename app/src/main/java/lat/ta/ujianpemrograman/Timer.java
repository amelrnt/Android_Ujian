package lat.ta.ujianpemrograman;

import android.annotation.SuppressLint;
import android.os.CountDownTimer;

import java.util.concurrent.TimeUnit;

public class Timer extends CountDownTimer {

    private OnChangeListener listener;
    private Runnable runnable;

    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */
    public Timer(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }

    @Override
    public void onFinish() {
        runnable.run();
    }

    @Override
    @SuppressLint("DefaultLocale")
    public void onTick(long millisUntilFinished) {
        String now =  String.format(
                "%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                        TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))
        );

        listener.onChange(now);
    }

    public void setOnFinish(Runnable runnable) {
        this.runnable = runnable;
    }

    public void setOnChangeListener(OnChangeListener listener) {
        this.listener = listener;
    }

    interface OnChangeListener {
        void onChange(String time);
    }
}
