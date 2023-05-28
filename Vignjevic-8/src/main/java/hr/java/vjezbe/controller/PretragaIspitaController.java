package hr.java.vjezbe.controller;

import hr.java.vjezbe.entitet.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class PretragaIspitaController implements Initializable {
    @FXML
    private DatePicker datumIVrijemeIspitaDatePicker;

    @FXML
    private ComboBox<Predmet> predmetComboBox;

    @FXML
    private ComboBox<Student> studentComboBox;

    @FXML
    private ComboBox<Ocjena> ocjenaComboBox;

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

        predmetComboBox.setItems(FXCollections.observableList(predmeti));
        studentComboBox.setItems(FXCollections.observableList(studenti));
        ocjenaComboBox.setItems(FXCollections.observableArrayList(Ocjena.values()));

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
        Predmet uneseniPredmet = predmetComboBox.getValue();
        Student uneseniStudent = studentComboBox.getValue();
        Ocjena unesenaOcjena = ocjenaComboBox.getValue();
        if(uneseniPredmet==null || uneseniStudent==null || unesenaOcjena==null || datumIVrijemeIspitaDatePicker.getValue()==null){
            System.out.println("Nije sve uneseno");
        }
        else{
            if(uneseniPredmet!=null){
                ispiti=ispiti.stream().filter(ispit -> ispit.getPred().getNaziv().equals(uneseniPredmet.getNaziv())).collect(Collectors.toList());
            }
            if(uneseniStudent!=null){
                ispiti=ispiti.stream().filter(ispit -> ispit.getStud().getJmbag().equals(uneseniStudent.getJmbag())).collect(Collectors.toList());
            }
            if(unesenaOcjena!=null){
                ispiti=ispiti.stream().filter(ispit -> ispit.getOcjena().equals(unesenaOcjena)).collect(Collectors.toList());
            }
            if(datumIVrijemeIspitaDatePicker.getValue()!=null){
                ispiti=ispiti.stream().filter(ispit -> ispit.getDatumIVrijeme().toLocalDate().equals(datumIVrijemeIspitaDatePicker.getValue())).collect(Collectors.toList());
            }
        }



        nazivPredmetaTableColumn.setCellValueFactory(new PropertyValueFactory<>("pred"));
        imeIPrezimeStudnentaTableColumn.setCellValueFactory(new PropertyValueFactory<>("stud"));
        ocjenaTableColumn.setCellValueFactory(new PropertyValueFactory<>("ocjena"));
        vrijemeIspitaTableColumn.setCellValueFactory(new PropertyValueFactory<>("datumIVrijeme"));

        ObservableList<Ispit> observableIspiti = FXCollections.observableList(ispiti);
        ispitiTableView.setItems(observableIspiti);
    }
}
