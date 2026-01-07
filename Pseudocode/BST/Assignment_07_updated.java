/*
Name: Nouf Bamne
Roll No: UCE2025004
Batch: A4
Assignment 07

Problem Statement:
Implement Dictionary using Binary Search Tree (BST) with operations:
Create, Insert, Search, Delete, Traversals, Update, Leaf nodes, Path, Boundary nodes, Interior nodes
*/

import java.util.*;

class Node {
    String word, meaning;
    Node left, right;

    Node(String w, String m) {
        word = w;
        meaning = m;
        left = right = null;
    }
}

class BSTDictionary {
    Node root;
    Scanner sc = new Scanner(System.in);

    // =========================================
    // 1️⃣ Create Tree
    // =========================================
    void createTree() {
        if (root != null) {
            System.out.println("Tree already exists!");
            return;
        }
        System.out.print("Enter first keyword: ");
        String w = sc.next();
        sc.nextLine();
        System.out.print("Enter meaning: ");
        String m = sc.nextLine();

        root = new Node(w, m);
        System.out.println("✅ Tree created successfully with root: " + w);
    }

    // =========================================
    // 2️⃣ Insert Node (ptr method)
    // =========================================
    void insert(String w, String m) {
        Node newNode = new Node(w, m);
        if (root == null) {
            root = newNode;
            return;
        }

        Node ptr = root, parent = null;

        while (ptr != null) {
            parent = ptr;
            if (w.equalsIgnoreCase(ptr.word)) {
                System.out.println("❌ Duplicate keyword not allowed!");
                return;
            }
            if (w.compareToIgnoreCase(ptr.word) < 0)
                ptr = ptr.left;
            else
                ptr = ptr.right;
        }

        if (w.compareToIgnoreCase(parent.word) < 0)
            parent.left = newNode;
        else
            parent.right = newNode;

        System.out.println("✅ Inserted: " + w);
    }

    // =========================================
    // 3️⃣ Search Node (ptr method)
    // =========================================
    Node search(String key) {
        if (root == null) {
            System.out.println("Dictionary is empty!");
            return null;
        }

        Node ptr = root;
        while (ptr != null) {
            if (key.equalsIgnoreCase(ptr.word))
                return ptr;
            if (key.compareToIgnoreCase(ptr.word) < 0)
                ptr = ptr.left;
            else
                ptr = ptr.right;
        }
        return null;
    }

    // =========================================
    // 4️⃣ Delete Node (3 conditions)
    // =========================================
    Node delete(Node root, String key) {
        if (root == null)
            return null;

        if (key.compareToIgnoreCase(root.word) < 0)
            root.left = delete(root.left, key);
        else if (key.compareToIgnoreCase(root.word) > 0)
            root.right = delete(root.right, key);
        else {
            // CASE 1: No child
            if (root.left == null && root.right == null)
                return null;
            // CASE 2: One child
            else if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;
            // CASE 3: Two children
            else {
                Node successor = minNode(root.right);
                root.word = successor.word;
                root.meaning = successor.meaning;
                root.right = delete(root.right, successor.word);
            }
        }
        return root;
    }

    Node minNode(Node ptr) {
        while (ptr.left != null)
            ptr = ptr.left;
        return ptr;
    }

    // =========================================
    // 5️⃣ Inorder Traversal (sorted display)
    // =========================================
    void inorder(Node ptr) {
        if (ptr == null)
            return;
        inorder(ptr.left);
        System.out.println(ptr.word + " : " + ptr.meaning);
        inorder(ptr.right);
    }

    // =========================================
    // 6️⃣ Preorder Traversal
    // =========================================
    void preorder(Node ptr) {
        if (ptr == null)
            return;
        System.out.println(ptr.word + " : " + ptr.meaning);
        preorder(ptr.left);
        preorder(ptr.right);
    }

    // =========================================
    // 7️⃣ Postorder Traversal
    // =========================================
    void postorder(Node ptr) {
        if (ptr == null)
            return;
        postorder(ptr.left);
        postorder(ptr.right);
        System.out.println(ptr.word + " : " + ptr.meaning);
    }

