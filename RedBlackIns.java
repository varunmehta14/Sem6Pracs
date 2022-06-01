import java.util.*;

class Node 
{
  int data;
  Node parent;
  Node left;
  Node right;
  int color;
}

public class RedBlackIns 
{
  private Node root;
  private Node TNULL;

  private void fixInsert(Node k) 
  {
    Node u;
    while (k.parent.color == 1) 
    {
      
      if (k.parent == k.parent.parent.right)
      {
        System.out.println("Parent is on right of grandparent.");
        u = k.parent.parent.left;
        if (u.color == 1) 
        {
          System.out.println("Parent Neighbour is Red.Hence we recolour parent and uncle nodes.");
          u.color = 0;
          k.parent.color = 0;
          k.parent.parent.color = 1;
          k = k.parent.parent;
        } 
        else 
        {
          System.out.println("Parent Neighbour is Black.Hence we rotate and recolour.If child is to left of parent, right rotate followed by left rotate.Else only left rotate.");
          if (k == k.parent.left) 
          {
            System.out.println("Right Rotate");
            k = k.parent;
            rightRotate(k);
          }
          System.out.println("Left Rotate");
          k.parent.color = 0;
          k.parent.parent.color = 1;
          leftRotate(k.parent.parent);
        }
      } 
      else 
      {
        System.out.println("Parent is on left of grandparent.");
        u = k.parent.parent.right;
        if (u.color == 1) 
        {
          System.out.println("Parent Neighbour is Red.Hence we recolour parent and uncle nodes.");
          u.color = 0;
          k.parent.color = 0;
          k.parent.parent.color = 1;
          k = k.parent.parent;
        } 
        else 
        {
          System.out.println("Parent Neighbour is Black.Hence we rotate and recolour.If child is to right of parent, left rotate followed by right rotate.Else only right rotate.");
          if (k == k.parent.right) 
          {
            System.out.println("Left Rotate");
            k = k.parent;
            leftRotate(k);
          }
          System.out.println("Right Rotate");
          k.parent.color = 0;
          k.parent.parent.color = 1;
          rightRotate(k.parent.parent);
        }
      }
      if (k == root) 
      {
        break;
      }
    }
    root.color = 0;
  }

  private void printHelper(Node root, String indent, boolean last) 
  {
    if (root != TNULL) 
    {
      System.out.print(indent);
      if (last) 
      {
        System.out.print("R----");
        indent += "   ";
      } 
      else 
      {
        System.out.print("L----");
        indent += "|  ";
      }

      String sColor = root.color == 1 ? "RED" : "BLACK";
      System.out.println(root.data + "(" + sColor + ")");
      printHelper(root.left, indent, false);
      printHelper(root.right, indent, true);
    }
  }

  public RedBlackIns() 
  {
    TNULL = new Node();
    TNULL.color = 0;
    TNULL.left = null;
    TNULL.right = null;
    root = TNULL;
  }

  public void leftRotate(Node x) 
  {
    Node y = x.right;
    x.right = y.left;
    if (y.left != TNULL) 
    {
      y.left.parent = x;
    }
    y.parent = x.parent;
    if (x.parent == null) 
    {
      this.root = y;
    } 
    else if (x == x.parent.left) 
    {
      x.parent.left = y;
    } 
    else 
    {
      x.parent.right = y;
    }
    y.left = x;
    x.parent = y;
  }

  public void rightRotate(Node x) 
  {
    Node y = x.left;
    x.left = y.right;
    if (y.right != TNULL) 
    {
      y.right.parent = x;
    }
    y.parent = x.parent;
    if (x.parent == null) 
    {
      this.root = y;
    } 
    else if (x == x.parent.right) 
    {
      x.parent.right = y;
    } 
    else 
    {
      x.parent.left = y;
    }
    y.right = x;
    x.parent = y;
  }

  public void insert(int key) 
  {
    Node node = new Node();
    node.parent = null;
    node.data = key;
    node.left = TNULL;
    node.right = TNULL;
    node.color = 1;

    Node y = null;
    Node x = this.root;

    while (x != TNULL) 
    {
      y = x;
      if (node.data < x.data) 
      {
        x = x.left;
      } 
      else 
      {
        x = x.right;
      }
    }

    node.parent = y;
    if (y == null) 
    {
      root = node;
    } 
    else if (node.data < y.data) 
    {
      y.left = node;
    } 
    else 
    {
      y.right = node;
    }

    if (node.parent == null) 
    {
      node.color = 0;
      return;
    }

    if (node.parent.parent == null) 
    {
      return;
    }
    System.out.println("Before Conflict Resolution:");
    printTree();
    fixInsert(node);
  }

  public void printTree() 
  {
    printHelper(this.root, "", true);
  }

  public static void main(String[] args) 
  {
    Scanner sc = new Scanner(System.in);
    RedBlackIns bst = new RedBlackIns();
    System.out.print("Enter number of elements:");
    int n = sc.nextInt();
    for(int i=0; i<n;i++)
    {
      System.out.print("Enter Element:");
      int x = sc.nextInt();
      bst.insert(x);
      System.out.println("After Conflict Resolution:");
      bst.printTree();
    }
    System.out.println("Red Black Tree Formed is:");
    bst.printTree();
    int choice;
    while(true)
    {
      System.out.println("1. Enter new element:");
      System.out.println("2. Exit");
      System.out.print("Enter choice:");
      choice= sc.nextInt();
      switch (choice) 
      {
        case 1: System.out.print("Enter number to be inserted:");
                int y = sc.nextInt();
                bst.insert(y);
                System.out.println("Red Black Tree Formed is:");
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