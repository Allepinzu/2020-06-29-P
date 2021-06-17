/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.PremierLeague;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.PremierLeague.model.Match;
import it.polito.tdp.PremierLeague.model.Mese;
import it.polito.tdp.PremierLeague.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	Model model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnCreaGrafo"
    private Button btnCreaGrafo; // Value injected by FXMLLoader

    @FXML // fx:id="btnConnessioneMassima"
    private Button btnConnessioneMassima; // Value injected by FXMLLoader

    @FXML // fx:id="btnCollegamento"
    private Button btnCollegamento; // Value injected by FXMLLoader

    @FXML // fx:id="txtMinuti"
    private TextField txtMinuti; // Value injected by FXMLLoader

    @FXML // fx:id="cmbMese"
    private ComboBox<Mese> cmbMese; // Value injected by FXMLLoader

    @FXML // fx:id="cmbM1"
    private ComboBox<Match> cmbM1; // Value injected by FXMLLoader

    @FXML // fx:id="cmbM2"
    private ComboBox<Match> cmbM2; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doConnessioneMassima(ActionEvent event) {
    	this.txtResult.clear();
    	this.txtResult.appendText(this.model.matchMigliori());
    }

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	model.creaGrafo(Integer.parseInt( this.txtMinuti.getText()),this.cmbMese.getValue().getMese());
    	this.txtResult.appendText(model.numVA());
    	this.cmbM1.getItems().addAll(model.getVertexSet());
    	this.cmbM2.getItems().addAll(model.getVertexSet());
    }

    @FXML
    void doCollegamento(ActionEvent event) {
    	this.model.listaMigliore(this.cmbM1.getValue(), this.cmbM2.getValue());
    	this.txtResult.clear();
    	for(Match m:model.getMigliore())
    	this.txtResult.appendText(m.toString()+"\n");
    	
    	this.txtResult.appendText(Double.toString( this.model.getPesoTot()));
    	
    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnConnessioneMassima != null : "fx:id=\"btnConnessioneMassima\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCollegamento != null : "fx:id=\"btnCollegamento\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMinuti != null : "fx:id=\"txtMinuti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbMese != null : "fx:id=\"cmbMese\" was not injected: check your FXML file 'Scene.fxml'.";        assert cmbM1 != null : "fx:id=\"cmbM1\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbM2 != null : "fx:id=\"cmbM2\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    
    	this.cmbMese.getItems().add(new Mese(1,"Gennaio"));
    	this.cmbMese.getItems().add(new Mese(2,"Febbraio"));
    	this.cmbMese.getItems().add(new Mese(3,"Marzo"));
    	this.cmbMese.getItems().add(new Mese(4,"Aprile"));
    	this.cmbMese.getItems().add(new Mese(5,"Maggio"));
    	this.cmbMese.getItems().add(new Mese(6,"Giugno"));
    	this.cmbMese.getItems().add(new Mese(7,"Luglio"));
    	this.cmbMese.getItems().add(new Mese(8,"Agosto"));
    	this.cmbMese.getItems().add(new Mese(9,"Settembre"));
    	this.cmbMese.getItems().add(new Mese(10,"Ottobre"));
    	this.cmbMese.getItems().add(new Mese(11,"Novembre"));
    	this.cmbMese.getItems().add(new Mese(12,"Dicembre"));
    }
    
    
}
