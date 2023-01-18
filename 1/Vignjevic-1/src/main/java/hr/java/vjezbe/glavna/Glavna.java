package hr.java.vjezbe.glavna;

import hr.java.vjezbe.entitet.Ispit;
import hr.java.vjezbe.entitet.Predmet;
import hr.java.vjezbe.entitet.Profesor;
import hr.java.vjezbe.entitet.Student;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Glavna {

    //definiramo broj unosa profesora, predmeta, studenata i ispitnih rokova
    private static final Integer brojProfesora=2;
    private static final Integer brojPredmeta=3;
    private static final Integer brojStudenta=3;
    private static final Integer brojIspitnihRokova=1;


    public static void main(String[] args) {

        Scanner unos = new Scanner(System.in);//definiramo ulaznu klasu scanner
        Integer i,j,k; //definiramo varijable za petlje

        //deifniramo polja klasa koja cemo koristiti
        Profesor[] profesori = new Profesor[brojProfesora];
        Predmet[] predmeti = new Predmet[brojPredmeta];
        Student[] studenti = new Student[brojStudenta];
        Ispit[] ispiti = new Ispit[brojIspitnihRokova];

        //unosimo sve informacije za profesore pa spremamo u polje profesora
        for (i = 0; i<brojProfesora;i++){
            System.out.print("Unesite " + (i+1) + ". profesora: \n");
            System.out.print("Unesite sifru profesora: ");
            String sifProf = unos.nextLine();
            System.out.print("Unesite ime profesora: ");
            String imeProf = unos.nextLine();
            System.out.print("Unesite prezime profesora: ");
            String prezimeProf = unos.nextLine();
            System.out.print("Unesite titulu profesora: ");
            String titulaProf = unos.nextLine();

            profesori[i] = new Profesor(sifProf,imeProf,prezimeProf,titulaProf);
        }
        //unosimo sve informacije za predmete pa spremamo u polje predmeta
        for (i = 0; i<brojPredmeta;i++){
            System.out.print("Unesite " + (i+1) + ". predmet: \n");
            System.out.print("Unesite sifru predmeta: ");
            String sifPredmet = unos.nextLine();
            System.out.print("Unesite naziv predmeta: ");
            String nazivPredmet = unos.nextLine();
            System.out.print("Unesite broj ECTS bodova za predmet '" + nazivPredmet + "' :");
            Integer brEcts = unos.nextInt();
            System.out.print("Odaberite profesora: ");
            for (j = 0; j < brojProfesora; j++) {
                System.out.print("\n" + profesori[j].getSifra() + ". " + profesori[j].getIme() + " " +
                        profesori[j].getPrezime());
            }
            System.out.print("\nOdabir >> ");
            Integer m =unos.nextInt();
            System.out.print("Unesite broj studenata za predmet '" + nazivPredmet + "' :");
            Integer brStudenata = unos.nextInt();

            Profesor profa= new Profesor(
                    profesori[m-1].getSifra(),
                    profesori[m-1].getIme(),
                    profesori[m-1].getPrezime(),
                    profesori[m-1].getTitula());
            predmeti[i]=new Predmet(sifPredmet,nazivPredmet,brEcts,profa,brStudenata);

            unos.nextLine();
        }

        //unosimo sve informacije za studente pa spremamo u polje studenata
        for (i=0;i<brojStudenta;i++) {
            System.out.print("Unesite " + (i+1) + ". studenta:\n");
            System.out.print("Unesite ime studenta: ");
            String studIme = unos.nextLine();
            System.out.print("Unesite prezime studenta: ");
            String studPrezime = unos.nextLine();
            System.out.print("Unesite datum rođenja studenta " + studPrezime + " "+ studIme +
                    " u formatu (dd.MM.yyyy.): ");
            String datumRodjenjaString = unos.nextLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
            LocalDate datumRodjenja = LocalDate.parse(datumRodjenjaString, formatter);
            System.out.println("Unesite JMBAG studenta " + studPrezime + " "+ studIme + ": ");
            String studJmbag = unos.nextLine();
            studenti[i] = new Student(studIme,studPrezime, studJmbag,datumRodjenja);
        }

        //unosimo sve informacije za ispitne rokove pa spremamo u polje ispitnih rokova

        for(i=0;i<brojIspitnihRokova;i++){
            System.out.println("Unesite" + (i+1) + ". ispitni rok:\n");
            System.out.println("Odaberite predmet:\n");
            for(j=0;j<brojPredmeta;j++){
                System.out.println((j+1) + ". " + predmeti[j].getNaziv());
            }
            System.out.println("Odabir >>");
            Integer n = unos.nextInt()-1;
            System.out.println("Odaberite studenta:\n");
            for(j=0;j<brojStudenta;j++){
                System.out.println((j+1) + ". " + studenti[j].getIme() + " " + studenti[j].getPrezime());
            }
            System.out.println("Odabir >>");
            Integer m = unos.nextInt()-1;
            predmeti[m]=new Predmet(predmeti[n].getSifra(),predmeti[n].getNaziv(),predmeti[i].getBrojEctsBodova(),predmeti[n].getNositelj(),studenti[m]);
            System.out.println("Odaberite ocjenu na ispitu (1-5): ");
            Integer ocjena = unos.nextInt();
            unos.nextLine();
            System.out.println("Unesite datum i vrijeme ispita u formatu (dd.MM.yyyy.THH:mm):");
            String datum = unos.nextLine();
            DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy.'T'HH:mm");
            LocalDateTime datumIVrijeme = LocalDateTime.parse(datum, myFormat);
            ispiti[i] = new Ispit(predmeti[n],studenti[m],ocjena, datumIVrijeme);
            unos.nextLine();
        }
        // po ispitnim rokovima trazimo tko je ostvario ocjenu odlican
        for(i=0;i<brojIspitnihRokova;i++){
            if(ispiti[i].getOcjena()==5){
                System.out.println("Student " + ispiti[i].getStud().getIme() + " " + ispiti[i].getStud().getPrezime() +
                " je ostvario ocjenu 'izvrstan' na predmetu '" + ispiti[i].getPred().getNaziv() +"'\n");
            }
        }

    }
}
