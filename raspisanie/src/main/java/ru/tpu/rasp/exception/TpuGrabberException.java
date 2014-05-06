package ru.tpu.rasp.exception;

/**
 * Возвращается, когда сервер не смог обработать данные (success = false)
 *
 * @author andrey.pogrebnoy
 */
public class TpuGrabberException extends ParseException {
	public TpuGrabberException(String message) {
		super(message);
	}
}
