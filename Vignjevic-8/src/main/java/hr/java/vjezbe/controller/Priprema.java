package hr.java.vjezbe.controller;

import hr.java.vjezbe.entitet.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Priprema extends Application {
    public static final String PROFESORI ="dat/profesori.txt";
    public static final String STUDENTI ="dat/studenti.txt";
    public static final String PREDMETI ="dat/predmeti.txt";
    public static final String ISPITNIROK ="dat/ispitni_rok.txt";
    public static final String OBRAZOVNAUSTANOVA ="dat/obrazovneUstanove.txt";
    private static Stage glavnaScena;
    public static Map<Profesor, List<Predmet>> mapaProfesoraIPredmeta = new HashMap<>();

    @Override
    public void start(Stage stage) throws IOException {
        glavnaScena=stage;
        getFMXLLoader("pocetna.fxml", "Dobrodošli!");
    }

    public static void main(String[] args) {
        launch();
    }
    public static Stage getGlavnaScena(){
        return glavnaScena;
    }

    public static void getFMXLLoader(String nazivFXMLDatoteke, String naslovProzora)  throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Priprema.class.getResource(nazivFXMLDatoteke));
        Scene scene = new Scene(fxmlLoader.load(), 600, 500);
        getGlavnaScena().setTitle(naslovProzora);
        getGlavnaScena().setScene(scene);
        getGlavnaScena().show();
    }

    public static Sveuciliste<ObrazovnaUstanova> dohvatiUstanove(List<Profesor> profesori, List<Student> studenti, List<Predmet> predmeti, List<Ispit> ispitniRok) {
        List<ObrazovnaUstanova> obrazovneUstanove = new ArrayList<>();
        Sveuciliste<ObrazovnaUstanova> upisaneUstanove = new Sveuciliste<>(obrazovneUstanove);
        try(BufferedReader ulaz = new BufferedReader(new FileReader(OBRAZOVNAUSTANOVA))) {
            String ucitanaLinija;
            int counter = 1;
            Long id = null, idUstanova=null;
            String[] ids = null;
            Long check= Long.valueOf(1);
            String naziv=null;
            ObrazovnaUstanova ustanova = null;
            List<Predmet> predmetiUstanove = new ArrayList<>();
            List<Student> studentiUstanove = new ArrayList<>();
            List<Ispit> ispitiUstanove = new ArrayList<>();
            List<Profesor> profesoriUstanove = new ArrayList<>();
            while ((ucitanaLinija = ulaz.readLine()) != null) {
                switch (counter) {
                    case 1:
                        id = Long.parseLong(ucitanaLinija);
                        break;
                    case 2:
                        idUstanova = Long.parseLong(ucitanaLinija); // 1- veleuciliste 2 -fakultet racunarstva
                        break;
                    case 3:
                        naziv = ucitanaLinija;
                        break;
                    case 4:
                        ids = ucitanaLinija.split(" ");
                        for(String s : ids) {
                            Long idProf = Long.parseLong(s);
                            Profesor temp = profesori.stream()
                                    .filter(profesor -> profesor.getId().equals(idProf))
                                    .findFirst().get();
                            profesoriUstanove.add(temp);
                        }
                        break;
                    case 5:
                        ids = ucitanaLinija.split(" ");
                        for(String s : ids) {
                            Long idPred = Long.parseLong(s);
                            Predmet temp = predmeti.stream()
                                    .filter(predmet -> predmet.getId().equals(idPred))
                                    .findFirst().get();
                            predmetiUstanove.add(temp);
                        }
                        break;
                    case 6:
                        // 1-5-4 2-5-5
                        ids = ucitanaLinija.split(" ");
                        // 1-5-4, 2-5-5
                        for(String s : ids) { //1-5-4
                            String[] ocj = s.split("-");
                            Student temp = studenti.stream()
                                    .filter(student -> student.getId().equals(Long.parseLong(ocj[0])))
                                    .findFirst().get();
                            studentiUstanove.add(temp);
                            temp.setOcjenaZavrsni(Integer.parseInt(ocj[1]));
                            temp.setOcjenaObranaZavrsni(Integer.parseInt(ocj[2]));
                        }
                        break;
                    case 7:
                        ids = ucitanaLinija.split(" ");
                        for(String s : ids) {
                            Long idIspit = Long.parseLong(s);
                            Ispit temp = ispitniRok.stream()
                                    .filter(ispit -> ispit.getId().equals(idIspit))
                                    .findFirst().get();
                            ispitiUstanove.add(temp);
                        }
                        if(idUstanova.equals(check)){
                            ustanova = new VeleucilisteJave(id,naziv,predmetiUstanove,profesoriUstanove,studentiUstanove,ispitiUstanove);
                        }
                        else
                            ustanova = new FakultetRacunarstva(id,naziv,predmetiUstanove,profesoriUstanove,studentiUstanove,ispitiUstanove);
                        upisaneUstanove.dodajObrazovnuUstanovu(ustanova);
                        counter=0;
                        break;
                }
                counter++;
            }
        }
        catch (IOException e){
            System.err.println(e);
        }
        return upisaneUstanove;
    }

    public static List<Ispit> dohvatiIspitniRok(List<Profesor> profesori, List<Student> studenti, List<Predmet> predmeti) {
        List<Ispit> uneseniRokovi = new ArrayList<>();

        try(BufferedReader ulaz = new BufferedReader(new FileReader(ISPITNIROK))) {
            String ucitanaLinija;
            int counter = 1;
            Long id = null;
            String imeDvorane = null, imeZgrade = null;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.'T'HH:mm");
            LocalDateTime vrijemeIspita = null;
            Predmet tempPredmet = null;
            Profesor tempProfesor = null;
            Student tempStudent = null;
            Dvorana tempDvorana = null;
            Ispit tempRok = null;
            Ocjena ocjena = null;
            while ((ucitanaLinija = ulaz.readLine()) != null) {
                switch (counter) {
                    case 1:
                        id = Long.parseLong(ucitanaLinija);
                        break;
                    case 2:
                        Long odabraniPredmet = Long.parseLong(ucitanaLinija);
                        tempPredmet = predmeti.stream()
                                .filter(predmet -> predmet.getId().equals(odabraniPredmet))
                                .findFirst().get();
                        break;
                    case 3:
                        imeDvorane = ucitanaLinija;
                        break;
                    case 4:
                        imeZgrade = ucitanaLinija;
                        tempDvorana = new Dvorana(imeDvorane, imeZgrade);
                        break;
                    case 5:
                        Long idStud = Long.parseLong(ucitanaLinija);
                        tempStudent = studenti.stream()
                                .filter(student -> student.getId().equals(idStud))
                                .findFirst().get();
                        break;
                    case 6:
                        Integer tempOcjena = Integer.parseInt(ucitanaLinija);
                        switch(tempOcjena){
                            case 1:
                                ocjena=Ocjena.NEDOVOLJAN;
                                break;
                            case 2:
                                ocjena=Ocjena.DOVOLJAN;
                                break;
                            case 3:
                                ocjena=Ocjena.DOBAR;
                                break;
                            case 4:
                                ocjena=Ocjena.VRLO_DOBAR;
                                break;
                            case 5:
                                ocjena=Ocjena.ODLICAN;
                                break;
                            default:
                                System.err.println("Nije unesena dobra ocjena!");
                        }
                        break;
                    case 7:
                        vrijemeIspita = LocalDateTime.parse(ucitanaLinija, formatter);
                        tempRok=new Ispit(id,tempPredmet,tempStudent,ocjena,vrijemeIspita, tempDvorana);
                        uneseniRokovi.add(tempRok);
                        counter=0;
                        break;
                }
                counter++;
            }
        }
        catch (IOException e){
            System.err.println(e);
        }

        return uneseniRokovi;
    }

    public static List<Student> dohvatiStudente() {
        List<Student> uneseniStudenti = new ArrayList<>();
        Long id=null;
        String imeStudenta=null, prezimeStudenta=null,jmbagStudenta=null;
        LocalDate datumRodjenjaStudenta=null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
        try(BufferedReader ulaz=new BufferedReader(new FileReader(STUDENTI))) {
            String ucitanaLinija;
            int counter = 1;
            while ((ucitanaLinija = ulaz.readLine()) != null) {
                switch (counter) {
                    case 1:
                        id = Long.parseLong(ucitanaLinija);
                        break;
                    case 2:
                        imeStudenta = ucitanaLinija;
                        break;
                    case 3:
                        prezimeStudenta = ucitanaLinija;
                        break;
                    case 4:
                        datumRodjenjaStudenta = LocalDate.parse(ucitanaLinija, formatter);
                        break;
                    case 5:
                        jmbagStudenta = ucitanaLinija;
                        counter = 0;
                        Student temp = new Student(id,imeStudenta,prezimeStudenta,jmbagStudenta,datumRodjenjaStudenta);
                        uneseniStudenti.add(temp);
                        break;
                }
                counter++;
            }
        }catch (IOException e){
            System.err.println(e);
        }
        return uneseniStudenti;
    }

    public static List<Predmet> dohvatiPredmete(List<Profesor> profesori, List<Student> studenti) {
        List<Predmet> uneseniPredmeti = new ArrayList<>();
        try(BufferedReader ulaz = new BufferedReader(new FileReader(PREDMETI))){
            String ucitanaLinija;
            int counter=1;
            Long id=null;
            String sifra=null, naziv=null;
            Integer brojECTS=null;
            Profesor odabraniProfesor=null;
            Set<Student> odabraniStudenti = new HashSet<>();
            List<Predmet> listaPredmeta= new ArrayList<>();
            while((ucitanaLinija= ulaz.readLine())!=null){
                switch (counter) {
                    case 1:
                        id=Long.parseLong(ucitanaLinija);
                        break;
                    case 2:
                        sifra=ucitanaLinija;
                        break;
                    case 3:
                        naziv=ucitanaLinija;
                        break;
                    case 4:
                        brojECTS=Integer.parseInt(ucitanaLinija);
                        break;
                    case 5:
                        Long profId = Long.parseLong(ucitanaLinija);
                        odabraniProfesor = profesori.stream()
                                .filter(profesor -> profesor.getId().equals(profId))
                                .findFirst().get();
                        break;
                    case 6:
                        String[] ids = ucitanaLinija.split(" ");
                        for(String s : ids){
                            Long idStud=Long.parseLong(s);
                            Student temp = studenti.stream()
                                    .filter(student -> student.getId().equals(idStud))
                                    .findFirst().get();
                            odabraniStudenti.add(temp);
                        }
                        Predmet temp = new Predmet(id, sifra,naziv,brojECTS,odabraniProfesor,odabraniStudenti);
                        uneseniPredmeti.add(temp);
                        listaPredmeta =mapaProfesoraIPredmeta.get(odabraniProfesor);
                        if(listaPredmeta==null){
                            listaPredmeta=new ArrayList<>();
                        }
                        listaPredmeta.add(temp);
                        mapaProfesoraIPredmeta.put(odabraniProfesor,listaPredmeta);
                        counter=0;
                        break;
                }
                counter++;
            }
        }
        catch (IOException e){
            System.err.println(e);
        }

        return uneseniPredmeti;
    }

    public static List<Profesor> dohvatiProfesore() {
        List<Profesor> uneseniProfesori = new ArrayList<>();

        try(BufferedReader ulaz = new BufferedReader(new FileReader(PROFESORI))){
            String ucitanaLinija;
            int counter=1;
            Profesor.ProfesorBuilder builder = new Profesor.ProfesorBuilder();
            while((ucitanaLinija= ulaz.readLine())!=null){
                switch (counter) {
                    case 1:
                        builder.setid(Long.parseLong(ucitanaLinija));
                        break;
                    case 2:
                        builder.setSifra(ucitanaLinija);
                        break;
                    case 3:
                        builder.setIme(ucitanaLinija);
                        break;
                    case 4:
                        builder.setPrezime(ucitanaLinija);
                        break;
                    case 5:
                        builder.setTitula(ucitanaLinija);
                        uneseniProfesori.add(builder.build());
                        counter=0;
                }
                counter++;
            }
        }
        catch (IOException e){
            System.err.println(e);
        }

        return uneseniProfesori;
    }

}
