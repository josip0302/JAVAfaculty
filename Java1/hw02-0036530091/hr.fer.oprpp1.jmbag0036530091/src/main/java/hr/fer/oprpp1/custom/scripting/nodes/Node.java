package hr.fer.oprpp1.custom.scripting.nodes;

import hr.fer.oprpp1.custom.collections.ArrayIndexedCollection;

public class Node {
    ArrayIndexedCollection coll;

    /**
     * adds child to ArrayIndexedCollection and initalizes it if it was not already initalized
     * @param child
     */
    public void addChildNode(Node child){
        if(coll==null) coll=new ArrayIndexedCollection();
        coll.add(child);

    }

    /**
     *
     * @return number of children nodes stored in ArrayIndexedCollection coll
     */
    public int numberOfChildren(){
        return coll.size();
    }

    /**
     *
     * @param index
     * @return child node stored at given index in the array
     */
    public Node getChild(int index){

        return (Node) coll.get(index);
    }

    /**
     *
     * @return true if element has child nodes and false otherwise
     */
    public boolean hasChild(){
        return  this.coll!=null;
    }
}
