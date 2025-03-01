package hr.fer.oprpp1.custom.scripting.lexer;

import hr.fer.oprpp1.hw02.prob1.TokenType;

public class RealToken {
    private RealTokenType type;
    private Object value;
    public RealToken(RealTokenType type, Object value) {
        this.type=type;
        this.value=value;

    }

    /**
     *
     * @return value od this token
     */
    public Object getValue() {
        return this.value;
    }

    /**
     *
     * @return RealTokenType of this token
     */
    public RealTokenType getType() {
        return this.type;
    }
}