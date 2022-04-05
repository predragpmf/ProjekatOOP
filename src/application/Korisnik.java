//
//	Sadrzi zajednicko za profesora i ucenika, kao i listu svih korisnika.
//
package application;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Korisnik {
	protected int id, pol;
	protected String ime, prezime;
	protected PristupniPodaci pristupniPodaci;
	protected Skola skola;
	protected int razred;
	
	public ArrayList<Izostanci> sviIzostanci = new ArrayList<>();
	public ArrayList<Ocjena> ocjeneKorisnika = new ArrayList<>();
	public ArrayList<PredmetUSkoli> predajePredmete = new ArrayList<>();
	
	public static ArrayList<Korisnik> sviKorisnici = new ArrayList<>();
	public static Korisnik prijavljeniKorisnik;
	
	public Set<Skola> sveSkoleProfesor = new HashSet<>();
	

	public int getPristupniPodaciId() {
		return pristupniPodaci.getId();
	}
	
	public String getIme() {
		return ime;
	}
	
	public String getPrezime() {
		return prezime;
	}
	
	public String getPol() {
		if(pol == 0) {
			return "Ž";
		}else {
			return "M";
		}
	}
	
	public String getSkola() {
		return skola.getNaziv() + ", " + skola.getGrad() + ", " + skola.getMjesto() + ", " + skola.getDrzava() ;
	}
	
	public String getRazred() {
		return Integer.toString(razred);
	}
	
	public int getId() {
		return id;
	}
	
	public PristupniPodaci getPristupniPodaci() {
		return pristupniPodaci;
	}
	
}