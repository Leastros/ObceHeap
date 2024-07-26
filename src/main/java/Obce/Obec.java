
package Obce;

public class Obec implements Comparable<Obec>{
    
    private int cisloKraje;
    private String psc;
    private String nazev;
    private int pocetMuzu;
    private int pocetZen;
    private int celkem;

    public Obec(int cisloKraje, String psc, String nazev, int pocetMuzu, int pocetZen, int celkem) {
        this.cisloKraje = cisloKraje;
        this.psc = psc;
        this.nazev = nazev;
        this.pocetMuzu = pocetMuzu;
        this.pocetZen = pocetZen;
        this.celkem = celkem;
    }
    
    

    public int getCisloKraje() {
        return cisloKraje;
    }

    public void setCisloKraje(int cisloKraje) {
        this.cisloKraje = cisloKraje;
    }

    public String getPsc() {
        return psc;
    }

    public void setPsc(String psc) {
        this.psc = psc;
    }

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public int getPocetMuzu() {
        return pocetMuzu;
    }

    public void setPocetMuzu(int pocetMuzu) {
        this.pocetMuzu = pocetMuzu;
    }

    public int getPocetZen() {
        return pocetZen;
    }

    public void setPocetZen(int pocetZen) {
        this.pocetZen = pocetZen;
    }

    public int getCelkem() {
        return celkem;
    }

    public void setCelkem(int celkem) {
        this.celkem = celkem;
    }

    @Override
    public String toString() {
        return "Obec{" + "cisloKraje=" + cisloKraje + ", psc=" + psc + ", nazev=" + nazev + ", pocetMuzu=" + pocetMuzu + ", pocetZen=" + pocetZen + ", celkem=" + celkem + '}';
    }
    
    

    @Override
    public int compareTo(Obec o) {
        return this.getNazev().compareTo(o.getNazev());
    }
    
    
    
}
