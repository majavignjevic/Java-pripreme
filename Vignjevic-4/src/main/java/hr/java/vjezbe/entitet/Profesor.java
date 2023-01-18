package hr.java.vjezbe.entitet;

public class Profesor extends Osoba{
    private String sifra;
    private String titula;

    private Profesor(String ime, String prezime, String sifra, String titula) {
        super(ime, prezime);
        this.sifra = sifra;
        this.titula = titula;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    public String getTitula() {
        return titula;
    }

    public void setTitula(String titula) {
        this.titula = titula;
    }
    public static class ProfesorBuilder {
        private String ime;
        private String prezime;
        private String sifra;
        private String titula;

        public ProfesorBuilder setIme(String ime) {
            this.ime = ime;
            return this;
        }

        public ProfesorBuilder setPrezime(String prezime) {
            this.prezime = prezime;
            return this;
        }

        public ProfesorBuilder setSifra(String sifra) {
            this.sifra = sifra;
            return this;
        }

        public ProfesorBuilder setTitula(String titula) {
            this.titula = titula;
            return this;
        }

        public Profesor build() {
            Profesor profesor = new Profesor(ime,prezime,sifra,titula);
            this.ime=ime;
            this.prezime=prezime;
            this.sifra=sifra;
            this.titula=titula;
            return profesor;
        }
    }
}
