package hr.java.vjezbe.entitet;

import java.time.LocalDateTime;

public final class Ispit implements Online{
    private Predmet pred;
    private Student stud;
    private Integer ocjena;
    private LocalDateTime datumIVrijeme;
    private Dvorana dvorana;

    public Ispit(Predmet pred, Student stud, Integer ocjena, LocalDateTime datumIVrijeme, Dvorana dvorana) {
        this.pred = pred;
        this.stud = stud;
        this.ocjena = ocjena;
        this.datumIVrijeme = datumIVrijeme;
        this.dvorana = dvorana;
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

    public Dvorana getDvorana() {
        return dvorana;
    }

    public void setDvorana(Dvorana dvorana) {
        this.dvorana = dvorana;
    }
}

