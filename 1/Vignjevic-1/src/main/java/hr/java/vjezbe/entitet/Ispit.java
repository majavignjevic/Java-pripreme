package hr.java.vjezbe.entitet;

import java.time.LocalDateTime;

public class Ispit {
    private Predmet pred;
    private Student stud;
    private Integer ocjena;
    private  LocalDateTime datumIVrijeme;

    public Ispit(Predmet pred, Student stud, Integer ocjena, LocalDateTime datumIVrijeme) {
        this.pred = pred;
        this.stud = stud;
        this.ocjena = ocjena;
        this.datumIVrijeme = datumIVrijeme;
    }

    public Predmet getPred() {
        return pred;
    }

    public void setPred(Predmet pred) {
        this.pred = pred;
    }

    public Student getStud() {
        return stud;
    }

    public void setStud(Student stud) {
        this.stud = stud;
    }

    public Integer getOcjena() {
        return ocjena;
    }

    public void setOcjena(Integer ocjena) {
        this.ocjena = ocjena;
    }

    public LocalDateTime getDatumIVrijeme() {
        return datumIVrijeme;
    }

    public void setDatumIVrijeme(LocalDateTime datumIVrijeme) {
        this.datumIVrijeme = datumIVrijeme;
    }
}

