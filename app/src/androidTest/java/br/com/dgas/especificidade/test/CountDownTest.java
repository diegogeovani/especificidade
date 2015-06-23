package br.com.dgas.especificidade.test;

import junit.framework.TestCase;

import br.com.dgas.especificidade.util.CountDown;

@SuppressWarnings("unused")
public class CountDownTest extends TestCase {

    private CountDown countDown;
    private boolean finished;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.finished = false;
        this.countDown = new CountDown(new CountDown.CountDownListener() {

            @Override
            public void onTick() {

            }

            @Override
            public void onCountDownFinished() {
                finished = true;
            }

        });
    }

    public void testCountDownMustBeRunningRightAfterOnStart() {
        countDown.start();
        assertTrue(!finished);
    }

    public void testGetTimeRemainingMustReturnSecondsAsInteger() {
        final int expected = 7;
        final int actual = countDown.getTimeRemaining();
        assertEquals(expected, actual);
    }

}
