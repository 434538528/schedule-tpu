package ru.tpu.rasp.provider;

/**
 * Обёртка над данными вместе с ошибкой.
 */
public class OkResult<T> implements Result<T> {

	private T data;

	public OkResult(T data) {
		this.data = data;
	}

	@Override
	public T get() {
		return data;
	}

}
