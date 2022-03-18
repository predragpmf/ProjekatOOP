package application;

import java.util.ArrayList;

public class PredmetUSkoli {
	private int id;
	private Predmet predmetId;
	private Skola skolaId;
	private Profesor profesorId;
	public static ArrayList<PredmetUSkoli> sviPredmetiUSkoli = new ArrayList<>();
	
	public PredmetUSkoli(int id, int predmetId, int skolaId, int profesorId) {
		this.id = id;
		this.predmetId = Predmet.getPredmet(predmetId);
		this.profesorId = Profesor.getProfesor(profesorId);
		this.skolaId = Skola.getSkola(skolaId);
		// TODO
	}
	
	public static PredmetUSkoli getPredmetUSkoli(int id) {
		for(PredmetUSkoli pus : sviPredmetiUSkoli) {
			if(pus.id == id) {
				return pus;
			}
		}
		return null;
	}
	
}
