package br.com.dgas.especificidade.os;

import android.os.Build;

public final class Version {

    @SuppressWarnings("WeakerAccess")
    public static final int CODE = Build.VERSION.SDK_INT;

    private Version() {

    }

    @SuppressWarnings("SameParameterValue")
    public static boolean isCompatibleWith(int versionCode) {
        return CODE >= versionCode;
    }

}
