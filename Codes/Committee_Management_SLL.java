/*
Name: Nouf Bamne
Roll No: UCE2025004
Batch: A4
Assignment 02

Singly Linked List (SLL) implementation for Code Club member management.
All operations follow the SLL pseudocode structure with detailed comments.
*/

import java.util.*;

// Node structure: Member
class Member {
    int memberId;       // corresponds to 'data' in pseudocode
    String name;
    String address;
    String position;
    Member next;        // pointer to next node, exactly like pseudocode 'next'

    Member(int id, String name, String addr, String pos) {
        this.memberId = id;
        this.name = name;
        this.address = addr;
        this.position = pos;
        this.next = null; // initially points to null
    }
}

// SLL implementation: CodeClub
class CodeClub {
    Member head;        // head pointer
    Scanner sc = new Scanner(System.in);

    CodeClub() {
        head = null;    // initialize empty list
    }

    // ===============================
    // Check if list is empty
    // ===============================
    boolean isEmpty() {
        // Pseudocode: if head == null return true else false
        return head == null;
    }

    // ===============================
    // Display all members
    // ===============================
    void display() {
        if (isEmpty()) { // pseudocode: if head == null
            System.out.println("List is empty!");
            return;
        }

        Member ptr = head; // pseudocode: ptr = head
        System.out.println("\n--- Code Club Members ---");
        while (ptr != null) { // pseudocode: while ptr != null
            System.out.println("ID: " + ptr.memberId + ", Name: " + ptr.name +
                    ", Address: " + ptr.address + ", Position: " + ptr.position);
            ptr = ptr.next; // move to next node
        }
    }

    // ===============================
    // insertFirst(data)
    // ===============================
    void insertFirst() {
        System.out.print("Enter Member ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Address: ");
        String addr = sc.nextLine();
        System.out.print("Enter Position: ");
        String pos = sc.nextLine();

        Member newMember = new Member(id, name, addr, pos);

        newMember.next = head; // pseudocode: N.next = head
        head = newMember;      // pseudocode: head = N
        System.out.println("Member added at start successfully!");
    }

    // ===============================
    // insertLast(data)
    // ===============================
    void insertLast() {
        System.out.print("Enter Member ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Address: ");
        String addr = sc.nextLine();
        System.out.print("Enter Position: ");
        String pos = sc.nextLine();

        Member newMember = new Member(id, name, addr, pos);

        if (isEmpty()) { // pseudocode: if head == null
            head = newMember;
            System.out.println("Member added at end successfully!");
            return;
        }

        Member ptr = head; // pseudocode: ptr = head
        while (ptr.next != null) { // pseudocode: while ptr.next != null
            ptr = ptr.next;         // move to last node
        }
        ptr.next = newMember;      // append at end
        System.out.println("Member added at end successfully!");
    }

    // ===============================
    // insertAt(index, data)
    // ===============================
    void insertAt() {
        System.out.print("Enter index to insert at (0-based): ");
        int index = sc.nextInt();
        sc.nextLine();

        if (index == 0) { // pseudocode: if index == 0
            insertFirst();
            return;
        }

        System.out.print("Enter Member ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Address: ");
        String addr = sc.nextLine();
        System.out.print("Enter Position: ");
        String pos = sc.nextLine();

        Member newMember = new Member(id, name, addr, pos);

        Member ptr = head; // pseudocode: ptr = head
        for (int i = 0; i < index - 1; i++) { // pseudocode: for i = 0 to index-2
            if (ptr == null) {
                System.out.println("Invalid index");
                return;
            }
            ptr = ptr.next;
        }

        if (ptr == null) { // safety check
            System.out.println("Invalid index");
            return;
        }

        newMember.next = ptr.next; // pseudocode: N.next = ptr.next
        ptr.next = newMember;      // pseudocode: ptr.next = N
        System.out.println("Member added at index " + index + " successfully!");
    }

    // ===============================
    // deleteFirst()
    // ===============================
    void deleteFirst() {
        if (isEmpty()) { // pseudocode: if head == null
            System.out.println("List is empty!");
            return;
        }

        head = head.next; // pseudocode: head = head.next
        System.out.println("First member deleted successfully!");
    }

