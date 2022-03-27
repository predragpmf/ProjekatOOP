//
//	Kontroler za prozor ucenika.
//
package login;

import java.net.URL;
import java.util.Collections;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import application.Izostanci;
import application.Korisnik;
import application.Ocjena;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class UcenikKontroler implements Initializable {
	
	@FXML
    private TextField imePolje;
	@FXML
    private TextField prezimePolje;
	@FXML
    private TextField polPolje;
	@FXML
    private TextField skolaPolje;
	@FXML
	private TextField razredPolje;
	@FXML
	private TextArea tekstVelikoPolje;
	@FXML
	private TextArea tekstVelikoPolje2;
	@FXML
	private ChoiceBox<String> odaberiPredmet;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		// Informacije o uceniku:
		ispisiInfo();
		
		// Ocjene:
		ispisiOcjene();
		
		// Izostanci:
		ispisiIzostanke();

	}
	
	private void ispisiInfo() {
		
		imePolje.setText(Korisnik.prijavljeniKorisnik.getIme());
		prezimePolje.setText(Korisnik.prijavljeniKorisnik.getPrezime());
		polPolje.setText(Korisnik.prijavljeniKorisnik.getPol());
		skolaPolje.setText(Korisnik.prijavljeniKorisnik.getSkola());
		razredPolje.setText(Korisnik.prijavljeniKorisnik.getRazred());
		
	}
	
	private void ispisiOcjene() {
		
		Collections.sort(Korisnik.prijavljeniKorisnik.ocjeneKorisnika, new Sortiranje());
		Set<String> ocjene = new HashSet<>();
		for(Ocjena o : Korisnik.prijavljeniKorisnik.ocjeneKorisnika) {
			tekstVelikoPolje.appendText(o.getDatum() + ", " + o.getPredmetUSkoli().getPredmet().getNaziv() + ", " + o.getOcjena() + "\n");
			ocjene.add(o.getPredmetUSkoli().getPredmet().getNaziv());
		}
		odaberiPredmet.getItems().addAll(ocjene);
		odaberiPredmet.getItems().add("Svi");
		odaberiPredmet.setOnAction((event) -> {
		    //int selectedIndex = odaberiPredmet.getSelectionModel().getSelectedIndex();
		    //Object selectedItem = odaberiPredmet.getSelectionModel().getSelectedItem();
			String nazivOdabranogPredmeta = (String) odaberiPredmet.getValue();
			tekstVelikoPolje.clear();
			for(Ocjena o : Korisnik.prijavljeniKorisnik.ocjeneKorisnika) {
				if(o.getPredmetUSkoli().getPredmet().getNaziv().equals(nazivOdabranogPredmeta)) {
					tekstVelikoPolje.appendText(o.getDatum() + ", " + o.getPredmetUSkoli().getPredmet().getNaziv() + ", " + o.getOcjena() + "\n");
				}else if (nazivOdabranogPredmeta.equals("Svi")) {
						tekstVelikoPolje.appendText(o.getDatum() + ", " + o.getPredmetUSkoli().getPredmet().getNaziv() + ", " + o.getOcjena() + "\n");
						ocjene.add(o.getPredmetUSkoli().getPredmet().getNaziv());
					}
				}
		    //System.out.println("Selection made: [" + selectedIndex + "] " + selectedItem);
		    //System.out.println("   ComboBox.getValue(): " + odaberiPredmet.getValue());
		});
		
	}
	
	private void ispisiIzostanke() {
		
		for(Izostanci i : Korisnik.prijavljeniKorisnik.sviIzostanci) {
			tekstVelikoPolje2.appendText(i.getDatum() + ", " + i.getPredmetUSkoli().getPredmet().getNaziv() + "\n");
		}
		
	}
	
}
