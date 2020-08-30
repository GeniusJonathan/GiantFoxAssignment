package com.giantfox.gemeenteAPI.model;

import javax.persistence.*;


@Entity
@Table(name = "provincies")
public class Provincie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String naam;
    private String hoofdstad;
    private long oppervlaktkm2;

    public Provincie(String naam, String hoofdstad, long oppervlaktkm2) {
        this.naam = naam;
        this.hoofdstad = hoofdstad;
        this.oppervlaktkm2 = oppervlaktkm2;
    }

    public Provincie() {}

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getHoofdstad() {
        return hoofdstad;
    }

    public void setHoofdstad(String hoofdstad) {
        this.hoofdstad = hoofdstad;
    }

    public long getOppervlaktkm2() {
        return oppervlaktkm2;
    }

    public void setOppervlaktkm2(long oppervlaktkm2) {
        this.oppervlaktkm2 = oppervlaktkm2;
    }
}
