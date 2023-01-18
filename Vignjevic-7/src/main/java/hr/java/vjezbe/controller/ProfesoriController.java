package hr.java.vjezbe.controller;

import hr.java.vjezbe.entitet.Profesor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ProfesoriController implements Initializable {
    @FXML
    private TextField sifraProfesoraTextField;
    @FXML
    private TextField imeProfesoraTextField;
    @FXML
    private TextField prezimeProfesoraTextField;
    @FXML
    private TextField titulaProfesoraTextField;
    @FXML
    TableView<Profesor> profesorTableView;
    @FXML
    TableColumn<Profesor, String> sifraProfesoraTableColumn;
    @FXML
    TableColumn<Profesor, String> imeProfesoraTableColumn;
    @FXML
    TableColumn<Profesor, String> prezimeProfesoraTableColumn;
    @FXML
    TableColumn<Profesor, String> titulaProfesoraTableColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Profesor> profesori = new ArrayList<>();
        profesori=Priprema.dohvatiProfesore();

        sifraProfesoraTableColumn.setCellValueFactory(new PropertyValueFactory<>("sifra"));
        titulaProfesoraTableColumn.setCellValueFactory(new PropertyValueFactory<>("titula"));
        imeProfesoraTableColumn.setCellValueFactory(new PropertyValueFactory<>("ime"));
        prezimeProfesoraTableColumn.setCellValueFactory(new PropertyValueFactory<>("prezime"));

        ObservableList<Profesor> observableProfesori = FXCollections.observableList(profesori);
        profesorTableView.setItems(observableProfesori);
    }

    @FXML
    public void pretrazi(){
        List<Profesor> profesori = new ArrayList<>();
        profesori=Priprema.dohvatiProfesore();

       if(!sifraProfesoraTextField.getText().isBlank()){
           profesori=profesori.stream().filter(profesor -> profesor.getSifra().contains(sifraProfesoraTextField.getText())).collect(Collectors.toList());
       }
       if(!imeProfesoraTextField.getText().isBlank()){
           profesori=profesori.stream().filter(profesor -> profesor.getIme().contains(imeProfesoraTextField.getText())).collect(Collectors.toList());
       }
       if(!prezimeProfesoraTextField.getText().isBlank()){
           profesori=profesori.stream().filter(profesor -> profesor.getPrezime().contains(prezimeProfesoraTextField.getText())).collect(Collectors.toList());
       }
       if(!titulaProfesoraTextField.getText().isBlank()){
           profesori=profesori.stream().filter(profesor -> profesor.getTitula().contains(titulaProfesoraTextField.getText())).collect(Collectors.toList());
       }

        sifraProfesoraTableColumn.setCellValueFactory(new PropertyValueFactory<>("sifra"));
        titulaProfesoraTableColumn.setCellValueFactory(new PropertyValueFactory<>("titula"));
        imeProfesoraTableColumn.setCellValueFactory(new PropertyValueFactory<>("ime"));
        prezimeProfesoraTableColumn.setCellValueFactory(new PropertyValueFactory<>("prezime"));

        ObservableList<Profesor> observableProfesori = FXCollections.observableList(profesori);
        profesorTableView.setItems(observableProfesori);
    }
}
