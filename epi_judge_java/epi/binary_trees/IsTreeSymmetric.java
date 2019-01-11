package epi.binary_trees;

import epi.BinaryTreeNode;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class IsTreeSymmetric {
  @EpiTest(testDataFile = "is_tree_symmetric.tsv")

  public static boolean isSymmetric(BinaryTreeNode<Integer> tree) {
    if (tree == null) {
      return true;
    }
    else {
      return isSymmetric(tree.left, tree.right);
    }
  }

  public static boolean isSymmetric(BinaryTreeNode<Integer> tree1, BinaryTreeNode<Integer> tree2) {
    if (tree1 == null && tree2 == null) {
      return true;
    }

    else if (tree1 == null || tree2 == null) {
      return false;
    }
    else {
      if (!tree1.data.equals(tree2.data)) {
        return false;
      }
      else {
        return isSymmetric(tree1.left, tree2.right) && isSymmetric(tree1.right, tree2.left);
      }
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IsTreeSymmetric.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
