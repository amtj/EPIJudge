package epi.binary_trees;

import epi.BinaryTreeNode;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TreePreorder {
  @EpiTest(testDataFile = "tree_preorder.tsv")

  public static List<Integer> preorderTraversal(BinaryTreeNode<Integer> tree) {
    List<Integer> output = new ArrayList<>();
    Stack<BinaryTreeNode<Integer>> stack = new Stack<>();
    BinaryTreeNode<Integer> node;

    if (tree != null) {
      stack.push(tree);
      output.add(tree.data);
    }

    while (!stack.empty()) {
      node = stack.peek();

      if (node.left == null && node.right == null) {
        stack.pop();

        if (!stack.empty()) {
          node = stack.peek();
        }

        while (node.right == null && !stack.empty()) {
          stack.pop();

          if (!stack.empty()) {
            node = stack.peek();
          }
        }

        if (!stack.empty()) {
          stack.pop();
          stack.push(node.right);
          output.add(node.right.data);
        }
      }
      else {
        if (node.left != null) {
          stack.push(node.left);
          output.add(node.left.data);
        }

        else {
          stack.pop();
          stack.push(node.right);
          output.add(node.right.data);
        }
      }
    }

    return output;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreePreorder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
