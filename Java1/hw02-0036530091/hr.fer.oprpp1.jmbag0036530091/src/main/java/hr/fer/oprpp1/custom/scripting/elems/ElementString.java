package hr.fer.oprpp1.custom.scripting.elems;

public class ElementString extends Element{
    private String value;

    public ElementString(String value) {
        this.value = value;
    }
    /**
     *
     * @return element as text
     */
    @Override
    public String asText() {
        return value.toString();
    }
}
