package ru.tpu.rasp.provider;

/**
 * @author andrey.pogrebnoy
 */
public class OkResult<T> implements Result<T> {

	private T mData;

	public OkResult(T data) {
		this.mData = data;
	}

	@Override
	public T get() {
		return mData;
	}

}
