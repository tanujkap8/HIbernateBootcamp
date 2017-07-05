import org.hibernate.HibernateException;
import org.hibernate.Metamodel;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.lang.String;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.persistence.metamodel.EntityType;

/**
 * Created by tanuj on 6/28/17.
 */
//@SuppressWarnings("ALL")
public class Main {
    private static final SessionFactory ourSessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();
            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    private static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    private static void createData() throws ParseException {
        //Author-One
        Author author1 = new Author();
        author1.setFirstName("TANUJ");
        author1.setLastName("KAPILA");
        author1.setAge(20);
        author1.setId(1);

        author1.dob = new SimpleDateFormat("dd-MM-yyyy").parse("08-06-1996");

        Address address = new Address();
        address.setStreetNumber(123);
        address.setLocation("GHAZIABAD");
        address.setState("UTTARPRADESH");
        author1.address = address;

        List<String> sub1 = new ArrayList<>();
        sub1.add("JAVA");
        sub1.add("C++");
        sub1.add("PHP");
        author1.subject.addAll(sub1);

        Book book1 = new Book();
        Book book2 = new Book();
        book1.setBookName("Travel");
        book2.setBookName("HIstory");
        author1.getbook().add(book1);
        author1.getbook().add(book2);
        book1.getAuthorList().add(author1);
        book2.getAuthorList().add(author1);
//        book2.setAuthor(author1);

        //Author-Two
        Author author2 = new Author();
        author2.setFirstName("ANKUR");
        author2.setLastName("SHARMA");
        author2.setAge(20);
        author2.setId(2);
        author2.dob = new SimpleDateFormat("dd-MM-yyyy").parse("08-06-1996");

        Address address2 = new Address();
        address2.setStreetNumber(123);
        address2.setLocation("GHAZIABAD");
        address2.setState("UTTARPRADESH");
        author2.address = address;

        List<String> sub2 = new ArrayList<>();
        sub2.add("JAVA");
        sub2.add("C++");
        sub2.add("PHP");
        author2.subject.addAll(sub2);

        Book book3 = new Book();
        book3.setBookName("JAVA");
        author2.getbook().add(book1);
        author2.getbook().add(book2);

        //Author-Three
        Author author3 = new Author();
        author3.firstName = "ADITYA";
        author3.lastName = "SHARMA";
        author3.age = 20;
        author3.id = 3;
        author3.dob = new SimpleDateFormat("dd-MM-yyyy").parse("08-06-1996");
        Address address3 = new Address();
        address3.setStreetNumber(123);
        address3.setLocation("GHAZIABAD");
        address3.setState("UTTARPRADESH");
        author3.address = address;
        List<String> sub3 = new ArrayList<>();
        sub3.add("JAVA");
        sub3.add("C++");
        sub3.add("PHP");
        author3.subject.addAll(sub3);
        Book book4 = new Book();
        book4.setBookName("GEONET");
        author3.getbook().add(book3);
        author3.getbook().add(book4);

        try (Session session = getSession()) {

            session.beginTransaction();
            session.save(author1);
            session.save(book1);
            session.save(book2);
session.save(author2);
session.save(author3);
session.save(book3);
session.save(book4);

            session.getTransaction().commit();
            session.close();
        }
    }

    private static void updateData() {
        try (Session session = getSession()) {
            Author author = session.get(Author.class, 2);
            author.firstName = "ROHIT";
            author.lastName = "SWAMI";
            author.age = 21;

            Address address = new Address();
            address.setStreetNumber(123);
            address.setLocation("GHAZIABAD");
            address.setState("UTTARPRADESH");
            author.address = address;
            session.beginTransaction();
            session.update(author);
            session.getTransaction().commit();
            session.close();
        }
    }

//    private static void readData() {
//        try (Session session = getSession()) {
//            session.beginTransaction();
//            Author author = session.get(Author.class, 1);
//            session.getTransaction().commit();
//            session.close();
//        }
//    }

    private static void deleteData() {
        try (Session session = getSession()) {
            Author author = session.get(Author.class, 1);
            session.beginTransaction();
            session.delete(author);
            session.getTransaction().commit();
            session.close();
        }

    }

    public static void main(final String[] args) throws Exception {
        createData();
       querydata();
        // readData();
//        updateData();
        //  deleteData();
    }

    public static void querydata() {
        try (Session session = getSession()) {
            System.out.println("querying all the managed entities...");
            final Metamodel metamodel = session.getSessionFactory().getMetamodel();
            for (EntityType<?> entityType : metamodel.getEntities()) {
                final String entityName = entityType.getName();
                final Query query = session.createQuery("from " + entityName);
                System.out.println("executing: " + query.getQueryString());
                for (Object o : query.list()) {
                    System.out.println("  " + o);
                }
            }
        } finally {

            ourSessionFactory.close();
        }
    }


}