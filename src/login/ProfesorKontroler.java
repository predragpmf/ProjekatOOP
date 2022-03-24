//
//	Kontroler za prozor profesora.
//
package login;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import application.IzmjenaBaze;
import application.Izostanci;
import application.Korisnik;
import application.Ocjena;
import application.Predmet;
import application.PredmetUSkoli;
import application.PristupniPodaci;
import application.Profesor;
import application.Skola;
import application.Ucenik;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class ProfesorKontroler implements Initializable{

	@FXML
    private TextField imePolje;
	@FXML
    private TextField prezimePolje;
	@FXML
    private TextField dodajUcenikaIme;
	@FXML
    private TextField dodajUcenikaPrezime;
	@FXML
    private TextField dodajUcenikaPol;
	@FXML
    private TextField dodajUcenikaKorisnickoIme;
	@FXML
    private TextField dodajUcenikaEmail;
	@FXML
    private TextField dodajNazivPredmeta;
	@FXML
    private TextField dodajRazredPredmeta;
	@FXML
	private TextField nazivSkolePolje;
	@FXML
	private TextField gradSkolePolje;
	@FXML
	private TextField mjestoSkolePolje;
	@FXML
	private TextField drzavaSkolePolje;
	@FXML
	private TextField imeProfesoraPolje;
	@FXML
	private TextField prezimeProfesoraPolje;
	@FXML
	private TextField polProfesoraPolje;
	@FXML
	private TextField korisnickoImeProfesoraPolje;
	@FXML
	private TextField emailProfesoraPolje;
	@FXML
	private TextArea skolePolje;
	@FXML
	private TextArea predmetiPolje;
	@FXML
	private TextArea sviUceniciPolje;
	@FXML
	private TextArea sviPredmetiPolje;
	@FXML
	private TextArea sveSkolePolje;
	@FXML
	private TextArea sviProfesoriPolje;
	@FXML
	private TextArea sviPredmetiProfesora;
	@FXML
	private TextArea sveOcjeneUcenika;
	@FXML
	private Button dodajUcenikeTipka;
	@FXML
	private Button dodajPredmetTipka;
	@FXML
	private Button dodajSkoluTipka;
	@FXML
	private Button dodajProfesoraTipka;
	@FXML
	private Button dodajPredmetProfesora;
	@FXML
	private Button ocjeniUcenikaTipka;
	@FXML
	private ChoiceBox<String> predmetProfesoraBox;
	@FXML
	private ChoiceBox<String> skolaProfesoraBox;
	@FXML
	private ChoiceBox<String> odaberiPredOcjBox;
	@FXML
	private ChoiceBox<String> odaberiUcenOcjBox;
	@FXML
	private ChoiceBox<String> odaberiOcjBox;
	@FXML
	private ChoiceBox<String> odaberiSkolOcjBox;
	
	public String nazivIMjesto;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		// Informacije o profesoru:
		imePolje.setText(Korisnik.prijavljeniKorisnik.getIme());
		prezimePolje.setText(Korisnik.prijavljeniKorisnik.getPrezime());
		for(Skola s : Korisnik.prijavljeniKorisnik.sveSkoleProfesor) {
			skolePolje.appendText(s.getNaziv() + "\n");
		}
		for(PredmetUSkoli pus : Korisnik.prijavljeniKorisnik.predajePredmete) {
			predmetiPolje.appendText(pus.getSkola().getNaziv() + ", " + pus.getPredmet().getNaziv() + "\n");
		}
		
		// Dodaj novog ucenika:
		ispisiKorisnike("Ucenik");
		dodajUcenikeTipka.setOnAction(event -> {
			String ime = dodajUcenikaIme.getText();
			String prezime = dodajUcenikaPrezime.getText();
			String polString = dodajUcenikaPol.getText();
			int pol;
			if(polString.equals("z")) {
				pol = 1;
			} else if (polString.equals("m")) {
				pol = 0;
			} else { 
				pol = -1;
			}
			String korisnickoIme = dodajUcenikaKorisnickoIme.getText();
			for(PristupniPodaci pp : PristupniPodaci.sviPristupniPodaci) {
				if(pp.getKorisnickoIme().equals(korisnickoIme)) {
					obavjestenjeProzor("Korisnicko ime vec postoji!");
					return;
				}
			}
			String lozinka = korisnickoIme.concat("123");
			String email = dodajUcenikaEmail.getText();
			try {
				int pristupniPodaciId = IzmjenaBaze.posaljiPristupnePodatke(korisnickoIme, email, LoginKontroler.hesirajLozinku(lozinka));
				int ucenikId = IzmjenaBaze.posaljiUcenika(ime, prezime, pol, pristupniPodaciId);
				
				new PristupniPodaci(pristupniPodaciId, korisnickoIme, lozinka, email);
				new Ucenik(ucenikId, ime, prezime, pol, pristupniPodaciId);	
			
			} catch (Exception e) {
				System.err.println("Ucenik vec postoji!");
				e.printStackTrace();
			}
			sviUceniciPolje.clear();
			ispisiKorisnike("Ucenik");
		});
		
		// Dodaj novi predmet:
		ispisiPredmete();
		dodajPredmetTipka.setOnAction(event -> {
			String naziv = dodajNazivPredmeta.getText();
			String razred = dodajRazredPredmeta.getText();
			int predmetId = IzmjenaBaze.posaljiPredmet(naziv, Integer.parseInt(razred));
			try {
				new Predmet(predmetId, naziv, Integer.parseInt(razred));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			sviPredmetiPolje.clear();
			ispisiPredmete();
			Set<String> naziviPredmeta = new HashSet<>();
			for(Predmet p : Predmet.sviPredmeti) {
				naziviPredmeta.add(p.getNaziv());
			}
			predmetProfesoraBox.getItems().addAll(naziviPredmeta);
			odaberiPredOcjBox.getItems().addAll(naziviPredmeta);
		});
		
		// Dodaj novu skolu:
		for(Skola s : Skola.sveSkole) {
			sveSkolePolje.appendText(s.getNaziv() + ", " + s.getGrad() + ", " + s.getMjesto() + "\n");
		}
		dodajSkoluTipka.setOnAction(event -> {
			String naziv = nazivSkolePolje.getText();
			String grad = gradSkolePolje.getText();
			String mjesto = mjestoSkolePolje.getText();
			String drzava = drzavaSkolePolje.getText();
			int skolaId = IzmjenaBaze.posaljiSkolu(naziv, grad, mjesto, drzava);
			try {
				new Skola(skolaId, naziv, grad, mjesto, drzava);
			} catch (Exception e) {
				e.printStackTrace();
			}
			sveSkolePolje.clear();
			for(Skola s : Skola.sveSkole) {
				sveSkolePolje.appendText(s.getNaziv() + ", " + s.getGrad() + ", " + s.getMjesto() + "\n");
				skolaProfesoraBox.getItems().add(s.getNaziv() + " " + s.getMjesto());
			}
		});
		
		// Dodaj novog profesora:
		ispisiKorisnike("Profesor");
		dodajProfesoraTipka.setOnAction(event -> {
			String ime = imeProfesoraPolje.getText();
			String prezime = prezimeProfesoraPolje.getText();
			String polString = polProfesoraPolje.getText();
			int pol;
			if(polString.equals("z")) {
				pol = 1;
			} else if (polString.equals("m")) {
				pol = 0;
			} else { 
				pol = -1;
			}
			String korisnickoIme = korisnickoImeProfesoraPolje.getText();
			String lozinka = korisnickoIme.concat("123");
			String email = emailProfesoraPolje.getText();
			try {
				int pristupniPodaciId = IzmjenaBaze.posaljiPristupnePodatke(korisnickoIme, email, LoginKontroler.hesirajLozinku(lozinka));
				int profesorId = IzmjenaBaze.posaljiProfesora(ime, prezime, pol, pristupniPodaciId);
				
				new PristupniPodaci(pristupniPodaciId, korisnickoIme, lozinka, email);
				new Profesor(profesorId, ime, prezime, pol, pristupniPodaciId);	
			
			} catch (Exception e) {
				System.err.println("Profesor vec postoji!");
				e.printStackTrace();
			}
			sviProfesoriPolje.clear();
			ispisiKorisnike("Profesor");
		});
		
		// Dodaj novi predmet profesora:
		for(PredmetUSkoli pus : Korisnik.prijavljeniKorisnik.predajePredmete) {
			sviPredmetiProfesora.appendText(pus.getPredmet().getNaziv() + ", " + pus.getSkola().getNaziv() + "\n");
		}
		
		// Dodaj predmete u ChoiceBox:
		Set<String> naziviPredmeta = new HashSet<>();
		for(Predmet p : Predmet.sviPredmeti) {
			naziviPredmeta.add(p.getNaziv());
		}
		predmetProfesoraBox.getItems().addAll(naziviPredmeta);
		
		// Dodaj skole u ChoiceBox
		for(Skola s : Skola.sveSkole) {
			skolaProfesoraBox.getItems().add(s.getNaziv() + " " + s.getMjesto());
		}
		
		dodajPredmetProfesora.setOnAction(event -> {
			String nazivOdabranogPredmeta = (String) predmetProfesoraBox.getValue();
			String nazivOdabraneSkole = (String) skolaProfesoraBox.getValue();
			//Provjera postojanja:
			for(PredmetUSkoli pus : PredmetUSkoli.sviPredmetiUSkoli) {
				if(nazivOdabranogPredmeta.equals(pus.getPredmet().getNaziv()) && nazivOdabraneSkole.equals(pus.getSkola().getNaziv())) {
					obavjestenjeProzor("Profesor vec predaje taj predmet!");
					return;
				}
			}
			int predmetId = 0;
			int skolaId = 0;
			for(Predmet p : Predmet.sviPredmeti) {
				if(p.getNaziv().equals(nazivOdabranogPredmeta)) {
					predmetId = p.getId();
				}
			}
			for(Skola s : Skola.sveSkole) {
				if(s.getNaziv().equals(nazivOdabraneSkole)) {
					skolaId = s.getId();
				}
			}
			int predmetUSkoliId = IzmjenaBaze.posaljiPredmetUSkoli(predmetId, skolaId, Korisnik.prijavljeniKorisnik.getId());
			new PredmetUSkoli(predmetUSkoliId, predmetId, skolaId, Korisnik.prijavljeniKorisnik.getId());
			sviPredmetiProfesora.clear();
			for(PredmetUSkoli pus : Korisnik.prijavljeniKorisnik.predajePredmete) {
				sviPredmetiProfesora.appendText(pus.getPredmet().getNaziv() + ", " + pus.getSkola().getNaziv() + "\n");
			}
		});
		
		//Ocjenjivanje:
		for(Skola s : Skola.sveSkole) {
			odaberiSkolOcjBox.getItems().add(s.getNaziv() + "," + s.getMjesto());
		}
		
		
		odaberiSkolOcjBox.setOnAction((event) -> {
			odaberiPredOcjBox.getItems().clear();
			Set<String> setPredmeta = new HashSet<>();
			nazivIMjesto = odaberiSkolOcjBox.getValue();
			System.out.println(nazivIMjesto);
			String nazivSkole = nazivIMjesto.split(",")[0];
			String mjestoSkole = nazivIMjesto.split(",")[1];
			for(PredmetUSkoli pus : PredmetUSkoli.sviPredmetiUSkoli) {
				if(pus.getSkola().getNaziv().equals(nazivSkole) && pus.getSkola().getMjesto().equals(mjestoSkole)) {
					setPredmeta.add(pus.getPredmet().getNaziv());
				}
			}
			odaberiPredOcjBox.getItems().addAll(setPredmeta);
		});
		/*
		for(Skola s : Skola.sveSkole) {
			if(ProfesorKontroler.nazivOdabraneSkole[0].equals(s.getNaziv()) && nazivOdabraneSkole[1].stripLeading().equals(s.getMjesto())) {
				idOdabraneSkole = s.getId();
			}
		}
		Set<String> setSkola = new HashSet<>();
		for(PredmetUSkoli pus : PredmetUSkoli.sviPredmetiUSkoli) {
			if(pus.getSkola().getId() == idOdabraneSkole) {
				setSkola.add(pus.getPredmet().getNaziv());
			}
		}
		*/
		
		odaberiPredOcjBox.setOnAction((event) -> {
			//nazivSkole = nazivIMjesto[0];
			//String mjestoSkole = nazivIMjesto[1];
			//for(Skola s : Skola.sveSkole) {
			//	if(s.getNaziv().equals(nazivSkole) && s.getMjesto().equals(mjestoSkole)) {
			//		idSkole = s.getId();
			//	}
			//}
			
			odaberiUcenOcjBox.getItems().clear();
			String nazivOdabranogPredmeta = (String) odaberiPredOcjBox.getValue();
			Set<String> setUcenika = new HashSet<>();
			for(Ocjena o : Ocjena.sveOcjene) {
				if(o.getPredmetUSkoli().getPredmet().getNaziv().equals(nazivOdabranogPredmeta) && o.getPredmetUSkoli().getProfesor().getId() == Korisnik.prijavljeniKorisnik.getId()) {
					setUcenika.add(o.getUcenik().getPristupniPodaci().getKorisnickoIme());
				}
			}
			for(Izostanci i : Izostanci.sviIzostanci) {
				if(i.getPredmetUSkoli().getPredmet().getNaziv().equals(nazivOdabranogPredmeta) && i.getPredmetUSkoli().getProfesor().getId() == Korisnik.prijavljeniKorisnik.getId()) {
					setUcenika.add(i.getUcenik().getPristupniPodaci().getKorisnickoIme());
				}
			}
			odaberiUcenOcjBox.getItems().addAll(setUcenika);
		});
		odaberiUcenOcjBox.setOnAction((event) -> {
			String korisnickoIme = odaberiUcenOcjBox.getValue();
			for(Ocjena o: Ocjena.sveOcjene) {
				if(o.getUcenik().getPristupniPodaci().getKorisnickoIme().equals(korisnickoIme)) {
					sveOcjeneUcenika.appendText(o.getPredmetUSkoli().getPredmet().getNaziv() + ", " + o.getOcjena() + "\n");
				}
			}
		});
		
		for(int i = 1; i < 6; i++) {
			odaberiOcjBox.getItems().add(Integer.toString(i));
		}
		ocjeniUcenikaTipka.setOnAction((event) -> {
			String skolaNaziv = odaberiSkolOcjBox.getValue().split(",")[0];
			String skolaMjesto = odaberiSkolOcjBox.getValue().split(",")[1];
			String ucenik = odaberiUcenOcjBox.getValue();
			int idSkola = 0;
			int idPredmet = 0;
			int idPredmetUSkoli = 0;
			int idUcenik = 0;
			String predmet = odaberiPredOcjBox.getValue();
			int ocjena = Integer.parseInt(odaberiOcjBox.getValue());
			long mili = System.currentTimeMillis();  
		    java.sql.Date datum = new java.sql.Date(mili);
		    for(Skola s : Skola.sveSkole) {
		    	if(s.getNaziv().equals(skolaNaziv) && s.getMjesto().equals(skolaMjesto)) {
		    		idSkola = s.getId();
		    	}
		    }
		    for(Predmet p : Predmet.sviPredmeti) {
		    	if(p.getNaziv().equals(predmet)) {
		    		idPredmet = p.getId();
		    	}
		    }
		    for(PredmetUSkoli pus : PredmetUSkoli.sviPredmetiUSkoli) {
		    	if(pus.getPredmet().getId() == idPredmet && pus.getSkola().getId() == idSkola && pus.getProfesor().getId() == Korisnik.prijavljeniKorisnik.getId()) {
		    		idPredmetUSkoli = pus.getId();
		    	}
		    }
		    for(Ucenik u : Ucenik.sviUcenici) {
		    	if(u.getPristupniPodaci().getKorisnickoIme().equals(ucenik)) {
		    		idUcenik = u.getId();
		    	}
		    }
		    int ocjenaid = IzmjenaBaze.posaljiOcjena(idUcenik, idPredmetUSkoli, ocjena, datum);
		    new Ocjena(ocjenaid, idUcenik, idPredmetUSkoli, ocjena, datum.toString());
		    sveOcjeneUcenika.clear();
		    for(Ocjena o: Ocjena.sveOcjene) {
				if(o.getUcenik().getPristupniPodaci().getKorisnickoIme().equals(ucenik)) {
					sveOcjeneUcenika.appendText(o.getPredmetUSkoli().getPredmet().getNaziv() + ", " + o.getOcjena() + "\n");
				}
			}
		});
				
	}
	
	private void ispisiKorisnike(String tip) {
		if(tip.equals("Ucenik")) {
			for(Korisnik k : Korisnik.sviKorisnici) {
				if(k instanceof Ucenik) {
					sviUceniciPolje.appendText(k.getIme() + " " + k.getPrezime() + "\n");
				} 
			}
		} else if (tip.equals("Profesor")) {
			for(Korisnik k : Korisnik.sviKorisnici) {
				if(k instanceof Profesor) {
					sviProfesoriPolje.appendText(k.getIme() + " " + k.getPrezime() + "\n");
				} 
			}
		}
	}
	
	private void ispisiPredmete() {
		Set<String> naziviPredmeta = new HashSet<>();
		for(Predmet p : Predmet.sviPredmeti) {
			naziviPredmeta.add(p.getNaziv());
		}
		for(String np : naziviPredmeta) {
			sviPredmetiPolje.appendText(np + "\n");
		}
	}
	
	private void obavjestenjeProzor(String poruka) {
		Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Greška!");
        alert.setHeaderText(null);
        alert.setContentText(poruka);
        alert.showAndWait();
	}
	

}
