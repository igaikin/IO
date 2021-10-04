package com.belhard.io;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

public class FileUtil {
	public static StringBuilder fileToStringBuilder(String path) {

		StringBuilder sb = new StringBuilder();
		try (FileReader fr = new FileReader(path)) {

			int i;
			char[] buffer = new char[4096];
			while ((i = fr.read(buffer)) != -1) {
				sb.append(buffer, 0, i);
			}
		} catch (IOException exc) {
			exc.printStackTrace();
		}
		return sb;

	}

	public static boolean stringBuilderToFile(StringBuilder sb, String path) {
		try (FileWriter fw = new FileWriter(path)) {
			fw.write(sb.toString());
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean ifNotFolder_Create(String path) {
		Path pathToFolder = Paths.get(path);
		if (Files.notExists(pathToFolder, LinkOption.NOFOLLOW_LINKS)) {
			try {
				Files.createDirectory(pathToFolder);
				return true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public static void copyFiles(String path1, String path2) {

		try (FileInputStream fin = new FileInputStream(path1);
				FileOutputStream fos = new FileOutputStream(path2);
				FileWriter writer = new FileWriter("resources/out/log.txt", true)) {
			byte[] buffer = new byte[fin.available()];

			fin.read(buffer, 0, buffer.length);

			fos.write(buffer, 0, buffer.length);
			writer.write(new Date().toString() + "\n" + "input:: " + path1 + "\n" + "output:: " + path2 + '\n');
		} catch (IOException ex) {

			System.out.println(ex.getMessage());
		}
	}

	public static boolean sortedFormatJpgAndJpeg(File file) {

		String str = file.getName();

		String b = ".jpg";
		String c = ".jpeg";

		int index = str.lastIndexOf(b);
		int index2 = str.lastIndexOf(c);

		if (index > -1 || index2 > -1) {
			return true;
		} else {
			return false;

		}
	}

	public static void copyAndWriteLogFile(File file, String one) throws IOException {

		String nameFile = one;
		String path1 = "resources/in/" + nameFile;
		String path2 = "resources/out/" + nameFile;
		String size = getFileSizeBytes(file);
		Path testFile = Paths.get(path1);

//		Path fileName = testFile.getFileName();

		testFile = Files.copy(testFile, Paths.get(path2), REPLACE_EXISTING);
		try (FileWriter writer = new FileWriter("resources/out/log.txt", true)) {
			writer.write(new Date().toString() + "\n" + "input:: " + path1 + "\n" + "output:: " + path2 + "(copy)"
					+ '\n' + "SIZE OF FALE (bytes) :: " + size + "\n\r");

		} catch (IOException ex) {

			System.out.println(ex.getMessage());
		}
	}

	private static String getFileSizeBytes(File file) {
		return file.length() + " bytes";
	}
}
