package ru.tpu.rasp.cache;

import java.io.Serializable;

/**
 * @author andrey.pogrebnoy
 */
public class ParamData<T, J> implements Serializable {
	T mData;
	J mParam;

	ParamData(T data, J param) {
		mData = data;
		mParam = param;
	}
}
