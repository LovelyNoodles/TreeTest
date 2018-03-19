package com.tree.binary.conclusion;

import java.util.Stack;

import com.tree.binary.model.TreeLinkNode;

public class PopulatingPointers {
	public static class Solution<E> {
		public void connect(TreeLinkNode root) {

			if (root == null) {
				return;
			}

			Stack<TreeLinkNode> stack = new Stack<TreeLinkNode>();
			stack.push(root);
			TreeLinkNode next = null;
			do {
				if (stack.isEmpty()) {
					while (next != null) {
						if (next.left != null) {
							stack.push(next.left);
						}
						if (next.right != null) {
							stack.push(next.right);
						}
						next = next.next;
					}
				}

				while (!stack.isEmpty()) {
					TreeLinkNode pop = stack.pop();
					pop.next = next;
					next = pop;
				}
			} while (next != null || !stack.isEmpty());

		}
	}

	public static void main(String[] args) {
		TreeLinkNode node = new TreeLinkNode(1);
		node.left = new TreeLinkNode(2);
		node.right = new TreeLinkNode(3);

		node.left.left = new TreeLinkNode(4);
		node.left.right = new TreeLinkNode(5);

		node.right.left = new TreeLinkNode(6);
		node.right.right = new TreeLinkNode(7);

		new Solution<TreeLinkNode>().connect(node);
	}

}
