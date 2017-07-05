import javax.persistence.Embeddable;
import javax.persistence.Embedded;

/**
 * Created by tanuj on 6/30/17.
 */
@Embeddable
public class Address {
    public void setStreetNumber(Integer streetNumber) {
        this.streetNumber = streetNumber;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public void setState(String state) {
        State = state;
    }

    private Integer streetNumber;
    private String Location;
    private String State;


    @Override
    public String toString() {
        return "Address{" +
                "streetNumber=" + streetNumber +
                ", Location='" + Location + '\'' +
                ", State='" + State + '\'' +
                '}';
    }
}
