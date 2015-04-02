package br.com.dgas.especificidade.component.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.io.File;

import br.com.dgas.especificidade.R;
import br.com.dgas.especificidade.os.task.SinglePlayerDatabaseCheckTask;

public class LoadingActivity extends ActionBarActivity
        implements SinglePlayerDatabaseCheckTask.OnTaskCompleteListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        this.startSinglePlayerDataBaseTask(3000);
    }

    @SuppressWarnings("SameParameterValue")
    private void startSinglePlayerDataBaseTask(int delay) {
        final SinglePlayerDatabaseCheckTask singlePlayerTask =
                new SinglePlayerDatabaseCheckTask(this);

        if (delay > 0) {
            Handler handler = new Handler();
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    singlePlayerTask.execute();
                }
            };
            handler.postDelayed(runnable, delay);

        } else {
            singlePlayerTask.execute();
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
    public void onTaskComplete(File file) {
        if (!isFinishing()) {
            finish();
            this.displayMainScreen();
        }
    }

}
