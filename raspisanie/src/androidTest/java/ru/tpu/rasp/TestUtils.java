package ru.tpu.rasp;

/**
 * @author andrey.pogrebnoy
 */
public class TestUtils {

	public static String wrapGrabberJSON(String json) {
		return "{" +
				"success:true," +
				"result:" + json +
				"}";
	}

}
