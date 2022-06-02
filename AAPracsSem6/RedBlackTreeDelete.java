import java.util.*;
class Node {
  int data;
  Node parent;
  Node left;
  Node right;
  int color;
}

public class RedBlackTreeDelete {
  private Node root;
  private Node TNULL;
 
  public RedBlackTreeDelete() {
    TNULL = new Node();
    TNULL.color = 0;
    TNULL.left = null;
    TNULL.right = null;
    root = TNULL;
  }
 
  private void fixInsert(Node k) {
    Node u;
    while (k.parent.color == 1) {
      if (k.parent == k.parent.parent.right) {
        u = k.parent.parent.left;
        if (u.color == 1) {
          u.color = 0;
          k.parent.color = 0;
          k.parent.parent.color = 1;
          k = k.parent.parent;
        } else {
          if (k == k.parent.left) {
            k = k.parent;
            rightRotate(k);
          }
          k.parent.color = 0;
          k.parent.parent.color = 1;
          leftRotate(k.parent.parent);
        }
      } else {
        u = k.parent.parent.right;

        if (u.color == 1) {
          u.color = 0;
          k.parent.color = 0;
          k.parent.parent.color = 1;
          k = k.parent.parent;
        } else {
          if (k == k.parent.right) {
            k = k.parent;
            leftRotate(k);
          }
          k.parent.color = 0;
          k.parent.parent.color = 1;
          rightRotate(k.parent.parent);
        }
      }
      if (k == root) {
        break;
      }
    }
    root.color = 0;
  }
 
  public void leftRotate(Node x) {
    Node y = x.right;
    x.right = y.left;
    if (y.left != TNULL) {
      y.left.parent = x;
    }
    y.parent = x.parent;
    if (x.parent == null) {
      this.root = y;
    } else if (x == x.parent.left) {
      x.parent.left = y;
    } else {
      x.parent.right = y;
    }
    y.left = x;
    x.parent = y;
  }

  public void rightRotate(Node x) {
    Node y = x.left;
    x.left = y.right;
    if (y.right != TNULL) {
      y.right.parent = x;
    }
    y.parent = x.parent;
    if (x.parent == null) {
      this.root = y;
    } else if (x == x.parent.right) {
      x.parent.right = y;
    } else {
      x.parent.left = y;
    }
    y.right = x;
    x.parent = y;
  }

  public void insert(int key) {
    Node node = new Node();
    node.parent = null;
    node.data = key;
    node.left = TNULL;
    node.right = TNULL;
    node.color = 1;

    Node y = null;
    Node x = this.root;

    while (x != TNULL) {
      y = x;
      if (node.data < x.data) {
        x = x.left;
      } else {
        x = x.right;
      }
    }

    node.parent = y;
    if (y == null) {
      root = node;
    } else if (node.data < y.data) {
      y.left = node;
    } else {
      y.right = node;
    }

    if (node.parent == null) {
      node.color = 0;
      return;
    }

    if (node.parent.parent == null) {
      return;
    }

    fixInsert(node);
  }

  // Balance the tree after deletion of a node
  private void fixDelete(Node x) {
    Node s;
    // 1. Do the following until the x is not the root of the tree and the color of x is BLACK
    while (x != root && x.color == 0) {
        // 2. x is the left child of its parent
      if (x == x.parent.left) {
        //   Assign s to the sibling of x
        s = x.parent.right;
        // ************* Case 1 ******************
        // sibling is red
        if (s.color == 1) {
        // recolor sibling as black
          s.color = 0;
        //   recolor parent as black
          x.parent.color = 1;
        // left rotate the parent of x
          leftRotate(x.parent);
        // Assign the rightChild of the parent of x to s
          s = x.parent.right;
        }
        // ************* Case 2 ******************
        // color of both the right and the leftChild of s is BLACK
        if (s.left.color == 0 && s.right.color == 0) {
            // set sibling as red
          s.color = 1;
        //   assign parent of x to x
          x = x.parent;
        }
        // ************ Case 3 ****************** 
        else {

            // color of the rightChild of s is BLACK
          if (s.right.color == 0) {
            //   Set the color of the leftChild of s as BLACK
            s.left.color = 0;
            // color of s as red
            s.color = 1;
            // right rotate s
            rightRotate(s);
            // Assign the rightChild of the parent of x to s.
            s = x.parent.right;
          }
        // ************ Case 4 ******************
        // Set the color of s as the color of the parent of x.
          s.color = x.parent.color;
        //   Set the color of the parent of parent of x as BLACK.
          x.parent.color = 0;
        //   Set the color of the right child of s as BLACK.
          s.right.color = 0;
        //   left rotate parent of x
          leftRotate(x.parent);
        // Set x as the root of the tree
          x = root;
        }
      } 
    // 3.same as above with right changed to left and vice versa.
      else {
        s = x.parent.left;
        if (s.color == 1) {
          s.color = 0;
          x.parent.color = 1;
          rightRotate(x.parent);
          s = x.parent.left;
        }

        if (s.right.color == 0 && s.right.color == 0) {
          s.color = 1;
          x = x.parent;
        } else {
          if (s.left.color == 0) {
            s.right.color = 0;
            s.color = 1;
            leftRotate(s);
            s = x.parent.left;
          }

          s.color = x.parent.color;
          x.parent.color = 0;
          s.left.color = 0;
          rightRotate(x.parent);
          x = root;
        }
      }
    }
    // 4.Set the color of x as BLACK
    x.color = 0;
  }

