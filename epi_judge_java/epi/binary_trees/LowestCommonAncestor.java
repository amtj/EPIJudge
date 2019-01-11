package epi.binary_trees;

import epi.BinaryTreeNode;
import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class LowestCommonAncestor {
  public static BinaryTreeNode<Integer> LCA(BinaryTreeNode<Integer> tree,
                                            BinaryTreeNode<Integer> node0,
                                            BinaryTreeNode<Integer> node1) {

    if (tree == null) {
      return null;
    }

    List<BinaryTreeNode<Integer>> list = new ArrayList<>();
    find(tree, node0, list);
    HashSet<BinaryTreeNode<Integer>> set = new HashSet<>(list);
    list = new ArrayList<>();
    find(tree, node1, list);

    for (int i = 0; i < list.size(); i++) {
      if (set.contains(list.get(i))) {
        return list.get(i);
      }
    }

    return tree;
  }

  public static boolean find(BinaryTreeNode<Integer> tree,
                          BinaryTreeNode<Integer> node,
                          List<BinaryTreeNode<Integer>> list) {
    if (tree == node) {
      return true;
    }
    else {
      if (tree.left != null && find(tree.left, node, list)) {
        list.add(tree.left);
        return true;
      }
      else if (tree.right != null && find(tree.right, node, list)) {
        list.add(tree.right);
        return true;
      }
    }

    return false;
  }

  @EpiTest(testDataFile = "lowest_common_ancestor.tsv")
  public static int lcaWrapper(TimedExecutor executor,
                               BinaryTreeNode<Integer> tree, Integer key0,
                               Integer key1) throws Exception {
    BinaryTreeNode<Integer> node0 = BinaryTreeUtils.mustFindNode(tree, key0);
    BinaryTreeNode<Integer> node1 = BinaryTreeUtils.mustFindNode(tree, key1);

    BinaryTreeNode<Integer> result =
        executor.run(() -> LCA(tree, node0, node1));

    if (result == null) {
      throw new TestFailure("Result can not be null");
    }
    return result.data;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "LowestCommonAncestor.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
