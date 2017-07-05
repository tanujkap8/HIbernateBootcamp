import javax.persistence.*;
import javax.security.auth.Subject;
import java.util.Collection;
import java.util.HashSet;
import java.util.*;
/**
 * Created by tanuj on 6/28/17.
 */
@Entity
public class Author {
    public void setId(Integer id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = ("Author_id"))
    Integer id;
    @Column(name = ("First_Name"))
    String firstName;
    @Column(name = ("Last_Name"))
    //  @Transient
    String lastName;
    @Column(name = ("Age"))
    Integer age;
    @Column(name = ("Date_Of_Birth"))
    @Temporal(TemporalType.DATE)
    Date dob;
    @Embedded
    Address address;

    public List<String> getSubject() {
        return subject;
    }

    public void setSubject(List<String> subject) {
        this.subject = subject;
    }

    //    @OneToOne
//    @JoinColumn(name = ("Book_Id"))
//    Book book;
//@OneToMany(cascade = CascadeType.PERSIST)
//@JoinTable(joinColumns = @JoinColumn(name="AUTHOR_ID")
//        ,inverseJoinColumns = @JoinColumn(name = "Book_Id"))
@ManyToMany
    private Collection<Book>book=new ArrayList<Book>();

    public Collection<Book>getbook(){
        return book;
    }

    public void setBook(Collection<Book>book){
        this.book=book;
    }


    @ElementCollection
    List<String> subject = new ArrayList<>();



    @Override
    public String toString()
    {
        return "Author{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", dob=" + dob +
                ", subject=" + subject +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getAge() {
        return age;
    }

    public Date getDob() {
        return dob;
    }

    public Address getAddress() {
        return address;
    }
}