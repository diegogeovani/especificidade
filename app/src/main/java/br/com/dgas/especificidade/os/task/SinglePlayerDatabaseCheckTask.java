package br.com.dgas.especificidade.os.task;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SinglePlayerDatabaseCheckTask extends AsyncTask<String, Void, Boolean> {

    private static final int CONNECTION_TIMEOUT = 5000;
    private static final String NULL_RESULT_ERROR_MESSAGE = "Null result. The task could not " +
            "be cancelled because something went wrong.";
    private final OnTaskResultListener listener;

    public SinglePlayerDatabaseCheckTask(OnTaskResultListener listener) {
        this.listener = listener;
    }

    @Override
    protected Boolean doInBackground(String... params) {
        try {
            URL url = new URL(params[0]);
            HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
            httpConnection.setRequestMethod("GET");
            httpConnection.setConnectTimeout(CONNECTION_TIMEOUT);
            httpConnection.connect();

            return true;

        } catch (MalformedURLException e) {
            e.printStackTrace();
            cancel(true);
            return null;

        } catch (IOException e) {
            e.printStackTrace();
            cancel(true);
            return null;
        }
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        listener.onTaskCancelled();
    }

    @Override
    protected void onPostExecute(Boolean result) {
        super.onPostExecute(result);
        if (result != null) {
            listener.onTaskComplete();
        } else {
            throw new IllegalArgumentException(NULL_RESULT_ERROR_MESSAGE);
        }
    }

    public interface OnTaskResultListener {
        void onTaskCancelled();

        void onTaskComplete();
    }

}
