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

public class Inorder {

	static class Solution {
		public List<Integer> inorderTraversal(TreeNode root) {

			// Recursive
			// return recursiveProcess(root);

			// Iterative
			return iterativeProcess(root);
		}

		private List<Integer> iterativeProcess(TreeNode root) {
			List<Integer> list = new ArrayList<Integer>();
			if (root == null) {
				return list;
			}

			Stack<TreeNode> stack = new Stack<TreeNode>();
			TreeNode temp = root;
			do {
				// 把所有的左子树压入栈
				while (temp != null) {
					stack.push(temp);
					temp = temp.left;
				}
				
				TreeNode node = stack.pop();
				// 加入节点的值
				list.add(node.val);
				// 将右子树作为全新的一棵树赋值给temp，再次做此循环
				if (node.right != null) {
					temp = node.right;
				}
				
			} while (!stack.isEmpty() || temp != null);
			
			return list;
		}

		private List<Integer> recursiveProcess(TreeNode root) {
			List<Integer> list = new ArrayList<Integer>();
			if (root != null) {
				list.addAll(recursiveProcess(root.left));
				list.add(root.val);
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

			List<Integer> ret = new Solution().inorderTraversal(root);

			String out = integerArrayListToString(ret);

			System.out.print(out);
		}
	}
}
