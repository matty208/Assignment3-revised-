package edu.monmouth.hw3;

import edu.monmouth.book.Book;
import edu.monmouth.book.BookException;
import edu.monmouth.book.BookTypes;
import java.io.*;
import java.util.*;

public class HW3 {

    public static void main(String[] args) throws BookException {
        redirectOutput();

        ArrayList<String> stringArrayList = new ArrayList<>();
        LinkedList<String> stringLinkedList = new LinkedList<>();
        ArrayList<Book> bookArrayList = new ArrayList<>();
        LinkedList<Book> bookLinkedList = new LinkedList<>();

        readStrings("string.txt", stringArrayList, stringLinkedList);
        readBooks("books.txt", bookArrayList, bookLinkedList);

        useArrayListMethods(stringArrayList, bookArrayList);
        useLinkedListMethods(stringLinkedList, bookLinkedList);
    }

    private static void redirectOutput() {
        try {
            PrintStream fileOut = new PrintStream(new FileOutputStream("HW3.txt"));
            System.setOut(fileOut);
            System.setErr(fileOut);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void readStrings(String filename, ArrayList<String> arrayList, LinkedList<String> linkedList) {
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                arrayList.add(line);
                linkedList.add(line);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    private static void readBooks(String filename, ArrayList<Book> arrayList, LinkedList<Book> linkedList) {
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                try {
                    Book book = parseBook(line);
                    arrayList.add(book);
                    linkedList.add(book);
                } catch (BookException | IllegalArgumentException e) {
                    System.err.println("Error parsing book: " + e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    private static Book parseBook(String line) throws NumberFormatException, IllegalArgumentException, BookException {
        String[] parts = line.split(",");
        if (parts.length != 4) {
            throw new IllegalArgumentException("Incorrect number of fields in line.");
        }
        String title = parts[0].trim();
        BookTypes bookType = BookTypes.valueOf(parts[1].trim().toUpperCase());
        int numberOfPages = Integer.parseInt(parts[2].trim());
        double price = Double.parseDouble(parts[3].trim());
        return new Book(numberOfPages, price, title, bookType);
    }

    private static void useArrayListMethods(ArrayList<String> stringArrayList, ArrayList<Book> bookArrayList) throws BookException {
        System.out.println("\nString ArrayList is empty: " + stringArrayList.isEmpty());
        
        if (!stringArrayList.isEmpty()) {
            stringArrayList.remove(0);
        }
        
        System.out.println("String ArrayList size after removal: " + stringArrayList.size());

        stringArrayList.add("iPhone");
        
        Iterator<String> iterator = stringArrayList.iterator();
        while (iterator.hasNext()) {
            System.out.println("String ArrayList: " + iterator.next());
        }
        
        ListIterator<String> listIterator = stringArrayList.listIterator(stringArrayList.size());
        while (listIterator.hasPrevious()) {
            System.out.println("String ArrayList in reverse: " + listIterator.previous());
        }
        
        System.out.println("\nBook ArrayList is empty: " + bookArrayList.isEmpty());
        
        if (!bookArrayList.isEmpty()) {
            bookArrayList.remove(0);
        }
        
        System.out.println("Book ArrayList size after removal: " + bookArrayList.size());

        bookArrayList.add(new Book(300, 15.99, "12 Angry Men", BookTypes.SOFTBACK));

        System.out.println("Book ArrayList:");
        Iterator<Book> bookIterator = bookArrayList.iterator();
        while (bookIterator.hasNext()) {
            System.out.println(bookIterator.next());
        }
        System.out.println("Book ArrayList in reverse:");
        ListIterator<Book> bookListIterator = bookArrayList.listIterator(bookArrayList.size());
        while (bookListIterator.hasPrevious()) {
            System.out.println(bookListIterator.previous());
        }
    }

    private static void useLinkedListMethods(LinkedList<String> stringLinkedList, LinkedList<Book> bookLinkedList) throws BookException {
    	System.out.println("\n");
        stringLinkedList.add("iPhone");

        ListIterator<String> stringListIterator = stringLinkedList.listIterator(stringLinkedList.size());
        while (stringListIterator.hasPrevious()) {
            System.out.println("String LinkedList in reverse: " + stringListIterator.previous());
        }

        Iterator<String> stringIterator = stringLinkedList.iterator();
        while (stringIterator.hasNext()) {
            System.out.println("String LinkedList: " + stringIterator.next());
        }
        System.out.println("\n");
 
        bookLinkedList.add(new Book(300, 15.99, "12 Angry Men", BookTypes.SOFTBACK));


        System.out.println("Book LinkedList in reverse: ");
        ListIterator<Book> bookListIterator = bookLinkedList.listIterator(bookLinkedList.size());
        while (bookListIterator.hasPrevious()) {
            System.out.println(bookListIterator.previous());
        }

    
        System.out.println("Book LinkedList: ");
        Iterator<Book> bookIterator = bookLinkedList.iterator();
        while (bookIterator.hasNext()) {
            System.out.println(bookIterator.next());
        }

        System.out.println("\n");

        Book bookToRemove = new Book(120, 29.99, "The Lord of the Rings", BookTypes.HARDBACK);
        System.out.println("LinkedList contains 'The Lord of the Rings': " + bookLinkedList.contains(bookToRemove));
        bookLinkedList.remove(bookToRemove);
        
        Book bookNotInList = new Book(123, 15.99, "Green Eggs and Ham", BookTypes.SOFTBACK);
        System.out.println("LinkedList contains 'Green Eggs and Ham': " + bookLinkedList.contains(bookNotInList));
        bookLinkedList.remove(bookNotInList);
    }
}
