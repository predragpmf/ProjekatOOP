package application;

import java.util.ArrayList;

public class Skola {
	private int id;
	private String naziv, grad, mjesto, drzava;
	public static ArrayList<Skola> sveSkole = new ArrayList<Skola>();
	
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
		System.err.println("Nije moguce pronaci skolu (Skola.getSkola)");
		return null;
	}
	
	public static String getNazivById(int id) {
		for(Skola s : sveSkole) {
			if(s.id == id) {
				return s.naziv;
			}
		}
		return null;
	}
	
	public String getNaziv() {
		return naziv;
	}
	
	public String getGrad() {
		return grad;
	}
	
	public String getMjesto() {
		return mjesto;
	}
	
	public String getDrzava() {
		return drzava;
	}
}
