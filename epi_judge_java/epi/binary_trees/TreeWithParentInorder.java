package epi.binary_trees;

import epi.BinaryTree;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.List;

public class TreeWithParentInorder {
  @EpiTest(testDataFile = "tree_with_parent_inorder.tsv")

  public static List<Integer> inorderTraversal(BinaryTree<Integer> tree) {
    List<Integer> output = new ArrayList<>();
    BinaryTree<Integer> node = tree;
    boolean flag = true;
    boolean rootPassage = true;

    if (tree == null) {
      return output;
    }

    while (true) {

      if (node.left == null && node.right == null) {
        output.add(node.data);

        while (node.parent != null && node.parent.right == node) {
          node = node.parent;

          if (node == tree) {
            flag = false;
          }
        }

        if (!flag) {
          break;
        }

        if (node.parent != null) {
          node = node.parent;
          output.add(node.data);
        }

        while (node.parent != null && node.right == null) {
          node = node.parent;
          if (node == tree && !rootPassage) {
            break;
          }
          output.add(node.data);
        }

        if (node.right != null) {

          if (node == tree) {

            if (!rootPassage) {
              break;
            }

            rootPassage = false;
          }

          node = node.right;
        }
        else {
          break;
        }
      }
      else {
        if (node.left != null) {
          node = node.left;
        }

        else {
          output.add(node.data);
          node = node.right;
        }
      }
    }

    return output;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "TreeWithParentInorder.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
