package com.tree.binary;

public class Test {
	public static void main(String[] args) {
		StringBuilder builder = new StringBuilder("123");
		yyp(builder);
		System.out.println(builder);

		A a = new A();
		System.out.println(a.a);
		changeA(a);
		System.out.println(a.a);

	}

	private static void changeA(A a) {
		a.a = 2;
	}

	private static void yyp(StringBuilder builder) {
//		builder = new StringBuilder("1.2.3");
		builder.append("aaa");
	}

	static class A {
		int a = 1;
	}
}
