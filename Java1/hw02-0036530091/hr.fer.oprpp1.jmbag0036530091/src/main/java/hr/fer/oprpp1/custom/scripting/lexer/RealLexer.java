package hr.fer.oprpp1.custom.scripting.lexer;

import hr.fer.oprpp1.custom.scripting.parser.SmartScriptParserException;
import hr.fer.oprpp1.hw02.prob1.LexerState;
import hr.fer.oprpp1.hw02.prob1.Token;
import hr.fer.oprpp1.hw02.prob1.TokenType;

public class RealLexer {
    private RealLexerState state;
    private char[] data; // ulazni tekst
    private RealToken token; // trenutni token
    private int currentIndex; // indeks prvog neobrađenog znaka
    boolean operatorEq;
    boolean operatorFOR;
    int elementcounter;
    boolean forend;

    /**
     *
     * REAL LEXER CONSRTUCTIOR WITH STRIN ARGUMENT
     */
    public RealLexer(String documentBody) {
        this.state = RealLexerState.TEXT;
        data = documentBody.toCharArray();
        currentIndex = 0;
        operatorEq = false;
        operatorFOR = false;
        elementcounter=0;
        forend=false;
    }
    /**
     *
     * @return next token
     */
    public RealToken nextToken() {
        int x = currentIndex;
        if (currentIndex== data.length-1) {
            this.token = new RealToken(RealTokenType.EOF, null);
            return token;
        }
        if (state == RealLexerState.TEXT) {
            StringBuilder text = new StringBuilder(data.length);
            boolean backslash = false;
            for (int i = x; i < data.length; i++) {
                if(i== data.length-1){
                    forend=true;
                }

                currentIndex++;
                if (data[i] == '\\') {

                    if (backslash) {
                        text.append(data[i]);
                        backslash = false;
                    } else if (i != data.length - 1) {
                        if (data[i + 1] != '\\' && data[i + 1] != '{') {
                            throw new SmartScriptParserException();
                        } else {
                            backslash = true;
                        }

                    } else {
                        backslash = true;
                    }

                } else if (data[i] == '{' && i != data.length - 1 && !backslash) {
                    if (data[i + 1] == '$') {
                        this.setState(RealLexerState.TAG);
                        currentIndex++;
                        if(text.toString().length()>0) {
                            System.out.println(text.toString());
                            token = new RealToken(RealTokenType.TEXT, text.toString());
                            return token;
                        }else {
                            return this.nextToken();
                        }

                    }
                } else if (i==data.length-1) {
                    text.append(data[i]);
                    token = new RealToken(RealTokenType.TEXT, text.toString());
                    if(text.toString().length()==0){
                        this.token = new RealToken(RealTokenType.EOF, null);
                        return token;
                    }
                    forend=true;
                    return token;
                } else {
                    text.append(data[i]);
                }




        }}
        if(state == RealLexerState.TAG){
            StringBuilder string = new StringBuilder(data.length);
            StringBuilder variable = new StringBuilder(data.length);
            StringBuilder function = new StringBuilder(data.length);
            StringBuilder number = new StringBuilder(data.length);
            boolean isString = false;
            boolean backslash2 = false;
            boolean isFunction=false;
            boolean isVariable=false;
            boolean isNumber=false;
            currentIndex=x;
            for (int i = x; i < data.length; i++) {
                if(i== data.length-1){
                    forend=true;
                }
                currentIndex++;
                System.out.println(data[i]);
                if(operatorFOR){
                    if(Character.isLetter(data[i]) && !isString && !backslash2){
                        variable.append(data[i]);
                        isVariable=true;
                        if(data[i+1]==' '){
                            isVariable=false;
                            elementcounter++;
                            token = new RealToken(RealTokenType.VARIABLE, variable.toString());
                            return token;
                        }
                    }else if(isVariable) {
                        variable.append(data[i]);
                    }else if(data[i]=='-'){
                        isNumber=true;
                        if(elementcounter==0){
                            throw new SmartScriptParserException();
                        }
                        number.append(data[i]);
                    } else if (Character.isDigit(data[i])||isNumber) {
                        if(elementcounter==0){
                            throw new SmartScriptParserException();
                        }
                        if(!isNumber)isNumber=true;
                        number.append(data[i]);
                        if(!Character.isDigit(data[i+1])){
                            elementcounter++;
                            token = new RealToken(RealTokenType.NUMBER, number.toString());
                            return token;
                        }
                    } else if (data[i]=='\"') {
                    }else if (data[i]=='@') {
                        throw new SmartScriptParserException();
                    }else if (data[i] == '$' && i != data.length - 1) {
                        if (data[i + 1] == '}') {

                            if(elementcounter<2 || elementcounter>4)  {
                                elementcounter=0;
                                throw new SmartScriptParserException();
                            }
                            currentIndex++;
                            operatorFOR=false;
                            elementcounter=0;
                            this.setState(RealLexerState.TEXT);
                            return nextToken();
                        }}

                }else if (operatorEq) {
                    if(data[i]=='@'){
                        isFunction=true;
                    } else if (isFunction) {
                        function.append(data[i]);
                        if(data[i+1]==' '){
                            token = new RealToken(RealTokenType.FUNCTION, function.toString());
                            return token;
                        }
                    } else if(Character.isLetter(data[i]) && !isString && !backslash2){
                        variable.append(data[i]);
                        isVariable=true;
                        if(data[i+1]==' ' || data[i+1]=='$'){
                            token = new RealToken(RealTokenType.VARIABLE, variable.toString());
                            return token;
                        }
                    }else if(isVariable) {
                        variable.append(data[i]);
                    }else if (data[i] == '\"' && !backslash2) {
                        if (isString == false) {
                            isString = true;
                        } else {
                            isString = false;
                            token = new RealToken(RealTokenType.STRING, string.toString());
                            return token;
                        }
                    } else if (data[i] == '\\' && !backslash2) {
                        backslash2 = true;
                        string.append(data[i]);
                    }
                    else if (isString) {
                        if (backslash2) {
                            if (data[i] == 'n' || data[i] == 'r' || data[i] == 't' || data[i] == '\"') {
                                backslash2 = false;
                            } else {
                                operatorEq = false;
                                throw new SmartScriptParserException();
                            }
                        }

                        string.append(data[i]);
                    }else if(backslash2){
                        if (data[i] == 'n' || data[i] == 'r' || data[i] == 't' || data[i] == '\"') {
                            throw new SmartScriptParserException();
                        }


                    } else if (data[i]=='*' ||data[i]=='+' ||data[i]=='-' ||data[i]=='/' ||data[i]=='^') {
                        if(data[i]=='*'){
                        token = new RealToken(RealTokenType.OPERATOR, "*");
                        return token;}
                        if(data[i]=='+'){
                            token = new RealToken(RealTokenType.OPERATOR, "+");
                            return token;}
                        if(data[i]=='-'){
                            token = new RealToken(RealTokenType.OPERATOR, "-");
                            return token;}
                        if(data[i]=='/'){
                            token = new RealToken(RealTokenType.OPERATOR, "/");
                            return token;}
                        if(data[i]=='^'){
                            token = new RealToken(RealTokenType.OPERATOR, "^");
                            return token;}
                        
                    }else if (data[i] == '$' && (i != data.length - 1)) {
                        if (data[i + 1] == '}') {
                            currentIndex++;
                            operatorEq = false;
                            this.setState(RealLexerState.TEXT);

                            if(i+1== data.length-1) {this.token = new RealToken(RealTokenType.EOF, null);
                                return token;
                            };
                            return nextToken();
                        }
                    } else if (forend || i==data.length-1) {
                        this.token = new RealToken(RealTokenType.EOF, null);
                        return token;

                    }
                }else if (data[i] == '=') {
                    operatorEq = true;
                    token = new RealToken(RealTokenType.OPERATOR, "=");
                    return token;
                } else if (data[i] == 'F' || data[i] == 'f') {
                    if ((data[i + 1] == 'O' || data[i + 1] == 'o') && (data[i + 2] == 'R' || data[i + 2] == 'r')) {
                        currentIndex += 2;
                        operatorFOR = true;
                        token = new RealToken(RealTokenType.OPERATOR, "FOR");
                        return token;
                    }
                } else if (data[i] == 'E' || data[i] == 'e') {
                    if ((data[i + 1] == 'N' || data[i + 1] == 'n') && (data[i + 2] == 'd' || data[i + 2] == 'D')) {
                        currentIndex += 2;
                        token = new RealToken(RealTokenType.OPERATOR, "END");
                        return token;
                    }
                }else if (data[i] == '$' && i != data.length - 1) {
                    if (data[i + 1] == '}') {
                        if((elementcounter<2 || elementcounter>4) && operatorFOR)  throw new SmartScriptParserException();
                        currentIndex++;
                        operatorEq = false;
                        operatorFOR=false;
                        this.setState(RealLexerState.TEXT);
                        if(i+1== data.length-1) {this.token = new RealToken(RealTokenType.EOF, null);
                            return token;
                        };
                        return nextToken();
                    }

                }else if(data[i]==' '){

                }


            }


        }
        this.token = new RealToken(RealTokenType.EOF, null);
        currentIndex++;
        return token;
    }
    /**
     *
     * @return this token
     */
    public RealToken getToken() {
        return token;
    }

    /**
     *
     * @param state
     * @throws NullPointerException
     */
    public void setState(RealLexerState state) throws NullPointerException {
        if (state == null) throw new NullPointerException();
        this.state = state;
    }

    /**
     *
     * @return CURRENT realLExerState of this lexer
     */
    public RealLexerState getState() {
        return state;
    }
}
