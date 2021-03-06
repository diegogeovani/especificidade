package br.com.dgas.especificidade.instrumentation;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import br.com.dgas.especificidade.R;
import br.com.dgas.especificidade.ui.activity.GameActivity;

@SuppressWarnings("unused")
public class GameActivityTest extends ActivityInstrumentationTestCase2<GameActivity> {

    private GameActivity activity;

    public GameActivityTest() {
        super(GameActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.activity = getActivity();
    }

    public void testPreconditions() {
        assertNotNull(activity);
    }

    public void testMustDisplayProgressBar() {
        ProgressBar progressBar = (ProgressBar) activity.findViewById(R.id.progress_game);
        int expected = View.VISIBLE;
        int actual = progressBar.getVisibility();
        assertEquals(expected, actual);
    }

    public void testMustDisplayCountDownLabel() {
        TextView countDownView = (TextView) activity.findViewById(R.id.text_game_count_down);
        assertTrue(countDownView.getVisibility() == View.VISIBLE);
    }

}
