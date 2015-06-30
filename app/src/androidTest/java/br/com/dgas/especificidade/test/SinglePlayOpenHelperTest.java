package br.com.dgas.especificidade.test;

import android.test.AndroidTestCase;

import br.com.dgas.especificidade.persistence.SinglePlayOpenHelper;

public class SinglePlayOpenHelperTest extends AndroidTestCase {

    private SinglePlayOpenHelper singlePlayOpenHelper;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.singlePlayOpenHelper = SinglePlayOpenHelper.getInstance(getContext());
    }

    public void testPreconditions() {
        assertNotNull(singlePlayOpenHelper);
    }

}
