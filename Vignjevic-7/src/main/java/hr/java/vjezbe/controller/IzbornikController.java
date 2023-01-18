package hr.java.vjezbe.controller;

import javafx.fxml.FXML;

import java.io.IOException;

public class IzbornikController {
    @FXML
    public void pretraziProfesore() throws IOException {
        Priprema.getFMXLLoader("profesori.fxml", "Pretraga profesora");
    }

    @FXML
    public void pretraziStudente() throws  IOException{
        Priprema.getFMXLLoader("studenti.fxml", "Pretraga studenata");
    }

    @FXML
    public void pretraziIspite() throws IOException{
        Priprema.getFMXLLoader("ispiti.fxml", "Pretraga ispita");
    }

    @FXML
    public void pretraziPredmete() throws IOException{
        Priprema.getFMXLLoader("predmeti.fxml", "Pretraga predmeti");
    }
}