  private void rbTransplant(Node u, Node v) {
    // u is root node
    if (u.parent == null) {
      root = v;
    } 
    // u is left child of parent
    else if (u == u.parent.left) {
      u.parent.left = v;
    } 
    //  u is right child of parent
    else {
      u.parent.right = v;
    }
    v.parent = u.parent;
  }

  private void deleteNodeHelper(Node node, int key) {
    Node z = TNULL;
    Node x, y;
    // find key in tree
    while (node != TNULL) {
      if (node.data == key) {
        z = node;
      }

      if (node.data <= key) {
        node = node.right;
      } else {
        node = node.left;
      }
    }
    // key not found
    if (z == TNULL) {
      System.out.println("Couldn't find key in the tree");
      return;
    }

    y = z;
    // saving the original color
    int yOriginalColor = y.color;
    // left child of nodeToBeDeleted is NULL
    if (z.left == TNULL) {
    //Assign the right child of nodeToBeDeleted to x.
      x = z.right;
    // Transplant nodeToBeDeleted with x
      rbTransplant(z, z.right);
    } 
    // the right child of nodeToBeDeleted is NULL
    else if (z.right == TNULL) {
    // Assign the left child of nodeToBeDeleted into x
      x = z.left;
      // Transplant nodeToBeDeleted with x
      rbTransplant(z, z.left);
    } 
    else {
    // Assign the minimum of right subtree of noteToBeDeleted into y.
      y = minimum(z.right);
    // Save the color of y in originalColor.
      yOriginalColor = y.color;
    // Assign the rightChild of y into x
      x = y.right;
    // y is a child of nodeToBeDeleted, then set the parent of x as y
      if (y.parent == z) {
        x.parent = y;
      } 
    // transplant y with rightChild of y. 
      else {
        rbTransplant(y, y.right);
        y.right = z.right;
        y.right.parent = y;
      }
    // Transplant nodeToBeDeleted with y
      rbTransplant(z, y);
      y.left = z.left;
      y.left.parent = y;
    //  Set the color of y with originalColor.
      y.color = z.color;
    }
    //  originalColor is BLACK, call DeleteFix(x).
    if (yOriginalColor == 0) {
      fixDelete(x);
    }
  }
 
  public Node minimum(Node node) {
    while (node.left != TNULL) {
      node = node.left;
    }
    return node;
  }

  public void deleteNode(int data) {
    deleteNodeHelper(this.root, data);
  }

  private void printHelper(Node root, String indent, boolean last) {
    if (root != TNULL) {
      System.out.print(indent);
      if (last) {
        System.out.print("R----");
        indent += "   ";
      } else {
        System.out.print("L----");
        indent += "|  ";
      }

      String sColor = root.color == 1 ? "RED" : "BLACK";
      System.out.println(root.data + "(" + sColor + ")");
      printHelper(root.left, indent, false);
      printHelper(root.right, indent, true);
    }
  }

  public void printTree() {
    printHelper(this.root, "", true);
  }

  public static void main(String[] args) {
    RedBlackTreeDelete bst = new RedBlackTreeDelete();
    bst.insert(10);
    bst.insert(18);
    bst.insert(7);
    bst.insert(15);
    bst.insert(16);
    bst.insert(30);
    bst.insert(25);
    bst.insert(40);
    bst.insert(60);
    bst.insert(2);
    bst.insert(1);
    bst.insert(70);
    bst.printTree();
    Scanner sc=new Scanner(System.in);
    int choice;
    while(true)
    {
      System.out.println("1. Delete");
      System.out.println("2. Exit");
      System.out.print("Enter choice:");
      choice= sc.nextInt();
      switch (choice) 
      {
        case 1: System.out.print("Enter number to be deleted:");
                int y = sc.nextInt();
                bst.deleteNode(y);
                System.out.println("Red Black Tree formed after deletion is:");
                bst.printTree();
                break;  
        case 2: System.out.println("Program Ended");
                System.exit(0);
        default:
                System.out.println("Invalid choice!!! Please make a valid choice. \\n\\n");
      }
    }
    
    
  }
}