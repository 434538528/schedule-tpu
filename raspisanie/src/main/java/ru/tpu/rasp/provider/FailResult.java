package ru.tpu.rasp.provider;

/**
 * @author andrey.pogrebnoy
 */
public class FailResult<T> implements Result<T> {
	private Exception mException;

	public FailResult(Exception e) {
		this.mException = e;
	}

	@Override
	public T get() throws Exception {
		throw mException;
	}
}
