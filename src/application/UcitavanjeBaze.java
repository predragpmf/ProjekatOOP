package application;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

public class UcitavanjeBaze {
	private static BazaPodataka db = new BazaPodataka();
	
	public static void ucitavanje() {
        db.uspostaviVezu();
        ucitajSkole();
        ucitajPredmete();
        ucitajPitanja();
        ucitajPristupnePodatke();
        ucitajProfesore();
        ucitajOcjene();
        ucitajUcenike();
        ucitajPredmeteUSkoli();
        ucitajIzostanke();
        ucitajOcjenePredmeta();
        db.prekidVeze();
    }
	
	// Ucitava sve podatke iz baze u klase:
	private static void ucitajSkole() {
		try {
            Statement izjava = db.getVeza().createStatement();
            ResultSet setRezultata = izjava.executeQuery("select * from skola");
            while (setRezultata.next()) {
                int id = setRezultata.getInt("id");
                String naziv = setRezultata.getString("naziv");
                String grad = setRezultata.getString("grad");
                String mjesto = setRezultata.getString("mjesto");
                String drzava = setRezultata.getString("drzava");
                new Skola(id, naziv, grad, mjesto, drzava);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	private static void ucitajPredmete() {
		try {
            Statement izjava = db.getVeza().createStatement();
            ResultSet setRezultata = izjava.executeQuery("select * from predmet");
            while (setRezultata.next()) {
                int id = setRezultata.getInt("id");
                String naziv = setRezultata.getString("naziv");
                int razred = setRezultata.getInt("razred");
                new Predmet(id, naziv, razred);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	private static void ucitajPitanja() {
		try {
            Statement izjava = db.getVeza().createStatement();
            ResultSet setRezultata = izjava.executeQuery("select * from pitanje");
            while (setRezultata.next()) {
                int id = setRezultata.getInt("id");
                String pitanje = setRezultata.getString("pitanje");
                new Pitanje(id, pitanje);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	private static void ucitajPristupnePodatke() {
		try {
            Statement izjava = db.getVeza().createStatement();
            ResultSet setRezultata = izjava.executeQuery("select * from pristupni_podaci");
            while (setRezultata.next()) {
                int id = setRezultata.getInt("id");
                String korisnickoIme = setRezultata.getString("korisnicko_ime");
                String sifra = setRezultata.getString("sifra");
                String email = setRezultata.getString("email");
                new PristupniPodaci(id, korisnickoIme, sifra, email);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	private static void ucitajProfesore() {
		try {
            Statement izjava = db.getVeza().createStatement();
            ResultSet setRezultata = izjava.executeQuery("select * from profesor");
            while (setRezultata.next()) {
                int id = setRezultata.getInt("id");
                String ime = setRezultata.getString("ime");
                String prezime = setRezultata.getString("prezime");
                int pol = setRezultata.getInt("pol");
                int pristupniPodaciId = setRezultata.getInt("pristupni_podaci_id");
                new Profesor(id, ime, prezime, pol, pristupniPodaciId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	private static void ucitajOcjene() {
		try {
            Statement izjava = db.getVeza().createStatement();
            ResultSet setRezultata = izjava.executeQuery("select * from ocjena");
            while (setRezultata.next()) {
                int id = setRezultata.getInt("id");
                int ucenikId = setRezultata.getInt("ucenik_id");
                int predmetUSkoliId = setRezultata.getInt("predmet_u_skoli_id");
                int ocjena = setRezultata.getInt("ocjena");
                Date datum = setRezultata.getDate("datum");
                new Ocjena(id, ucenikId, predmetUSkoliId, ocjena, datum);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	private static void ucitajUcenike() {
		try {
            Statement izjava = db.getVeza().createStatement();
            ResultSet setRezultata = izjava.executeQuery("select * from ucenik");
            while (setRezultata.next()) {
                int id = setRezultata.getInt("id");
                String ime = setRezultata.getString("ime");
                String prezime = setRezultata.getString("prezime");
                int pol = setRezultata.getInt("pol");
                int pristupniPodaciId = setRezultata.getInt("pristupni_podaci_id");
                new Ucenik(id, ime, prezime, pol, pristupniPodaciId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	private static void ucitajPredmeteUSkoli() {
		try {
            Statement izjava = db.getVeza().createStatement();
            ResultSet setRezultata = izjava.executeQuery("select * from predmet_u_skoli");
            while (setRezultata.next()) {
                int id = setRezultata.getInt("id");
                int predmetId = setRezultata.getInt("predmet_id");
                int skolaId = setRezultata.getInt("skola_id");
                int profesorId = setRezultata.getInt("profesor_id");
                new PredmetUSkoli(id, predmetId, skolaId, profesorId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	private static void ucitajIzostanke() {
		try {
            Statement izjava = db.getVeza().createStatement();
            ResultSet setRezultata = izjava.executeQuery("select * from izostanci");
            while (setRezultata.next()) {
                int id = setRezultata.getInt("id");
                int ucenikId = setRezultata.getInt("ucenik_id");
                int predmetUSkoliId = setRezultata.getInt("predmet_u_skoli_id");
                Date datum = setRezultata.getDate("datum");
                new Izostanci(id, ucenikId, predmetUSkoliId, datum);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	private static void ucitajOcjenePredmeta() {
		try {
            Statement izjava = db.getVeza().createStatement();
            ResultSet setRezultata = izjava.executeQuery("select * from ocjena_predmeta");
            while (setRezultata.next()) {
                int id = setRezultata.getInt("id");
                int predmetUSkoliId = setRezultata.getInt("predmet_u_skoli_id");
                int ucenikId = setRezultata.getInt("ucenik_id");
                int pitanjeId = setRezultata.getInt("pitanje_id");
                int ocjena = setRezultata.getInt("ocjena");
                new OcjenaPredmeta(id, predmetUSkoliId, ucenikId, pitanjeId, ocjena);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
