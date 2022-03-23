//
//	Kontroler za prozor profesora.
//
package login;

import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import application.IzmjenaBaze;
import application.Korisnik;
import application.Predmet;
import application.PredmetUSkoli;
import application.PristupniPodaci;
import application.Profesor;
import application.Skola;
import application.Ucenik;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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
	private TextArea skolePolje;
	@FXML
	private TextArea predmetiPolje;
	@FXML
	private TextArea sviUceniciPolje;
	@FXML
	private TextArea sviPredmetiPolje;
	@FXML
	private Button dodajUcenikeTipka;
	@FXML
	private Button dodajPredmetTipka;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		imePolje.setText(Korisnik.prijavljeniKorisnik.getIme());
		prezimePolje.setText(Korisnik.prijavljeniKorisnik.getPrezime());
		for(Skola s : Korisnik.prijavljeniKorisnik.sveSkoleProfesor) {
			skolePolje.appendText(s.getNaziv() + "\n");
		}
		for(PredmetUSkoli pus : Korisnik.prijavljeniKorisnik.predajePredmete) {
			predmetiPolje.appendText(pus.getSkola().getNaziv() + ", " + pus.getPredmet().getNaziv() + "\n");
		}
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
			String lozinka = korisnickoIme.concat("123");
			String email = dodajUcenikaEmail.getText();
			try {
				int pristupniPodaciId = IzmjenaBaze.posaljiPristupnePodatke(korisnickoIme, hesirajLozinku(lozinka), email);
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
		for(Predmet p : Predmet.sviPredmeti) {
			Set<String> naziviPredmeta = new HashSet<>();
			naziviPredmeta.add(p.getNaziv());
			for(String np : naziviPredmeta) {
				sviPredmetiPolje.appendText(np + "\n");
			}
		}
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
			for(Predmet p : Predmet.sviPredmeti) {
				Set<String> naziviPredmeta = new HashSet<>();
				naziviPredmeta.add(p.getNaziv());
				for(String np : naziviPredmeta) {
					sviPredmetiPolje.appendText(np + "\n");
				}
			}
		});
				
	}
	
	public void ispisiKorisnike(String tip) {
		if(tip.equals("Ucenik")) {
			for(Korisnik k : Korisnik.sviKorisnici) {
				if(k instanceof Ucenik) {
					sviUceniciPolje.appendText(k.getIme() + " " + k.getPrezime() + "\n");
				} 
			}
		} else if (tip.equals("Profesor")) {
			/*
			for(Korisnik k : Korisnik.sviKorisnici) {
				if(k instanceof Profesor) {
					sviUceniciPolje.appendText(k.getIme() + " " + k.getPrezime());
				} 
			}
			*/
		}
	}
	
	private String hesirajLozinku(String lozinka) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(lozinka.getBytes());
	        BigInteger no = new BigInteger(1, messageDigest);
	        String hashtext = no.toString(16);
	        while (hashtext.length() < 32) {
	            hashtext = "0" + hashtext;
	        }
	        return hashtext;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}


	
}
