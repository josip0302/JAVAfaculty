package hr.fer.oprpp1.custom.scripting.nodes;

import hr.fer.oprpp1.custom.scripting.lexer.RealTokenType;

public class DocumentNode extends Node{
    /**
     *
     * @return string form all of its child nodes
     */
    @Override
    public String toString() {
        StringBuilder result=new StringBuilder(47483647);
        int x=this.numberOfChildren();
        for(int i=0;i<x;i++){
            System.out.println(i);
                result.append(getChild(i).toString());
            if(this.getChild(i).hasChild()){
                int y=this.getChild(i).numberOfChildren();
                for(int t=0;t<y;t++){
                    result.append(this.getChild(i).getChild(t).toString());
            }
        }
        }
      return result.toString();
    }
}
