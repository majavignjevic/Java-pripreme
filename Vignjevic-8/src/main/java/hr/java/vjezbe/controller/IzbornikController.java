package hr.java.vjezbe.controller;

import javafx.fxml.FXML;

import java.io.IOException;

public class IzbornikController {
    @FXML
    public void pretraziProfesore() throws IOException {
        Priprema.getFMXLLoader("pretragaProfesora.fxml", "Pretraga profesora");
    }

    @FXML
    public void unesiProfesore() throws IOException{
        Priprema.getFMXLLoader("unosProfesora.fxml", "Unos profesora");
    }

    @FXML
    public void pretraziStudente() throws  IOException{
        Priprema.getFMXLLoader("pretragaStudenata.fxml", "Pretraga studenata");
    }

    @FXML
    public void unesiStudente() throws IOException{
        Priprema.getFMXLLoader("unosStudenata.fxml", "Unos studenata");
    }

    @FXML
    public void pretraziIspite() throws IOException{
        Priprema.getFMXLLoader("pretrazivanjeIspita.fxml", "Pretraga ispita");
    }

    @FXML
    public void unesiIspite() throws IOException{
        Priprema.getFMXLLoader("unosIspita.fxml", "Unos ispita");
    }

    @FXML
    public void pretraziPredmete() throws IOException{
        Priprema.getFMXLLoader("pretraziPredmete.fxml", "Pretraga predmeti");
    }

    @FXML
    public void unesiPredmete() throws IOException{
        Priprema.getFMXLLoader("unosPredmeta.fxml", "Unos predmeta");
    }
}
