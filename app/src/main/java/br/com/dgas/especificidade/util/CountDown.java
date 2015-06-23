package br.com.dgas.especificidade.util;

import android.os.CountDownTimer;

public final class CountDown extends CountDownTimer {

    private long millisUntilFinished;
    private final CountDownListener listener;
    @SuppressWarnings("WeakerAccess")
    public static final long SEVEN_SECONDS = 7000;

    public CountDown(CountDownListener countDownListener) {
        super(SEVEN_SECONDS + 1000, 1000);
        this.listener = countDownListener;
        this.millisUntilFinished = SEVEN_SECONDS;
    }

    public int getTimeRemaining() {
        return (int) (millisUntilFinished / 1000);
    }

    @Override
    public void onTick(long millisUntilFinished) {
        this.millisUntilFinished = millisUntilFinished;
        listener.onTick();
    }

    @Override
    public void onFinish() {
        listener.onCountDownFinished();
    }

    public interface CountDownListener {
        void onTick();

        void onCountDownFinished();
    }

}
