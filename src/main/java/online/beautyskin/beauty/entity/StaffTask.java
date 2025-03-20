package online.beautyskin.beauty.entity;

import jakarta.persistence.*;
import online.beautyskin.beauty.enums.StaffTaskEnums;

import java.time.LocalDateTime;

@Entity
public class StaffTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private User staff;

    @OneToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    private LocalDateTime lastUpdate;

    private StaffTaskEnums staffTaskEnums;

    public StaffTask() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getStaff() {
        return staff;
    }

    public void setStaff(User staff) {
        this.staff = staff;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public StaffTaskEnums getStaffTaskEnums() {
        return staffTaskEnums;
    }

    public void setStaffTaskEnums(StaffTaskEnums staffTaskEnums) {
        this.staffTaskEnums = staffTaskEnums;
    }
}
