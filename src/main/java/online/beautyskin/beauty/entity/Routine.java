package online.beautyskin.beauty.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
public class Routine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private long id;
    @Column(name = "RoutineName")
    private String name;
    @Column(name = "Description")
    private String description;
    @Column(name = "LastUpdate")
    @NotNull
    private LocalDateTime lastUpdate;
    private boolean isDeleted = false;
    // fk skintype id

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
}
