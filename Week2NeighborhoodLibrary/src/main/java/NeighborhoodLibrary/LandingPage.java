package NeighborhoodLibrary;

import java.util.Scanner;

public class LandingPage {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Book[] horrorBooks = {
                new Book(1, "9780451524935", "Dracula", false, ""),
                new Book(2, "9780451525260", "Frankenstein", false, ""),
                new Book(3, "9780307743657", "The Shining", true, "John Smith"),
                new Book(4, "9781501142970", "IT", false, ""),
                new Book(5, "9780743477123", "The Exorcist", true, "Sarah Jones"),
                new Book(6, "9780062699458", "The Haunting of Hill House", false, ""),
                new Book(7, "9781982102319", "Pet Sematary", false, ""),
                new Book(8, "9780385121675", "The Amityville Horror", true, "Griffin Mobley"),
                new Book(9, "9780062085677", "Bird Box", false, ""),
                new Book(10, "9780385537858", "The Cabin at the End of the World", false, ""),
                new Book(11, "9780451169518", "Carrie", true, "Griffin Mobley"),
                new Book(12, "9781501143106", "Misery", false, ""),
                new Book(13, "9780060530921", "House of Leaves", false, ""),
                new Book(14, "9780385538442", "The Silence of the Lambs", true, "David Wilson"),
                new Book(15, "9780060853983", "World War Z", false, ""),
                new Book(16, "9780312865047", "Swan Song", false, ""),
                new Book(17, "9780316563767", "The Only Good Indians", false, ""),
                new Book(18, "9780593237893", "Mexican Gothic", true, "Jessica Miller"),
                new Book(19, "9780062662582", "The Fisherman", false, ""),
                new Book(20, "9781982132613", "The Southern Book Club's Guide to Slaying Vampires", false, "")
        };

        while (true) {
            System.out.println("---Neighborhood Library Menu---");
            System.out.println("1. Show Available Books");
            System.out.println("2. Checked Out Books");
            System.out.println("3. Exit");
            System.out.println("Please Enter Your Choice: ");

            int userInput = scanner.nextInt();
            scanner.nextLine();

            switch (userInput) {
                case 1:
                    for (Book book : horrorBooks) {
                        if (book.isCheckedOut() == false) {
                            System.out.println(book.getTitle());
                        }
                    }
                    System.out.println("Which Book would you like?");
                    String bookNameB = scanner.nextLine();
                    System.out.println("What is your name?");
                    String customerNameB = scanner.nextLine();
                    for (Book book : horrorBooks) {
                        if (book.getTitle().equals(bookNameB)) {
                            book.setIsCheckedOut(true);
                            book.setCheckedOutTo(customerNameB);
                            System.out.println("Book " + bookNameB + " is checked out to " + customerNameB + "!");
                            System.out.println("Redirecting To Main Menu...");
                        }
                    }
                    break;
                case 2:
                    System.out.println("Are you returning a book? (Y/N)");
                    char input = scanner.next().charAt(0);
                    if (input == 'Y') {
                        scanner.nextLine();
                        System.out.println("What is your first and last name?");
                        String customerNameR = scanner.nextLine();

                        for (Book book : horrorBooks) {
                            if (book.getCheckedOutTo().equals(customerNameR)) {
                                System.out.println(book.getTitle());
                            }
                        }
                    } else
                        System.out.println("Redirecting To Main Menu...");

                        System.out.println("Please choose the book you'd like to return.");
                        String bookNameR = scanner.nextLine();
                        for (Book book : horrorBooks) {
                            if (book.getTitle().equals(bookNameR)) {
                                book.setIsCheckedOut(false);
                                book.setCheckedOutTo("");
                                System.out.println("Thank you for returning " + bookNameR + "!");
                                System.out.println("Returning To Main Menu...");
                            }

                        }
                        break;
                        case 3:
                            System.exit(0);
                        default: {
                            System.out.println("Invalid choice.");
                            System.out.println("Redirecting To Main Menu...");
                        }


                    }
            }
        }
    }
