package br.com.dgas.especificidade.ui.listener;

import android.os.SystemClock;
import android.view.View;

/**
 * An {@link android.view.View.OnClickListener} implementation for layouts with multiple
 * clickable views. To avoid the multi tapping(e.g. avoid two buttons to be clicked at
 * the same time), you must set only one instance of this class to as many views as you want.
 */
public final class NonMultiTappingOnClickListener implements View.OnClickListener {

    private long lastClickTime;
    private final OnClickResponse onClickResponse;
    private static final int SECOND = 1000;

    /**
     * @param onClickResponse Implementation of the click event itselft.
     */
    public NonMultiTappingOnClickListener(OnClickResponse onClickResponse) {
        this.onClickResponse = onClickResponse;
    }

    @Override
    public void onClick(View v) {
        if (!this.isClickIntervalLessThanOneSecond()) {
            this.lastClickTime = SystemClock.elapsedRealtime();
            this.onClickResponse.onClick(v);
        }
    }

    private boolean isClickIntervalLessThanOneSecond() {
        return SystemClock.elapsedRealtime() - lastClickTime < SECOND;
    }

    /**
     * Handles the click event.
     * */
    public interface OnClickResponse {
        /**
         * It should be something like a switch statement around the view's id.
         * */
        void onClick(View view);
    }

}
