package hr.java.vjezbe.controller;

import hr.java.vjezbe.entitet.Predmet;
import hr.java.vjezbe.entitet.Profesor;
import hr.java.vjezbe.entitet.Student;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.*;

public class UnosPredmetaController implements Initializable {
    @FXML
    private TextField sifraPredmetaTextField;

    @FXML
    private TextField nazivPredmetaTextField;

    @FXML
    private TextField imeIPrezimeNositeljaTextField;

    @FXML
    private TextField brojETCSbodovaTextField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Student> studenti = new ArrayList<>();
        List<Profesor> profesori = new ArrayList<>();
        List<Predmet> predmeti = new ArrayList<>();

        studenti = Priprema.dohvatiStudente();
        profesori = Priprema.dohvatiProfesore();
        predmeti = Priprema.dohvatiPredmete(profesori, studenti);
    }

    @FXML
    public void unesi(){
        String sifra = sifraPredmetaTextField.getText();
        String naziv = nazivPredmetaTextField.getText();
        String[] uneseniProfesor = imeIPrezimeNositeljaTextField.getText().split(" ");
        Profesor profesorPredmeta = Priprema.dohvatiProfesore().stream()
                .filter(profesor -> profesor.getIme().equals(uneseniProfesor[0]) && profesor.getPrezime().equals(uneseniProfesor[1]))
                .findFirst().get();
        Long id = Priprema.dohvatiProfesore().stream().count()+1;
        Integer brojEtcsBodova = Integer.parseInt(brojETCSbodovaTextField.getText());

        if(sifra.isBlank() || naziv.isBlank() || profesorPredmeta==null || brojETCSbodovaTextField.getText().isBlank()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Look, an Information Dialog");
            alert.setContentText("Nisu uneseni svi podaci!");
            alert.showAndWait();
        }
        else{
            Predmet uneseniPredmet = new Predmet(id, sifra,naziv, brojEtcsBodova, profesorPredmeta);

            try (PrintWriter out = new PrintWriter(new FileWriter(new File("dat/predmeti.txt"),true))) {
                out.println("\n" + id);
                out.println(sifra);
                out.println(naziv);
                out.println(brojEtcsBodova);
                out.print(profesorPredmeta.getId());
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }
}
