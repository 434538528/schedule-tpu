package ru.tpu.rasp.provider;

/**
 * @author andrey.pogrebnoy
 */
public interface Result<T> {
	T get() throws Exception;
}
