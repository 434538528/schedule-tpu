package ru.tpu.rasp.provider;

/**
 * @author andrey.pogrebnoy
 */
public class FailResult<T> implements Result<T> {
	private Exception e;

	public FailResult(Exception e) {
		this.e = e;
	}

	@Override
	public T get() throws Exception {
		throw e;
	}
}