    // =========================================
    // 8️⃣ Update meaning
    // =========================================
    void update(String key, String newMeaning) {
        if (root == null) {
            System.out.println("Dictionary is empty!");
            return;
        }

        Node ptr = root;
        while (ptr != null) {
            if (key.equalsIgnoreCase(ptr.word)) {
                ptr.meaning = newMeaning;
                System.out.println("✅ Meaning updated successfully!");
                return;
            }
            if (key.compareToIgnoreCase(ptr.word) < 0)
                ptr = ptr.left;
            else
                ptr = ptr.right;
        }
        System.out.println("❌ Keyword not found!");
    }

    // =========================================
    // 9️⃣ Print Leaf Nodes
    // =========================================
    void printLeaves(Node ptr) {
        if (ptr == null)
            return;
        if (ptr.left == null && ptr.right == null)
            System.out.println(ptr.word);
        printLeaves(ptr.left);
        printLeaves(ptr.right);
    }

    // =========================================
    // 🔟 Print Path to a Node
    // =========================================
    boolean printPath(Node root, String key) {
        if (root == null)
            return false;
        System.out.print(root.word + " -> ");
        if (key.equalsIgnoreCase(root.word))
            return true;
        if (key.compareToIgnoreCase(root.word) < 0)
            return printPath(root.left, key);
        else
            return printPath(root.right, key);
    }

    // =========================================
    // 1️⃣1️⃣ Print Interior Nodes
    // =========================================
    void printInterior(Node ptr) {
        if (ptr == null)
            return;
        if (!(ptr.left == null && ptr.right == null))
            System.out.println(ptr.word);
        printInterior(ptr.left);
        printInterior(ptr.right);
    }
}

// =========================================
// MAIN MENU
// =========================================
public class Assignment_07_updated {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BSTDictionary dict = new BSTDictionary();
        int ch;

        do {
            System.out.println("\n========= DICTIONARY USING BST =========");
            System.out.println("0) Exit");
            System.out.println("1) Create Tree");
            System.out.println("2) Insert Keyword");
            System.out.println("3) Display (Inorder)");
            System.out.println("4) Search Keyword");
            System.out.println("5) Update Meaning");
            System.out.println("6) Delete Keyword");
            System.out.println("7) Preorder Traversal");
            System.out.println("8) Postorder Traversal");
            System.out.println("9) Print Leaf Nodes");
            System.out.println("10) Print Path to a Keyword");
            System.out.println("11) Print Interior Nodes");
            System.out.print("Enter choice: ");
            ch = sc.nextInt();

            switch (ch) {
                case 0:
                    System.out.println("Terminated.");
                    break;
                case 1:
                    dict.createTree();
                    break;
                case 2:
                    System.out.print("Enter keyword: ");
                    String w = sc.next();
                    sc.nextLine();
                    System.out.print("Enter meaning: ");
                    String m = sc.nextLine();
                    dict.insert(w, m);
                    break;
                case 3:
                    dict.inorder(dict.root);
                    break;
                case 4:
                    System.out.print("Enter keyword to search: ");
                    String key = sc.next();
                    Node res = dict.search(key);
                    if (res == null)
                        System.out.println("❌ Keyword not found!");
                    else
                        System.out.println("Meaning: " + res.meaning);
                    break;
                case 5:
                    System.out.print("Enter keyword to update: ");
                    String k = sc.next();
                    sc.nextLine();
                    System.out.print("Enter new meaning: ");
                    String newM = sc.nextLine();
                    dict.update(k, newM);
                    break;
                case 6:
                    System.out.print("Enter keyword to delete: ");
                    String del = sc.next();
                    dict.root = dict.delete(dict.root, del);
                    System.out.println("✅ Keyword deleted (if existed).");
                    break;
                case 7:
                    dict.preorder(dict.root);
                    break;
                case 8:
                    dict.postorder(dict.root);
                    break;
                case 9:
                    System.out.println("Leaf Nodes:");
                    dict.printLeaves(dict.root);
                    break;
                case 10:
                    System.out.print("Enter keyword to find path: ");
                    String pathKey = sc.next();
                    if (!dict.printPath(dict.root, pathKey))
                        System.out.println("\n❌ Keyword not found!");
                    break;
                case 11:
                    System.out.println("Interior Nodes:");
                    dict.printInterior(dict.root);
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (ch != 0);
        sc.close();
    }
}
