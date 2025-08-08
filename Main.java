import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // 1 - Persons
        Person p1 = new Person(1, "Ahmet", "Yılmaz", 35);
        Person p2 = new Person(2, "Ayşe", "Demir", 28);
        Person p3 = new Person(3, "Ali", "Kaya", 40); // director
        Person p4 = new Person(4, "Fatma", "Koç", 45); // writer

        // 2 - Categories
        Category cat1 = new Category(1, "ACT", "Action");
        Category cat2 = new Category(2, "ROM", "Romance");

        // 3 - Movies
        Movie m1 = new Movie(1, "Kahraman", null, 2021, 25, cat1, "movie", new ArrayList<>(), p3);
        m1.addActor(p1);
        m1.addScore(5);
        m1.addScore(4);

        Movie m2 = new Movie(2, "Aşk Zamanı", null, 2020, 30, cat2, "movie", new ArrayList<>(), p2);
        m2.addActor(p2);
        m2.addScore(3);
        m2.addScore(2);

        // 4 - Books
        Book b1 = new Book(3, "Hayatın İçinden", null, 2019, 20, cat1, "book", p4, 300);
        b1.addScore(4);
        b1.addScore(5);

        Book b2 = new Book(4, "Aşk ve Umut", null, 2022, 35, cat2, "book", p2, 250);
        b2.addScore(2);
        b2.addScore(3);

        // 5 - User
        User user = new User(10, "Mehmet", "Yılmaz", 32, "testUser", "123456");

        // 6 - Netflix
        Netflix netflix = new Netflix();

        // 7 - Login
        netflix.login(user);

        // 8 - Add materials
        netflix.addMovie(m1);
        netflix.addMovie(m2);
        netflix.addBook(b1);
        netflix.addBook(b2);

        // 9 - Fonksiyonlar
        netflix.showHighestAvgScoreMaterial();
        netflix.showLowestAvgScoreMovie();
        netflix.showMostExpensiveMaterialByCategory(2);
        netflix.showMoviesByActor(1);
    }
}