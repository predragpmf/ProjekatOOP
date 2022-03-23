package application;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IzmjenaBaze {
	private static BazaPodataka db = UcitavanjeBaze.db;
	
	public static int posaljiPristupnePodatke(String korisnickoIme, String lozinka, String email) {
		int id = 0;
		try {
			String upit = "INSERT INTO pristupni_podaci(korisnicko_ime, email, sifra)  VALUES (?,?,?)";
			PreparedStatement izjava = db.getVeza().prepareStatement(upit, PreparedStatement.RETURN_GENERATED_KEYS);
			izjava.setString(1, korisnickoIme);
	        izjava.setString(2, lozinka);
	        izjava.setString(3, email);
	        izjava.executeUpdate();
            ResultSet rs = izjava.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
    }
	
	public static int posaljiUcenika(String ime, String prezime, int pol, int pristupniPodaciId) {
		int id = 0;
		try {
			String upit = "INSERT INTO ucenik(ime, prezime, pol, pristupni_podaci_id)  VALUES (?,?,?,?)";
			PreparedStatement izjava = db.getVeza().prepareStatement(upit, PreparedStatement.RETURN_GENERATED_KEYS);
			izjava.setString(1, ime);
	        izjava.setString(2, prezime);
	        izjava.setInt(3, pol);
	        izjava.setInt(4, pristupniPodaciId);
	        izjava.executeUpdate();
            ResultSet rs = izjava.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
    }
	
	public static int posaljiPredmet(String naziv, int razred) {
		int id = 0;
		try {
			String upit = "INSERT INTO predmet(naziv, razred)  VALUES (?,?)";
			PreparedStatement izjava = db.getVeza().prepareStatement(upit, PreparedStatement.RETURN_GENERATED_KEYS);
			izjava.setString(1, naziv);
	        izjava.setInt(2, razred);
	        izjava.executeUpdate();
            ResultSet rs = izjava.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
    }
	
	
}
