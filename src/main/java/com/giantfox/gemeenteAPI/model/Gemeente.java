package com.giantfox.gemeenteAPI.model;

import javax.persistence.*;

@Entity
@Table(name = "gemeenten")
public class Gemeente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String naam;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "provincie_id", nullable = false)
    private Provincie provincie;
    private long inwoners;

    public Gemeente(String naam, Provincie provincie, long inwoners) {
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

    public Provincie getProvincie() {
        return provincie;
    }

    public void setProvincie(Provincie provincie) {
        this.provincie = provincie;
    }

    public long getInwoners() {
        return inwoners;
    }

    public void setInwoners(long inwoners) {
        this.inwoners = inwoners;
    }
}
