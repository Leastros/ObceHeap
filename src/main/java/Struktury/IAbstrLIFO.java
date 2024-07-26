
package Struktury;

import java.util.Iterator;


public interface IAbstrLIFO<T> extends Iterable<T> {
    
    void zrus();
    boolean jePrazdny();
    void vloz(T data);
    T odeber();
    Iterator vytvorIterator();
    
}
