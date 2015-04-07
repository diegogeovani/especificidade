package br.com.dgas.especificidade.component.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import br.com.dgas.especificidade.R;
import br.com.dgas.especificidade.os.task.SinglePlayerDatabaseCheckTask;

public class LoadingActivity extends ActionBarActivity
        implements SinglePlayerDatabaseCheckTask.OnTaskResultListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        this.startSinglePlayerTask(3000);
    }

    @SuppressWarnings("SameParameterValue")
    private void startSinglePlayerTask(int delay) {
        final SinglePlayerDatabaseCheckTask singlePlayerTask =
                new SinglePlayerDatabaseCheckTask(this);
        final String singlePlayerUrl = getString(R.string.url_singleplayer_version_checker);

        if (delay > 0) {
            Handler handler = new Handler();
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    singlePlayerTask.execute(singlePlayerUrl);
                }
            };
            handler.postDelayed(runnable, delay);

        } else {
            singlePlayerTask.execute(singlePlayerUrl);
        }
    }

    private void displayMainScreen() {
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_loading, menu);
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
    public void onTaskCancelled() {
        finish();
    }

    @Override
    public void onTaskComplete() {
        if (!isFinishing()) {
            finish();
            this.displayMainScreen();
        }
    }

}
