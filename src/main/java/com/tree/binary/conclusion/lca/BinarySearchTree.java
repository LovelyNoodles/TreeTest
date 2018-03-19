package com.tree.binary.conclusion.lca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

import com.tree.binary.model.TreeNode;

/**
 * 二叉搜索树
 * 
 * @author Administrator
 *
 */
public class BinarySearchTree {

	static class Solution {
		public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

			// recursive
//			return method1(root, p, q);
			
			// non-recursive
			return method2(root, p, q);
			

		}

		private TreeNode method2(TreeNode root, TreeNode p, TreeNode q) {
			while (root != null) {
				if (root.val > p.val && root.val > q.val) {
					root = root.left;
					continue;
				}
				if (root.val < p.val && root.val < q.val) {
					root = root.right;
					continue;
				}
				return root;
			}
			return null;
		}

		private TreeNode method1(TreeNode root, TreeNode p, TreeNode q) {
			if (root == null) {
				return null;
			}
			if (root.val > p.val && root.val > q.val) {
				lowestCommonAncestor(root.left, p, q);
			}
			if (root.val < p.val && root.val < q.val) {
				lowestCommonAncestor(root.right, p, q);
			}
			return root;
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
        while(!nodeQueue.isEmpty()) {
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
    
    public static String treeNodeToString(TreeNode root) {
        if (root == null) {
            return "[]";
        }
    
        String output = "";
        Queue<TreeNode> nodeQueue = new LinkedList<TreeNode>();
        nodeQueue.add(root);
        while(!nodeQueue.isEmpty()) {
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
            TreeNode root = stringToTreeNode(line);
            line = in.readLine();
            int m = Integer.parseInt(line);
            line = in.readLine();
            int n = Integer.parseInt(line);
            
            TreeNode ret = new Solution().lowestCommonAncestor(root, new TreeNode(m), new TreeNode(n));
            
            String out = treeNodeToString(ret);
            
            System.out.print(out);
        }
    }
}
