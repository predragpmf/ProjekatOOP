package application;

import java.util.ArrayList;
import java.util.Date;

public class Izostanci {
	private int id;
	private Date datum;
	private Ucenik ucenikId;
	private PredmetUSkoli predmetUSkoliId;
	public static ArrayList<Izostanci> sviIzostanci = new ArrayList<>();
	
	public Izostanci(int id, int ucenikId, int predmetUSkoliId, Date datum) {
		this.id = id;
		this.ucenikId = Ucenik.getUcenik(ucenikId);
		this.predmetUSkoliId = PredmetUSkoli.getPredmetUSkoli(predmetUSkoliId);
		this.datum = datum;
		sviIzostanci.add(this);
	}
	
}
