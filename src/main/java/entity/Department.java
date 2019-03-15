package entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "department")
public class Department {

    public Department() {
    }

    public Department(String name, boolean status) {
        this.name = name;
        this.status = status;
    }

    public Department(String name, boolean status, Set<Worker> workers) {
        this.name = name;
        this.status = status;
        this.workers = workers;
    }

    @Id
    @GeneratedValue(generator = "increment")
    private int id;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private Set<Worker> workers = new HashSet<>();

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    private boolean status;

    public int getId() {
        return id;
    }

    public Set<Worker> getWorkers() {
        return workers;
    }

    public void setWorkers(Set<Worker> workers) {
        this.workers = workers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
