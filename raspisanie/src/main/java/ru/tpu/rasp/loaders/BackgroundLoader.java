package ru.tpu.rasp.loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import ru.tpu.rasp.providers.Result;

/**
 * Created by Andrey on 06.04.2014.
 */
public abstract class BackgroundLoader<D> extends AsyncTaskLoader<Result<D>> {
	public BackgroundLoader(Context context) {
		super(context);
		forceLoad();
	}
}
