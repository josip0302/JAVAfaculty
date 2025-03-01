package hr.fer.oprpp1.custom.scripting.elems;

public class ElementConstantInteger extends Element{
    private int value;

    public ElementConstantInteger(int value) {
        this.value = value;
    }
    /**
     *
     * @return element as text
     */
    @Override
    public String asText() {
        return Integer.toString(value);
    }
}
