package com.tree.binary.traversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import com.tree.binary.model.TreeNode;

public class Preorder {

	static class Solution {
		public List<Integer> preorderTraversal(TreeNode root) {

			// Recursive
//			return recursiveProcess(root);

			// Iterative
			return iterativeProcess(root);
		}

		private List<Integer> iterativeProcess(TreeNode root) {
			List<Integer> list = new ArrayList<Integer>();
			Stack<TreeNode> stack = new Stack<TreeNode>();
			if (root == null) {
				return list;
			}

			stack.push(root);
			while (!stack.isEmpty()) {
				TreeNode node = stack.pop();
				list.add(node.val);
				
				if (node.right != null) {
					stack.push(node.right);
				}
				if (node.left != null) {
					stack.push(node.left);
				}
			}
			return list;
		}

		private List<Integer> recursiveProcess(TreeNode root) {
			List<Integer> list = new ArrayList<Integer>();
			if (root != null) {
				list.add(root.val);
				list.addAll(recursiveProcess(root.left));
				list.addAll(recursiveProcess(root.right));
			}
			return list;
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

	public static String integerArrayListToString(List<Integer> nums, int length) {
		if (length == 0) {
			return "[]";
		}

		String result = "";
		for (int index = 0; index < length; index++) {
			Integer number = nums.get(index);
			result += Integer.toString(number) + ", ";
		}
		return "[" + result.substring(0, result.length() - 2) + "]";
	}

	public static String integerArrayListToString(List<Integer> nums) {
		return integerArrayListToString(nums, nums.size());
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line;
		while ((line = in.readLine()) != null) {
			TreeNode root = stringToTreeNode(line);

			List<Integer> ret = new Solution().preorderTraversal(root);

			String out = integerArrayListToString(ret);

			System.out.print(out);
		}
	}
}
