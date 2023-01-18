package hr.java.vjezbe.glavna;

import hr.java.vjezbe.entitet.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

import hr.java.vjezbe.iznimke.PostojiViseNajmladjihStudenataException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *Program nastao kao priprema za kolegij Programiranje u jeziku Java
 * @author Maja Vignjević
 */
public class Glavna {

    //definiramo broj unosa profesora, predmeta, studenata i ispitnih rokova
    private static final Integer brojProfesora=2;
    private static final Integer brojPredmeta=2;
    private static final Integer brojStudenta=2;
    private static final Integer brojIspitnihRokova=2;
    private static final Logger logger = LoggerFactory.getLogger(Glavna.class);

    public static void main(String[] args) {

        Scanner unos = new Scanner(System.in);//definiramo ulaznu klasu scanner
        Integer i, j, k, l = 0, m, n; //definiramo varijable za petlje
        Integer brojObrazovnihUstanova=1;

        //deifniramo polja klasa koja cemo koristiti
        Profesor[] profesori = new Profesor[brojProfesora];
        Predmet[] predmeti = new Predmet[brojPredmeta];
        Student[] studenti = new Student[brojStudenta];
        Ispit[] ispiti = new Ispit[brojIspitnihRokova];

        brojObrazovnihUstanova = unosBroja(unos, "Unesite broj obrazovnih ustanova");
        ObrazovnaUstanova[] ustanove = new ObrazovnaUstanova[brojObrazovnihUstanova];

        for (m = 0; m < brojObrazovnihUstanova; m++) { //unosimo podatke za n obrazovnih ustanova, vrti sve:/
            System.out.println("Unesite podatke za " + (m + 1) + ". obrazovnu ustanovu: ");
            //unosimo sve informacije za profesore pa spremamo u polje profesora za tu obrazovnu ustanovu (prebaciti u metodu?)
            for (i = 0; i < brojProfesora; i++) {
                System.out.print("Unesite " + (i + 1) + ". profesora: \n");

                System.out.print("Unesite sifru profesora: ");
                String sifProf = unos.nextLine();

                System.out.print("Unesite ime profesora: ");
                String imeProf = unos.nextLine();

                System.out.print("Unesite prezime profesora: ");
                String prezimeProf = unos.nextLine();

                System.out.print("Unesite titulu profesora: ");
                String titulaProf = unos.nextLine();

                profesori[i] = new Profesor.ProfesorBuilder()  //unosimo profesora preko buildera
                        .setIme(imeProf)
                        .setPrezime(prezimeProf)
                        .setSifra(sifProf)
                        .setTitula(titulaProf)
                        .build();
            }

            //unosimo sve informacije za predmete pa spremamo u polje predmeta
            for (i = 0; i < brojPredmeta; i++) {
                System.out.print("Unesite " + (i + 1) + ". predmet: \n");

                System.out.print("Unesite sifru predmeta: ");
                String sifPredmet = unos.nextLine();

                System.out.print("Unesite naziv predmeta: ");
                String nazivPredmet = unos.nextLine();
                Integer brEcts = unosBroja(unos,"Unesite broj ETCS bodova:");

                System.out.print("Odaberite profesora: ");
                for (j = 0; j < brojProfesora; j++) {
                    System.out.print("\n" + (j + 1) + ". " + profesori[j].getIme() + " " +
                            profesori[j].getPrezime());
                }
                System.out.print("\nOdabir >> ");
                Integer poljeProf = unosBroja(unos, "Odaberite profesora:\nOdabir >>");

                System.out.print("Unesite broj studenata za predmet '" + nazivPredmet + "' :");
                Integer brStudenata = unosBroja(unos, "Unesite broj studenata: ");

                Profesor profa = new Profesor.ProfesorBuilder()
                        .setIme(profesori[poljeProf - 1].getIme())
                        .setPrezime(profesori[poljeProf - 1].getPrezime())
                        .setSifra(profesori[poljeProf - 1].getSifra())
                        .setTitula(profesori[poljeProf - 1].getTitula())
                        .build();

                predmeti[i] = new Predmet(sifPredmet, nazivPredmet, brEcts, profa, brStudenata);

                unos.nextLine();
            }

            //unosimo sve informacije za studente pa spremamo u polje studenata
            for (i = 0; i < brojStudenta; i++) {
                System.out.print("Unesite " + (i + 1) + ". studenta:\n");

                System.out.print("Unesite ime studenta: ");
                String studIme = unos.nextLine();

                System.out.print("Unesite prezime studenta: ");
                String studPrezime = unos.nextLine();

                System.out.print("Unesite datum rođenja studenta " + studPrezime + " " + studIme +
                        " u formatu (dd.MM.yyyy.): ");
                String datumRodjenjaString = unos.nextLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
                LocalDate datumRodjenja = LocalDate.parse(datumRodjenjaString, formatter);

                System.out.print("Unesite JMBAG studenta " + studPrezime + " " + studIme + ": ");
                String studJmbag = unos.nextLine();

                studenti[i] = new Student(studIme, studPrezime, studJmbag, datumRodjenja);
            }

            //unosimo sve informacije za ispitne rokove pa spremamo u polje ispitnih rokova
            for (i = 0; i < brojIspitnihRokova; i++) {
                ispiti[i] = (null);

                System.out.println("Unesite" + (i + 1) + ". ispitni rok:");

                System.out.println("Odaberite predmet:");
                for (j = 0; j < brojPredmeta; j++) {
                    System.out.println((j + 1) + ". " + predmeti[j].getNaziv());
                }
                System.out.println("Odabir >>");
                Integer poljePredmet = unosBroja(unos, "Unesi broj predmeta:\nOdabir >>");

                System.out.print("Unesite naziv dvorane: ");
                String dvorana = unos.nextLine();

                System.out.print("Unesite zgradu dvorane: ");
                String zgrada = unos.nextLine();

                Dvorana dvorana1 = new Dvorana(dvorana, zgrada);

                System.out.println("Odaberite studenta:");
                for (j = 0; j < brojStudenta; j++) {
                    System.out.println((j + 1) + ". " + studenti[j].getIme() + " " + studenti[j].getPrezime());
                }
                System.out.print("Odabir >>");
                Integer poljeStudent = unos.nextInt();
                unos.nextLine();

                Predmet pred= new Predmet(
                        predmeti[poljePredmet-1].getSifra(),
                        predmeti[poljePredmet-1].getNaziv(),
                        predmeti[poljePredmet-1].getBrojEctsBodova(),
                        predmeti[poljePredmet-1].getNositelj(),
                        studenti[poljeStudent-1]);

                System.out.println("Odaberite ocjenu na ispitu (1-5): ");
                Integer ocjena = unosBroja(unos, "Unesite ocjenu na ispitu:");

                System.out.println("Unesite datum i vrijeme ispita u formatu (dd.MM.yyyy.THH:mm):");
                String datum = unos.nextLine();
                DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy.'T'HH:mm");
                LocalDateTime datumIVrijeme = LocalDateTime.parse(datum, myFormat);
                ispiti[i] = new Ispit(predmeti[poljePredmet], studenti[poljeStudent], ocjena, datumIVrijeme, dvorana1);
                unos.nextLine();
            }

            // po ispitnim rokovima trazimo tko je ostvario ocjenu odlican
            for (i = 0; i < brojIspitnihRokova; i++) {
                if (ispiti[i].getOcjena() == 5) {
                    System.out.println("Student " + ispiti[i].getStud().getIme() + " " + ispiti[i].getStud().getPrezime() +
                            " je ostvario ocjenu 'izvrstan' na predmetu '" + ispiti[i].getPred().getNaziv() + "'\n");
                }
            }

            System.out.print("Odaberite obrazovnu ustanovu za navedene podatke koju želite unijeti\n (1 -" +
                    "Veleučilište Jave, 2 - Fakultet računarstva): ");
            Integer brojUstanove = unosBroja(unos, "Unesite broj obrazovne ustanove");

            System.out.print("Unesite naziv obrazovne ustanove: ");
            String nazivUstanove = unos.nextLine();

            if (brojUstanove == 1) {
                ustanove[brojUstanove] = new VeleucilisteJave(nazivUstanove, predmeti, profesori, studenti, ispiti);
            } else if (brojUstanove == 2) {           //instance of trazimo koji je Veleuciliste Jave ili Fakultet racunarstva
                ustanove[brojUstanove] = new FakultetRacunarstva(nazivUstanove, predmeti, profesori, studenti, ispiti);
            } else {
                System.out.print("Krivi unos!\n");
            }

            for (Student student : studenti) {

                Visokoskolska visoka =null;
                Integer br=0;

                for(Ispit ispit : ispiti){
                    if(ispit.getStud().equals(student)){
                        br++;
                    }
                }
                Ispit[] polozeni = new Ispit[br];
                for(Ispit ispit : polozeni) {
                    polozeni[i] = new Ispit(ispit.getPred(), ispit.getStud(), ispit.getOcjena(), ispit.getDatumIVrijeme(), ispit.getDvorana());
                    if (ispit.getOcjena() == 1) {
                        System.out.println("Student " + student.getIme() + " " + student.getPrezime() + "ima negativnu ocjenu iz ispita i ne moze pristupiti obrani\n");
                        break;
                    } else{
                        System.out.print("Unesite ocjenu završnog rada za studenta " + student.getIme() + " " + student.getPrezime() + ": ");
                        Integer zavrsni = unosBroja(unos, "Unesite ocjenu zavrsnog rada");

                        System.out.print("Unesite ocjenu obrane završnog rada za studenta " + student.getIme() + " " + student.getPrezime() + ": ");
                        Integer obrana = unosBroja(unos, "Unesite ocjenu obrane zavrsnog rada");

                        BigDecimal prosjek = visoka.izracunajKonacnuOcjenuStudijaZaStudenta(polozeni,zavrsni,obrana);

                        System.out.println("Student " + student.getIme() + " " + student.getPrezime() + "ima prosjek " + prosjek);
                    }
                }
            }
            l++;
        }

        for(ObrazovnaUstanova ou : ustanove){           //provjeravamo postoji li vise najmladih studenata na fakultetu
            if(ou instanceof FakultetRacunarstva fer){
                try{                                    //ako nema, trazimo studenta koji osvaja rektorovu nagradu
                    fer.odrediStudentaZaRektorovuNagradu();
                    logger.info("Pronaden je student za rektorovu nagradu");
                }catch (PostojiViseNajmladjihStudenataException e){     //ako ima bacamo grešku koju lovimo sa class exceptionom i završavamo program
                    System.out.println("Program završava s izvođenjem.");
                    System.out.println(e.getMessage());
                    logger.info("Postoji vise najmladih studenata, program zavrsava sa izvodenjem\n");
                    System.exit(0);
                }
            }
        }
    }

    private static Integer unosBroja(Scanner unos, String poruka) {         //unosimo brojcanu vrijednost u program i hvatamo iznimku

        Integer x=null;
        boolean bool=true;

        do{
            try{
                bool=false;
                System.out.print(poruka);
                String temp = unos.nextLine(); // "5"-> 5
                Integer.parseInt(temp);
                logger.info("Unesen je broj");
            }
            catch (NumberFormatException ex){
                bool=true;
                System.out.println("Morate unesti brojčani podatak");
                logger.info("Nije unesena brojcana vrijednost");
               // unos.nextLine();
            }
        }while(bool);
        return x;
    }
}

//                unos.nextLine();
