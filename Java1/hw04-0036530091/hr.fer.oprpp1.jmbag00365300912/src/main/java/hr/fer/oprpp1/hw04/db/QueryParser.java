package hr.fer.oprpp1.hw04.db;

import java.util.ArrayList;
import java.util.List;

public class QueryParser {

    List<ConditionalExpression> list=new ArrayList<>();
    boolean isDirect;

    /**
     * @param input initalizes this query and collects all tokens and puts them to list
     * if query is direct sets isDirect to true and to false otherwise
     */
    public QueryParser(String input) {
        QueryLexer lexer=new QueryLexer(input);
        while (lexer.nextToken()!=null){
            list.add(lexer.getToken());
        }
        if(list.size()==1 && list.get(0).getFieldGetter()==FieldValueGetters.JMBAG){
            isDirect=true;
        }else {
            isDirect=false;
        }

    }

    /**
     * @return boolean isDirect
     */
    public boolean isDirectQuery(){
        return isDirect;

    }

    /**
     * @return  returns queried jmbag
     * @throws IllegalStateException if query is not direct
     */
   public String getQueriedJMBAG() throws IllegalStateException{
        if(!isDirect) throw new IllegalStateException();
        return list.get(0).getStringLiteral();

    }

    /**
     * @return list of conditional expression
     */
    public List<ConditionalExpression> getQuery(){
        return this.list;

    }

}
