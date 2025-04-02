package online.beautyskin.beauty.entity.request;


import jakarta.persistence.Column;

public class BlogRequest {
    private String title;
    @Column(name = "Content",columnDefinition = "LONGTEXT")
    private String content;
    @Column(name = "Image",columnDefinition = "LONGTEXT")
    private String image;
    private String Slug;
    private String author;
    private String tag;
    
    public BlogRequest() {
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
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
