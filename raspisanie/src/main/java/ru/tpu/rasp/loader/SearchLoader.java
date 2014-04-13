package ru.tpu.rasp.loader;

import android.content.Context;

import ru.tpu.rasp.app.App;
import ru.tpu.rasp.provider.Result;

public class SearchLoader extends BackgroundLoader<String[]> {
	private String mToken;

	public SearchLoader(Context context, String token) {
		super(context);
		mToken = token;
	}

	@Override
	public Result<String[]> loadInBackground() {
		return ((App) getContext().getApplicationContext()).getSearchProvider().getKeys(mToken);
	}
}
