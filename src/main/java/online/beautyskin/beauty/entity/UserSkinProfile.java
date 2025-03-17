package online.beautyskin.beauty.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
public class UserSkinProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private long id;
    @Column(name = "LastUpdate")
    @NotNull
    private LocalDateTime lastUpdate;
    private boolean isDeleted = false;

    @OneToOne
    @JoinColumn(name = "UserID", unique = true)
    @JsonIgnore
    private User user;

    @OneToOne
    @JoinColumn(name = "TypeID")
    private SkinType skinType;

    @OneToOne
    @JoinColumn(name = "ConcernID")
    private SkinConcern skinConcern;

    public UserSkinProfile() {}
    public UserSkinProfile(@NotNull final LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public LocalDateTime getLastUpdate() { return lastUpdate; }
    public void setLastUpdate(LocalDateTime lastUpdate) { this.lastUpdate = lastUpdate; }
    public boolean isDeleted() { return isDeleted; }
    public void setDeleted(boolean isDeleted) { this.isDeleted = isDeleted; }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public SkinType getSkinType() {
        return skinType;
    }

    public void setSkinType(SkinType skinType) {
        this.skinType = skinType;
    }

    public SkinConcern getSkinConcern() {
        return skinConcern;
    }

    public void setSkinConcern(SkinConcern skinConcern) {
        this.skinConcern = skinConcern;
    }
}
