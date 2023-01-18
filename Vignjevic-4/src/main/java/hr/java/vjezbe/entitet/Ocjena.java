package hr.java.vjezbe.entitet;

public enum Ocjena {
    NEDOVOLJAN (1, "nedovoljan"),
    DOVOLJAN (2, "dovoljan"),
    DOBAR (3, "dobar"),
    VRLO_DOBAR (4, "vrlo dobar"),
    ODLICAN (5, "odlican");

    private final Integer number;
    private final String ime;

    Ocjena(Integer number, String ime) {
        this.number = number;
        this.ime = ime;
    }

    public Integer getNumber() {
        return number;
    }

    public String getIme() {
        return ime;
    }
}
