package online.beautyskin.beauty.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

@Entity
public class Volume {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private long id;
    @Column(name = "Volume", nullable = false)
    @Min(0)
    private int volume;
    private boolean isDeleted = false;

    public Volume() {}
    public Volume(int volume) { this.volume = volume; }
    public long getId() { return id; }
    public int getVolume() { return volume; }
    public void setVolume(int volume) { this.volume = volume; }
    public boolean isDeleted() { return isDeleted; }
    public void setDeleted(boolean isDeleted) { this.isDeleted = isDeleted; }
}
