package ru.tpu.rasp.cache;

import java.io.Serializable;

/**
 * @author andrey.pogrebnoy
 */
public class ParamData<T, J> implements Serializable {
	T data;
	J param;

	ParamData(T data, J param) {
		this.data = data;
		this.param = param;
	}
}
