package epi.binary_trees;

import epi.BinaryTreeNode;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IsTreeBalanced {

  @EpiTest(testDataFile = "is_tree_balanced.tsv")

  public static boolean isBalanced(BinaryTreeNode<Integer> tree) {
    if (tree == null) {
      return true;
    }

    calculateHeight(tree);

    if (tree.left == null && tree.right == null) {
      return true;
    }
    else if (tree.right == null) {
      return tree.left.data == 0;
    }
    else if (tree.left == null) {
      return tree.right.data == 0;
    }
    else {
      if (Math.abs(tree.left.data - tree.right.data) > 1 ) {
        return false;
      }
      else {
        return isBalanced(tree.left) && isBalanced(tree.right);
                                                  }
    }
  }

  public static void calculateHeight(BinaryTreeNode<Integer> tree) {
    if (tree.left == null && tree.right == null) {
       tree.data = 0;
    }
    else if (tree.right == null) {
      calculateHeight(tree.left);
      tree.data = tree.left.data + 1;
    }
    else if (tree.left == null) {
      calculateHeight(tree.right);
      tree.data = tree.right.data + 1;
    }
    else {
      calculateHeight(tree.left);
      calculateHeight(tree.right);
      tree.data = Math.max(tree.left.data, tree.right.data) + 1;
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsTreeBalanced.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
