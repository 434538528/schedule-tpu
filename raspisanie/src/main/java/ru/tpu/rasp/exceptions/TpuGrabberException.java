package ru.tpu.rasp.exceptions;

/**
 * Возвращается, когда сервер не смог обработать данные (success = false)
 */
public class TpuGrabberException extends ParseException {
    public TpuGrabberException(String message){
        super(message);
    }
}
