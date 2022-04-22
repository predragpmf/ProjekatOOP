package pmf.projekat.application;

import java.util.ArrayList;

public class Ocjena {
    public static ArrayList<Ocjena> sveOcjene = new ArrayList<>();
    private int id, ocjena;
    private String datum;
    private Ucenik ucenik;
    private PredmetUSkoli predmetUSkoli;

    public Ocjena(int id, int ucenikId, int predmetUSkoliId, int ocjena, String datum) {
        this.id = id;
        this.ucenik = Ucenik.getUcenik(ucenikId);
        this.predmetUSkoli = PredmetUSkoli.getPredmetUSkoli(predmetUSkoliId);
        this.ucenik.skola = predmetUSkoli.getSkola();
        this.ucenik.razred = predmetUSkoli.getPredmet().getRazred();
        this.ocjena = ocjena;
        this.datum = datum;
        sveOcjene.add(this);
        this.ucenik.ocjeneKorisnika.add(this);
    }

    public Ucenik getUcenik() {
        return ucenik;
    }

    public String getDatum() {
        return datum;
    }

    public PredmetUSkoli getPredmetUSkoli() {
        return predmetUSkoli;
    }

    public int getOcjena() {
        return ocjena;
    }

    public int getId() {
        return id;
    }
}
