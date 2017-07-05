/**
 * Created by tanuj on 6/28/17.
 */
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Employee")
public class Person {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Integer id;
    String name;
    @Transient
    Integer age;
    @Temporal(TemporalType.DATE)
    Date dob;
    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }


}
