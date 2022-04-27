package pmf.projekat.application;

import java.util.ArrayList;

public class Predmet {
    public static ArrayList<Predmet> sviPredmeti = new ArrayList<>();
    private int id, razred;
    private String naziv;

    public Predmet(int id, String naziv, int razred) throws Exception {
        this.id = id;
        this.naziv = naziv;
        this.razred = razred;
        if (!predmetPostoji(this)) {
            sviPredmeti.add(this);
        } else {
            throw new Exception("Predmet vec postoji!");
        }
    }

    public static Predmet getPredmet(int id) {
        for (Predmet p : sviPredmeti) {
            if (p.id == id) {
                return p;
            }
        }
        System.err.println("Predmet nije pronadjen (Predmet.getPredmet)");
        return null;
    }

    private boolean predmetPostoji(Predmet predmet) {
        for (Predmet p : sviPredmeti) {
            if (p.naziv.equals(predmet.naziv) && (p.razred == predmet.razred)) {
                return true;
            }
        }
        return false;
    }

    public int getRazred() {
        return razred;
    }

    public String getNaziv() {
        return naziv;
    }

    public int getId() {
        return id;
    }

}
