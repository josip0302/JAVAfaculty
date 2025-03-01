package hr.fer.oprpp1.hw04.db;

public class QueryLexer {
    ConditionalExpression token;
     char []data;
    int counter;
    public QueryLexer(String documentBody) {
        this.data = documentBody.toCharArray();
        counter=0;
    }

    /**
     * @return next query condition (Conditional expression)
     */
    public ConditionalExpression nextToken(){
        token=null;
        IFieldValueGetter fieldGetter=null;
        IComparisonOperator comparisonOperator=null;
        boolean isString=false;
        StringBuilder reference=new StringBuilder();
        for(int i=counter;i<data.length;i++){

            if(data[i]=='q' && data.length>i+1 && !isString){
                if(data[i+1]=='u' && data[i+2]=='e' && data[i+3]=='r' && data[i+4]=='y'){
                    counter+=4;
                    i+=4;
                }
            }else if(data[i]=='j'&& !isString){
                if(data[i+1]=='m' && data[i+2]=='b' && data[i+3]=='a' && data[i+4]=='g'){
                    fieldGetter=FieldValueGetters.JMBAG;
                    counter+=4;
                    i+=4;
                }
            }else if(data[i]=='l'&& !isString){
                if(data[i+1]=='a' && data[i+2]=='s' && data[i+3]=='t' && data[i+4]=='N'){
                    fieldGetter=FieldValueGetters.LAST_NAME;
                    counter+=7;
                    i+=7;
                }
            }
            else if(data[i]=='f'&& !isString){
                if(data[i+1]=='i' && data[i+2]=='r' && data[i+3]=='s' && data[i+4]=='t'){
                    fieldGetter=FieldValueGetters.FIRST_NAME;
                    counter+=8;
                    i+=8;
                }
            }else if(data[i]=='=' && !isString){
                comparisonOperator = ComparisonOperators.EQUALS;
            }else if(data[i]=='>' && !isString){
                if(data[i+1]=='='){
                    i++;
                    counter++;
                    comparisonOperator = ComparisonOperators.GREATER_OR_EQUALS;
                }else {
                    comparisonOperator = ComparisonOperators.GREATER;
                }
            }else if(data[i]=='<' && !isString){
                if(data[i+1]=='='){
                    i++;
                    counter++;
                    comparisonOperator = ComparisonOperators.LESS_OR_EQUALS;
                }else {
                    comparisonOperator = ComparisonOperators.LESS;
                }
            }else if (data[i]=='L' && !isString) {
                if(data[i+1]=='I'&&data[i+2]=='K'&&data[i+3]=='E'){
                    counter+=3;
                    i+=3;
                    comparisonOperator = ComparisonOperators.LIKE;
                }

            } else if (data[i]=='\"') {
                if(!isString) {
                    isString = true;
                }else {
                    counter++;
                   this.token=new ConditionalExpression(fieldGetter,reference.toString(),comparisonOperator);
                   break;
                }
            } else if (isString) {
                reference.append(data[i]);
            } else if (data[i]=='A'||data[i]=='a') {
                if((data[i+1]=='N'||data[i+1]=='n')&&(data[i+1]=='D'||data[i+1]=='d')){
                    counter+=2;
                    i+=2;
                }

            }
            counter++;
        }
        return token;


    }

    /**
     * @return this ConditionalExpression token
     */
    public ConditionalExpression getToken(){
        return token;

    }
}
