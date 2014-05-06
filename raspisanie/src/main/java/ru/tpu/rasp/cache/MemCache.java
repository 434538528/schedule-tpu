package ru.tpu.rasp.cache;

import java.lang.ref.SoftReference;

/**
 * @author andrey.pogrebnoy
 */
public class MemCache<T, J> implements Cache<T, J> {
	private SoftReference<ParamData<T, J>> mDataRef;
	private final boolean mDeleteOnWrongParam;

	public MemCache(boolean deleteOnWrongParam) {
		mDeleteOnWrongParam = deleteOnWrongParam;
	}

	@Override
	public void put(T data, J param) {
		this.mDataRef = new SoftReference<ParamData<T, J>>(new ParamData<T, J>(data, param));
	}

	@Override
	public T get(J param) {
		if (mDataRef == null) {
			return null;
		}
		ParamData<T, J> data = mDataRef.get();
		if (data == null) {
			return null;
		}
		if (!data.mParam.equals(param)) {
			if (mDeleteOnWrongParam) {
				mDataRef = null;
			}
			return null;
		}
		return data.mData;
	}
}
