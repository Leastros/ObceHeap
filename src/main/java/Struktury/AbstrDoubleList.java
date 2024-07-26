
package Struktury;

import Struktury.IAbstrDoubleList;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class AbstrDoubleList<E> implements IAbstrDoubleList<E> {
    
    private Prvek prvni;
    private Prvek aktualni;
    private Prvek posledni;
    private int pocet;
    
    public AbstrDoubleList(){
        this.pocet = 0;
    }
    
    private class Prvek{
        Prvek previous;
        Prvek next;
        E data;
        
        public Prvek(E data) {
            if(data == null){
                throw new NullPointerException();
            }
            this.previous = null;
            this.next = null;
            this.data = data;
        }
    }

    @Override
    public void zrus() {
        prvni = null;
        aktualni = null;
        posledni = null;
        pocet = 0;
    }

    @Override
    public boolean jePrazdny() {
        return pocet == 0;
    }
    
    @Override
    public void vlozPrvni(E data) {
        if(data == null){
            throw new NullPointerException();
        }
        Prvek prvek = new Prvek(data);
        
        if(jePrazdny()){
            prvni = prvek;
            aktualni = prvek;
            posledni = prvek;
        }else{
            prvni.previous = prvek;
            prvek.next = prvni;
            prvni = prvek;
        }
        pocet++;
    }
    
    @Override
    public void vlozPosledni(E data) {
        if(jePrazdny()){
            vlozPrvni(data);
        }else{
            Prvek prvek = new Prvek(data);
            posledni.next = prvek;
            prvek.previous = posledni;
            posledni = prvek;
            pocet++;
        }
    }
    
    @Override
    public void vlozNaslednika(E data) {
        if(jePrazdny()){
            vlozPrvni(data);
        }else if(aktualni == posledni){
            vlozPosledni(data);
        }else{
            Prvek tmp = aktualni.next;
            Prvek novy = new Prvek(data);
            
            novy.previous = aktualni;
            novy.next = aktualni.next;
            aktualni.next = novy;
            novy.next.previous = novy;
            pocet++;
        }
    }
    
    @Override
    public void vlozPredchudce(E data) {
        if(jePrazdny() || aktualni == prvni){
            vlozPrvni(data);
        }else{
            Prvek tmp = aktualni.previous;
            Prvek novy = new Prvek(data);
            novy.previous = aktualni.previous;
            novy.next = aktualni;
            aktualni.previous = novy;
            novy.previous.next = novy;
            pocet++;
        }

    }

    @Override
    public E zpristupniAktualni() {
        if(jePrazdny() || aktualni == null){
            throw new NullPointerException();
        }
        return aktualni.data;
    }

    @Override
    public E zpristupniPrvni() {
        if(jePrazdny() || prvni == null){
            throw new NullPointerException();
        }
        aktualni = prvni;
        return aktualni.data;
    }

    @Override
    public E zpristupniPosledni() {
        if(jePrazdny() || posledni == null){
            throw new NullPointerException();
        }
        aktualni = posledni;
        return aktualni.data;
    }

    @Override
    public E zpristupniNaslednika() {
        if(aktualni == posledni){
            return aktualni.data;
        }
        if(jePrazdny() || aktualni == null){
            throw new NullPointerException();
        }
        aktualni = aktualni.next;
        return aktualni.data;
    }

    @Override
    public E zpristupniPredchudce() {
        if(aktualni == prvni){
            return aktualni.data;
        }
        if(jePrazdny() || aktualni == null){
            throw new NullPointerException();
        }
        aktualni = aktualni.previous;
        return aktualni.data;
    }
    
    @Override
    public E odeberAktualni() {
        Prvek tmp = null;
        if(!jePrazdny() && aktualni != null){
            tmp = prvni;
            if(aktualni == prvni){
                odeberPrvni();
                aktualni = prvni;
            }else if(aktualni == posledni){
                odeberPosledni();
                aktualni = prvni;
            }else if(prvni == posledni){
                zrus();
            }else{
                tmp = aktualni;
                aktualni.previous.next = aktualni.next;
                aktualni.next.previous = aktualni.previous;
                pocet--;
                aktualni = prvni;
            }
        }
        return tmp.data;
    }
    
    @Override
    public E odeberPrvni() {
        Prvek tmp = null;
        if(!jePrazdny()){
            if(pocet == 1){
                tmp = prvni;
                zrus();
                //pocet--;
            }else{
                tmp = prvni;
                prvni = prvni.next;
                prvni.previous = null;
                tmp.next = null;
                pocet--;
            }
            
        }
        
        return tmp.data;
    }
    
    @Override
    public E odeberPosledni() {
        Prvek tmp = null;
        if(!jePrazdny() && posledni != null){
            if(pocet == 1){
                tmp = posledni;
                zrus();
                
            }else{
                tmp = posledni;
                posledni = posledni.previous;
                posledni.next = null;
                tmp.previous = null;
                pocet--;
            }
        }
        
        return tmp.data;
    }
    
    @Override
    public E odeberNaslednika() {
        Prvek tmp = prvni;
        if(aktualni != null && aktualni.next != null){
            tmp = aktualni.next;
            if(tmp == posledni){
                odeberPosledni();
            }else{
                aktualni.next = tmp.next;
                tmp.next.previous = aktualni;
                tmp.next = tmp.previous = null;
                pocet--;
            }
        }
        return tmp.data;
    }
    
    @Override
    public E odeberPredchudce() {
        Prvek tmp = prvni;
        if(aktualni != null && aktualni.previous != null){
            tmp = aktualni.previous;
            if(tmp == prvni){
                odeberPrvni();
            }else{
                aktualni.previous = tmp.previous;
                tmp.previous.next = aktualni;
                tmp.next = tmp.previous = null;
                pocet--;
            }
        }
        
        return tmp.data;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Prvek prvek = prvni;
            int i = 0;
            public boolean hasNext(){
                return i < pocet;
            }
            public E next(){

                if(hasNext()){
                    E data = prvek.data;
                    prvek = prvek.next;
                    i++;
                    return data;
                    
                }
                throw new NoSuchElementException();
            }
        };
    }
    
    public int velikost(){
        return pocet;
    }
    
    
}
