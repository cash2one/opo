package com.darg.opo.test.ansj;

import java.util.List;

import org.ansj.domain.Term;
import org.ansj.recognition.NatureRecognition;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.ansj.util.FilterModifWord;

import com.darg.opo.ansj.AnsjConfiguration;

public class BaseTest {
	
	
	public static void main(String[] args) {
		
//		List<Term> parse = ToAnalysis.parse("让战士们过一个欢乐祥和的新春佳节。");
//	    System.out.println(parse);
//	    FilterModifWord.insertStopWord("让");
//		new NatureRecognition(parse).recognition();
//		parse = FilterModifWord.modifResult(parse);
////		System.out.println(parse);
//	    System.out.println(parse);
		
//		System.out.println(AnsjConfiguration.getStopWords());
		System.out.println(Thread.currentThread().getContextClassLoader().getResource(""));
		System.out.println(BaseTest.class.getClassLoader().getResource(""));
		 System.out.println(BaseTest.class.getResource("/"));
		
	}
	
}
