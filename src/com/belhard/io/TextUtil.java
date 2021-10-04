package com.belhard.io;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextUtil {
	public static StringBuilder formatText(StringBuilder sb) {
		String str = sb.toString();
		String[] strs = str.split("(\\r\\n){2,}");
		StringBuffer[] sbs = new StringBuffer[strs.length];

		for (int i = 0; i < strs.length; i++) {
			sbs[i] = TextUtil.removeAllExcessSpacesInString(strs[i]);
		}
		for (int i = 0; i < sbs.length; i++) {
			int counter = 120;
			for (int j = 0; j < sbs[i].length(); j++) {
				if (counter < sbs[i].length()) {
					if (sbs[i].charAt(counter) == 32) {
						sbs[i].replace(counter, ++counter, "\r\n");
						counter += 120;
					} else {
						counter--;
						continue;
					}
				} else {
					break;
				}
			}
		}
		Pattern p = Pattern.compile("(\\.\\s?\\s)([a-z])");
		Pattern p1 = Pattern.compile("^[a-z]");
		for (int i = 0; i < strs.length; i++) {
			strs[i] = sbs[i].toString();
			Matcher m = p.matcher(strs[i]);
			while (m.find()) {
				char replace = (char) (strs[i].charAt(m.end() - 1) - 32);
				String replacement = String.valueOf(replace);
				strs[i] = strs[i].replaceFirst("(\\.\\s?\\s)([a-z])", "$1" + replacement);
			}
			Matcher m1 = p1.matcher(strs[i]);
			while (m1.find()) {
				char replace = (char) (strs[i].charAt(m1.start()) - 32);
				String replacement = String.valueOf(replace);
				strs[i] = strs[i].replaceFirst("^[a-z]", replacement);
			}
		}
		for (int i = 0; i < strs.length; i++) {
			strs[i] = strs[i].replaceFirst("^", "\t");
		}

		StringBuilder strbuild = new StringBuilder();
		for (int i = 0; i < strs.length; i++) {
			if (i == strs.length - 1) {
				strbuild.append(strs[i]);
			} else {
				strbuild.append(strs[i]).append("\n\n");
			}
		}
		return strbuild;
	}

	public static StringBuffer removeAllExcessSpacesInString(String str) {
		str = str.replaceAll("\\s+", " ");
		str = str.replaceAll("^\\s+", "");
		str = str.replaceAll("[\\x00-\\x20]+$", "");
		str = str.replaceAll("\\s\\'", "");
		Pattern p = Pattern.compile("\\b\\s([.,])");
		Matcher m = p.matcher(str);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "$1");
		}
		m.appendTail(sb);
		return sb;
	}
}
