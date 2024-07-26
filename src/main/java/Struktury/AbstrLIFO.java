
package Struktury;

import java.util.Iterator;

//zasobnik
public class AbstrLIFO<T> implements IAbstrLIFO<T> {
    
    private IAbstrDoubleList<T> list;
    
    public AbstrLIFO(){
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
        return list.odeberPrvni();
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
