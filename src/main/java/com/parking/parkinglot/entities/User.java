package com.parking.parkinglot.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
  private String username;
  private String password;
  private String email;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO) //asta face ca id-u sa se autoincrementeze
  private Long id;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  @OneToMany(mappedBy = "owner", orphanRemoval = true)
  private List<Car> cars = new ArrayList<>();

  public List<Car> getUsers() {
    return cars;
  }

  public List<Car> getCars() {
    return cars;
  }

  public void setCars(List<Car> cars) {
    this.cars = cars;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }
}