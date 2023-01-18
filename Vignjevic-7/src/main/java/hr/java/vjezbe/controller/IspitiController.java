package hr.java.vjezbe.controller;

import hr.java.vjezbe.entitet.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class IspitiController implements Initializable {
    @FXML
    private TextField nazivPredmetaTextField;

    @FXML
    private TextField imePrezimeStudentaTextField;

    @FXML
    private TextField ocjenaTextField;

    @FXML
    private TextField datumIVrijemeIspitaDatePicker;

    @FXML
    TableView<Ispit> ispitiTableView;

    @FXML
    TableColumn<Ispit, String> nazivPredmetaTableColumn;

    @FXML
    TableColumn<Ispit, String> imeIPrezimeStudnentaTableColumn;

    @FXML
    TableColumn<Ispit, Ocjena> ocjenaTableColumn;

    @FXML
    TableColumn<Ispit, LocalDateTime> vrijemeIspitaTableColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Ispit> ispiti = new ArrayList<>();
        List<Predmet> predmeti = new ArrayList<>();
        List<Profesor> profesori = new ArrayList<>();
        List<Student> studenti = new ArrayList<>();
        studenti = Priprema.dohvatiStudente();
        profesori=Priprema.dohvatiProfesore();
        predmeti=Priprema.dohvatiPredmete(profesori,studenti);
        ispiti=Priprema.dohvatiIspitniRok(profesori,studenti,predmeti);

        nazivPredmetaTableColumn.setCellValueFactory(new PropertyValueFactory<>("pred"));
        imeIPrezimeStudnentaTableColumn.setCellValueFactory(new PropertyValueFactory<>("stud"));
        ocjenaTableColumn.setCellValueFactory(new PropertyValueFactory<>("ocjena"));
        vrijemeIspitaTableColumn.setCellValueFactory(new PropertyValueFactory<>("datumIVrijeme"));

        ObservableList<Ispit> observableIspiti = FXCollections.observableList(ispiti);
        ispitiTableView.setItems(observableIspiti);

    }

    public void pretrazi(){
        List<Ispit> ispiti = new ArrayList<>();
        List<Predmet> predmeti = new ArrayList<>();
        List<Profesor> profesori = new ArrayList<>();
        List<Student> studenti = new ArrayList<>();
        studenti = Priprema.dohvatiStudente();
        profesori=Priprema.dohvatiProfesore();
        predmeti=Priprema.dohvatiPredmete(profesori,studenti);
        ispiti=Priprema.dohvatiIspitniRok(profesori,studenti,predmeti);

        if(!nazivPredmetaTextField.getText().isBlank()){
            ispiti=ispiti.stream().filter(ispit -> ispit.getPred().getNaziv().contains(nazivPredmetaTextField.getText())).collect(Collectors.toList());
        }
        if(!imePrezimeStudentaTextField.getText().isBlank()){
            String[] ime = imePrezimeStudentaTextField.getText().split(" ");
            ispiti=ispiti.stream().filter(ispit -> ispit.getStud().getIme().contains(ime[0]) && ispit.getStud().getPrezime().contains(ime[1])).collect(Collectors.toList());
        }
        if(!ocjenaTextField.getText().isBlank()){
            switch (ocjenaTextField.getText()) {
                case "1":
                    Ocjena nedovoljan = Ocjena.NEDOVOLJAN;
                    ispiti = ispiti.stream().filter(ispit -> ispit.getOcjena().equals(nedovoljan)).collect(Collectors.toList());
                    break;
                case "2":
                    Ocjena dovoljan = Ocjena.DOVOLJAN;
                    ispiti = ispiti.stream().filter(ispit -> ispit.getOcjena().equals(dovoljan)).collect(Collectors.toList());
                    break;
                case "3":
                    Ocjena dobar = Ocjena.DOBAR;
                    ispiti = ispiti.stream().filter(ispit -> ispit.getOcjena().equals(dobar)).collect(Collectors.toList());
                    break;
                case "4":
                    Ocjena vrloDobar = Ocjena.VRLO_DOBAR;
                    ispiti = ispiti.stream().filter(ispit -> ispit.getOcjena().equals(vrloDobar)).collect(Collectors.toList());
                    break;
                case "5":
                    Ocjena odlican = Ocjena.ODLICAN;
                    ispiti = ispiti.stream().filter(ispit -> ispit.getOcjena().equals(odlican)).collect(Collectors.toList());
                    break;
            }
        }
        if(!datumIVrijemeIspitaDatePicker.getText().isBlank()){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
            LocalDate unesenoVrijeme = LocalDate.parse(datumIVrijemeIspitaDatePicker.getText(),formatter);
            ispiti=ispiti.stream().filter(ispit -> ispit.getDatumIVrijeme().equals(unesenoVrijeme)).collect(Collectors.toList());
        }

        nazivPredmetaTableColumn.setCellValueFactory(new PropertyValueFactory<>("pred"));
        imeIPrezimeStudnentaTableColumn.setCellValueFactory(new PropertyValueFactory<>("stud"));
        ocjenaTableColumn.setCellValueFactory(new PropertyValueFactory<>("ocjena"));
        vrijemeIspitaTableColumn.setCellValueFactory(new PropertyValueFactory<>("datumIVrijeme"));

        ObservableList<Ispit> observableIspiti = FXCollections.observableList(ispiti);
        ispitiTableView.setItems(observableIspiti);
    }
}
