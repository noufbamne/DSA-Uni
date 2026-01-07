// Updated code with input taken inside functions instead of switch
// Other logic untouched

import java.util.*;

class DCNode {
    int id;
    String songName;
    String artist;
    DCNode prev;
    DCNode next;

    public DCNode(int id, String songName, String artist) {
        this.id = id;
        this.songName = songName;
        this.artist = artist;
        this.prev = null;
        this.next = null;
    }
}

class DCLLPlaylist {
    DCNode head;
    DCNode tail;
    DCNode current;
    Scanner sc = new Scanner(System.in);

    public DCLLPlaylist() {
        head = null;
        tail = null;
        current = null;
    }

    public void insertFirst() {
        System.out.print("Enter Song ID: ");
        int id = sc.nextInt(); sc.nextLine();
        System.out.print("Enter Song Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Artist Name: ");
        String artist = sc.nextLine();

        DCNode temp = new DCNode(id, name, artist);

        if (head == null) {
            head = tail = temp;
            head.prev = tail;
            tail.next = head;
            current = head;
            return;
        }

        temp.next = head;
        temp.prev = tail;
        head.prev = temp;
        tail.next = temp;
        head = temp;
    }

    public void insertLast() {
        System.out.print("Enter Song ID: ");
        int id = sc.nextInt(); sc.nextLine();
        System.out.print("Enter Song Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Artist Name: ");
        String artist = sc.nextLine();

        DCNode temp = new DCNode(id, name, artist);

        if (head == null) {
            head = tail = temp;
            head.prev = tail;
            tail.next = head;
            current = head;
            return;
        }

        tail.next = temp;
        temp.prev = tail;
        temp.next = head;
        head.prev = temp;
        tail = temp;
    }

    public void insertAt() {
        System.out.print("Enter Index to insert: ");
        int index = sc.nextInt(); sc.nextLine();
        System.out.print("Enter Song ID: ");
        int id = sc.nextInt(); sc.nextLine();
        System.out.print("Enter Song Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Artist Name: ");
        String artist = sc.nextLine();

        if (index == 0) {
            insertFirst();
            return;
        }

        DCNode temp = new DCNode(id, name, artist);
        DCNode ptr = head;

        for (int i = 0; i < index; i++) {
            ptr = ptr.next;
            if (ptr == head) {
                System.out.println("Index out of bounds!");
                return;
            }
        }

        temp.prev = ptr.prev;
        temp.next = ptr;
        ptr.prev.next = temp;
        ptr.prev = temp;

        if (ptr == head) {
            tail = temp;
        }
    }

    public void deleteFirst() {
        if (head == null) {
            System.out.println("Playlist is empty!");
            return;
        }

        if (head == tail) {
            head = tail = current = null;
            return;
        }

        if (current == head)
            current = head.next;

        head = head.next;
        head.prev = tail;
        tail.next = head;
    }

    public void deleteLast() {
        if (head == null) {
            System.out.println("Playlist is empty!");
            return;
        }

        if (head == tail) {
            head = tail = current = null;
            return;
        }

        if (current == tail)
            current = tail.prev;

        tail = tail.prev;
        tail.next = head;
        head.prev = tail;
    }

    public void deleteAt() {
        System.out.print("Enter Index to delete: ");
        int index = sc.nextInt();

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

        if (ptr == tail) {
            tail = ptr.prev;
        }

        if (current == ptr) {
            current = ptr.next;
        }
    }

    public void search() {
        System.out.print("Enter Song ID to search: ");
        int id = sc.nextInt();

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

    public void playNext() {
        if (current == null) {
            System.out.println("No songs in playlist!");
            return;
        }
        current = current.next;
        showCurrent();
    }

    public void playPrevious() {
        if (current == null) {
            System.out.println("No songs in playlist!");
            return;
        }
        current = current.prev;
        showCurrent();
    }

    public void showCurrent() {
        if (current == null) {
            System.out.println("No song is playing currently!");
            return;
        }
        System.out.println("\n🎵 Now Playing → ID: " + current.id + ", Song: " + current.songName + ", Artist: " + current.artist);
    }

    public boolean isEmpty() {
        return head == null;
    }
}

public class DCLL_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DCLLPlaylist playlist = new DCLLPlaylist();
        int choice;
        char ans;
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

            switch (choice) {
                case 1: playlist.insertFirst(); break;
                case 2: playlist.insertLast(); break;
                case 3: playlist.insertAt(); break;
                case 4: playlist.deleteFirst(); break;
                case 5: playlist.deleteLast(); break;
                case 6: playlist.deleteAt(); break;
                case 7: playlist.search(); break;
                case 8: playlist.displayForward(); break;
                case 9: playlist.displayReverse(); break;
                case 10: playlist.playNext(); break;
                case 11: playlist.playPrevious(); break;
                case 12: playlist.showCurrent(); break;
                case 13: System.out.println("Exiting..."); return;
                default: System.out.println("Invalid Choice!");
            }

            System.out.println("Would you like to comtinue: (Y/N): ");
            ans= sc.next().charAt(0);

        } while (ans == 'Y' || ans == 'y');
    }
}
