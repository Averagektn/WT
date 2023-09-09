package by.bsuir.lab1.task13;

import by.bsuir.lab1.task12.Book;

public class Task {
    public static void main(String[] args){
        ProgrammersBook book1 = new ProgrammersBook("MyCoolBook", "CoolAuthor", 1488, "Java", 0);
        Book book2 = new ProgrammersBook("AnotherBook", "Noname", 0, "C#", 100);
        if (!book1.equals(book2)){
            System.out.println(book1.hashCode());
            System.out.println(book2.toString());
        }
    }
}
