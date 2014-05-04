package ru.tpu.rasp.provider;

import ru.tpu.rasp.api.TpuClient;

public class SearchProvider {
	private TpuClient tpuClient;

	public SearchProvider(TpuClient tpuClient) {
		this.tpuClient = tpuClient;
	}

	public String[] getKeys(final String part) throws Exception{
		return tpuClient.search(part);
	}
}
