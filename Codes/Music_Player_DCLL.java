/*
Name: Nouf Bamne
Roll No: UCE2025004
Batch: A4
Assignment: 04

PROBLEM STATEMENT: 
Simulate the working of a simple music player playlist using Doubly Circular Linked List (DCLL) demonstrating operations like adding songs, deleting songs, moving forward & backward, searching, and displaying songs.
*/

import java.util.*;

// ==========================
// Node Structure (DCLL)
// ==========================
class DCNode {
    int id;             // Song ID
    String songName;    // Song Name
    String artist;      // Artist Name
    DCNode prev;        // Previous node pointer
    DCNode next;        // Next node pointer

    // Constructor (same as pseudocode)
    public DCNode(int id, String songName, String artist) {
        this.id = id;
        this.songName = songName;
        this.artist = artist;
        this.prev = null; // initially null
        this.next = null; // initially null
    }
}

// ==========================
// Playlist class implementing DCLL
// ==========================
class DCLLPlaylist {
    DCNode head;    // head pointer (start of playlist)
    DCNode tail;    // tail pointer (end of playlist)
    DCNode current; // currently playing song pointer

    public DCLLPlaylist() {
        head = null;   
        tail = null;
        current = null;
    }

    // ================================
    // 1) insertFirst(data) - Pseudocode
    // Ref: DCLL pseudocode section 2
    // ================================
    public void insertFirst(int id, String name, String artist) {
        // Step 1: Create new node
        DCNode temp = new DCNode(id, name, artist);

        // Step 2: If head is null (empty list)
        if (head == null) {
            head = tail = temp;      // head and tail both point to temp
            head.prev = tail;        // circular prev
            tail.next = head;        // circular next
            current = head;          // current song is first
            return;
        }

        // Step 3: Normal insert at beginning
        temp.next = head;           // new node next = old head
        temp.prev = tail;           // new node prev = tail
        head.prev = temp;           // old head prev = new node
        tail.next = temp;           // tail next = new node
        head = temp;                // update head
    }

    // ================================
    // 2) insertLast(data) - Pseudocode
    // Ref: DCLL pseudocode section 3
    // ================================
    public void insertLast(int id, String name, String artist) {
        DCNode temp = new DCNode(id, name, artist);

        // If list empty
        if (head == null) {
            head = tail = temp;
            head.prev = tail;
            tail.next = head;
            current = head;
            return;
        }

        // Insert at end
        tail.next = temp;   // old tail next = temp
        temp.prev = tail;   // temp prev = old tail
        temp.next = head;   // temp next = head
        head.prev = temp;   // head prev = temp
        tail = temp;        // update tail
    }

    // ================================
    // 3) insertAt(index, data) - Pseudocode
    // Ref: DCLL pseudocode section 4
    // ================================
    public void insertAt(int index, int id, String name, String artist) {
        // If index 0, insertFirst
        if (index == 0) {
            insertFirst(id, name, artist);
            return;
        }

        // Step 1: Create node
        DCNode temp = new DCNode(id, name, artist);
        DCNode ptr = head;

        // Step 2: Traverse to position
        for (int i = 0; i < index; i++) {
            ptr = ptr.next;
            if (ptr == head) {          // index out of bounds
                System.out.println("Index out of bounds!");
                return;
            }
        }

        // Step 3: Insert node
        temp.prev = ptr.prev;
        temp.next = ptr;
        ptr.prev.next = temp;
        ptr.prev = temp;

        // Step 4: If inserting at last, update tail
        if (ptr == head) { // if wrapped around
            tail = temp;
        }
    }

    // ================================
    // 4) deleteFirst() - Pseudocode
    // Ref: DCLL pseudocode section 5
    // ================================
    public void deleteFirst() {
        if (head == null) {
            System.out.println("Playlist is empty!");
            return;
        }

        // Only one node
        if (head == tail) {
            head = tail = current = null;
            return;
        }

        head = head.next;       // update head
        head.prev = tail;       // maintain circular prev
        tail.next = head;       // maintain circular next

        if (current == head.prev) {
            current = head;     // move current if deleted
        }
    }

    // ================================
    // 5) deleteLast() - Pseudocode
    // Ref: DCLL pseudocode section 6
    // ================================
    public void deleteLast() {
        if (head == null) {
            System.out.println("Playlist is empty!");
            return;
        }

        // Only one node
        if (head == tail) {
            head = tail = current = null;
            return;
        }

        tail = tail.prev;       // update tail
        tail.next = head;       // circular next
        head.prev = tail;       // circular prev

        if (current == tail.next) {
            current = tail;     // move current if deleted
        }
    }

    // ================================
    // 6) deleteAt(index) - Pseudocode
    // Ref: DCLL pseudocode section 7
    // ================================
    public void deleteAt(int index) {
        if (head == null) {
            System.out.println("Playlist is empty!");
            return;
        }

        if (index == 0) {
            deleteFirst();
            return;
        }

        DCNode ptr = head;
        for (int i = 0; i < index; i++) {
            ptr = ptr.next;
            if (ptr == head) {
                System.out.println("Index out of bounds!");
                return;
            }
        }

        ptr.prev.next = ptr.next;
        ptr.next.prev = ptr.prev;

        if (ptr == tail) {   // update tail if last deleted
            tail = ptr.prev;
        }

        if (current == ptr) { // move current
            current = ptr.next;
        }
    }

