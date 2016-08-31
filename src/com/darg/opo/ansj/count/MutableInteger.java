package com.darg.opo.ansj.count;

// 可变Integer
public final class MutableInteger {
	private int val;

	private String url;

	public MutableInteger(int val, String url) {
		this.val = val;
		this.url = url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public int get() {
		return this.val;
	}

	public void set(int val) {
		this.val = val;
	}

	// 为了方便打印
	public String toString() {
		return Integer.toString(val)+"  url="+url;
	}
}