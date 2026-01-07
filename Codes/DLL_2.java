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

    Scanner sc = new Scanner(System.in);   // SINGLE SHARED SCANNER (Option B)

    // Constructor
    public Playlist() {
        head = null;
        tail = null;
        current = null;
    }

    // ================================
    // 1) insertFirst()
    // ================================
    public void insertFirst() {

        System.out.print("Enter Song ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Song Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Artist Name: ");
        String artist = sc.nextLine();

        DNode temp = new DNode(id, name, artist);

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

        System.out.println("Song added at start successfully!");
    }

    // ================================
    // 2) insertLast()
    // ================================
    public void insertLast() {

    System.out.print("Enter Song ID: ");
    int id = sc.nextInt();
    sc.nextLine();

    System.out.print("Enter Song Name: ");
    String name = sc.nextLine();

    System.out.print("Enter Artist Name: ");
    String artist = sc.nextLine();

    DNode temp = new DNode(id, name, artist);

    if (head == null) {
        head = temp;
        tail = temp;
        current = head;
        System.out.println("Song added at end successfully!");
        return;
    }

    tail.next = temp;
    temp.prev = tail;
    tail = temp;

    System.out.println("Song added at end successfully!");
}


    // ================================
    // 3) insertAt(index)
    // ================================
    public void insertAt() {

        System.out.print("Enter Index to Insert: ");
        int index = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Song ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Song Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Artist Name: ");
        String artist = sc.nextLine();

        if (index == 0) {
            insertFirst();
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
        if (ptr == null) {

            tail.next = temp;
            temp.prev = tail;
            tail = temp;

            System.out.println("Song added at index " + index + " successfully!");
            return;
        }
        temp.prev = ptr.prev;
        temp.next = ptr;

        if (ptr.prev != null) {
            ptr.prev.next = temp;
        }

        ptr.prev = temp;

        System.out.println("Song added at index " + index + " successfully!");
    }

    // ================================
    // 4) deleteFirst()
    // ================================
    public void deleteFirst() {

        if (head == null) {
            System.out.println("Playlist is empty!");
            return;
        }

        head = head.next;

        if (head != null) {
            head.prev = null;
        } 
        
        if (current == head) {
            current = head;
        }

        System.out.println("First song deleted!");
    }

    // ================================
    // 5) deleteLast()
    // ================================
    public void deleteLast() {

    if (tail == null) {
        System.out.println("Playlist is empty!");
        return;
    }

    if (head == tail) {  
        head = null;
        tail = null;
        current = null;
        System.out.println("Last song deleted!");
        return;
    }

    if (current == tail) {
        current = tail.prev;
    }

    tail = tail.prev;
    tail.next = null;

    System.out.println("Last song deleted!");
}

//6. delet at(index)
public void deleteAt() {

    System.out.print("Enter Index to Delete: ");
    int index = sc.nextInt();
    sc.nextLine();

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

    // ⭐ NEW OPTIMIZATION → deleting last node
    if (ptr == tail) {
        deleteLast();
        return;
    }

    // Normal middle deletion
    if (ptr.prev != null) {
        ptr.prev.next = ptr.next;
    }

    if (ptr.next != null) {
        ptr.next.prev = ptr.prev;
    }

    if (current == ptr) {
        if (ptr.next != null) {
            current = ptr.next;
        } else {
            current = ptr.prev;
        }
    }

    System.out.println("Song deleted at index!");
}

    // ================================
    // 7) deleteSongByID()
    // ================================
    public void deleteSongByID() {

        System.out.print("Enter Song ID to Delete: ");
        int id = sc.nextInt();
        sc.nextLine();

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
        } 
        else if (ptr == tail) {
            deleteLast();
        } 
        else {
            ptr.prev.next = ptr.next;
            ptr.next.prev = ptr.prev;

            if (current == ptr) {
                if (ptr.next != null) {
                    current = ptr.next;
                } else {
                    current = ptr.prev;
                }
            }
        }

        System.out.println("Song deleted successfully!");
    }

    // ================================
    // 8) search()
    // ================================
    public void search() {

        System.out.print("Enter Song ID to Search: ");
        int id = sc.nextInt();
        sc.nextLine();

        DNode ptr = head;
        int index = 0;

        while (ptr != null) {

            if (ptr.id == id) {
                System.out.println("Song found at index: " + index);
                return;
            }

            ptr = ptr.next;
            index = index + 1;
        }

        System.out.println("Song not found!");
    }

    // ================================
    // 9) displayForward()
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
    // 10) displayReverse()
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
    // 11) isEmpty()
    // ================================
    public boolean isEmpty() {
        if (head == null) {
            return true;
        } else {
            return false;
        }
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
        } 
        else {
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
        } 
        else {
            System.out.println("You are at the first song!");
        }
    }

    // ================================
    // Show Current Song
    // ================================
    public void showCurrent() {

        if (current == null) {
            System.out.println("No song is playing currently!");
        } 
        else {
            System.out.println("\n🎵 Now Playing → ID: " + current.id +
                    ", Song: " + current.songName + ", Artist: " + current.artist);
        }
    }
}

// ================================
// Main Program
// ================================
public class DLL_2 {
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
                    playlist.insertLast();
                    break;

                case 2:
                    playlist.insertFirst();
                    break;

                case 3:
                    playlist.insertAt();
                    break;

                case 4:
                    playlist.deleteFirst();
                    break;

                case 5:
                    playlist.deleteLast();
                    break;

                case 6:
                    playlist.deleteAt();
                    break;

                case 7:
                    playlist.deleteSongByID();
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
                    playlist.search();
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
                    } 
                    else {
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
