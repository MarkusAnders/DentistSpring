package com.coursework.dentistry.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "provisionServices")
@NoArgsConstructor
@Data
public class ProvisionService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Поле обязательно для заполнения")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Поле обязательно для заполнения")
    @Size(min = 1, max = 100, message = "Слишком короткое или слишком длинное описание")
    @Column(nullable = false)
    private String description;

    @NotNull(message = "Поле обязательно для заполнения")
    @Column(nullable = false)
    private Integer price;

    @OneToMany(mappedBy = "provisionService", cascade = CascadeType.ALL)
    private List<Reception> receptions;

    @ManyToMany
    @JoinTable(inverseJoinColumns = { @JoinColumn(name = "doctor_id") })
    private List<Doctor> doctors;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public List<Reception> getReceptions() {
        return receptions;
    }

    public void setReceptions(List<Reception> receptions) {
        this.receptions = receptions;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }
}
