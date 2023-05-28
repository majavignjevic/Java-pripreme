package hr.java.vjezbe.controller;

import hr.java.vjezbe.entitet.Profesor;
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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UnosProfesoraController implements Initializable {
    @FXML
    private TextField sifraProfesoraTextField;

    @FXML
    private TextField imeProfeosraTextField;

    @FXML
    private TextField prezimeProfesoraTextField;

    @FXML
    private TextField titulaProfesoraTextField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Profesor> profesori = new ArrayList<>();
        profesori=Priprema.dohvatiProfesore();
    }

    @FXML
    public void unesi(){
        String sifra = sifraProfesoraTextField.getText();
        String ime = imeProfeosraTextField.getText();
        String prezime = prezimeProfesoraTextField.getText();
        String titula = titulaProfesoraTextField.getText();
        Long id = Long.valueOf(Priprema.dohvatiProfesore().size()+1);
        List<Profesor> profesori = new ArrayList<>();
        profesori=Priprema.dohvatiProfesore();

        if(sifra.isBlank() || ime.isBlank() || prezime.isBlank() || titula.isBlank()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Look, an Information Dialog");
            alert.setContentText("Nisu uneseni svi podaci!");
            alert.showAndWait();
        }
        else{
            Profesor noviProfesor = new Profesor(id, ime, prezime,sifra,titula);
            profesori.add(noviProfesor);

            try (PrintWriter out = new PrintWriter(new FileWriter(new File("dat/profesori.txt"),true))) {
                out.println("\n" + id);
                out.println(sifra);
                out.println(ime);
                out.println(prezime);
                out.print(titula);
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }
}
