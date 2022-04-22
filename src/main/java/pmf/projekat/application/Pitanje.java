package pmf.projekat.application;

import java.util.ArrayList;

public class Pitanje {
    public static ArrayList<Pitanje> svaPitanja = new ArrayList<>();
    private int id;
    private String pitanje;

    public Pitanje(int id, String pitanje) throws Exception {
        this.id = id;
        this.pitanje = pitanje;
        if (!pitanjePostoji(this)) {
            svaPitanja.add(this);
        } else {
            throw new Exception("Pitanje vec postoji!");
        }
    }

    public static Pitanje getPitanje(int id) {
        for (Pitanje p : svaPitanja) {
            if (p.id == id) {
                return p;
            }
        }
        System.err.println("Pitanje nije pronadjeno (Pitanje.getPitanje)");
        return null;
    }

    private boolean pitanjePostoji(Pitanje pitanje) {
        for (Pitanje p : svaPitanja) {
            if (p.pitanje.equals(pitanje.pitanje)) {
                return true;
            }
        }
        return false;
    }

    public String getPitanjeTekst() {
        return pitanje;
    }

}
