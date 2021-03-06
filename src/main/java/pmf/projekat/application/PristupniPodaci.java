package pmf.projekat.application;

import java.util.ArrayList;

public class PristupniPodaci {
    public static ArrayList<PristupniPodaci> sviPristupniPodaci = new ArrayList<>();
    private int id;
    private String korisnickoIme, email;
    private String lozinka;

    public PristupniPodaci(int id, String korisnickoIme, String lozinka, String email) throws Exception {
        this.id = id;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.email = email;
        if (!pristupniPodaciPostoje(this)) {
            sviPristupniPodaci.add(this);
        } else {
            throw new Exception("Pristupni podaci vec postoje!");
        }
    }

    public static boolean korisnickoImePostoji(String korisnickoIme) {
        for (PristupniPodaci pp : sviPristupniPodaci) {
            if (pp.getKorisnickoIme().equals(korisnickoIme)) {
                return true;
            }
        }
        return false;
    }

    public static PristupniPodaci getPristupniPodaci(int id) {
        for (PristupniPodaci pp : sviPristupniPodaci) {
            if (pp.id == id) {
                return pp;
            }
        }
        System.err.println("Pristupni podaci nisu pronadjeni (PristupniPodaci.getPristupniPodaci)");
        return null;
    }

    public static void promjeniLozinku(String korisnickoIme, String lozinka) {
        for (PristupniPodaci pp : sviPristupniPodaci) {
            if (pp.getKorisnickoIme().equals(korisnickoIme)) {
                pp.setLozinka(lozinka);
            }
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

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

}
