package hr.fer.oprpp1.custom.scripting.nodes;

import hr.fer.oprpp1.custom.scripting.elems.Element;

import java.util.Arrays;

public class EchoNode extends Node{
    private Element[] elements;

    public EchoNode(Element[] elements) {
        this.elements = elements;
    }

    /**
     *
     * @return elements array
     */
    public Element[] getElements() {
        return elements;
    }
    public static boolean start=false;

    /**
     *
     * @return string with all of its elements
     */
    @Override
    public String toString() {
        StringBuilder result=new StringBuilder(elements.length);
        if(!start) {
            result.append("{$");
        }
        for(int i=0;i<elements.length;i++){
            result.append(elements[i].asText()+" ");
        }
        if(start){
            result.append("$}");
            start=false;
        }else {
            start = true;
        }
        return result.toString();
    }
}
