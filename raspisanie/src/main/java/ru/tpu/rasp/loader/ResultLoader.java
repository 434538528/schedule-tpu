package ru.tpu.rasp.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import ru.tpu.rasp.provider.FailResult;
import ru.tpu.rasp.provider.OkResult;
import ru.tpu.rasp.provider.Result;

/**
 * Created by Andrey on 06.04.2014.
 */
public abstract class ResultLoader<D> extends AsyncTaskLoader<Result<D>> {
	public ResultLoader(Context context) {
		super(context);
	}

	@Override
	public Result<D> loadInBackground() {
		try {
			return new OkResult<D>(loadSafe());
		} catch (Exception e) {
			Log.e(this.getClass().getSimpleName(), "loading error", e);
			return new FailResult<D>(e);
		}
	}

	abstract D loadSafe() throws Exception;
}
