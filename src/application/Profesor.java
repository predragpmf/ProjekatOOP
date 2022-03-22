package application;

import java.util.ArrayList;

public class Profesor extends Korisnik {
	public static ArrayList<Profesor> sviProfesori = new ArrayList<>();
	public static Profesor prijavljeniProfesor;
	
	public Profesor(int id, String ime, String prezime, int pol, int pristupniPodaciId) {
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.pol = pol;
		this.pristupniPodaci = PristupniPodaci.getPristupniPodaci(pristupniPodaciId);
		sviProfesori.add(this);
		sviKorisnici.add(this);
	}
	
	public static Profesor getProfesor(int id) {
		for(Profesor p : sviProfesori) {
			if(p.id == id) {
				return p;
			}
		}
		System.err.println("Profesor nije pronadjen (Profesor.getProfesor)");
		return null;
	}
	
}
