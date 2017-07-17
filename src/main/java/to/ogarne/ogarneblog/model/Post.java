package to.ogarne.ogarneblog.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by jedrz on 16.07.2017.
 */

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull
    @Size(max = 300)
    private String title;

    @Column
    @NotNull
    private String body;

    @ManyToOne(fetch = FetchType.LAZY)
    private User author;


    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @Column
    private Date date = new Date();


    public Post() {
    }

    public Post(String title, String body, User author, Category category, Date date) {
        this.title = title;
        this.body = body;
        this.author = author;
        this.category = category;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
