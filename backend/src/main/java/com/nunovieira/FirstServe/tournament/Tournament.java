package com.nunovieira.FirstServe.tournament;

import com.nunovieira.FirstServe.tournament.enums.Category;
import com.nunovieira.FirstServe.tournament.enums.SurfaceType;
import com.nunovieira.FirstServe.tournament.enums.Tour;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity 
@Table (name = "tournaments")
public class Tournament {
    
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Enumerated(EnumType.STRING)
    private SurfaceType surface;

    @Enumerated(EnumType.STRING)
    private Tour tour;
    
    private String country;
    private String city;
    private Boolean isIndoor;
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
    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
    public SurfaceType getSurface() {
        return surface;
    }
    public void setSurface(SurfaceType surface) {
        this.surface = surface;
    }
    public Tour getTour() {
        return tour;
    }
    public void setTour(Tour tour) {
        this.tour = tour;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public Boolean isIndoor() {
        return isIndoor;
    }
    public void setIndoor(Boolean isIndoor) {
        this.isIndoor = isIndoor;
    }

}
