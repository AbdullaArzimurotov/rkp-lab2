package ru.kafpin.laboratoriya2;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

@Entity
@Table(name = "schoolboys")
public class Schoolboy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "surname", nullable = false)
    @NotBlank(message = "Укажите фамилию.") @Size(max = 80, message = "Фамилия слишком длинная.")
    private String surname;

    @Column(name = "name", nullable = false)
    @NotBlank(message = "Укажите имя.") @Size(max = 80, message = "Имя слишком длинное.")
    private String name;

    @Column(name = "patronymic")
    @Size(max = 80, message = "Отчество слишком длинное.")
    private String patronymic;

    @Column(name = "gender")
    @NotBlank(message = "Укажите пол.") @Pattern(regexp = "Мужской|Женский", message = "Выберите значение пола из списка.")
    private String gender;

    @Column(name = "nationality")
    @NotBlank(message = "Укажите национальность.") @Size(max = 80, message = "Национальность указана слишком длинно.")
    private String nationality;

    @Column(name = "height")
    @NotNull(message = "Укажите рост.") @DecimalMin(value = "40", message = "Рост должен быть не менее 40 см.") @DecimalMax(value = "250", message = "Рост должен быть не более 250 см.")
    private Double height;

    @Column(name = "weight")
    @NotNull(message = "Укажите вес.") @DecimalMin(value = "2", message = "Вес должен быть не менее 2 кг.") @DecimalMax(value = "300", message = "Вес должен быть не более 300 кг.")
    private Double weight;

    @Column(name = "birth_date")
    @NotNull(message = "Укажите дату рождения.") @Past(message = "Дата рождения должна быть в прошлом.")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthDate;

    @Column(name = "phone_number")
    @NotBlank(message = "Укажите мобильный телефон.") @Pattern(regexp = "[+0-9 ()-]{7,22}", message = "Проверьте формат мобильного телефона.")
    private String phoneNumber;

    @Column(name = "home_phone")
    @Pattern(regexp = "(?:[+0-9 ()-]{7,22})?", message = "Проверьте формат домашнего телефона.")
    private String homePhone;

    public Schoolboy() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPatronymic() { return patronymic; }
    public void setPatronymic(String patronymic) { this.patronymic = patronymic; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getNationality() { return nationality; }
    public void setNationality(String nationality) { this.nationality = nationality; }

    public Double getHeight() { return height; }
    public void setHeight(Double height) { this.height = height; }

    public Double getWeight() { return weight; }
    public void setWeight(Double weight) { this.weight = weight; }

    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getHomePhone() { return homePhone; }
    public void setHomePhone(String homePhone) { this.homePhone = homePhone; }
}
