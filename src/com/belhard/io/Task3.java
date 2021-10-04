package com.belhard.io;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Task3 {

	public static void main(String[] args) throws IOException {

		ArrayList<File> files = new ArrayList<>();
		File folder = new File("resources/in/");
		for (File file : folder.listFiles()) {
			files.add(file);

		}
		for (int i = 0; i < files.size(); i++) {
			if (FileUtil.sortedFormatJpgAndJpeg(files.get(i)) == true) {

				FileUtil.copyAndWriteLogFile(files.get(i), files.get(i).getName());

			}

		}

	}
}