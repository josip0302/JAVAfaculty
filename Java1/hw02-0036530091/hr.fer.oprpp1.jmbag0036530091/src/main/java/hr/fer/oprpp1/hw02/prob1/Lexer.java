package hr.fer.oprpp1.hw02.prob1;

public class Lexer {
    private char[] data; // ulazni tekst
    private Token token; // trenutni token
    private int currentIndex; // indeks prvog neobrađenog znaka
    private LexerState state;
    // konstruktor prima ulazni tekst koji se tokenizira
    public Lexer(String text) {
        if (text == null ) throw new NullPointerException();
        this.data = text.toCharArray();
        currentIndex = 0;
        state=LexerState.BASIC;
    }


    // generira i vraća sljedeći token
    // baca LexerException ako dođe do pogreške
    public Token nextToken() {
        int x = currentIndex;
        if (this.token != null && token.getType() == TokenType.EOF) {
            throw new LexerException();
        }
        if(this.state==LexerState.BASIC) {
            StringBuilder word = new StringBuilder(data.length);
            StringBuilder number = new StringBuilder(data.length);
            String symbol = "";
            boolean wasBackSlash = false;
            int longDigits = 0;
            for (int i = x; i < data.length; i++) {
                currentIndex++;
                if (Character.isLetter(data[i]) || wasBackSlash) {

                    if (wasBackSlash) {
                        wasBackSlash = false;
                        if (Character.isLetter(data[i])) throw new LexerException();

                    }
                    word.append(data[i]);


                    if (i == data.length - 1) {

                        token = new Token(TokenType.WORD, word.toString());
                        return token;
                    } else if (!Character.isLetter(data[i + 1]) && data[i + 1] != '\\') {

                        token = new Token(TokenType.WORD, word.toString());
                        return token;

                    }
                } else if (data[i] == '\\') {
                    wasBackSlash = true;
                    if (i == data.length - 1) throw new LexerException();

                } else if (data[i] == ' ' || data[i] == '\n' || data[i] == '\r' || data[i] == '\t') {

                } else if (Character.isDigit(data[i])) {

                    number.append(data[i]);
                    longDigits++;
                    if (longDigits >= 16) throw new LexerException();
                    if (i == data.length - 1) {
                        token = new Token(TokenType.NUMBER, Long.parseLong(number.toString()));
                        return token;
                    } else if (!Character.isDigit(data[i + 1]) || data[i + 1] == '\\' || data[i + 1] == '\n' || data[i + 1] == '\r' || data[i + 1] == '\t') {
                        token = new Token(TokenType.NUMBER, Long.parseLong(number.toString()));
                        return token;
                    }

                } else {
                    if(data[i]=='#')setState(LexerState.EXTENDED);
                    token = new Token(TokenType.SYMBOL, data[i]);
                    return token;
                }
            }

            this.token = new Token(TokenType.EOF, null);
            currentIndex++;
            return token;
        }else {
            StringBuilder all=new StringBuilder(data.length);
            for (int i = x; i < data.length; i++) {
                currentIndex++;
                if(data[i]=='#'){
                    all.append(data[i]);
                    this.state=LexerState.BASIC;
                    token = new Token(TokenType.SYMBOL,all.toString().charAt(0));
                    return token;

                }
                else if (data[i] == ' ' || data[i] == '\n' || data[i] == '\r' || data[i] == '\t') {}
                else {


                    all.append(data[i]);
                    if (i == data.length - 1) {
                        token = new Token(TokenType.WORD, all.toString());
                        return token;
                    }else if(i!=0){
                        if(data[i]=='a' && data[i-1]=='\\' || data[i+1]=='#'){
                            System.out.println(all.toString());
                            token = new Token(TokenType.WORD, all.toString());
                            return token;
                        }
                    }
                }


            }
            this.token = new Token(TokenType.EOF, null);
            currentIndex++;
            return token;

        }

    }

    // vraća zadnji generirani token; može se pozivati
    // više puta; ne pokreće generiranje sljedećeg tokena
    public Token getToken() {
        return token;
    }

    public void setState(LexerState state)  throws NullPointerException{
        if(state==null) throw new NullPointerException();
        this.state=state;
    }


}