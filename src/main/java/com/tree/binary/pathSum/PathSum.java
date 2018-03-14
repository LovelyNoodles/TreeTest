package com.tree.binary.pathSum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

import com.tree.binary.model.TreeNode;

public class PathSum {

	public static class Solution {
		
		public boolean hasPathSum(TreeNode root, int sum) {
			
			// recursive
			return method1(root, sum);
			
		}
		
		private boolean method1(TreeNode root, int sum) {
			if (root == null) {
				return false;
			}
//			if (sum < 0) {// 假设所有的root.val > 0，则在此处返回
//				return false;
//			}
			if (root.left == null && root.right == null && root.val == sum) {
				return true;
			}
			return method1(root.left, sum - root.val) || method1(root.right, sum - root.val); // 利用减法
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
			line = in.readLine();
			int sum = Integer.parseInt(line);

			boolean ret = new Solution().hasPathSum(root, sum);

			String out = booleanToString(ret);

			System.out.print(out);
		}
	}
}
