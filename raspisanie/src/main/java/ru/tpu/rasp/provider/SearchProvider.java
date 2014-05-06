package ru.tpu.rasp.provider;

import ru.tpu.rasp.api.TpuClient;

public class SearchProvider {
	private TpuClient mTpuClient;

	public SearchProvider(TpuClient tpuClient) {
		this.mTpuClient = tpuClient;
	}

	public String[] getKeys(final String part) throws Exception{
		return mTpuClient.search(part);
	}
}
