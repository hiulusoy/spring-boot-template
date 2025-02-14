package com.hiu.template.springboottemplate.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Represents an employee entity in the system.
 * This class is mapped to the "employees" table in the database.
 * It includes various JPA annotations for object-relational mapping and named queries for common operations.
 */
@Entity
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employees", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email"})
})
@NamedQueries({
        @NamedQuery(
                name = "Employee.findByLastName",
                query = "SELECT e FROM Employee e WHERE e.lastName = :lastName"
        ),
        @NamedQuery(
                name = "Employee.findByEmail",
                query = "SELECT e FROM Employee e WHERE e.email = :email"
        )
})
public class Employee {

    /**
     * The unique identifier for the employee.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    /**
     * The first name of the employee.
     */
    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    /**
     * The last name of the employee.
     */
    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;

    /**
     * The email address of the employee. This field is unique across all employees.
     */
    @Column(name = "email", length = 100, nullable = false)
    private String email;

    /**
     * The timestamp when the employee record was created.
     */
    @Basic(optional = false)
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    /**
     * The timestamp when the employee record was last updated.
     */
    @Basic(optional = true)
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    /**
     * Temporary data field that is not persisted to the database.
     */
    @Transient
    private String temporaryData;

    /**
     * Lifecycle callback method that sets the creation timestamp before persisting the entity.
     */
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    /**
     * Lifecycle callback method that sets the update timestamp before updating the entity.
     */
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
