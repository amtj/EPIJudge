package epi.binary_trees;

import epi.BinaryTreeNode;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class SumRootToLeaf {
  @EpiTest(testDataFile = "sum_root_to_leaf.tsv")

  public static int sumRootToLeaf(BinaryTreeNode<Integer> tree) {
    return sumRoot(tree, "");
  }

  public static int sumRoot(BinaryTreeNode<Integer> tree, String currentBin) {
    if (tree == null) {
      return 0;
    }
    else if (tree.left == null && tree.right == null) {
      return Integer.parseInt(currentBin + tree.data, 2);
    }
    else {
      return sumRoot(tree.left, currentBin + tree.data) + sumRoot(tree.right, currentBin + tree.data);
    }
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "SumRootToLeaf.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
