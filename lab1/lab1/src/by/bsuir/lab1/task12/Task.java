package by.bsuir.lab1.task12;

public class Task {
    public static void main(String[] args) {
        Book book1 = new Book("MyCoolBook", "CoolAuthor", 1488);
        Book book2 = new Book("AnotherBook", "Noname", 0);
        if (!book1.equals(book2)) {
            System.out.println(book1.hashCode());
            System.out.println(book2.toString());
        }
    }
}
