package hr.java.vjezbe.controller;

import hr.java.vjezbe.entitet.Ispit;
import hr.java.vjezbe.entitet.Predmet;
import hr.java.vjezbe.entitet.Profesor;
import hr.java.vjezbe.entitet.Student;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UnosIspitaContoller implements Initializable {
    @FXML
    private TextField nazivPredmetaTextField;

    @FXML
    private  TextField imeiPrezimeStudentaTextField;

    @FXML
    private TextField ocjenaTextField;

    @FXML
    private TextField datumIVrijemeIspitaTextField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Student> studenti = new ArrayList<>();
        List<Profesor> profesori = new ArrayList<>();
        List<Predmet> predmeti = new ArrayList<>();
        List <Ispit> ispit = new ArrayList<>();

        studenti = Priprema.dohvatiStudente();
        profesori = Priprema.dohvatiProfesore();
        predmeti = Priprema.dohvatiPredmete(profesori,studenti);
        ispit = Priprema.dohvatiIspitniRok(profesori,studenti,predmeti);
    }

    @FXML
    public void unesi(){

    }

}
