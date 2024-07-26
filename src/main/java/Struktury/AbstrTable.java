
package Struktury;

import Obce.eTypProhl;
import java.util.Iterator;


public class AbstrTable<K extends Comparable<K>, V> implements IAbstrTable<K, V> {
    
    private Prvek root;
    private int pocet;
    
    
    private class Prvek{
        Prvek parent;
        Prvek left;
        Prvek right;
        K key;
        V value;
        
        public Prvek(K key, V value) {
            if(key == null || value == null){
                throw new NullPointerException();
            }
            this.key = key;
            this.value = value;
            this.parent = null;
            this.left = null;
            this.right = null;
        }
    }
    
    public AbstrTable(){
        root = null;
    }

    @Override
    public void zrus() {
        root = null;
    }

    @Override
    public boolean jePrazdny() {
        return root == null;
    }

    @Override
    public V najdi(K key) {
        if(key == null){
            throw new NullPointerException();
        }
        Prvek tmp = root;
        while (tmp != null){
            if(key.compareTo(tmp.key) == 0){
                return tmp.value;
            }else if(key.compareTo(tmp.key) < 0){
                tmp = tmp.left;
            }else if(key.compareTo(tmp.key) > 0){
                tmp = tmp.right;
            }
        }
        throw new NullPointerException();
    }

    @Override
    public void vloz(K key, V value) {
        if(key == null || value == null){
            return;
        }
        Prvek novy = new Prvek(key, value);
        if(jePrazdny()){
            root = novy;
        }else{
            rekurzVloz(root, novy);
        }
    }
    
    //ODEBER -------------------------
    @Override
    public V odeber(K key) {
        najdi(key);
        Prvek odebirany = najdiPrvek(root, key);
        V value = odebirany.value;
        root = odeberPrvek(root, key);
        return value;
    }
    private Prvek odeberPrvek(Prvek prvek, K key) {

        if (key.compareTo(prvek.key) < 0) {
            prvek.left = odeberPrvek(prvek.left, key);
        }else if (key.compareTo(prvek.key) > 0) {
            prvek.right = odeberPrvek(prvek.right, key);
        }else{
            if (prvek.left == null) {
                return prvek.right;
            }else if (prvek.right == null) {
                return prvek.left;
            }else{
                Prvek nejvetsi = najdiVeVetvi(prvek.left);
                prvek.key = nejvetsi.key;
                prvek.value = nejvetsi.value;
                prvek.left = odeberPrvek(prvek.left, prvek.key);
            }
        }

        return prvek;
    }
    private Prvek najdiVeVetvi(Prvek prvek) {
        if (prvek.right != null) {
            return najdiVeVetvi(prvek.right);
        }
        return prvek;
    }
    private Prvek najdiPrvek(Prvek prvek, K key) {
        if (key.compareTo(prvek.key) < 0) {
            return najdiPrvek(prvek.left, key);
        }else if (key.compareTo(prvek.key) > 0) {
            return najdiPrvek(prvek.right, key);
        }else{
            return prvek;
        }
    }// KONEC ODEBER-------------
    

    @Override
    public Iterator vytvorIterator(eTypProhl typ) {
        if(typ.equals(eTypProhl.HLOUBKA)){
            return new itHloubka();
        }else{
            return new itSirka();
        }
    }
    
    private void rekurzVloz(Prvek odkud, Prvek novy) {
        if(novy.key.compareTo(odkud.key) < 0){
            if(odkud.left == null){
                odkud.left = novy;
                novy.parent = odkud;
            }else{
                rekurzVloz(odkud.left, novy);
            }
        }else if(novy.key.compareTo(odkud.key) > 0){
            if(odkud.right == null){
                odkud.right = novy;
                novy.parent = odkud;
            }else{
                rekurzVloz(odkud.right, novy);
            }
        }
    }
    
    
    private class itHloubka implements Iterator<V>{
        
        private IAbstrLIFO<Prvek> zasobnik;
        private Prvek tmp;

        public itHloubka(){
            zasobnik = new AbstrLIFO<Prvek>();
            if(root != null){
            zasobnik.vloz(root);
            }
            
        }
        
        @Override
        public boolean hasNext() {
            return !zasobnik.jePrazdny();
        }

        @Override
        public V next() {
            tmp = zasobnik.odeber();
            if(tmp.right != null){
                zasobnik.vloz(tmp.right);
            }
            if(tmp.left != null){
                zasobnik.vloz(tmp.left);
            }
            return tmp.value;
        }
        
    }
    private class itSirka implements Iterator<V>{
        
        private IAbstrFIFO<Prvek> fronta;
        private Prvek tmp;

        public itSirka(){
            fronta = new AbstrFIFO<Prvek>();
            if(root != null){
            fronta.vloz(root);
            }
        }
        
        @Override
        public boolean hasNext() {
            return !fronta.jePrazdny();
        }

        @Override
        public V next() {
            tmp = fronta.odeber();
            if(tmp.left != null){
                fronta.vloz(tmp.left);
            }
            if(tmp.right != null){
                fronta.vloz(tmp.right);
            }
            return tmp.value;
        }
        
    }
    
}
