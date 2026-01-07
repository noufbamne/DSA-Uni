/*
Name: Nouf Bamne
Roll No: UCE2025004
Batch: A4
Assignment 01

This version includes detailed comments mapping pseudocode to Java code.
Every main step from pseudocode is mentioned, with explanations where Java differs.
*/

import java.util.*;

class Book {
    String BookName;
    String AuthorName;
    int ISBNNo;

    Book(String bookName, String authorName, int isbnNo) {
        this.BookName = bookName;
        this.AuthorName = authorName;
        this.ISBNNo = isbnNo;
    }

    @Override
    public String toString() {
        return "Book Name: " + BookName + ", Author: " + AuthorName + ", ISBN: " + ISBNNo;
    }
}

class Library {
    static Scanner sc = new Scanner(System.in);
    Book[] theBooks = new Book[50];  // Pseudocode: arr is array
    int count = 0;                   // Java adaptation: count tracks filled elements (pseudocode length(arr) concept)

    // --------------------------------------------------
    // 1) Add a new book
    // Pseudocode: Not explicitly in pseudocode, but add element in array
    // Java: Using count to track first empty index
    // --------------------------------------------------
    void addBook() {
        if (count >= theBooks.length) {  // Pseudocode: check array full
            System.out.println("Library is full. Cannot add more books.");
            return;
        }

        System.out.print("Enter Book Name: ");
        String name = sc.nextLine();   // Pseudocode: read input
        System.out.print("Enter Author Name: ");
        String author = sc.nextLine(); // Pseudocode: read input
        System.out.print("Enter ISBN No: ");
        int isbn = sc.nextInt();
        sc.nextLine();                 // Java: consume newline

        theBooks[count++] = new Book(name, author, isbn); 
        // Pseudocode: insert into arr
        // Java: placed at index count, then increment count
        System.out.println("Book Added Successfully!");
    }

    // --------------------------------------------------
    // Display all books
    // Pseudocode: for i = 0 to length(arr)-1
    // Java: for i = 0 to count-1 (since only filled elements)
    // --------------------------------------------------
    void displayBooks() {
        if (count == 0) {  // Pseudocode: arr empty check
            System.out.println("No books available.");
            return;
        }

        System.out.println("\n--- Book List ---");
        for (int i = 0; i < count; i++) { 
            // Pseudocode: loop through all elements
            // Java: using count to avoid nulls in unused array slots
            System.out.println(theBooks[i]);
        }
    }

    // --------------------------------------------------
    // Algorithm BinarySearch(arr, target)
    // Pre: arr is sorted array
    // Post: return index if found, else -1
    // --------------------------------------------------
    void searchISBN_Binary() {
        if (count == 0) { // Pseudocode: check if arr empty
            System.out.println("No books available.");
            return;
        }

        // Sort before binary search
        bubbleSort(); // Pseudocode: arr must be sorted

        System.out.print("Enter ISBN to search: ");
        int target = sc.nextInt();
        sc.nextLine();

        int start = 0;            // Pseudocode: start = 0
        int end = count - 1;      // Pseudocode: end = length(arr)-1
                                   // Java: using count-1 to match filled elements
        int result = -1;          // Pseudocode: return -1 if not found

        while (start <= end) {    // Pseudocode: while start <= end
            int mid = start + (end - start) / 2; // Pseudocode: mid calculation

            if (theBooks[mid].ISBNNo == target) { // Pseudocode: if arr[mid] = target
                result = mid;                     // Java: store found index
                break;
            } else if (target < theBooks[mid].ISBNNo) { // Pseudocode: else if target < arr[mid]
                end = mid - 1;                           // Java: adjust end
            } else {                                     // Pseudocode: else
                start = mid + 1;                         // Java: adjust start
            }
        }

        if (result != -1) // Pseudocode: if found return index
            System.out.println("Book Found: " + theBooks[result]);
        else              // Pseudocode: else return -1
            System.out.println("Book not found.");
    }

    // --------------------------------------------------
    // Algorithm LinearSearch(arr, target)
    // Pre: arr is array, target is element to be found
    // Post: return index if found, else -1
    // --------------------------------------------------
    void searchAuthor_Linear() {
        if (count == 0) { // Pseudocode: check arr empty
            System.out.println("No books available.");
            return;
        }

        System.out.print("Enter Author Name: ");
        String target = sc.nextLine(); // Pseudocode: read target element
        boolean found = false;         // Pseudocode: result flag

        for (int i = 0; i < count; i++) { // Pseudocode: for i = 0 to length(arr)-1
            if (theBooks[i].AuthorName.equalsIgnoreCase(target)) { // Pseudocode: if arr[i] = target
                System.out.println(theBooks[i]);
                found = true;
            }
        }

        if (!found) // Pseudocode: if not found return -1
            System.out.println("No books found by " + target);
    }

