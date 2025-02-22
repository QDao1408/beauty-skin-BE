package online.beautyskin.beauty.entity;

import jakarta.persistence.*;

@Entity
public class SkinType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private long id;
    @Column(name = "Type")
    private String typeName;
    @Column(name = "Description")
    private String description;

    public SkinType() {
    }

    public SkinType(final String typeName, final String description) {
        this.typeName = typeName;
        this.description = description;
    }

    public long getId() {
        return id;
    }



    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
