package pmf.projekat.controller;

import java.util.Comparator;

import pmf.projekat.application.Ocjena;

public class Sortiranje implements Comparator<Ocjena> {
    public int compare(Ocjena a, Ocjena b) {
        return a.getDatum().compareTo(b.getDatum());
    }
}