package online.beautyskin.beauty.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private long id;
    @Column(name = "Title")
    private String title;
    @Column(name = "Content",columnDefinition = "LONGTEXT")
    private String content;
    @Column(name = "Image",columnDefinition = "LONGTEXT")
    private String image;
    @Column(name = "Publish")
    private LocalDate publish;
    @Column(name = "Slug")
    private String Slug;
    @Column(name = "Author")
    private String author;
    @Column(name = "Tag")
    private String tag;

    private boolean isDeleted = false;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean delete) {
        isDeleted = delete;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Blog() {
    }

    public Blog(long id, String title, String content, String image, LocalDate publish, String slug, String author, String tag, boolean isDeleted) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.image = image;
        this.publish = publish;
        Slug = slug;
        this.author = author;
        this.tag = tag;
        this.isDeleted = isDeleted;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getPublish() {
        return publish;
    }

    public void setPublish(LocalDate publish) {
        this.publish = publish;
    }

    public String getSlug() {
        return Slug;
    }

    public void setSlug(String slug) {
        Slug = slug;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
