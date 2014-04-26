package ru.tpu.rasp.cache;

import android.util.Log;

import java.io.File;

import ru.tpu.rasp.app.Utils;

/**
 * @author andrey.pogrebnoy
 */
public class DiskCache<T, J> implements Cache<T, J> {
	private static final String TAG = DiskCache.class.getSimpleName();

	private final File mFile;
	private final boolean mDeleteOnWrongParam;

	public DiskCache(File file, boolean deleteOnWrongParam) {
		mFile = file;
		mDeleteOnWrongParam = deleteOnWrongParam;
	}

	@Override
	public void put(T data, J param) {
		Utils.saveObjectToFile(new ParamData<T, J>(data, param), mFile);
	}

	@Override
	public T get(J param) {
		Object object = Utils.readObjectFromFile(mFile);
		if (object == null) {
			return null;
		}
		try {
			ParamData data = (ParamData) object;
			if (!data.param.equals(param)) {
				if (mDeleteOnWrongParam) {
					mFile.delete();
				}
				return null;
			}
			return (T) data.data;
		} catch (ClassCastException e) {
			Log.e(TAG, "unable to cast file: ", e);
		}
		return null;
	}
}