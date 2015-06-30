package br.com.dgas.especificidade.persistence;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import br.com.dgas.especificidade.os.Version;

final class SinglePlayAsset {

    private static String path;
    private static String fileName;
    static final String DB_NAME = "especificidade";
    private static final String FILE_NAME = DB_NAME + ".sqlite";
    static final String COPY_ERROR_MESSAGE = "Nao foi possivel copiar o asset " +
            FILE_NAME + " para o sistema de arquivos do aparelho.";

    private SinglePlayAsset() {

    }

    public static boolean existsInTheFileSystem(Context context) {
        setPath(context);
        fileName = path + FILE_NAME;
        File dbFile = new File(fileName);
        return dbFile.exists();
    }

    @SuppressLint("SdCardPath")
    private static void setPath(Context context) {
        if (Version.isCompatibleWith(Build.VERSION_CODES.JELLY_BEAN_MR1)) {
            path = context.getApplicationInfo().dataDir + "/databases/";
        } else {
            path = "/data/data/" + context.getPackageName() + "/databases/";
        }
    }

    public static void copyToTheFileSystem(Context context) throws IOException {
        InputStream inputStream = context.getAssets().open("databases/" + FILE_NAME);
        OutputStream outputStream = new FileOutputStream(fileName);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, length);
        }
        outputStream.flush();
        outputStream.close();
        inputStream.close();
    }

    public static String getFileName() {
        return fileName;
    }

}
