//
//	Sadrzi zajednicko za profesora i ucenika, kao i listu svih korisnika.
//
package application;

import java.util.ArrayList;

public class Korisnik {
	protected int id, pol;
	protected String ime, prezime;
	protected PristupniPodaci pristupniPodaci;
	protected Skola skola;
	protected int razred;
	public static ArrayList<Korisnik> sviKorisnici = new ArrayList<>();
	public static Korisnik prijavljeniKorisnik;

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
	
}