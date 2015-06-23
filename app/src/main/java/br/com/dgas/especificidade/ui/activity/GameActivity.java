package br.com.dgas.especificidade.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import br.com.dgas.especificidade.R;
import br.com.dgas.especificidade.util.CountDown;

public class GameActivity extends Activity implements CountDown.CountDownListener {

    private CountDown countDown;
    private TextView countDownView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        this.countDown = new CountDown(this);
        this.countDownView = (TextView) findViewById(R.id.text_game_count_down);
        this.updateCountDownText(countDown.getTimeRemaining());
    }

    private void updateCountDownText(int secondsRemaining) {
        countDownView.setText(getString(R.string.the_game_will_begin_in, secondsRemaining));
    }

    @Override
    protected void onStart() {
        super.onStart();
        countDown.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTick() {
        this.updateCountDownText(countDown.getTimeRemaining());
    }

    @Override
    public void onCountDownFinished() {
        findViewById(R.id.viewgroup_game_count_down).setVisibility(View.INVISIBLE);
    }

}