    // ===============================
    // deleteLast()
    // ===============================
    void deleteLast() {
        if (isEmpty()) { // pseudocode: if head == null
            System.out.println("List is empty!");
            return;
        }

        if (head.next == null) { // only one node
            head = null;
            System.out.println("Last member deleted successfully!");
            return;
        }

        Member ptr = head;
        while (ptr.next.next != null) { // pseudocode: while ptr.next.next != null
            ptr = ptr.next;
        }
        ptr.next = null; // remove last node
        System.out.println("Last member deleted successfully!");
    }

    // ===============================
    // deleteAt(index)
    // ===============================
    void deleteAt() {
        System.out.print("Enter index to delete (0-based): ");
        int index = sc.nextInt();
        sc.nextLine();

        if (isEmpty()) {
            System.out.println("List is empty!");
            return;
        }

        if (index == 0) { // pseudocode: if index==0
            deleteFirst();
            return;
        }

        Member ptr = head;
        for (int i = 0; i < index - 1; i++) { // pseudocode: for i=0 to index-2
            if (ptr.next == null) {
                System.out.println("Invalid index");
                return;
            }
            ptr = ptr.next;
        }

        if (ptr.next == null) { // safety check
            System.out.println("Invalid index");
            return;
        }

        ptr.next = ptr.next.next; // pseudocode: ptr.next = ptr.next.next
        System.out.println("Member at index " + index + " deleted successfully!");
    }

    // ===============================
    // search(value) by memberId
    // ===============================
    void search() {
        if (isEmpty()) {
            System.out.println("List is empty!");
            return;
        }

        System.out.print("Enter Member ID to search: ");
        int value = sc.nextInt();
        sc.nextLine();

        Member ptr = head; // pseudocode: ptr = head
        int index = 0;     // pseudocode: index = 0
        while (ptr != null) { // pseudocode: while ptr != null
            if (ptr.memberId == value) { // pseudocode: if ptr.data == value
                System.out.println("Found at index: " + index);
                System.out.println("Name: " + ptr.name + ", Address: " + ptr.address + ", Position: " + ptr.position);
                return;
            }
            ptr = ptr.next; // move to next
            index++;
        }

        System.out.println("Member ID not found");
    }

    // ===============================
    // update(memberId)
    // ===============================
    void update() {
        if (isEmpty()) {
            System.out.println("List is empty!");
            return;
        }

        System.out.print("Enter Member ID to update: ");
        int value = sc.nextInt();
        sc.nextLine();

        Member ptr = head;
        while (ptr != null && ptr.memberId != value) { // search node
            ptr = ptr.next;
        }

        if (ptr == null) {
            System.out.println("Member not found!");
            return;
        }

        // Update fields
        System.out.print("Enter new Name: ");
        ptr.name = sc.nextLine();
        System.out.print("Enter new Address: ");
        ptr.address = sc.nextLine();
        System.out.print("Enter new Position: ");
        ptr.position = sc.nextLine();
        System.out.println("Member updated successfully!");
    }
}

// ===============================
// Main Program
// ===============================
public class Committee_Management_SLL {
    public static void main(String[] args) {
        CodeClub club = new CodeClub();
        Scanner sc = new Scanner(System.in);
        char ans;

        do {
            System.out.println("\n--- Code Club Menu ---");
            System.out.println("1. Insert Member at Start");
            System.out.println("2. Insert Member at End");
            System.out.println("3. Insert Member at Index");
            System.out.println("4. Delete First Member");
            System.out.println("5. Delete Last Member");
            System.out.println("6. Delete Member at Index");
            System.out.println("7. Search Member by ID");
            System.out.println("8. Update Member by ID");
            System.out.println("9. Display Members");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> club.insertFirst();
                case 2 -> club.insertLast();
                case 3 -> club.insertAt();
                case 4 -> club.deleteFirst();
                case 5 -> club.deleteLast();
                case 6 -> club.deleteAt();
                case 7 -> club.search();
                case 8 -> club.update();
                case 9 -> club.display();
                case 0 -> {
                    System.out.println("Exiting... Thank you!");
                    return;
                }
                default -> System.out.println("Invalid option. Try again!");
            }

            System.out.print("Do you want to continue? (Y/N): ");
            ans = sc.next().charAt(0);

        } while (ans == 'Y' || ans == 'y');
    }
}