    // --------------------------------------------------
    // Sorting Menu
    // --------------------------------------------------
    void sortBooksByISBN() {
        if (count == 0) { // Pseudocode: arr empty check
            System.out.println("No books available.");
            return;
        }

        System.out.println("\nChoose Sorting Algorithm:");
        System.out.println("1. Bubble Sort");
        System.out.println("2. Insertion Sort");
        System.out.println("3. Quick Sort");
        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) { // Pseudocode: select sorting algorithm
            case 1 -> bubbleSort();
            case 2 -> insertionSort();
            case 3 -> quickSort(0, count - 1);
            default -> System.out.println("Invalid choice.");
        }

        System.out.println("Books sorted by ISBN successfully.");
    }

    // --------------------------------------------------
    // Algorithm BubbleSort(arr)
    // Pre: arr is array
    // Post: arr sorted ascending
    // --------------------------------------------------
    void bubbleSort() {
        // Pseudocode: for i = 0 to length(arr)-1
        for (int i = 0; i < count - 1; i++) { // Java: use count-1 for filled elements
            boolean swapped = false;          // Pseudocode: swapped flag

            // Pseudocode: for j = 1 to length(arr)-i-1
            for (int j = 1; j < count - i; j++) {
                if (theBooks[j].ISBNNo < theBooks[j - 1].ISBNNo) { // Pseudocode: if arr[j] < arr[j-1]
                    Book temp = theBooks[j];                        // Pseudocode: swap arr[j], arr[j-1]
                    theBooks[j] = theBooks[j - 1];
                    theBooks[j - 1] = temp;
                    swapped = true;                                 // Pseudocode: set swapped = true
                }
            }
            if (!swapped) break; // Pseudocode: if swapped=false break
        }
    }

    // --------------------------------------------------
    // Algorithm InsertionSort(arr)
    // Pre: arr is array
    // Post: arr sorted ascending
    // --------------------------------------------------
    void insertionSort() {
        // Pseudocode: for i = 1 to length(arr)-1
        for (int i = 1; i < count; i++) { // Java: count used instead of length(arr)
            Book key = theBooks[i];       // Pseudocode: key = arr[i]
            int j = i - 1;                // Pseudocode: j = i-1

            // Pseudocode: while j>=0 and arr[j]>key
            while (j >= 0 && theBooks[j].ISBNNo > key.ISBNNo) {
                theBooks[j + 1] = theBooks[j]; // Pseudocode: shift arr[j] right
                j = j - 1;                     // Pseudocode: decrement j
            }
            theBooks[j + 1] = key; // Pseudocode: arr[j+1] = key
        }
    }

    // --------------------------------------------------
    // Algorithm QuickSort(arr, start, end)
    // Pre: arr is array, start and end are indices
    // Post: arr sorted ascending
    // --------------------------------------------------
    void quickSort(int start, int end) {
        if (start >= end) return; // Pseudocode: if start>=end return

        int mid = start + (end - start) / 2; // Pseudocode: mid = start + (end-start)/2
        int pivot = theBooks[mid].ISBNNo;    // Pseudocode: pivot = arr[mid]
        int left = start;                    // Pseudocode: left=start
        int right = end;                     // Pseudocode: right=end

        while (left <= right) {              // Pseudocode: while left<=right
            while (theBooks[left].ISBNNo < pivot) left++;   // Pseudocode: arr[left]<pivot
            while (theBooks[right].ISBNNo > pivot) right--; // Pseudocode: arr[right]>pivot

            if (left <= right) {             // Pseudocode: if left<=right
                Book temp = theBooks[left];  // Pseudocode: swap arr[left], arr[right]
                theBooks[left] = theBooks[right];
                theBooks[right] = temp;
                left++;
                right--;
            }
        }

        quickSort(start, right); // Pseudocode: QuickSort(arr, start, right)
        quickSort(left, end);    // Pseudocode: QuickSort(arr, left, end)
    }
}

// --------------------------------------------------
// Main Program
// --------------------------------------------------
public class Library_Management_Array {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library lib = new Library();
        int choice;

        do {
            System.out.println("\n- Library Menu -");
            System.out.println("1. Add Book");
            System.out.println("2. Display All Books");
            System.out.println("3. Search by ISBN (Binary Search)");
            System.out.println("4. List all books by Author (Linear Search)");
            System.out.println("5. Sort Books by ISBN (Bubble / Insertion / Quick)");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            // Pseudocode: switch/case menu selection
            switch (choice) {
                case 1 -> lib.addBook();
                case 2 -> lib.displayBooks();
                case 3 -> lib.searchISBN_Binary();
                case 4 -> lib.searchAuthor_Linear();
                case 5 -> lib.sortBooksByISBN();
                case 0 -> System.out.println("Exiting... Thank you!");
                default -> System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 0);

        sc.close();
    }
}
