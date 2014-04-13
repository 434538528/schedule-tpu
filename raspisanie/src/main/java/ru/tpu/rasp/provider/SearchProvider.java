package ru.tpu.rasp.provider;

import ru.tpu.rasp.api.TpuClient;

public class SearchProvider {
	private TpuClient tpuClient;

	public SearchProvider(TpuClient tpuClient) {
		this.tpuClient = tpuClient;
	}

	/**
	 * throws IOException, ParseException, TpuGrabberException
	 */
	public Result<String[]> getKeys(final String part) {
		return new Result<String[]>(new Result.Processor<String[]>() {
			@Override
			public String[] process() throws Exception {
				return tpuClient.search(part);
			}
		});
	}
}
