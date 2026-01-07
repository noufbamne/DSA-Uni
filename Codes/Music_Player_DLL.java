/*
Name: Nouf Bamne
Roll No: UCE2025004
Batch: A4
Assignment: 03

PROBLEM STATEMENT: 
Simulate the working of a simple music player playlist using Doubly Linked List demonstrating operations like adding songs, deleting songs, moving forward & backward through the playlist, and searching songs.
*/

import java.util.*;

// Node structure for DLL
class DNode {
    int id;
    String songName;
    String artist;
    DNode prev;
    DNode next;

    // Constructor
    public DNode(int id, String songName, String artist) {
        this.id = id;
        this.songName = songName;
        this.artist = artist;
        this.prev = null;
        this.next = null;
    }
}

// Playlist class implementing DLL methods
class Playlist {
    DNode head;
    DNode tail;
    DNode current;

    // Constructor
    public Playlist() {
        head = null;
        tail = null;
        current = null;
    }

    // ================================
    // 1) insertFirst(data) - Pseudocode step 2
    // ================================
    public void insertFirst(int id, String name, String artist) {
        DNode temp = new DNode(id, name, artist); // create new node
        temp.next = head;
        if (head != null) {
            head.prev = temp;
        }
        head = temp;
        if (tail == null) {
            tail = temp;
        }
        if (current == null) {
            current = head;
        }
    }

    // ================================
    // 2) insertLast(data) - Pseudocode step 3
    // ================================
    public void insertLast(int id, String name, String artist) {
        DNode temp = new DNode(id, name, artist); // create new node
        if (head == null) {
            head = tail = temp;
            current = head;
            return;
        }
        DNode ptr = head;
        while (ptr.next != null) { // traverse to last node
            ptr = ptr.next;
        }
        ptr.next = temp;
        temp.prev = ptr;
        tail = temp;
    }

    // ================================
    // 3) insertAt(index, data) - Pseudocode step 4
    // ================================
    public void insertAt(int index, int id, String name, String artist) {
        if (index == 0) {
            insertFirst(id, name, artist);
            return;
        }
        DNode temp = new DNode(id, name, artist);
        DNode ptr = head;
        for (int i = 0; i < index; i++) {
            if (ptr == null) {
                System.out.println("Invalid index!");
                return;
            }
            ptr = ptr.next;
        }
        if (ptr == null) { // insert at end
            insertLast(id, name, artist);
            return;
        }
        temp.prev = ptr.prev;
        temp.next = ptr;
        if (ptr.prev != null) {
            ptr.prev.next = temp;
        }
        ptr.prev = temp;
        if (index == 0) {
            head = temp;
        }
    }

    // ================================
    // 4) deleteFirst() - Pseudocode step 5
    // ================================
    public void deleteFirst() {
        if (head == null) {
            System.out.println("Playlist is empty!");
            return;
        }
        head = head.next;
        if (head != null) {
            head.prev = null;
        } else {
            tail = null;
        }
        if (current == head) {
            current = head;
        }
    }

    // ================================
    // 5) deleteLast() - Pseudocode step 6
    // ================================
    public void deleteLast() {
        if (head == null) {
            System.out.println("Playlist is empty!");
            return;
        }
        if (head.next == null) {
            head = tail = current = null;
            return;
        }
        DNode ptr = head;
        while (ptr.next != null) {
            ptr = ptr.next;
        }
        ptr.prev.next = null;
        tail = ptr.prev;
        if (current == ptr) {
            current = tail;
        }
    }

