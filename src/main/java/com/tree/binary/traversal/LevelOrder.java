package com.tree.binary.traversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.tree.binary.model.TreeNode;

public class LevelOrder {

	static class Solution {
		public List<List<Integer>> levelOrder(TreeNode root) {
			List<List<Integer>> list = new ArrayList<List<Integer>>();

			if (root == null) {
				return list;
			}

			Queue<TreeNode> supQueue = new LinkedList<TreeNode>();
			Queue<TreeNode> subQueue = new LinkedList<TreeNode>();
			supQueue.offer(root);
			
			while (!supQueue.isEmpty()) {
				ArrayList<Integer> vals = new ArrayList<Integer>();
				do {
					TreeNode node = supQueue.poll();
					
					// 取当前层级根值
					vals.add(node.val);
					
					// 保存下一层子树
					if (node.left != null) {
						subQueue.offer(node.left);
					}
					if (node.right != null) {
						subQueue.offer(node.right);
					}
					
				} while (!supQueue.isEmpty());
				
				// 保存每一次级根值
				list.add(vals);
				
				// 进行下一次的压栈
				while (!subQueue.isEmpty()) {
					supQueue.offer(subQueue.poll());
				}
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
    
    public static String integerArrayListToString(List<Integer> nums, int length) {
        if (length == 0) {
            return "[]";
        }
    
        String result = "";
        for(int index = 0; index < length; index++) {
            Integer number = nums.get(index);
            result += Integer.toString(number) + ", ";
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }
    
    public static String integerArrayListToString(List<Integer> nums) {
        return integerArrayListToString(nums, nums.size());
    }
    
    public static String int2dListToString(List<List<Integer>> nums) {
        StringBuilder sb = new StringBuilder("[");
        for (List<Integer> list: nums) {
            sb.append(integerArrayListToString(list));
            sb.append(",");
        }
    
        sb.setCharAt(sb.length() - 1, ']');
        return sb.toString();
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            TreeNode root = stringToTreeNode(line);
            
            List<List<Integer>> ret = new Solution().levelOrder(root);
            
            String out = int2dListToString(ret);
            
            System.out.print(out);
        }
    }
}
