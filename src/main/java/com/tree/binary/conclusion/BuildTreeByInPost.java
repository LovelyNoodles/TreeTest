package com.tree.binary.conclusion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

import com.tree.binary.model.TreeNode;

public class BuildTreeByInPost {

	public static class Solution {
		public TreeNode buildTree(int[] inorder, int[] postorder) {

			// 过滤数据
			if (inorder == null || postorder == null || inorder.length == 0 || postorder.length == 0
					|| inorder.length != postorder.length) {
				return null;
			}

			// 构建二叉树
			return solve(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
		}

		/**
		 * 构建二叉树
		 *
		 * @param inorder
		 *            中序遍历的结果
		 * @param is
		 *            中序遍历的开始位置
		 * @param ie
		 *            中序遍历的结束位置
		 * @param postorder
		 *            后序遍历的结果
		 * @param ps
		 *            后序遍历的开始位置
		 * @param pe
		 *            后序遍历的结束位置
		 * @return 二叉树
		 */
		public TreeNode solve(int[] inorder, int is, int ie, int[] postorder, int ps, int pe) {

			if (is >= 0 && is <= ie && ps >= 0 && ps <= pe) {
				// 只有一个元素，（此时也有i=j成）
				if (is == ie) {
					return new TreeNode(postorder[pe]);
				} else if (is < ie) { // 多于一个元素，此时也有i<j
					// 创建根结点
					TreeNode root = new TreeNode(postorder[pe]);

					// 找根结点在中序遍历的下标
					int idx = is;
					while (idx < ie && inorder[idx] != postorder[pe]) {
						idx++;
					}

					/**
					 * 对于二叉树的某一节点，其左子树进行中序、中序、后序所遍历到的节点个数是一样的
					 */
					// 左子树非空，构建左子树
					int leftLength = idx - is;
					if (leftLength > 0) {
						// ps, ps + leftLength - 1，后序遍历的左子树的起始，结束位置
						root.left = solve(inorder, is, idx - 1, postorder, ps, ps + leftLength - 1);
					}

					// 右子树非空，构建右子树
					int rightLength = ie - idx;
					if (rightLength > 0) {
						// ps + leftLength, pe - 1，后序遍历的右子树的起始，结束位置
						root.right = solve(inorder, idx + 1, ie, postorder, ps + leftLength, pe - 1);
					}

					return root;
				} else {
					return null;
				}
			}
			return null;
		}
	}

	public static int[] stringToIntegerArray(String input) {
		input = input.trim();
		input = input.substring(1, input.length() - 1);
		if (input.length() == 0) {
			return new int[0];
		}

		String[] parts = input.split(",");
		int[] output = new int[parts.length];
		for (int index = 0; index < parts.length; index++) {
			String part = parts[index].trim();
			output[index] = Integer.parseInt(part);
		}
		return output;
	}

	public static String treeNodeToString(TreeNode root) {
		if (root == null) {
			return "[]";
		}

		String output = "";
		Queue<TreeNode> nodeQueue = new LinkedList<TreeNode>();
		nodeQueue.add(root);
		while (!nodeQueue.isEmpty()) {
			TreeNode node = nodeQueue.remove();

			if (node == null) {
				output += "null, ";
				continue;
			}

			output += String.valueOf(node.val) + ", ";
			nodeQueue.add(node.left);
			nodeQueue.add(node.right);
		}
		return "[" + output.substring(0, output.length() - 2) + "]";
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line;
		while ((line = in.readLine()) != null) {
			int[] inorder = stringToIntegerArray(line);
			line = in.readLine();
			int[] postorder = stringToIntegerArray(line);

			TreeNode ret = new Solution().buildTree(inorder, postorder);

			String out = treeNodeToString(ret);

			System.out.print(out);
		}
	}
}
