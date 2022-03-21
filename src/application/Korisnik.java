//
//	Sadrzi zajednicko za profesora i ucenika, kao i listu svih korisnika.
//
package application;

import java.util.ArrayList;

public class Korisnik {
	protected int id, pol;
	protected String ime, prezime;
	protected int pristupId;
	public static ArrayList<Korisnik> sviKorisnici = new ArrayList<>();
	
	public int getPristupId() {
		return pristupId;
	}
	
}