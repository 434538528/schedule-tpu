package ru.tpu.rasp.activity;

import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;

/**
 * @author andrey.pogrebnoy
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {
	public MainActivityTest() {
		super(MainActivity.class);
	}
	public void testDummy(){
		int a = 1+1;
		assertEquals(2, a);
		Log.d("aaaa", "DUMMY TEST!!!!!!!!!!!");
	}
}
