package epi.binary_trees;

import epi.BinaryTreeNode;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TreeInorder {
  @EpiTest(testDataFile = "tree_inorder.tsv")

  public static List<Integer> inorderTraversal(BinaryTreeNode<Integer> tree) {
    List<Integer> output = new ArrayList<>();
    Stack<BinaryTreeNode<Integer>> stack = new Stack<>();
    BinaryTreeNode<Integer> node;

    if (tree != null) {
      stack.push(tree);
    }

    while (!stack.empty()) {
      node = stack.peek();

      if (node.left == null && node.right == null) {
        output.add(node.data);
        stack.pop();

        if (!stack.empty()) {
          node = stack.peek();
        }

        while (node.right == null && !stack.empty()) {
          output.add(node.data);
          stack.pop();

          if (!stack.empty()) {
            node = stack.peek();
          }
        }

        if (!stack.empty()) {
          output.add(node.data);
          stack.pop();
          stack.push(node.right);
        }
      }
      else {
        if (node.left != null) {
          stack.push(node.left);
        }

        else {
          output.add(node.data);
          stack.pop();
          stack.push(node.right);
        }
      }
    }

    return output;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeInorder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
