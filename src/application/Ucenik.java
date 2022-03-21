package application;

import java.util.ArrayList;

public class Ucenik extends Korisnik {
	public static ArrayList<Ucenik> sviUcenici = new ArrayList<>();
	
	public Ucenik(int id, String ime, String prezime, int pol, int pristupniPodaciId) {
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.pol = pol;
		this.pristupId = pristupniPodaciId;
		sviUcenici.add(this);
		sviKorisnici.add(this);
	}
	
	public static Ucenik getUcenik(int id) {
		for(Ucenik u : sviUcenici) {
			if(u.id == id) {
				return u;
			}
		}
		return null;
	}
	
}
