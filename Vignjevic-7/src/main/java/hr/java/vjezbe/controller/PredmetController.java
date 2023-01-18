package hr.java.vjezbe.controller;

import hr.java.vjezbe.entitet.*;
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

public class PredmetController implements Initializable {
    @FXML
    private TextField sifraPredmetaTextField;

    @FXML
    private TextField nazivPredmetaTextField;

    @FXML
    private TextField imeIPrezimeNositeljaTextField;

    @FXML
    private TextField brojECTSBodovaTextField;

    @FXML
    TableView <Predmet> predmetTableView;

    @FXML
    TableColumn<Predmet, String> sifraPredmetaTableColumn;

    @FXML
    TableColumn<Predmet, String> imeIPrezimeNositeljaTableColumn;

    @FXML
    TableColumn<Predmet, String> nazivPredmetaTableColumn;

    @FXML
    TableColumn<Predmet, Integer> brojECTSBodovaTableColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Profesor> profesori = new ArrayList<>();
        profesori=Priprema.dohvatiProfesore();

        List<Student> studenti = new ArrayList<>();
        studenti = Priprema.dohvatiStudente();

        List<Predmet> predmeti = new ArrayList<>();
        predmeti=Priprema.dohvatiPredmete(profesori, studenti);

        sifraPredmetaTableColumn.setCellValueFactory(new PropertyValueFactory<>("sifra"));
        nazivPredmetaTableColumn.setCellValueFactory(new PropertyValueFactory<>("naziv"));
        brojECTSBodovaTableColumn.setCellValueFactory(new PropertyValueFactory<>("brojEctsBodova"));
        imeIPrezimeNositeljaTableColumn.setCellValueFactory(new PropertyValueFactory<>("nositelj"));

        ObservableList<Predmet> observablePredmet = FXCollections.observableList(predmeti);
        predmetTableView.setItems(observablePredmet);
    }

    @FXML
    public void pretrazi(){
        List<Profesor> profesori = new ArrayList<>();
        profesori=Priprema.dohvatiProfesore();

        List<Student> studenti = new ArrayList<>();
        studenti = Priprema.dohvatiStudente();

        List<Predmet> predmeti = new ArrayList<>();
        predmeti=Priprema.dohvatiPredmete(profesori, studenti);

        if (!sifraPredmetaTextField.getText().isBlank()){
            predmeti=predmeti.stream().filter(predmet -> predmet.getSifra().contains(sifraPredmetaTextField.getText())).collect(Collectors.toList());
        }
        if(!nazivPredmetaTextField.getText().isBlank()){
            predmeti=predmeti.stream().filter(predmet -> predmet.getNaziv().contains(nazivPredmetaTextField.getText())).collect(Collectors.toList());
        }
        if(!brojECTSBodovaTextField.getText().isBlank()){
            predmeti=predmeti.stream().filter(predmet -> predmet.getBrojEctsBodova().equals(Integer.parseInt(brojECTSBodovaTextField.getText()))).collect(Collectors.toList());
        }
        if (!imeIPrezimeNositeljaTextField.getText().isBlank()){
            String[] ime = imeIPrezimeNositeljaTextField.getText().split(" ");
            predmeti=predmeti.stream().filter(predmet -> predmet.getNositelj().getIme().contains(ime[0]) && predmet.getNositelj().getPrezime().contains(ime[1])).collect(Collectors.toList());
        }

        sifraPredmetaTableColumn.setCellValueFactory(new PropertyValueFactory<>("sifra"));
        nazivPredmetaTableColumn.setCellValueFactory(new PropertyValueFactory<>("naziv"));
        brojECTSBodovaTableColumn.setCellValueFactory(new PropertyValueFactory<>("brojEctsBodova"));
        imeIPrezimeNositeljaTableColumn.setCellValueFactory(new PropertyValueFactory<>("nositelj"));

        ObservableList<Predmet> observablePredmet = FXCollections.observableList(predmeti);
        predmetTableView.setItems(observablePredmet);
    }
}

