package hr.fer.oprpp1.custom.scripting.nodes;

public class TextNode extends Node{
    private String text;

    public TextNode(String text) {
        this.text = text;
    }

    /**
     *
     * @return text content of textNode
     */
    public String toString() {
        return text;
    }
}
