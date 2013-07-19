package com.jwrapper.seven;


import static org.junit.Assert.*;

import java.io.File;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestSevenTool {


	@Test
	public void extract7z() throws Exception {

		String seven = "./src/test/resources/whichzip.7z";
		String folder = "./target/" + System.currentTimeMillis();
		String file = "whichzip.c";

		
		List<String> list = SevenTool.list(seven);
		
		System.out.println("list=" + list);

		assertEquals(1, list.size());
		assertEquals("whichzip.c", list.get(0));
		
		SevenTool.extract(seven, folder);
		
		assertTrue(new File(folder, file).exists());
		
	}

	// @Test
	public void extract7exe() throws Exception {

		String seven = "./src/test/resources/whichzip.exe";
		String folder = "./target/" + System.currentTimeMillis();
		String file = "whichzip.c";

		
		List<String> list = SevenTool.list(seven);
		
		System.out.println("list=" + list);

		assertEquals(1, list.size());
		assertEquals("whichzip.c", list.get(0));
		
		SevenTool.extract(seven, folder);
		
		assertTrue(new File(folder, file).exists());
		
	}

}
