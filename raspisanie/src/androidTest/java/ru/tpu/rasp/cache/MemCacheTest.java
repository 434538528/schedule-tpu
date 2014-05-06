package ru.tpu.rasp.cache;

import junit.framework.TestCase;

/**
 * @author andrey.pogrebnoy
 */
public class MemCacheTest extends TestCase {

	private MemCache<String, String> hardMemCache;
	private MemCache<String, String> softMemCache;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		hardMemCache = new MemCache<String, String>(true);
		softMemCache = new MemCache<String, String>(false);
	}

	public void testHardCache(){
		assertNull(hardMemCache.get("aaa"));
		hardMemCache.put("data", "param1");
		assertEquals("data", hardMemCache.get("param1"));
		assertNull(hardMemCache.get("param2"));
		assertNull(hardMemCache.get("param1"));
	}

	public void testSoftCache(){
		assertNull(softMemCache.get("aaa"));
		softMemCache.put("mData", "param1");
		assertEquals("mData", softMemCache.get("param1"));
		assertNull(softMemCache.get("param2"));
		assertEquals("mData", softMemCache.get("param1"));
	}
}
