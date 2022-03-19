package application;

import java.util.ArrayList;

public class Predmet {
	private int id, razred;
	private String naziv;
	public static ArrayList<Predmet> sviPredmeti = new ArrayList<>();
	
	public Predmet(int id, String naziv, int razred) throws Exception{
		this.id = id;
		this.naziv = naziv;
		this.razred = razred;
		if(!predmetPostoji(this)) {
			sviPredmeti.add(this);
		}else {
			throw new Exception("Predmet vec postoji!");
		}
	}
	
	private boolean predmetPostoji(Predmet predmet) {
        for (Predmet p : sviPredmeti) {
            if (p.naziv.equals(predmet.naziv) && (p.razred == predmet.razred)) {
                return true;
            }
        }
        return false;
    }
	
	public static Predmet getPredmet(int id) {
		for(Predmet p : sviPredmeti) {
			if(p.id == id) {
				return p;
			}
		}
		return null;
	}
	
}
