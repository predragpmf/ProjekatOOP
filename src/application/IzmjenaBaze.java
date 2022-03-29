package application;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IzmjenaBaze {
	private static BazaPodataka db = UcitavanjeBaze.db;
	
	public static int posaljiPristupnePodatke(String korisnickoIme, String email, String lozinka) {
		int id = 0;
		try {
			String upit = "INSERT INTO pristupni_podaci(korisnicko_ime, email, sifra)  VALUES (?,?,?)";
			PreparedStatement izjava = db.getVeza().prepareStatement(upit, PreparedStatement.RETURN_GENERATED_KEYS);
			izjava.setString(1, korisnickoIme);
	        izjava.setString(2, email);
	        izjava.setString(3, lozinka);
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
	
	public static int posaljiSkolu(String naziv, String grad, String mjesto, String drzava) {
		int id = 0;
		try {
			String upit = "INSERT INTO skola(naziv, grad, mjesto, drzava)  VALUES (?,?,?,?)";
			PreparedStatement izjava = db.getVeza().prepareStatement(upit, PreparedStatement.RETURN_GENERATED_KEYS);
			izjava.setString(1, naziv);
	        izjava.setString(2, grad);
	        izjava.setString(3, mjesto);
	        izjava.setString(4, drzava);
	        izjava.executeUpdate();
            ResultSet rs = izjava.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
    }
	
	public static int posaljiProfesora(String ime, String prezime, int pol, int pristupniPodaciId) {
		int id = 0;
		try {
			String upit = "INSERT INTO profesor(ime, prezime, pol, pristupni_podaci_id)  VALUES (?,?,?,?)";
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
	
	public static int posaljiPredmetUSkoli(int predmetId, int skolaId, int profesorId) {
		int id = 0;
		try {
			String upit = "INSERT INTO predmet_u_skoli(predmet_id, skola_id, profesor_id)  VALUES (?,?,?)";
			PreparedStatement izjava = db.getVeza().prepareStatement(upit, PreparedStatement.RETURN_GENERATED_KEYS);
	        izjava.setInt(1, predmetId);
	        izjava.setInt(2, skolaId);
	        izjava.setInt(3, profesorId);
	        izjava.executeUpdate();
            ResultSet rs = izjava.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
    }
	
	public static int posaljiOcjena(int ucenikId, int predmetUSkoliId, int ocjena, Date datum) {
		int id = 0;
		try {
			String upit = "INSERT INTO ocjena(ucenik_id, predmet_u_skoli_id, ocjena, datum)  VALUES (?,?,?,?)";
			PreparedStatement izjava = db.getVeza().prepareStatement(upit, PreparedStatement.RETURN_GENERATED_KEYS);
			izjava.setInt(1, ucenikId);
	        izjava.setInt(2, predmetUSkoliId);
	        izjava.setInt(3, ocjena);
	        izjava.setDate(4, datum);
	        izjava.executeUpdate();
            ResultSet rs = izjava.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
    }
	
	public static int posaljiIzostanak(int ucenikId, int predmetUSkoliId, Date datum) {
		int id = 0;
		try {
			String upit = "INSERT INTO izostanci(ucenik_id, predmet_u_skoli_id, datum)  VALUES (?,?,?)";
			PreparedStatement izjava = db.getVeza().prepareStatement(upit, PreparedStatement.RETURN_GENERATED_KEYS);
			izjava.setInt(1, ucenikId);
	        izjava.setInt(2, predmetUSkoliId);
	        izjava.setDate(3, datum);
	        izjava.executeUpdate();
            ResultSet rs = izjava.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
    }
	
	public static int posaljiOcjenuPredmeta(int ucenikId, int predmetUSkoliId, int pitanjeId, int ocjena) {
		int id = 0;
		try {
			String upit = "INSERT INTO ocjena_predmeta(ucenik_id, predmet_u_skoli_id, pitanje_id, ocjena)  VALUES (?,?,?,?)";
			PreparedStatement izjava = db.getVeza().prepareStatement(upit, PreparedStatement.RETURN_GENERATED_KEYS);
			izjava.setInt(1, ucenikId);
	        izjava.setInt(2, predmetUSkoliId);
	        izjava.setInt(3, pitanjeId);
	        izjava.setInt(4, ocjena);
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
