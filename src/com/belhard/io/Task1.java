package com.belhard.io;

public class Task1 {

	public static void main(String[] args) {

		StringBuilder sb = FileUtil.fileToStringBuilder("resources/in/text.txt");
		sb = TextUtil.formatText(sb);

		System.out.println(FileUtil.ifNotFolder_Create("resources/out"));
		System.out.print(FileUtil.stringBuilderToFile(sb, "resources/out/formattedText.txt"));

	}
}
