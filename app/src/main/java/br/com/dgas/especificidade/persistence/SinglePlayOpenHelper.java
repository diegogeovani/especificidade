package br.com.dgas.especificidade.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.AndroidRuntimeException;

import java.io.IOException;

public class SinglePlayOpenHelper extends SQLiteOpenHelper {

    private static SinglePlayOpenHelper instance;

    public static SinglePlayOpenHelper getInstance(Context context) {
        if (instance == null) {
            instance = new SinglePlayOpenHelper(context.getApplicationContext());
        }
        return instance;
    }

    private SinglePlayOpenHelper(Context context) {
        super(context, SinglePlayAsset.DB_NAME, null, 1);
        this.createDatabaseFromAssets(context);
    }

    private void createDatabaseFromAssets(Context context) {
        if (!SinglePlayAsset.existsInTheFileSystem(context)) {
            getReadableDatabase();
            close();

            try {
                SinglePlayAsset.copyToTheFileSystem(context);
            } catch (IOException e) {
                throw new AndroidRuntimeException(SinglePlayAsset.COPY_ERROR_MESSAGE);
            }

        }
    }

    private static SQLiteDatabase openDatabase() {
        return SQLiteDatabase.openDatabase(SinglePlayAsset.getFileName(), null,
                SQLiteDatabase.CREATE_IF_NECESSARY);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
