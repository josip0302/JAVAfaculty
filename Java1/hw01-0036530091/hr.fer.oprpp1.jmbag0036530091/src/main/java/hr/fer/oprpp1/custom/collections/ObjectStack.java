package hr.fer.oprpp1.custom.collections;

public class ObjectStack {
    private ArrayIndexedCollection stack;

    public ObjectStack() {
        this.stack = new ArrayIndexedCollection();
    }

    public boolean isEmpty(){
        return stack.isEmpty();
    }
    public int size(){
        return stack.size();
    }
    public void push(Object value){
        stack.add(value);
    }
    public Object pop(){
        if(stack.isEmpty()) throw new EmptyStackException();
        Object result=stack.get(stack.size()-1);
        stack.remove(stack.size()-1);
        return result;
    }
    public Object peek(){
        return stack.get(stack.size()-1);
    }

    void clear(){
        stack.clear();
    }


}
