package entity;

import javax.persistence.*;

@Entity
@Table(name = "workers")
public class Worker {

    public Worker() {}

    public Worker(int age, Availability availability, String full_name) {
        this.age = age;
        this.availability = availability;
        this.full_name = full_name;
    }

    public Worker(int age, Availability availability, String full_name, Department department) {
        this.age = age;
        this.availability = availability;
        this.full_name = full_name;
        this.department = department;
    }

    @Id
    @GeneratedValue(generator = "increment")
    private int id;

    @Column(name = "age")
    private int age;

    @Enumerated(EnumType.STRING)
    private Availability availability;

    @Column(name = "full_name")
    private String full_name;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    public int getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Availability getAvailability() {
        return availability;
    }

    public void setAvailability(Availability availability) {
        this.availability = availability;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
