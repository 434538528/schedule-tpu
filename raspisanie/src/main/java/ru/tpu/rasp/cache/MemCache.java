package ru.tpu.rasp.cache;

import java.lang.ref.SoftReference;

/**
 * @author andrey.pogrebnoy
 */
public class MemCache<T, J> implements Cache<T, J> {
	private SoftReference<ParamData<T, J>> dataRef;
	private final boolean mDeleteOnWrongParam;

	public MemCache(boolean deleteOnWrongParam) {
		mDeleteOnWrongParam = deleteOnWrongParam;
	}

	@Override
	public void put(T data, J param) {
		this.dataRef = new SoftReference<ParamData<T, J>>(new ParamData<T, J>(data, param));
	}

	@Override
	public T get(J param) {
		if (dataRef == null) {
			return null;
		}
		ParamData<T, J> data = dataRef.get();
		if (data == null) {
			return null;
		}
		if (!data.param.equals(param)) {
			if (mDeleteOnWrongParam) {
				dataRef = null;
			}
			return null;
		}
		return data.data;
	}
}
