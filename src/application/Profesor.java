package application;

import java.util.ArrayList;

public class Profesor extends Korisnik {
	//private int id, pol;
	//private String ime, prezime;
	//private PristupniPodaci pristupId;
	public static ArrayList<Profesor> sviProfesori = new ArrayList<>();
	
	public Profesor(int id, String ime, String prezime, int pol, int pristupId) {
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.pol = pol;
		this.pristupId = pristupId;
		sviProfesori.add(this);
		
	}
	
	public static Profesor getProfesor(int id) {
		for(Profesor p : sviProfesori) {
			if(p.id == id) {
				return p;
			}
		}
		return null;
	}
	
}
