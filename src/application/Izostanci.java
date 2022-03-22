package application;

import java.util.ArrayList;
import java.util.Date;

public class Izostanci {
	private int id;
	private Date datum;
	private Ucenik ucenik;
	private PredmetUSkoli predmetUSkoli;
	public static ArrayList<Izostanci> sviIzostanci = new ArrayList<>();
	
	public Izostanci(int id, int ucenikId, int predmetUSkoliId, Date datum) {
		this.id = id;
		this.ucenik = Ucenik.getUcenik(ucenikId);
		this.predmetUSkoli = PredmetUSkoli.getPredmetUSkoli(predmetUSkoliId);
		this.datum = datum;
		sviIzostanci.add(this);
	}
	
}
