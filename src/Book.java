import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by tanuj on 6/30/17.
 */
@Entity
public class Book
{
 @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer bookId;
  private   String bookName;

    public Collection<Author> getAuthorList() {
        return authorList;
    }

    public void setAuthorList(Collection<Author> authorList) {
        this.authorList = authorList;
    }

    @ManyToMany
  private Collection<Author> authorList = new ArrayList<>();

//    public Author getAuthor() {
//        return author;
//    }
//
//    public void setAuthor(Author author) {
//        this.author = author;
//    }

//   @ManyToOne
//    Author author;
//@ManyToMany(mappedBy = "author")
//    List<Author> authorList = new ArrayList<>();

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                '}';
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

}
