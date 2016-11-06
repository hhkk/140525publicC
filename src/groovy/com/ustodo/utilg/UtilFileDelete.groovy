package com.ustodo.utilg;

import java.io.File;

public class UtilFileDelete {
	// not yet tested
	public static void deleteFileOrFDir(String fileOrDirName, boolean errorIfNotExists)
	{

		// A File object to represent the filename
		File f = new File(fileOrDirName);

		// Make sure the file or directory exists and isn't write protected
		if (!f.exists())
		{
			if (errorIfNotExists)
				throw new IllegalArgumentException(
				"Delete: no such file or directory: " + fileOrDirName);
			else
				return
		}


		if (!f.canWrite())
			throw new IllegalArgumentException("Delete: write protected: "
			+ fileOrDirName);

		// If it is a directory, make sure it is empty
		if (f.isDirectory()) {
			String[] files = f.list();
			if (files.length > 0)
				throw new IllegalArgumentException(
				"Delete: directory not empty: " + fileOrDirName);
		}

		// Attempt to delete it
		boolean success = f.delete();

		if (!success)
			throw new IllegalArgumentException("Delete: deletion failed");

	}

}