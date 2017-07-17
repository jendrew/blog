package to.ogarne.ogarneblog.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Created by jedrz on 17.07.2017.
 */
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull
    @Size(max = 30)
    private String name;

    @Column
    @OneToMany(mappedBy = "category")
    private Set<Post> posts;

    public Category() {
    }

    public Category(String name, Set<Post> posts) {
        this.name = name;
        this.posts = posts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }
}
