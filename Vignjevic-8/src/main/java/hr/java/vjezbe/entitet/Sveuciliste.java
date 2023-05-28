package hr.java.vjezbe.entitet;

import java.util.ArrayList;
import java.util.List;

public class Sveuciliste<T extends ObrazovnaUstanova>{
    private List<T> obrazovneUstanove;

    public Sveuciliste(List<T> obrazovneUstanove) {
        this.obrazovneUstanove = obrazovneUstanove;
    }

    public void dodajObrazovnuUstanovu(T obrazovna){
        obrazovneUstanove.add(obrazovna);
    }

    public T dohvatiObrazovnuUstanovu(int indeks){
        T obrazovna=obrazovneUstanove.get(indeks);
        return obrazovna;
    }

    public List<T> getObrazovneUstanove() {
        return obrazovneUstanove;
    }
}
