package login;

import java.util.Comparator;
import application.Ocjena;

public class Sortiranje implements Comparator<Ocjena> {
	public int compare(Ocjena a, Ocjena b)
    {
        return a.getDatum().compareTo(b.getDatum());
    }
}