package hr.java.vjezbe.entitet;

public enum Ocjena {
    NEDOVOLJAN (1),
    DOVOLJAN (2),
    DOBAR (3),
    VRLO_DOBAR (4),
    ODLICAN (5);

    private final Integer number;

    Ocjena(Integer number) {
        this.number = number;
    }

    public Integer getNumber() {
        return number;
    }

}
