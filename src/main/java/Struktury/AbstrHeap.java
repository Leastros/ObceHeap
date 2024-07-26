
package Struktury;

import Obce.eTypProhl;
import Obce.enumPriorita;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class AbstrHeap<T> {
    
    private T[] heap;
    private int pocet;
    private Comparator<T> comparator;
    
    public AbstrHeap(Comparator cmpNovy){
        heap = (T[]) new Object[100];
        this.comparator = cmpNovy;
        pocet =  0;
    }
 
    //* -vybuduje požadovanou prioritní frontu, vstupní parametr pole obcí
    public void vybuduj(T[] array){
        zrus();
        pocet = 0;
        for (int i = 0; i < array.length; i++) {
            if (pocet + 1 == heap.length) {
                heap = zvetsi(heap);
            }
            heap[i + 1] = array[i];
            pocet++;
        }
        reorganizace();
    }
    private T[] zvetsi(T[] heap){
        T[] novaHeap = (T[]) new Object[pocet+10];
        for (int i = 0; i < heap.length; i++) {
            novaHeap[i] = heap[i];
        }
        
        return novaHeap;
    }
    
    //* - přebuduje prioritní frontu dle požadované priority
    public void reorganizace(){
        for(int i = pocet/2; i > 0; i--){
            int parent = i;
            int leftChild = 2*i;
            int rightChild = 2*i + 1;
            if(rightChild > pocet){
                if(leftChild <= pocet && compare(leftChild, parent)){
                    swap(parent, leftChild);
                    bubleDown(leftChild);
                }
            }else if(compare(leftChild, rightChild)){
                if(leftChild <= pocet && compare(leftChild, parent)){
                    swap(parent, leftChild);
                    bubleDown(leftChild);
                }
            }else if(compare(rightChild, leftChild)){
                if(rightChild <= pocet && compare(rightChild, parent)){
                    swap(parent, rightChild);
                    bubleDown(rightChild);
                }
            }
        }
    }
    private void bubleDown(int prvek) {
        while(2*prvek <= pocet){
            int leftChild = 2*prvek;
            int rightChild = 2*prvek + 1;
            if (leftChild < pocet && !compare(leftChild, rightChild)) {
                leftChild = rightChild;
            }
            if(compare(prvek, leftChild)){
                break;
            }else{
                swap(prvek, leftChild);
                prvek = leftChild;
            }
        }
    }
    private boolean compare(int a, int b) {
        return comparator.compare(heap[a], heap[b]) > 0;
    }

    private void swap(int a, int b) {
        T tmp = heap[a];
        heap[a] = heap[b];
        heap[b] = tmp;
    }
    public void zrus(){
        heap = (T[]) new Object[100];
        pocet = 0;
    }
    
    public boolean jePrazdny(){
        return pocet == 0;
    }
    
    public void vloz(T prvek){
        if(jePrazdny()){
            heap[1] = prvek;
            pocet++;
        }else{
            if(pocet+1 == heap.length) {
                heap = zvetsi(heap);
            }
            heap[pocet+1] = prvek;
            pocet++;
            reorganizace();

        }
    }
    
    public T odeberMax(){
        if(!jePrazdny()){
            T tmp = heap[1];
            heap[1] = heap[pocet];
            pocet--;
            reorganizace();
            return tmp;
        }
        return null;
    }
    
    public T zpristupniMax(){
        return heap[1];
    }
    
    //vypíše prvky prioritní fronty (využívá iterátor do šířky i do hloubky)
    public Iterator<T> vypis(eTypProhl typ){
        //if (typ.equals(eTypProhl.SIRKA)) {
            Iterator<T> iteratorSirka = new Iterator(){
                int i = 1;
                @Override
                public boolean hasNext(){
                    return i <= pocet;
                }
                @Override
                public T next(){
                    if(!hasNext()){
                        throw new NoSuchElementException();
                    }
                    T tmp = heap[i];
                    i++;
                    return tmp;
                }
            };
            return iteratorSirka;
        //}else{
            
        //}
    }
    
    public void zmenRazeni(Comparator cmpNovy){
        this.comparator = cmpNovy;
        reorganizace();
    }
    
}
