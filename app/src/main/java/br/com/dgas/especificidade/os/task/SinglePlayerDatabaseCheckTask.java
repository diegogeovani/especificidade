package br.com.dgas.especificidade.os.task;

import android.os.AsyncTask;

import java.io.File;

public class SinglePlayerDatabaseCheckTask extends AsyncTask<String, Void, File> {

    private final OnTaskCompleteListener listener;

    public SinglePlayerDatabaseCheckTask(OnTaskCompleteListener listener) {
        this.listener = listener;
    }

    @Override
    protected File doInBackground(String... params) {
        return null;
    }

    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);
        listener.onTaskComplete(file);
    }

    public interface OnTaskCompleteListener {
        void onTaskComplete(File file);
    }

}
