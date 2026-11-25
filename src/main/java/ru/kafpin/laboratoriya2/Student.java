package ru.kafpin.laboratoriya2;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Укажите фамилию.")
    private String surname;
    @NotBlank(message = "Укажите имя.")
    private String name;
    private String patronymic;
    @NotBlank(message = "Укажите город.")
    private String city;
    @NotBlank(message = "Укажите пол.")
    @Pattern(regexp = "Мужской|Женский", message = "Выберите значение пола из списка.")
    private String gender;
    @NotNull(message = "Укажите возраст.")
    @Min(value = 14, message = "Возраст должен быть не менее 14 лет.")
    @Max(value = 100, message = "Возраст должен быть не более 100 лет.")
    private Integer age;
    @NotBlank(message = "Укажите группу.")
    private String studyGroup;
    @NotBlank(message = "Укажите факультет.")
    private String faculty;

    public Long getId() { return id; }
    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPatronymic() { return patronymic; }
    public void setPatronymic(String patronymic) { this.patronymic = patronymic; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }
    public String getStudyGroup() { return studyGroup; }
    public void setStudyGroup(String studyGroup) { this.studyGroup = studyGroup; }
    public String getFaculty() { return faculty; }
    public void setFaculty(String faculty) { this.faculty = faculty; }
}