    // ================================
    // 7) search(value) - Pseudocode
    // Ref: DCLL pseudocode section 8
    // ================================
    public void search(int id) {
        if (head == null) {
            System.out.println("Playlist is empty!");
            return;
        }

        DCNode ptr = head;
        int index = 0;
        do {
            if (ptr.id == id) {
                System.out.println("Song found at index: " + index + " | " + ptr.songName);
                return;
            }
            ptr = ptr.next;
            index++;
        } while (ptr != head);

        System.out.println("Song not found!");
    }

    // ================================
    // 8) displayForward() - Pseudocode
    // Ref: DCLL pseudocode section 9
    // ================================
    public void displayForward() {
        if (head == null) {
            System.out.println("Playlist is empty!");
            return;
        }

        DCNode ptr = head;
        System.out.println("\n--- Playlist (Forward) ---");
        do {
            System.out.println("ID: " + ptr.id + " | Song: " + ptr.songName + " | Artist: " + ptr.artist);
            ptr = ptr.next;
        } while (ptr != head);
    }

    // ================================
    // 9) displayReverse() - Pseudocode
    // Ref: DCLL pseudocode section 10
    // ================================
    public void displayReverse() {
        if (tail == null) {
            System.out.println("Playlist is empty!");
            return;
        }

        DCNode ptr = tail;
        System.out.println("\n--- Playlist (Reverse) ---");
        do {
            System.out.println("ID: " + ptr.id + " | Song: " + ptr.songName + " | Artist: " + ptr.artist);
            ptr = ptr.prev;
        } while (ptr != tail);
    }

    // ================================
    // 10) Play Next Song
    // ================================
    public void playNext() {
        if (current == null) {
            System.out.println("No songs in playlist!");
            return;
        }
        current = current.next;  // move forward (circular)
        showCurrent();
    }

    // ================================
    // 11) Play Previous Song
    // ================================
    public void playPrevious() {
        if (current == null) {
            System.out.println("No songs in playlist!");
            return;
        }
        current = current.prev;  // move backward (circular)
        showCurrent();
    }

    // ================================
    // 12) Show Current Song
    // ================================
    public void showCurrent() {
        if (current == null) {
            System.out.println("No song is playing currently!");
            return;
        }
        System.out.println("\n🎵 Now Playing → ID: " + current.id + ", Song: " + current.songName + ", Artist: " + current.artist);
    }

    // ================================
    // 13) isEmpty() - Pseudocode
    // ================================
    public boolean isEmpty() {
        return head == null;
    }
}

// ==========================
// Main Class
// ==========================
public class Music_Player_DCLL {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DCLLPlaylist playlist = new DCLLPlaylist();
        int choice;

        System.out.println("🎶 Welcome to Spotify Simulation (DCLL) 🎶");

        do {
            System.out.println("\n--- Music Player Menu ---");
            System.out.println("1. Add Song at Start");
            System.out.println("2. Add Song at End");
            System.out.println("3. Add Song at Index");
            System.out.println("4. Delete First Song");
            System.out.println("5. Delete Last Song");
            System.out.println("6. Delete Song at Index");
            System.out.println("7. Search Song by ID");
            System.out.println("8. Show Playlist (Forward)");
            System.out.println("9. Show Playlist (Reverse)");
            System.out.println("10. Play Next Song");
            System.out.println("11. Play Previous Song");
            System.out.println("12. Show Current Song");
            System.out.println("13. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Song ID: ");
                    int id1 = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Song Name: ");
                    String name1 = sc.nextLine();
                    System.out.print("Enter Artist Name: ");
                    String artist1 = sc.nextLine();
                    playlist.insertFirst(id1, name1, artist1);
                    break;

                case 2:
                    System.out.print("Enter Song ID: ");
                    int id2 = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Song Name: ");
                    String name2 = sc.nextLine();
                    System.out.print("Enter Artist Name: ");
                    String artist2 = sc.nextLine();
                    playlist.insertLast(id2, name2, artist2);
                    break;

                case 3:
                    System.out.print("Enter Index to insert: ");
                    int idx = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Song ID: ");
                    int id3 = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Song Name: ");
                    String name3 = sc.nextLine();
                    System.out.print("Enter Artist Name: ");
                    String artist3 = sc.nextLine();
                    playlist.insertAt(idx, id3, name3, artist3);
                    break;

                case 4:
                    playlist.deleteFirst();
                    break;

                case 5:
                    playlist.deleteLast();
                    break;

                case 6:
                    System.out.print("Enter Index to delete: ");
                    int delIdx = sc.nextInt();
                    playlist.deleteAt(delIdx);
                    break;

                case 7:
                    System.out.print("Enter Song ID to search: ");
                    int searchId = sc.nextInt();
                    playlist.search(searchId);
                    break;

                case 8:
                    playlist.displayForward();
                    break;

                case 9:
                    playlist.displayReverse();
                    break;

                case 10:
                    playlist.playNext();
                    break;

                case 11:
                    playlist.playPrevious();
                    break;

                case 12:
                    playlist.showCurrent();
                    break;
                
                                case 13:
                    System.out.println("Exiting... Thanks for using Spotify Simulation (DCLL)!");
                    break;

                default:
                    System.out.println("Invalid choice, try again!");
            }

        } while (choice != 13);

        sc.close();
    }
}

