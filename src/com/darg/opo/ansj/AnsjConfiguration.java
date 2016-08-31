package com.darg.opo.ansj;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

/**
 * 对应配置的对象（对应library.properties）
 * 
 * 
 */
public class AnsjConfiguration {

	private static List<String> stopWords;

	@SuppressWarnings("unchecked")
	public static List<String> getStopWords() {
		if (stopWords != null) {
			return stopWords;
		} else {
			return Collections.EMPTY_LIST;
		}
	}

	private AnsjConfiguration() {
	}

	static {
		Properties pps = new Properties();
		try {
			InputStream in = new BufferedInputStream(new FileInputStream("resources/library.properties"));
			pps.load(in);
			String path = pps.getProperty("stopLibrary");

			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
			String temp = null;
			stopWords = new ArrayList<>();
			while ((temp = br.readLine()) != null) {
				stopWords.add(temp.trim());
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
