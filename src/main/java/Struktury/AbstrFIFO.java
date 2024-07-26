
package Struktury;

import java.util.Iterator;

//fronta
public class AbstrFIFO<T> implements IAbstrFIFO<T> {
    
    private IAbstrDoubleList<T> list;
    
    public AbstrFIFO(){
        this.list = new AbstrDoubleList<T>();
    }

    @Override
    public void zrus() {
        list.zrus();
    }

    @Override
    public boolean jePrazdny() {
        return list.jePrazdny();
    }

    @Override
    public void vloz(T data) {
        list.vlozPrvni(data);
    }

    @Override
    public T odeber() {
        return list.odeberPosledni();
    }

    @Override
    public Iterator vytvorIterator() {
        return iterator();
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }
    
}
