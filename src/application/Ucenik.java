package application;

import java.util.ArrayList;

public class Ucenik extends Korisnik {
	public static ArrayList<Ucenik> sviUcenici = new ArrayList<>();
	public static Ucenik prijavljeniUcenik;
	
	public Ucenik(int id, String ime, String prezime, int pol, int pristupniPodaciId) {
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.pol = pol;
		pristupniPodaci = PristupniPodaci.getPristupniPodaci(pristupniPodaciId);
		sviUcenici.add(this);
		sviKorisnici.add(this);
	}
	
	public static Ucenik getUcenik(int id) {
		for(Ucenik u : sviUcenici) {
			if(u.id == id) {
				return u;
			}
		}
		System.err.println("Ucenik sa id-om: " + id + " nije pronadjen (Ucenik.getUcenik)");
		return null;
	}
	
}
