package hr.java.vjezbe.controller;

import hr.java.vjezbe.entitet.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class PretragaStudenataController implements Initializable {
    @FXML
    private TextField jmbagStudentaTextField;

    @FXML
    private TextField imeStudentaTextField;

    @FXML
    private TextField prezimeStudentaTextField;

    @FXML
    private DatePicker datumRodenjaStudentaDatePicker;

    @FXML
    TableView<Student> studentTableView;

    @FXML
    TableColumn<Student, String> jmbagStudentColumnTable;

    @FXML
    TableColumn<Student, String> prezimeStudentaColumnTable;

    @FXML
    TableColumn<Student, String> imeStudentaColumnTable;

    @FXML
    TableColumn<Student, LocalDate> datumRodenjaStudentaColumnTable;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Student> studenti = new ArrayList<>();
        studenti=Priprema.dohvatiStudente();

        jmbagStudentColumnTable.setCellValueFactory(new PropertyValueFactory<>("jmbag"));
        prezimeStudentaColumnTable.setCellValueFactory(new PropertyValueFactory<>("prezime"));
        imeStudentaColumnTable.setCellValueFactory(new PropertyValueFactory<>("ime"));
        datumRodenjaStudentaColumnTable.setCellValueFactory(new PropertyValueFactory<>("datumRodjenja"));

        ObservableList<Student> observableStudenti = FXCollections.observableList(studenti);
        studentTableView.setItems(observableStudenti);
    }

    @FXML
    public void pretrazivanje(){
        List<Student> studenti = new ArrayList<>();
        studenti=Priprema.dohvatiStudente();

        if(!jmbagStudentaTextField.getText().isBlank()){
            studenti=studenti.stream().filter(student -> student.getJmbag().contains(jmbagStudentaTextField.getText())).collect(Collectors.toList());
        }
        if(!imeStudentaTextField.getText().isBlank()){
            studenti=studenti.stream().filter(student -> student.getIme().contains(imeStudentaTextField.getText())).collect(Collectors.toList());
        }
        if(!prezimeStudentaTextField.getText().isBlank()){
            studenti=studenti.stream().filter(student -> student.getPrezime().contains(prezimeStudentaTextField.getText())).collect(Collectors.toList());
        }
        if(!datumRodenjaStudentaDatePicker.getProperties().isEmpty()){
            studenti=studenti.stream().filter(student -> student.getDatumRodjenja().equals(datumRodenjaStudentaDatePicker)).collect(Collectors.toList());
        }

        jmbagStudentColumnTable.setCellValueFactory(new PropertyValueFactory<>("jmbag"));
        prezimeStudentaColumnTable.setCellValueFactory(new PropertyValueFactory<>("prezime"));
        imeStudentaColumnTable.setCellValueFactory(new PropertyValueFactory<>("ime"));
        datumRodenjaStudentaColumnTable.setCellValueFactory(new PropertyValueFactory<>("datumRodjenja"));

        ObservableList<Student> observableStudenti = FXCollections.observableList(studenti);
        studentTableView.setItems(observableStudenti);
    }
}