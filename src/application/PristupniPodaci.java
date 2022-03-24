package application;

import java.util.ArrayList;

public class PristupniPodaci {
	private int id;
	private String korisnickoIme, email;
	private String lozinka;
	public static ArrayList<PristupniPodaci> sviPristupniPodaci = new ArrayList<>();
	
	public PristupniPodaci(int id, String korisnickoIme, String lozinka, String email) throws Exception{
		this.id = id;
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.email = email;
		if(!pristupniPodaciPostoje(this)) {
			sviPristupniPodaci.add(this);
		}else {
			throw new Exception("Pristupni podaci vec postoje!");
		}
	}
	
	public static boolean pristupniPodaciPostoje(PristupniPodaci podaci) {
        for (PristupniPodaci pp : sviPristupniPodaci) {
            if (pp.korisnickoIme.equals(podaci.korisnickoIme) || pp.email.equals(podaci.email)) {
                return true;
            }
        }
        return false;
    }
	
	public static PristupniPodaci getPristupniPodaci(int id) {
		for(PristupniPodaci pp : sviPristupniPodaci) {
			if(pp.id == id) {
				return pp;
			}
		}
		System.err.println("Pristupni podaci nisu pronadjeni (PristupniPodaci.getPristupniPodaci)");
		return null;
	}
	
	public String getKorisnickoIme() {
		return korisnickoIme;
	}
	
	public String getLozinka() {
		return lozinka;
	}
	
	public int getId() {
		return id;
	}
	
}
