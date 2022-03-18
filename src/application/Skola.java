package application;

import java.util.ArrayList;

public class Skola {
	private int id;
	private String naziv, grad, mjesto, drzava;
	public static ArrayList<Skola> sveSkole = new ArrayList<>();
	
	public Skola(int id, String naziv, String grad, String mjesto, String drzava) throws Exception {
		this.id = id;
		this.naziv = naziv;
		this.grad = grad;
		this.mjesto = mjesto;
		this.drzava = drzava;
		if(!skolaPostoji(this)) {
			sveSkole.add(this);
		}else {
			throw new Exception("Skola vec postoji!");
		}
	}
	
	private boolean skolaPostoji(Skola skola) {
        for (Skola s : sveSkole) {
            if (s.naziv.equals(skola.naziv) && s.mjesto.equals(skola.mjesto)) {
                return true;
            }
        }
        return false;
    }
	
	public static Skola getSkola(int id) {
		for(Skola s : sveSkole) {
			if(s.id == id) {
				return s;
			}
		}
		return null;
	}
	
}
