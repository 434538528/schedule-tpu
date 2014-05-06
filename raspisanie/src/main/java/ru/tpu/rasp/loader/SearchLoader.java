package ru.tpu.rasp.loader;

import android.content.Context;

import ru.tpu.rasp.app.App;

/**
 * @author andrey.pogrebnoy
 */
public class SearchLoader extends ResultLoader<String[]> {
	private String mToken;

	public SearchLoader(Context context, String token) {
		super(context);
		mToken = token;
	}

	@Override
	String[] loadSafe() throws Exception {
		return ((App) getContext().getApplicationContext()).getSearchProvider().getKeys(mToken);
	}
}
