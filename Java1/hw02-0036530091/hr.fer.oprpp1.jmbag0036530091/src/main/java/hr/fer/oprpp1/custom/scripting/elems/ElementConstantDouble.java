package hr.fer.oprpp1.custom.scripting.elems;

public class ElementConstantDouble extends Element{
    private double value;

    public ElementConstantDouble(double value) {
        this.value = value;
    }

    /**
     *
     * @return element as text
     */
    @Override
    public String asText() {
        return Double.toString(value);
    }
}
