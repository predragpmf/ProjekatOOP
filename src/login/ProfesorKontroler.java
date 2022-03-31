//
//	Kontroler za prozor profesora.
//
package login;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import application.IzmjenaBaze;
import application.Izostanci;
import application.Korisnik;
import application.Ocjena;
import application.OcjenaPredmeta;
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
import javafx.scene.control.DatePicker;
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
	private TextField novaLozinka;
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
	private TextArea sviIzostanciUcenika;
	@FXML
	private TextArea ocjenePredmeta;
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
	private Button izostanakTipka;
	@FXML
	private Button odjaviSeTipkaProf;
	@FXML
	private Button promjeniLozinku;
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
	@FXML
	private ChoiceBox<String> odaberiPredOcj;
	@FXML
	private DatePicker odaberiDatum;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		// Informacije o profesoru tab:
		ispisiInfo();
		
		// Dodaj novog ucenika tab:
		dodajUcenika();
		
		// Dodaj novi predmet tab:
		dodajPredmet();
		
		// Dodaj novu skolu tab:
		dodajSkolu();
		
		// Dodaj novog profesora tab:
		dodajProfesora();
		
		// Predavanje tab:
		dodajPredavanje();
		
		//Ocjenjivanje tab:
		dodajOcjenu();
				
	}
	
	
	private void dodajUcenika() {
		
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
			if(PristupniPodaci.korisnickoImePostoji(korisnickoIme)) {
				obavjestenjeProzor("Korisnicko ime vec postoji!");
				return;
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
			dodajUcenikaIme.clear();
			dodajUcenikaPrezime.clear();
			dodajUcenikaPol.clear();
			dodajUcenikaKorisnickoIme.clear();
			dodajUcenikaEmail.clear();
			sviUceniciPolje.clear();
			ispisiKorisnike("Ucenik");
		});
		
	}
	
	private void dodajPredmet() {
		
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
		
	}
	
	
	private void dodajSkolu() {
		
		for(Skola s : Skola.sveSkole) {
			sveSkolePolje.appendText(s.ispisSkole(s) + "\n");
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
		
	}
	
	
	private void dodajProfesora() {
		
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
		
	}
	
	
	private void dodajPredavanje() {
		
		for(PredmetUSkoli pus : Korisnik.prijavljeniKorisnik.predajePredmete) {
			sviPredmetiProfesora.appendText(pus.getPredmet().getNaziv() + ", " + pus.getSkola().getNaziv() + ", " + pus.getPredmet().getRazred() + "\n");
			odaberiPredOcj.getItems().add(pus.getPredmet().getNaziv() + "," + pus.getSkola().getNaziv() + "," + pus.getPredmet().getRazred());
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
			odaberiPredOcj.getItems().clear();
			for(PredmetUSkoli pus : Korisnik.prijavljeniKorisnik.predajePredmete) {
				sviPredmetiProfesora.appendText(pus.getPredmet().getNaziv() + ", " + pus.getSkola().getNaziv() + ", " + pus.getPredmet().getRazred() + "\n");
				odaberiPredOcj.getItems().add(pus.getPredmet().getNaziv() + "," + pus.getSkola().getNaziv() + "," + pus.getPredmet().getRazred());
			}
		});
		odaberiPredOcj.setOnAction((event) -> {
			ocjenePredmeta.clear();
			String nazivPredmeta = odaberiPredOcj.getValue().split(",")[0];
			String nazivSkole = odaberiPredOcj.getValue().split(",")[1];
			int razred = Integer.parseInt(odaberiPredOcj.getValue().split(",")[2]);
			int idPredmetaUSkoli = 0;
			for(PredmetUSkoli pus : Korisnik.prijavljeniKorisnik.predajePredmete) {
				if(pus.getPredmet().getNaziv().equals(nazivPredmeta) && pus.getSkola().getNaziv().equals(nazivSkole) && pus.getPredmet().getRazred() == razred) {
					idPredmetaUSkoli = pus.getId();
				}
			}
			for(OcjenaPredmeta op : OcjenaPredmeta.sveOcjenePredmeta) {
				if(op.getPredmetUSkoli().getId() == idPredmetaUSkoli) {
					ocjenePredmeta.appendText(op.getPitanje().getPitanjeTekst() + ": " + op.getOcjena() + "\n");
				}
			}
		});
		
	}
	
	
	private void dodajOcjenu() {
		
		for(Skola s : Korisnik.prijavljeniKorisnik.sveSkoleProfesor) {
			odaberiSkolOcjBox.getItems().add(s.getNaziv() + "," + s.getMjesto() + "," + s.getDrzava());
		}
		for(int i = 1; i <= 5; i++) {
			odaberiOcjBox.getItems().add(Integer.toString(i));
		}
		odaberiDatum.setValue(LocalDate.now());

		odaberiSkolOcjBox.setOnAction((event) -> {
			odaberiPredOcjBox.getItems().clear();
			String nazivIMjesto = odaberiSkolOcjBox.getValue();
			String naziv = nazivIMjesto.split(",")[0];
			String mjesto = nazivIMjesto.split(",")[1];
			String drzava = nazivIMjesto.split(",")[2];
			for(PredmetUSkoli pus : Korisnik.prijavljeniKorisnik.predajePredmete) {
				if(pus.getSkola().getNaziv().equals(naziv) && pus.getSkola().getMjesto().equals(mjesto) && pus.getSkola().getDrzava().equals(drzava)) {
					odaberiPredOcjBox.getItems().add(pus.getPredmet().getNaziv() + "," + pus.getPredmet().getRazred());
				}
			}
		});
		
		odaberiPredOcjBox.setOnAction((event) -> {
			odaberiUcenOcjBox.getItems().clear();
			String nazivIRazred = odaberiPredOcjBox.getValue();
			String naziv = nazivIRazred.split(",")[0];
			int razred = Integer.parseInt(nazivIRazred.split(",")[1]);
			Set<Ucenik> ucenici = new HashSet<>();
			for(Ocjena o : Ocjena.sveOcjene) {
				if(o.getPredmetUSkoli().getPredmet().getNaziv().equals(naziv) && o.getPredmetUSkoli().getPredmet().getRazred() == razred) {
					ucenici.add(o.getUcenik());
				}
			}
			for(Izostanci i : Izostanci.sviIzostanci) {
				if(i.getPredmetUSkoli().getPredmet().getNaziv().equals(naziv) && i.getPredmetUSkoli().getPredmet().getRazred() == razred) {
					ucenici.add(i.getUcenik());
				}
			}
			for(Ucenik u : ucenici) {
				odaberiUcenOcjBox.getItems().add(u.getPristupniPodaci().getKorisnickoIme());
			}
		});
		
		odaberiUcenOcjBox.setOnAction((event) -> {
			//boolean skolaPrazna = odaberiSkolOcjBox.getSelectionModel().isEmpty();
			//boolean predmetPrazan = odaberiPredOcjBox.getSelectionModel().isEmpty();
			
			sveOcjeneUcenika.clear();
			String korisnickoIme = odaberiUcenOcjBox.getValue();
			for(Ocjena o : Ocjena.sveOcjene) {
				if(o.getUcenik().getPristupniPodaci().getKorisnickoIme().equals(korisnickoIme)) {
					sveOcjeneUcenika.appendText(o.getDatum() + " : " + o.getOcjena() + "\n");
				}
			}
			sviIzostanciUcenika.clear();
			for(Izostanci i : Izostanci.sviIzostanci) {
				if(i.getUcenik().getPristupniPodaci().getKorisnickoIme().equals(korisnickoIme)) {
					sviIzostanciUcenika.appendText(i.getDatum() + " : " + i.getPredmetUSkoli().getPredmet().getNaziv() + "\n");
				}
			}
		});
		
		ocjeniUcenikaTipka.setOnAction(event -> {
			int idUcenika = 0;
			int idPredmetaUSkoli = 0;
			String nazivPredmeta = odaberiPredOcjBox.getValue().split(",")[0];
			int razredPredmeta = Integer.parseInt(odaberiPredOcjBox.getValue().split(",")[1]);
			String nazivSkole = odaberiSkolOcjBox.getValue().split(",")[0];
			String mjestoSkole = odaberiSkolOcjBox.getValue().split(",")[1];
			LocalDate vrijednostDatuma = odaberiDatum.getValue();
			java.sql.Date datum = Date.valueOf(vrijednostDatuma);
			int ocjena = Integer.parseInt(odaberiOcjBox.getValue());
			//long mili = System.currentTimeMillis();  
		    //java.sql.Date datum = new java.sql.Date(mili);
			for(Ucenik u : Ucenik.sviUcenici) {
				if(u.getPristupniPodaci().getKorisnickoIme().equals(odaberiUcenOcjBox.getValue())) {
					idUcenika = u.getId();
				}
			}
			for(PredmetUSkoli pus : Korisnik.prijavljeniKorisnik.predajePredmete) {
				if(pus.getPredmet().getNaziv().equals(nazivPredmeta) && pus.getPredmet().getRazred() == razredPredmeta  && pus.getSkola().getNaziv().equals(nazivSkole) && pus.getSkola().getMjesto().equals(mjestoSkole)) {
					idPredmetaUSkoli = pus.getId();
				}
			}
			int idOcjene = IzmjenaBaze.posaljiOcjena(idUcenika, idPredmetaUSkoli, ocjena, datum);
			new Ocjena(idOcjene, idUcenika, idPredmetaUSkoli, ocjena, datum.toString());
			
			sveOcjeneUcenika.clear();
			for(Ocjena o : Ocjena.sveOcjene) {
				if(o.getUcenik().getPristupniPodaci().getKorisnickoIme().equals(odaberiUcenOcjBox.getValue())) {
					sveOcjeneUcenika.appendText(o.getDatum() + " : " + o.getOcjena() + "\n");
				}
			}
			
		});
		
		izostanakTipka.setOnAction(event -> {
			int idUcenika = 0;
			int idPredmetaUSkoli = 0;
			String nazivPredmeta = odaberiPredOcjBox.getValue().split(",")[0];
			int razredPredmeta = Integer.parseInt(odaberiPredOcjBox.getValue().split(",")[1]);
			String nazivSkole = odaberiSkolOcjBox.getValue().split(",")[0];
			String mjestoSkole = odaberiSkolOcjBox.getValue().split(",")[1];
			LocalDate vrijednostDatuma = odaberiDatum.getValue();
			java.sql.Date datum = Date.valueOf(vrijednostDatuma);
			
			for(Ucenik u : Ucenik.sviUcenici) {
				if(u.getPristupniPodaci().getKorisnickoIme().equals(odaberiUcenOcjBox.getValue())) {
					idUcenika = u.getId();
				}
			}
			
			for(PredmetUSkoli pus : Korisnik.prijavljeniKorisnik.predajePredmete) {
				if(pus.getPredmet().getNaziv().equals(nazivPredmeta) && pus.getPredmet().getRazred() == razredPredmeta  && pus.getSkola().getNaziv().equals(nazivSkole) && pus.getSkola().getMjesto().equals(mjestoSkole)) {
					idPredmetaUSkoli = pus.getId();
				}
			}
			
			int idIzostanka = IzmjenaBaze.posaljiIzostanak(idUcenika, idPredmetaUSkoli, datum);
			new Izostanci(idIzostanka, idUcenika, idPredmetaUSkoli, datum.toString());
			
			sviIzostanciUcenika.clear();
			for(Izostanci i : Izostanci.sviIzostanci) {
				if(i.getUcenik().getPristupniPodaci().getKorisnickoIme().equals(odaberiUcenOcjBox.getValue())) {
					sviIzostanciUcenika.appendText(i.getDatum() + " : " + i.getPredmetUSkoli().getPredmet().getNaziv() + "\n");
				}
			}
		});
		
	}
	
	
	private void ispisiInfo() {
		
		imePolje.setText(Korisnik.prijavljeniKorisnik.getIme());
		prezimePolje.setText(Korisnik.prijavljeniKorisnik.getPrezime());
		for(Skola s : Korisnik.prijavljeniKorisnik.sveSkoleProfesor) {
			skolePolje.appendText(s.getNaziv() + "\n");
		}
		for(PredmetUSkoli pus : Korisnik.prijavljeniKorisnik.predajePredmete) {
			predmetiPolje.appendText(pus.getSkola().getNaziv() + ", " + pus.getPredmet().getNaziv() + ", " + pus.getPredmet().getRazred() + "\n");
		}
		
		odjaviSeTipkaProf.setOnAction((event) -> {
			LoginWindow.promjeniScenu("/login/Login.fxml", "Prijava", 800, 600);
		});
		promjeniLozinku.setOnAction((event) -> {
			String lozinka = novaLozinka.getText();
			if(novaLozinka.getText().isEmpty()) {
				obavjestenjeProzor("Niste unijeli lozinku!");
				return;
			}
			String korisnickoIme = Korisnik.prijavljeniKorisnik.getPristupniPodaci().getKorisnickoIme();
			IzmjenaBaze.posaljiPromjenuLozinke(korisnickoIme, lozinka);
			Korisnik.prijavljeniKorisnik.getPristupniPodaci().promjeniLozinku(lozinka);
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
		
		for(Predmet p : Predmet.sviPredmeti) {
			sviPredmetiPolje.appendText(p.getNaziv() + ", " + p.getRazred() + "\n");
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
