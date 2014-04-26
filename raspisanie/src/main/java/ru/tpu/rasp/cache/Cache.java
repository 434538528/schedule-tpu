package ru.tpu.rasp.cache;

/**
 * @author andrey.pogrebnoy
 */
public interface Cache<T, J> {
	void put(T data, J param);
	T get(J param);
}
