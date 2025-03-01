package hr.fer.oprpp1.custom.collections;

public class ArrayIndexedCollection extends Collection{
private int size;
private Object[] elements;

    public ArrayIndexedCollection() {
        this.size=0;
        this.elements=new Object[16];
    }

    public ArrayIndexedCollection(int initialCapacity) throws IllegalArgumentException{
        if(initialCapacity<1) throw
                new IllegalArgumentException("Initial capacity must be greater than 0");
        this.size = 0;
        this.elements=new Object[initialCapacity];
    }
    public ArrayIndexedCollection(Collection collection) {
        if(collection == null) throw new NullPointerException();
        this.size=0;
        this.elements=new Object[16];
        this.addAll(collection);

    }
    public ArrayIndexedCollection(Collection collection,int initialCapacity) throws NullPointerException ,IllegalArgumentException {
        this(initialCapacity);
        if(collection == null) throw new NullPointerException();
        if(collection.size()>initialCapacity){
            this.size=collection.size();
            this.elements=collection.toArray();
        }else{
            this.addAll(collection);}
    }
    @Override
    public boolean isEmpty() {
        return this.size==0;
    }
    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void add(Object value) throws NullPointerException{
        if(value==null) throw new NullPointerException("Value can not be null");
        this.size++;
        if(this.elements.length<this.size){
            int x=elements.length*2;
            Object[] elem=this.elements.clone();
            elements=new Object[x];
            for(int i =0;i<this.size-1;i++){
                elements[i]=elem[i];
            }
        }
        elements[this.size - 1] = value;
    }

    @Override
    public boolean contains(Object value) {
        for(int i =0;i<this.size;i++){
            if(this.elements[i].equals(value)){
               return true;
            }
        }
        return false;
    }

    public int indexOf(Object value){
        if(value==null || isEmpty()) return -1;
        for(int i =0;i<this.size;i++){
            if(this.elements[i].equals(value)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean remove(Object value) {
        if(!contains(value)) return false;
        remove(indexOf(value));
        return true;
    }

    @Override
    public Object[] toArray() {
        Object[] result=new Object[this.size];
        for(int i =0;i<size;i++){
            result[i]=this.elements[i];
        }
        return result;
    }

    @Override
    public void forEach(Processor processor) {
        for(int i =0;i<size;i++){
            processor.process(elements[i]);
        }
    }

    @Override
    public void addAll(Collection other) {
        Processor pro=new Processor(){
            @Override
            public void process(Object value){
                add(value);

            }
        };
        other.forEach(pro);
    }

    @Override
    public void clear() {
        for(int i =0;i<this.size;i++){
            this.elements[i]=null;
        }
        this.size=0;
    }
    public Object get(int index){
        if(index<0 || index>size-1) throw new IndexOutOfBoundsException();
        else return elements[index];}
    public void insert(Object value, int position) throws IndexOutOfBoundsException{
        if(position<0 || position>size) throw new IndexOutOfBoundsException();
        Object [] array = this.toArray();

        if(position==elements.length){
            add(value);
            return;
        }
        int x=size;
        this.clear();
        for (int i =0;i<=x;i++){
            if(i==position){
                this.add(value);
            }else if(i<position){
               this.add(array[i]);
            }else {
                this.add(array[i-1]);
            }
        }
        array=null;
    }
    public void remove(int index) throws IndexOutOfBoundsException{
        if(index<0 || index>size-1) throw new IndexOutOfBoundsException();
        for (int i =index;i<this.size-1;i++){
            elements[i]=elements[i+1];
        }
        elements[this.size-1]=null;
        this.size--;
    }
}
