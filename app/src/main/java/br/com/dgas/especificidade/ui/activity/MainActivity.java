package br.com.dgas.especificidade.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import br.com.dgas.especificidade.R;
import br.com.dgas.especificidade.ui.listener.NonMultiTappingOnClickListener;

/**
 * Represents the main screen of the app. This is where the player will choose which
 * mode he wants to play.
 */
public class MainActivity extends ActionBarActivity implements
        NonMultiTappingOnClickListener.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NonMultiTappingOnClickListener onClickListener = new NonMultiTappingOnClickListener(this);
        findViewById(R.id.button_main_singleplayer).setOnClickListener(onClickListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.button_main_singleplayer:
                startActivity(new Intent(this, GameActivity.class));
                break;

        }
    }

}