    // ================================
    // 6) deleteAt(index) - Pseudocode step 7
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
        DNode ptr = head;
        for (int i = 0; i < index; i++) {
            if (ptr == null) {
                System.out.println("Invalid index!");
                return;
            }
            ptr = ptr.next;
        }
        if (ptr == null) {
            System.out.println("Invalid index!");
            return;
        }
        if (ptr.prev != null) {
            ptr.prev.next = ptr.next;
        }
        if (ptr.next != null) {
            ptr.next.prev = ptr.prev;
        }
        if (current == ptr) {
            current = (ptr.next != null) ? ptr.next : ptr.prev;
        }
    }

    // ================================
    // 7) deleteSongByID() - custom method similar to deleteAt
    // ================================
    public void deleteSongByID(int id) {
        if (head == null) {
            System.out.println("Playlist is empty!");
            return;
        }
        DNode ptr = head;
        while (ptr != null && ptr.id != id) {
            ptr = ptr.next;
        }
        if (ptr == null) {
            System.out.println("Song with ID " + id + " not found!");
            return;
        }
        if (ptr == head) {
            deleteFirst();
        } else if (ptr == tail) {
            deleteLast();
        } else {
            ptr.prev.next = ptr.next;
            ptr.next.prev = ptr.prev;
            if (current == ptr) {
                current = (ptr.next != null) ? ptr.next : ptr.prev;
            }
        }
        System.out.println("Song deleted successfully!");
    }

    // ================================
    // 8) search(value) - Pseudocode step 8
    // ================================
    public void search(int id) {
        DNode ptr = head;
        int index = 0;
        while (ptr != null) {
            if (ptr.id == id) {
                System.out.println("Song found at index: " + index);
                return;
            }
            ptr = ptr.next;
            index++;
        }
        System.out.println("Song not found!");
    }

    // ================================
    // 9) displayForward() - Pseudocode step 9
    // ================================
    public void displayForward() {
        if (head == null) {
            System.out.println("Playlist is empty!");
            return;
        }
        DNode ptr = head;
        System.out.println("\n--- Playlist (Forward) ---");
        while (ptr != null) {
            System.out.println("ID: " + ptr.id + " | Song: " + ptr.songName + " | Artist: " + ptr.artist);
            ptr = ptr.next;
        }
    }

    // ================================
    // 10) displayReverse() - Pseudocode step 10
    // ================================
    public void displayReverse() {
        if (head == null) {
            System.out.println("Playlist is empty!");
            return;
        }
        DNode ptr = tail;
        System.out.println("\n--- Playlist (Reverse) ---");
        while (ptr != null) {
            System.out.println("ID: " + ptr.id + " | Song: " + ptr.songName + " | Artist: " + ptr.artist);
            ptr = ptr.prev;
        }
    }

    // ================================
    // 11) isEmpty() - Pseudocode step 11
    // ================================
    public boolean isEmpty() {
        return head == null;
    }

    // ================================
    // Play Next Song
    // ================================
    public void playNext() {
        if (current == null) {
            System.out.println("No songs in the playlist!");
            return;
        }
        if (current.next != null) {
            current = current.next;
        } else {
            System.out.println("You are at the last song!");
        }
    }

    // ================================
    // Play Previous Song
    // ================================
    public void playPrevious() {
        if (current == null) {
            System.out.println("No songs in the playlist!");
            return;
        }
        if (current.prev != null) {
            current = current.prev;
        } else {
            System.out.println("You are at the first song!");
        }
    }

    // ================================
    // Show Current Song
    // ================================
    public void showCurrent() {
        if (current == null) {
            System.out.println("No song is playing currently!");
        } else {
            System.out.println("\n🎵 Now Playing → ID: " + current.id +
                    ", Song: " + current.songName + ", Artist: " + current.artist);
        }
    }
}

// ================================
// Main Program
// ================================
public class Music_Player_DLL {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Playlist playlist = new Playlist();
        int choice;

        System.out.println("🎶 Welcome to Spotify Simulation using Doubly Linked List 🎶");

        do {
            System.out.println("\n--- Music Player Menu ---");
            System.out.println("1. Add Song at End");
            System.out.println("2. Add Song at Start");
            System.out.println("3. Insert Song at Index");
            System.out.println("4. Delete First Song");
            System.out.println("5. Delete Last Song");
            System.out.println("6. Delete Song at Index");
            System.out.println("7. Delete Song by ID");
            System.out.println("8. Play Next Song");
            System.out.println("9. Play Previous Song");
            System.out.println("10. Show Current Song");
            System.out.println("11. Search Song by ID");
            System.out.println("12. Display Playlist Forward");
            System.out.println("13. Display Playlist Reverse");
            System.out.println("14. Check if Playlist is Empty");
            System.out.println("15. Exit");
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
                    playlist.insertLast(id1, name1, artist1);
                    System.out.println("Song added at end successfully!");
                    break;
                case 2:
                    System.out.print("Enter Song ID: ");
                    int id2 = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Song Name: ");
                    String name2 = sc.nextLine();
                    System.out.print("Enter Artist Name: ");
                    String artist2 = sc.nextLine();
                    playlist.insertFirst(id2, name2, artist2);
                    System.out.println("Song added at start successfully!");
                    break;
                case 3:
                    System.out.print("Enter Index to Insert: ");
                    int index = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Song ID: ");
                    int id3 = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Song Name: ");
                    String name3 = sc.nextLine();
                    System.out.print("Enter Artist Name: ");
                    String artist3 = sc.nextLine();
                    playlist.insertAt(index, id3, name3, artist3);
                    System.out.println("Song added at index " + index + " successfully!");
                    break;
                case 4:
                    playlist.deleteFirst();
                    break;
                case 5:
                    playlist.deleteLast();
                    break;
                case 6:
                    System.out.print("Enter Index to Delete: ");
                    int delIndex = sc.nextInt();
                    sc.nextLine();
                    playlist.deleteAt(delIndex);
                    break;
                case 7:
                    System.out.print("Enter Song ID to Delete: ");
                    int delID = sc.nextInt();
                    sc.nextLine();
                    playlist.deleteSongByID(delID);
                    break;
                case 8:
                    playlist.playNext();
                    playlist.showCurrent();
                    break;
                case 9:
                    playlist.playPrevious();
                    playlist.showCurrent();
                    break;
                case 10:
                    playlist.showCurrent();
                    break;
                case 11:
                    System.out.print("Enter Song ID to Search: ");
                    int searchID = sc.nextInt();
                    sc.nextLine();
                    playlist.search(searchID);
                    break;
                case 12:
                    playlist.displayForward();
                    break;
                case 13:
                    playlist.displayReverse();
                    break;
                case 14:
                    if (playlist.isEmpty()) {
                        System.out.println("Playlist is empty!");
                    } else {
                        System.out.println("Playlist has songs.");
                    }
                    break;
                case 15:
                    System.out.println("Exiting... Thanks for using Spotify Simulation!");
                    break;
                default:
                    System.out.println("Invalid choice, try again!");
            }

        } while (choice != 15);

        sc.close();
    }
}
