package online.beautyskin.beauty.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.util.RouteMatcher;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Routine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RountineID")
    private long id;



    @Column(name = "RoutineName")
    private String name;
    @Column(name = "Description")
    private String description;
    @Column(name = "LastUpdate")
    @NotNull
    private LocalDateTime lastUpdate;
    private boolean isDeleted = false;

    @OneToOne
    @JoinColumn(name = "type_id")
    private SkinType skinType;

    @OneToMany(mappedBy = "routine", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<RoutineStep> routineSteps = new ArrayList<>();

    public Routine() {}
    public Routine(String name, String description, LocalDateTime lastUpdate) { this.name = name; this.description = description; this.lastUpdate = lastUpdate; }
    public long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public LocalDateTime getLastUpdate() { return lastUpdate; }
    public void setLastUpdate(LocalDateTime lastUpdate) { this.lastUpdate = lastUpdate; }
    public boolean isDeleted() { return isDeleted; }
    public void setDeleted(boolean isDeleted) { this.isDeleted = isDeleted; }

    public SkinType getSkinType() {
        return skinType;
    }

    public void setSkinType(SkinType skinType) {
        this.skinType = skinType;
    }
}
