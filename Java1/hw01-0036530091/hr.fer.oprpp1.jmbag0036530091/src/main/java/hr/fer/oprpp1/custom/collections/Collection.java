package hr.fer.oprpp1.custom.collections;

public class Collection {
    public Collection(){}
    public boolean isEmpty(){
        return size()>0;
    }
    public int size(){
        return 0;
    }
    public void add(Object value){}
    public boolean contains(Object value){
        if(value==null) return false;
        return false;
    }

    public boolean remove(Object value){
        return false;
    }
    public Object[] toArray(){
        throw new UnsupportedOperationException();
    }
    public void forEach(Processor processor){

    }
    public void addAll(Collection other){

    }
    public void clear(){}
}
