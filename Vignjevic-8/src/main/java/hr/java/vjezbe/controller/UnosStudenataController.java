package hr.java.vjezbe.controller;

import hr.java.vjezbe.entitet.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.text.DateFormat;
import java.text.Format;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.ResourceBundle;

public class UnosStudenataController implements Initializable {
    @FXML
    private TextField imeStudentaTextField;

    @FXML
    private TextField prezimeStudentaTextField;

    @FXML
    private TextField jmbagStudentaTextField;

    @FXML
    DatePicker datumRodenjaStudentaDatePicker;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Student> studenti = new ArrayList<>();
        studenti=Priprema.dohvatiStudente();
    }

    @FXML
    public void unesi(){
        String ime = imeStudentaTextField.getText();
        String prezime = prezimeStudentaTextField.getText();
        String jmbag = jmbagStudentaTextField.getText();

        if(ime.isBlank() || prezime.isBlank() || jmbag.isBlank()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Look, an Information Dialog");
            alert.setContentText("Nisu uneseni svi podaci!");
            alert.showAndWait();
        }
        else{
            Long id = Priprema.dohvatiStudente().stream().count()+1;
            Student student = new Student(id,ime,prezime,jmbag,datumRodenjaStudentaDatePicker.getValue());
            try (PrintWriter out = new PrintWriter(new FileWriter(new File("dat/predmeti.txt"),true))) {
                out.println("\n" + id);
                out.println(ime);
                out.println(prezime);
                out.println(jmbag);
                out.print(datumRodenjaStudentaDatePicker.getValue());
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }
}
