package application;

import java.util.ArrayList;

public class PristupniPodaci {
	private int id;
	private String korisnickoIme, sifra, email;
	public static ArrayList<PristupniPodaci> sviPristupniPodaci = new ArrayList<>();
	
	public PristupniPodaci(int id, String korisnickoIme, String sifra, String email) throws Exception{
		this.id = id;
		this.korisnickoIme = korisnickoIme;
		this.sifra = sifra;
		this.email = email;
		if(!pristupniPodaciPostoje(this)) {
			sviPristupniPodaci.add(this);
		}else {
			throw new Exception("Pristupni podaci vec postoje!");
		}
	}
	
	private boolean pristupniPodaciPostoje(PristupniPodaci podaci) {
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
		return null;
	}
	
}