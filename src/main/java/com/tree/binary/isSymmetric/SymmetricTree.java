package com.tree.binary.isSymmetric;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import com.tree.binary.model.TreeNode;

public class SymmetricTree {

	public static class Solution<E> {
		public boolean isSymmetric(TreeNode root) {

			boolean flag = false;

			// recursive
			flag = method1(root);

			// queue
//			flag = method2(root);

			// stack
//			flag = method3(root);

			return flag;
		}

		private boolean method3(TreeNode root) {
			if (root == null || (root.left == null && root.right == null)) {
				return true;
			}
			
			return st3(root);
		}

		private boolean st3(TreeNode root) {
			
			Stack<TreeNode> stack = new Stack<TreeNode>();
			
			return false;
		}

		private boolean method2(TreeNode root) {
			if (root == null || (root.left == null && root.right == null)) {
				return true;
			}
			
			return st2(root);
		}

		private boolean st2(TreeNode root) {
			LinkedList<TreeNode> ql = new LinkedList<TreeNode>();
			LinkedList<TreeNode> qr = new LinkedList<TreeNode>();
			
			ql.offer(root.left);
			qr.offer(root.right);
			
			while (!ql.isEmpty() && !qr.isEmpty()) {
				TreeNode l = ql.poll();
				TreeNode r = qr.poll();
				
				if (l == null) {
					if (r == null) {
						continue;
					} else {
						return false;
					}
				}
				
				if (r == null || l.val != r.val) {
					return false;
				}
				
				ql.offer(l.left);
				qr.offer(r.right);
				
				ql.offer(l.right);
				qr.offer(r.left);
			}
			return true;
		}

		private boolean method1(TreeNode root) {
			if (root == null) {
				return true;
			}
			return st1(root.left, root.right);
		}

		private boolean st1(TreeNode left, TreeNode right) {
			if (left == null && right == null) {
				return true;
			} else if (left != null && right != null) {
				return left.val == right.val && st1(left.right, right.left) && st1(left.left, right.right);
			} else {
				return false;
			}
		}

	}

	public static TreeNode stringToTreeNode(String input) {
		input = input.trim();
		input = input.substring(1, input.length() - 1);
		if (input.length() == 0) {
			return null;
		}

		String[] parts = input.split(",");
		String item = parts[0];
		TreeNode root = new TreeNode(Integer.parseInt(item));
		Queue<TreeNode> nodeQueue = new LinkedList<TreeNode>();
		nodeQueue.add(root);

		int index = 1;
		while (!nodeQueue.isEmpty()) {
			TreeNode node = nodeQueue.remove();

			if (index == parts.length) {
				break;
			}

			item = parts[index++];
			item = item.trim();
			if (!item.equals("null")) {
				int leftNumber = Integer.parseInt(item);
				node.left = new TreeNode(leftNumber);
				nodeQueue.add(node.left);
			}

			if (index == parts.length) {
				break;
			}

			item = parts[index++];
			item = item.trim();
			if (!item.equals("null")) {
				int rightNumber = Integer.parseInt(item);
				node.right = new TreeNode(rightNumber);
				nodeQueue.add(node.right);
			}
		}
		return root;
	}

	public static String booleanToString(boolean input) {
		return input ? "True" : "False";
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line;
		while ((line = in.readLine()) != null) {
			TreeNode root = stringToTreeNode(line);

			boolean ret = new Solution().isSymmetric(root);

			String out = booleanToString(ret);

			System.out.print(out);
		}
	}
}
