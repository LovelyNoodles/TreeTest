package com.tree.binary.depth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

import com.tree.binary.model.TreeNode;

public class MaxDepth {

	static class Solution {

		int answer;

		public int maxDepth(TreeNode root) {
			// Top-down
//			topDown(root, 1);
//			return answer;
			// Bottom-up
			 return bottomUp(root);
		}

		private void topDown(TreeNode root, int depth) {
			if (root == null) {
				return;
			}

			if (root.left == null && root.right == null) {
				answer = Math.max(answer, depth);
			}

			topDown(root.left, depth + 1);
			topDown(root.right, depth + 1);
		}

		private int bottomUp(TreeNode root) {
			if (root == null) {
				return 0;
			}
			
			int l = bottomUp(root.left);
			int r = bottomUp(root.right);
			return l > r ? l + 1 : r + 1;
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

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line;
		while ((line = in.readLine()) != null) {
			TreeNode root = stringToTreeNode(line);

			int ret = new Solution().maxDepth(root);

			String out = String.valueOf(ret);

			System.out.print(out);
		}
	}
}
