
package Obce;

import Struktury.AbstrDoubleList;
import Struktury.AbstrTable;
import Struktury.IAbstrDoubleList;
import static Obce.enumPozice.POSLEDNI;
import static Obce.enumPozice.PRVNI;
import static Obce.eTypProhl.HLOUBKA;
import static Obce.eTypProhl.SIRKA;
import Struktury.AbstrHeap;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.Iterator;



public class SpravceObci{
    
    private Comparator<Obec> cmpPocet = (Obec o1, Obec o2) -> {
        Obec obec1 = (Obec) o1;
        double a = obec1.getCelkem();
        Obec obec2 = (Obec) o2;
        double b = obec2.getCelkem();

        if (a < b) {
            return 1;
        } else {
            return -1;
        }
    };
    
    private Comparator<Obec> cmpNazev = (Obec o1, Obec o2) -> {
        Obec obec1 = (Obec) o1;
        String a = obec1.getNazev();
        Obec obec2 = (Obec) o2;
        String b = obec2.getNazev();

        if (a.compareTo(b) < 0) {
            return 1;
        } else {
            return -1;
        }
    };
    
    //public AbstrDoubleList<Mereni> list = new AbstrDoubleList<>();
    public AbstrHeap<Obec> heap = new AbstrHeap<>(cmpPocet);

    public int importDat(String soubor) throws FileNotFoundException, IOException{
        
        int count = 0;
        try{
            BufferedReader reader = new BufferedReader(new FileReader(soubor));
            String radek;
            
            while((radek = reader.readLine()) != null ){
                count++;
            }
        }catch(Exception e){
                    
        }

        Obec obec = null;
        Obec[] poleObci = new Obec[count];   //17
        count = 0;
        try{
            BufferedReader reader = new BufferedReader(new FileReader(soubor));
            String radek;
            String[]atributy;
            
            int cisloKraje;
            String psc;
            String nazev;
            int pocetMuzu;
            int pocetZen;
            int celkem;
            
            while((radek = reader.readLine()) != null ){
                atributy = radek.split(";");
                cisloKraje = Integer.parseInt(atributy[0]);
                psc = atributy[2];
                nazev = atributy[3];
                pocetMuzu = Integer.parseInt(atributy[4]);
                pocetZen = Integer.parseInt(atributy[5]);
                celkem = Integer.parseInt(atributy[6]);
                obec = new Obec(cisloKraje, psc, nazev, pocetMuzu, pocetZen, celkem);
                poleObci[count] = obec;
                count++;
                //vlozObec(obec);
            }
            vybuduj(poleObci);
            
        }finally{
            
        }
        return 0;
    }

    public void najdiProces(Obec obec){
        
    }
    
    public void vybuduj(Obec[] pole){
        heap.vybuduj(pole);
    }
    
    public void vlozObec(Obec obec) {
        heap.vloz(obec);
    }

    public void odeberMax(){
        heap.odeberMax();
    }
    
    public void vypisMax(){
        System.out.println();
    }
    
    public Iterator vytvorTterator(eTypProhl typ) {
        return heap.vypis(typ);
    }
    

    public void zrus() {
        heap.zrus();
    }

    public void zmenRazeni(String typComparator) {
        if(typComparator.equals("NAZEV")){
            heap.zmenRazeni(cmpNazev);
        }else{
            heap.zmenRazeni(cmpPocet);
        }
    }
    
    
    
}
