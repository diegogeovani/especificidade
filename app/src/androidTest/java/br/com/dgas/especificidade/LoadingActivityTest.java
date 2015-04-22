package br.com.dgas.especificidade;

import android.content.Context;
import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.view.ContextThemeWrapper;

import br.com.dgas.especificidade.component.activity.LoadingActivity;

public class LoadingActivityTest extends ActivityUnitTestCase<LoadingActivity> {

    public LoadingActivityTest() {
        super(LoadingActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        Context context = getInstrumentation().getTargetContext();
        ContextThemeWrapper contextTheme = new ContextThemeWrapper(context, R.style.AppTheme);
        setActivityContext(contextTheme);
    }

    public void testPreconditions() {
        startActivity(new Intent(), null, null);
        fail("TODO");
    }

}
