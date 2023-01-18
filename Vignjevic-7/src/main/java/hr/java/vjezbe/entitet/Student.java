package hr.java.vjezbe.entitet;

import java.time.LocalDate;

public class Student extends Osoba{
    private String jmbag;
    private LocalDate datumRodjenja;
    private Integer ocjenaObranaZavrsni;
    private Integer ocjenaZavrsni;
    public Student(Long id,String ime, String prezime, String jmbag, LocalDate datumRodjenja) {
        super(id,ime, prezime);
        this.jmbag = jmbag;
        this.datumRodjenja = datumRodjenja;
    }

    public String getJmbag() {
        return jmbag;
    }

    public void setJmbag(String jmbag) {
        this.jmbag = jmbag;
    }

    public LocalDate getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(LocalDate datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public Integer getOcjenaObranaZavrsni() {
        return ocjenaObranaZavrsni;
    }

    public void setOcjenaObranaZavrsni(Integer ocjenaObranaZavrsni) {
        this.ocjenaObranaZavrsni = ocjenaObranaZavrsni;
    }

    public Integer getOcjenaZavrsni() {
        return ocjenaZavrsni;
    }

    public void setOcjenaZavrsni(Integer ocjenaZavrsni) {
        this.ocjenaZavrsni = ocjenaZavrsni;
    }

    @Override
    public String toString() {
        return getIme() + " " + getPrezime();
    }
}