package by.bsuir.lab1.task14;

public class Task {
    public static void main(String[] args) {
        Book book1 = new Book("MyCoolBook", "CoolAuthor", 1488);
        Object book2 = book1.clone();
        System.out.println(book2);
        System.out.println(book2.equals(book1));
    }

}
