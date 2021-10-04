package com.belhard.io;

import java.io.File;
import java.util.ArrayList;

public class Task2 {
	public static void main(String[] args) {

		ArrayList<String> nameFiles = new ArrayList<>();
		File folder = new File("resources/in/");
		for (File file : folder.listFiles()) {
			nameFiles.add(file.getName());
		}
		for (int i = 0; i < nameFiles.size(); i++) {

			String nameFile = nameFiles.get(i);
			String path1 = "resources/in/" + nameFile;
			String path2 = "resources/out/" + nameFile;

			FileUtil.copyFiles(path1, path2);

		}

	}
}