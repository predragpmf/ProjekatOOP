package pmf.projekat.application;

import java.util.ArrayList;

public class Skola {
    public static ArrayList<Skola> sveSkole = new ArrayList<Skola>();
    private int id;
    private String naziv, grad, mjesto, drzava;

    public Skola(int id, String naziv, String grad, String mjesto, String drzava) throws Exception {
        this.id = id;
        this.naziv = naziv;
        //System.out.print(naziv);
        this.grad = grad;
        //System.out.print(grad);
        this.mjesto = mjesto;
        //System.out.print(mjesto);
        this.drzava = drzava;
        //System.out.print(drzava);
        //System.out.println();
        if (!skolaPostoji(this)) {
            sveSkole.add(this);
        } else {
            throw new Exception("Skola vec postoji!");
        }
    }

    public static Skola getSkola(int id) {
        for (Skola s : sveSkole) {
            if (s.id == id) {
                return s;
            }
        }
        System.err.println("Nije moguce pronaci skolu (Skola.getSkola)");
        return null;
    }

    public static String getNazivById(int id) {
        for (Skola s : sveSkole) {
            if (s.id == id) {
                return s.naziv;
            }
        }
        return null;
    }

    public static Skola getByNazivIMjesto(String naziv, String mjesto) {
        for (Skola s : sveSkole) {
            if (s.getNaziv().equals(naziv) && s.getMjesto().equals(mjesto)) {
                return s;
            }
        }
        System.err.println("Skola nije pronadjena (Skola.getByNazivIMjesto()");
        return null;
    }

    private boolean skolaPostoji(Skola skola) {
        for (Skola s : sveSkole) {
            if (s.naziv.equals(skola.naziv) && s.mjesto.equals(skola.mjesto)) {
                return true;
            }
        }
        return false;
    }

    public String ispisSkole(Skola s) {
        return s.getNaziv() + ", " + s.getGrad() + ", " + s.getMjesto() + ", " + s.getDrzava();
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

    public int getId() {
        return id;
    }
}
