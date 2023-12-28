package com.coursework.dentistry.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "doctors")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotBlank(message = "Поле обязательно для заполнения")
    @Column(nullable = false)
    private String surname;
    @NotBlank(message = "Поле обязательно для заполнения")
    @Column(nullable = false)
    private String name;
    @NotBlank(message = "Поле обязательно для заполнения")
    @Column(nullable = false)
    private String patronymic;
    private String photo;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "specialization_id")
    Specialization specialization;

    @ManyToMany
    private List<ProvisionService> provisionServices;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Reception> receptions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    public List<ProvisionService> getProvisionServices() {
        return provisionServices;
    }

    public void setProvisionServices(List<ProvisionService> provisionServices) {
        this.provisionServices = provisionServices;
    }

    public List<Reception> getReceptions() {
        return receptions;
    }

    public void setReceptions(List<Reception> receptions) {
        this.receptions = receptions;
    }
}
