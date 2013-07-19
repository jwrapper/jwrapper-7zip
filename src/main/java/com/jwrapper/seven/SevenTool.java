package com.jwrapper.seven;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import SevenZip.ArchiveExtractCallback;
import SevenZip.HRESULT;
import SevenZip.MyRandomAccessFile;
import SevenZip.Archive.IArchiveExtractCallback;
import SevenZip.Archive.IInArchive;
import SevenZip.Archive.SevenZipEntry;
import SevenZip.Archive.SevenZip.Handler;

public class SevenTool {

	public static IInArchive archive(String file) throws IOException {

		final MyRandomAccessFile randomFile = new MyRandomAccessFile(file, "r");

		final IInArchive archive = new Handler();

		final int ret = archive.Open(randomFile);

		if (ret != 0) {
			throw new IOException("Invalid 7zip file: " + file);
		}

		return archive;

	}

	public static void extract(final String seven, String folder)
			throws Exception {

		IInArchive archive = archive(seven);

		extract(archive, folder);

	}

	static final int MODE_EXTRACT = 0;
	static final int MODE_TESTING = 1;

	static void extract(IInArchive archive, String folder) throws Exception {

		ArchiveExtractCallback extractCallback = new ArchiveExtractCallback();
		extractCallback.Init(archive, folder);

		int res = archive.Extract(null, -1, MODE_EXTRACT, extractCallback);

		if (res == HRESULT.S_OK) {
			if (extractCallback.NumErrors == 0)
				System.out.println("Ok Done");
			else
				System.out.println(" " + extractCallback.NumErrors + " errors");
		} else {
			System.out.println("ERROR !!");
		}

	}

	public static List<String> list(String file) throws IOException {

		List<String> list = new ArrayList<String>();

		IInArchive archive = archive(file);

		for (int i = 0; i < archive.size(); i++) {
			SevenZipEntry item = archive.getEntry(i);
			list.add(item.getName());
		}

		return list;

	}

}
