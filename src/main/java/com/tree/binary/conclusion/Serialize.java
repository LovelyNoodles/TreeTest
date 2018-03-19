package com.tree.binary.conclusion;

import java.util.LinkedList;
import java.util.Queue;

import com.tree.binary.model.TreeNode;

public class Serialize {
	public static class Codec {

		// Encodes a tree to a single string.
		public String serialize(TreeNode root) {

			if (root == null) {
				return "[]";
			}

			StringBuilder data = new StringBuilder();
			Queue<TreeNode> nodeList = new LinkedList<TreeNode>();
			nodeList.offer(root);
			while (!nodeList.isEmpty()) {
				TreeNode node = nodeList.poll();

				if (node == null) {
					data.append("null, ");
					continue;
				}

				data.append(node.val + ", ");
				nodeList.offer(node.left);
				nodeList.offer(node.right);

			}

			return "[" + data.substring(0, data.length() - 2) + "]";
		}

		// Decodes your encoded data to tree.
		public TreeNode deserialize(String data) {
			if (data == null || (data = data.trim()).length() < 3) {
				return null;
			}
			data = data.substring(1, data.length() - 1);
			String[] values = data.split(",");

			Queue<TreeNode> nodeList = new LinkedList<TreeNode>();
			String item = values[0];
			TreeNode treeNode = new TreeNode(Integer.parseInt(item));
			nodeList.offer(treeNode);

			int i = 1;
			while (!nodeList.isEmpty()) {

				TreeNode node = nodeList.poll();

				if (values.length == i) {
					break;
				}
				item = values[i++].trim();
				if (!"null".equals(item)) {
					node.left = new TreeNode(Integer.parseInt(item));
					nodeList.offer(node.left);
				}

				if (values.length == i) {
					break;
				}
				item = values[i++].trim();
				if (!"null".equals(item)) {
					node.right = new TreeNode(Integer.parseInt(item));
					nodeList.offer(node.right);
				}
			}

			return treeNode;
		}
	}

	public static void main(String[] args) {
		String data = "[1,2,3,4,5,6,7]";

		Codec codec = new Codec();
		TreeNode root = codec.deserialize(data);

		System.out.println(codec.serialize(root));
	}
}
