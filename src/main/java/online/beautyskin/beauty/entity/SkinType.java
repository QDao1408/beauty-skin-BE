package online.beautyskin.beauty.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class SkinType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TypeID")
    private Long id;
    @Column(name = "Type")
    private String typeName;
    @Column(name = "Description")
    private String description;

    @OneToMany(mappedBy = "skinType",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Routine> routines;

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
