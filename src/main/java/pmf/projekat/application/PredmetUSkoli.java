package pmf.projekat.application;

import java.util.ArrayList;

public class PredmetUSkoli {
    public static ArrayList<PredmetUSkoli> sviPredmetiUSkoli = new ArrayList<>();
    private int id;
    private Predmet predmet;
    private Skola skola;
    private Profesor profesor;

    public PredmetUSkoli(int id, int predmetId, int skolaId, int profesorId) {
        this.id = id;
        this.predmet = Predmet.getPredmet(predmetId);
        this.profesor = Profesor.getProfesor(profesorId);
        this.skola = Skola.getSkola(skolaId);
        this.profesor.sveSkoleProfesor.add(skola);
        this.profesor.predajePredmete.add(this);
        sviPredmetiUSkoli.add(this);
    }

    public static PredmetUSkoli getPredmetUSkoli(int id) {
        for (PredmetUSkoli pus : sviPredmetiUSkoli) {
            if (pus.id == id) {
                return pus;
            }
        }
        System.err.println("Predmet u skoli nije pronadjen (PredmetUSkoli.getPredmetUSkoli)" + id);
        return null;
    }

    public Skola getSkola() {
        return skola;
    }

    public Predmet getPredmet() {
        return predmet;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public int getId() {
        return id;
    }
}
