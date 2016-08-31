package com.darg.opo.ansj.count;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;

import com.darg.opo.pojo.T360topKeyWord;

public class CountTool {

	/**
	 * 计算热词
	 * @param keyWordList
	 * @return
	 */
	public static HashMap<String, MutableInteger> count(List<T360topKeyWord> keyWordList) {
		HashMap<String, MutableInteger> counter = new HashMap<String, MutableInteger>();
		for (T360topKeyWord kw : keyWordList) {
			String str = kw.getKeyword();
			List<Term> terms = ToAnalysis.parse(str);
			for (Iterator iterator = terms.iterator(); iterator.hasNext();) {
				Term term = (Term) iterator.next();
				MutableInteger initValue = new MutableInteger(1,kw.getNewsUrl());
				// 利用 HashMap 的put方法弹出旧值的特性  
				MutableInteger oldValue = counter.put(term.getName(), initValue);
				if (oldValue != null) {
					initValue.set(oldValue.get() + 1);
				}
			}

		}
		return counter;
	}

	/**
	 * 计算热词
	 * @param keyWordList
	 * @return
	 */
	public static HashMap<String, MutableInteger> count(HashMap<String, MutableInteger> counter, T360topKeyWord kw) {
		String str = kw.getKeyword();
		List<Term> terms = ToAnalysis.parse(str);
		for (Iterator iterator = terms.iterator(); iterator.hasNext();) {
			Term term = (Term) iterator.next();
			if(term.getName().length()<=1){
				continue;
			}
			MutableInteger initValue = new MutableInteger(1,kw.getNewsUrl());
			// 利用 HashMap 的put方法弹出旧值的特性  
			MutableInteger oldValue = counter.put(term.getName(), initValue);
			if (oldValue != null) {
				initValue.set(oldValue.get() + 1);
			}
		}

		return counter;
	}

	/**
	 * 对hashmap进行排序
	 * @param map
	 * @return
	 */
	public static List<Entry<String, MutableInteger>> sort(HashMap<String, MutableInteger> map) {
		List<Entry<String, MutableInteger>> list = new ArrayList<>(map.entrySet());
		//排序
		Collections.sort(list, new Comparator<Map.Entry<String, MutableInteger>>() {
			public int compare(Map.Entry<String, MutableInteger> o1, Map.Entry<String, MutableInteger> o2) {
				//return (o2.getValue() - o1.getValue()); 
				return o2.getValue().get() - o1.getValue().get();
			}
		});
		return list;
	}

}
