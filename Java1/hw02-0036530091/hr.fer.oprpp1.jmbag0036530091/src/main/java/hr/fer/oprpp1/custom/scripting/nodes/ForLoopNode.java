package hr.fer.oprpp1.custom.scripting.nodes;

import hr.fer.oprpp1.custom.scripting.elems.Element;
import hr.fer.oprpp1.custom.scripting.elems.ElementVariable;

public class ForLoopNode extends Node{
    private ElementVariable variable;
    private Element startExpression;
    private Element  endExpression;
    private Element  stepExpression;

    public ForLoopNode(ElementVariable variable, Element startExpression, Element endExpression, Element stepExpression) {
        this.variable = variable;
        this.startExpression = startExpression;
        this.endExpression = endExpression;
        this.stepExpression = stepExpression;
    }

    public ForLoopNode(ElementVariable variable, Element startExpression, Element endExpression) {
        this.variable = variable;
        this.startExpression = startExpression;
        this.endExpression = endExpression;
        this.stepExpression=null;
    }

    /**
     *
     * @return string with all of its elements
     */
    @Override
    public String toString() {
        if(stepExpression!=null) {
            return "{$ FOR" +
                    " " + variable.asText() +
                    " " + startExpression.asText() +
                    " " + endExpression.asText() +
                    " " + stepExpression.asText() +
                    " $}";
        }else {
            return "{$ FOR" +
                    " " + variable.asText() +
                    " " + startExpression.asText() +
                    " " + endExpression.asText() +
                    " $}";

        }
    }
}
