package com.giantfox.gemeenteAPI.model;

import javax.persistence.*;

@Entity
@Table(name = "gemeenten")
public class Gemeente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String naam;
    private String provincie;
    private long inwoners;

    public Gemeente(String naam, String provincie, long inwoners) {
        this.naam = naam;
        this.provincie = provincie;
        this.inwoners = inwoners;
    }

    public Gemeente () {}

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getProvincie() {
        return provincie;
    }

    public void setProvincie(String provincie) {
        this.provincie = provincie;
    }

    public long getInwoners() {
        return inwoners;
    }

    public void setInwoners(long inwoners) {
        this.inwoners = inwoners;
    }
}
