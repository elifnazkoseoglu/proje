import java.util.*;

// CLASS1
class Person {
    int id;
    String firstName;
    String lastName;
    int age;

    public Person(int id, String firstName, String lastName, int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String toString() {
        return firstName + " " + lastName + " (ID: " + id + ")";
    }
}

// CLASS2
class User extends Person {
    String userName;
    String password;

    public User(int id, String firstName, String lastName, int age, String userName, String password) {
        super(id, firstName, lastName, age);
        this.userName = userName;
        this.password = password;
    }
}

// CLASS3
class Category {
    int id;
    String categoryCode;
    String categoryName;

    public Category(int id, String code, String name) {
        this.id = id;
        this.categoryCode = code;
        this.categoryName = name;
    }

    public String toString() {
        return categoryName + " (" + categoryCode + ")";
    }
}

// CLASS4
abstract class Material {
    int id;
    String name;
    ArrayList<Integer> scores;
    int release_year;
    int price;
    Category category;
    String type;

    public Material(int id, String name, ArrayList<Integer> scores, int release_year, int price, Category category, String type) {
        this.id = id;
        this.name = name;
        this.scores = scores != null ? scores : new ArrayList<>();
        this.release_year = release_year;
        this.price = price;
        this.category = category;
        this.type = type;
    }

    public void addScore(int score) {
        scores.add(score);
    }

    public double getAvgScore() {
        if (scores.isEmpty()) return 0;
        int sum = 0;
        for (int s : scores) sum += s;
        return (double) sum / scores.size();
    }

    public abstract void showDetail();
}

// CLASS5
class Book extends Material {
    Person writer;
    int numberOfPages;

    public Book(int id, String name, ArrayList<Integer> scores, int release_year, int price, Category category, String type, Person writer, int numberOfPages) {
        super(id, name, scores, release_year, price, category, type);
        this.writer = writer;
        this.numberOfPages = numberOfPages;
    }

    public void setWriter(Person newWriter) {
        this.writer = newWriter;
    }

    @Override
    public void showDetail() {
        System.out.println("Book: " + name + ", Writer: " + writer + ", Pages: " + numberOfPages + ", Year: " + release_year + ", Category: " + category + ", Price: " + price + ", Avg Score: " + getAvgScore());
    }
}

// CLASS6
class Movie extends Material {
    ArrayList<Person> actors;
    Person director;

    public Movie(int id, String name, ArrayList<Integer> scores, int release_year, int price, Category category, String type, ArrayList<Person> actors, Person director) {
        super(id, name, scores, release_year, price, category, type);
        this.actors = actors != null ? actors : new ArrayList<>();
        this.director = director;
    }

    public void addActor(Person actor) {
        actors.add(actor);
    }

    @Override
    public void showDetail() {
        System.out.print("Movie: " + name + ", Director: " + director + ", Actors: ");
        for (Person actor : actors) System.out.print(actor + ", ");
        System.out.println("Year: " + release_year + ", Category: " + category + ", Price: " + price + ", Avg Score: " + getAvgScore());
    }
}

// CLASS7
class Netflix {
    ArrayList<Material> materials;
    User credential;
    boolean isLogin;

    public Netflix() {
        materials = new ArrayList<>();
        isLogin = false;
    }

    public void login(User user) {
        if (user.userName.equals("testUser") && user.password.equals("123456")) {
            isLogin = true;
            credential = user;
        } else {
            System.out.println("Login failed!");
        }
    }

    public void addMovie(Movie m1) {
        if (isLogin) materials.add(m1);
    }

    public void addBook(Book b1) {
        if (isLogin) materials.add(b1);
    }

    public void addMaterial(Material m2) {
        if (isLogin) materials.add(m2);
    }

    // N1
    public void showHighestAvgScoreMaterial() {
        Material best = null;
        double max = -1;
        for (Material m : materials) {
            if (m.getAvgScore() > max) {
                max = m.getAvgScore();
                best = m;
            }
        }
        System.out.println("\nN1 - En yüksek ortalama skor:");
        if (best != null) best.showDetail();
    }

    // N2
    public void showLowestAvgScoreMovie() {
        Movie worst = null;
        double min = Double.MAX_VALUE;
        for (Material m : materials) {
            if (m instanceof Movie) {
                double avg = m.getAvgScore();
                if (avg < min) {
                    min = avg;
                    worst = (Movie) m;
                }
            }
        }
        System.out.println("\nN2 - En düşük skorlu film:");
        if (worst != null) worst.showDetail();
    }

    // N3
    public void showMostExpensiveMaterialByCategory(int catId) {
        Material expensive = null;
        for (Material m : materials) {
            if (m.category.id == catId) {
                if (expensive == null || m.price > expensive.price) {
                    expensive = m;
                }
            }
        }
        System.out.println("\nN3 - Kategoride en pahalı materyal (ID " + catId + "):");
        if (expensive != null) expensive.showDetail();
    }

    // N4
    public void showMoviesByActor(int actorId) {
        System.out.println("\nN4 - Kişinin oynadığı filmler (ID " + actorId + "):");
        for (Material m : materials) {
            if (m instanceof Movie) {
                for (Person actor : ((Movie) m).actors) {
                    if (actor.id == actorId) {
                        m.showDetail();
                        break;
                    }
                }
            }
        }
    }
}