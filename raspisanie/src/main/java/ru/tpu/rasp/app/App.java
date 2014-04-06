package ru.tpu.rasp.app;

import android.app.Application;

import ru.tpu.rasp.api.TpuClient;
import ru.tpu.rasp.api.TpuGrabberClient;
import ru.tpu.rasp.providers.SearchProvider;

public class App extends Application {
	private SearchProvider searchProvider;
	private Config config;

	@Override
	public void onCreate() {
		super.onCreate();
		TpuClient tpuClient = new TpuGrabberClient();
		searchProvider = new SearchProvider(tpuClient);
		config = new SPConfig(this);
	}

	public SearchProvider getSearchProvider() {
		return searchProvider;
	}

	public Config getConfig(){
		return config;
	}
}
