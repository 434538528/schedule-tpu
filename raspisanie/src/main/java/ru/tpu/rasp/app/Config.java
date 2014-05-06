package ru.tpu.rasp.app;

/**
 * @author andrey.pogrebnoy
 */
public interface Config {
	String getScheduleToken();
	void setScheduleToken(String scheduleToken);
	boolean isEven();
	boolean isBeforeBreak();
	void setEven(boolean isEven);
	void setBeforeBreak(boolean isBeforeBreak);
}
