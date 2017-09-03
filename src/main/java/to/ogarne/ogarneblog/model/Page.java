package to.ogarne.ogarneblog.model;

import to.ogarne.ogarneblog.web.Parseable;
import to.ogarne.ogarneblog.web.customvalidator.NotChildIfInTheMenu;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NotChildIfInTheMenu
public class Page implements Parseable {


    @Id
    @Column(name = "page_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull
    @Size(min = 3, max = 300, message = "The size should be between 3 and 300")
    private String title;

    @Column
    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9\\-]*$")
    private String slug;

    @Column(columnDefinition = "text")
    @NotNull
    @Size(min = 3)
    private  String body;

    @Column(unique = true)
    private Long menuPosition;

    @ManyToOne
    private Page parent;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="ImagePaths", joinColumns=@JoinColumn(name="path_id"))
    @Column(name="images")
    private List<String> imagePaths;

    @OneToMany(mappedBy = "parent")
    private Set<Page> childern = new HashSet<>();

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

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Long getMenuPosition() {
        return menuPosition;
    }

    public void setMenuPosition(Long menuPosition) {
        this.menuPosition = menuPosition;
    }

    public Page getParent() {
        return parent;
    }

    public void setParent(Page parent) {
        this.parent = parent;
    }

    public Set<Page> getChildern() {
        return childern;
    }

    public void setChildern(Set<Page> childern) {
        this.childern = childern;
    }

    public List<String> getImagePaths() {
        return imagePaths;
    }

    public void setImagePaths(List<String> imagePaths) {
        this.imagePaths = imagePaths;
    }
}
