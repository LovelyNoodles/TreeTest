package com.tree.binary.heap;

public class IsHeap {
	public static void main(String[] args) {
		System.out.println(isHeap("3,8,12,5,20,15,22,28,19"));
		System.out.println(isHeap("3,5,12,19,20,15,22,8,28"));
		System.out.println(isHeap("3,12,5,8,28,20,15,22,19"));
		System.out.println(isHeap("3,5,12,8,28,20,15,22,19"));
	}

	private static boolean isHeap(String str) {
		return isHeap(str, 0) || isHeap(str, 1);
	}

	private static boolean isHeap(String str, int way) {
		String[] arr = str.split(",");
		int len = arr.length;
		for (int i = 0; i < len; i++) {
			int l = 0;
			int r = 0;
			int il = 2 * (i + 1) - 1;
			int ir = 2 * (i + 1);
			boolean flagl = il >= len;
			boolean flagr = ir >= len;

			int value = Integer.parseInt(arr[i]);
			if (way == 0) {
				l = flagl ? Integer.MAX_VALUE : Integer.parseInt(arr[il]);
				r = flagr ? Integer.MAX_VALUE : Integer.parseInt(arr[ir]);
				if (value > l || value > r) {
					return false;
				}
			} else {
				l = flagl ? 0 : Integer.parseInt(arr[il]);
				r = flagr ? 0 : Integer.parseInt(arr[ir]);
				if (value < l || value < r) {
					return false;
				}
			}

			if (flagl || flagr) {
				break;
			}
		}
		return true;
	}
}
