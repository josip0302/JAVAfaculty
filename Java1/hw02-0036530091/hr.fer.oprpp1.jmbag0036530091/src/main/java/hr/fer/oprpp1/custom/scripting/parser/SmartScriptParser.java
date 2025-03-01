package hr.fer.oprpp1.custom.scripting.parser;

import hr.fer.oprpp1.custom.scripting.elems.Element;
import hr.fer.oprpp1.custom.scripting.elems.ElementString;
import hr.fer.oprpp1.custom.scripting.elems.ElementVariable;
import hr.fer.oprpp1.custom.scripting.lexer.RealLexer;
import hr.fer.oprpp1.custom.scripting.lexer.RealLexerState;
import hr.fer.oprpp1.custom.scripting.lexer.RealToken;
import hr.fer.oprpp1.custom.scripting.lexer.RealTokenType;
import hr.fer.oprpp1.custom.scripting.nodes.DocumentNode;
import hr.fer.oprpp1.custom.scripting.nodes.EchoNode;
import hr.fer.oprpp1.custom.scripting.nodes.ForLoopNode;
import hr.fer.oprpp1.custom.scripting.nodes.TextNode;

import javax.print.DocFlavor;

public class SmartScriptParser {
    private String DocumentBody;
    RealLexer lexer;
    DocumentNode documentNode;
    public SmartScriptParser(String documentBody) {
        DocumentBody = documentBody;
        lexer=new RealLexer(documentBody);
        documentNode=new DocumentNode();
        Parse();

    }

    /**
     * does all parsing procces for parser
     */
    private void Parse() {
        RealToken token;
        boolean forLoop=false;
        ForLoopNode forNode=new ForLoopNode(new ElementVariable(""), new ElementString(""),
                new ElementString(""), new ElementString(""));
        do {
             token=lexer.nextToken();
             if(token.getValue()!=null) {
                 if (lexer.getState() == RealLexerState.TEXT && !forLoop) {

                     documentNode.addChildNode(new TextNode(token.getValue().toString()));
                 } else if(forLoop) {
                     if(token.getValue()=="END"){
                         forNode.addChildNode(new TextNode("{$END$}"));
                         forLoop=false;
                         documentNode.addChildNode(forNode);
                     }else {
                         if(token.getType()==RealTokenType.TEXT){
                             forNode.addChildNode(new TextNode(token.getValue().toString()));

                         }else {
                             Element[] elements=new Element[1];
                                     elements[0]=new ElementString(token.getValue().toString());
                             forNode.addChildNode(new EchoNode(elements));
                         }
                     }
                 }
                 else {
                     if(token.getType()==RealTokenType.OPERATOR){
                         if(token.getValue()=="FOR"){
                             token=lexer.nextToken();
                             String a=token.getValue().toString();
                             token=lexer.nextToken();
                             String b=token.getValue().toString();
                             token=lexer.nextToken();
                             String c=token.getValue().toString();
                             token=lexer.nextToken();
                             String d=token.getValue().toString();
                             if(lexer.getState()==RealLexerState.TAG) {
                                 forNode=new ForLoopNode(new ElementVariable(a), new ElementString(b),
                                         new ElementString(c), new ElementString(d));
                             }else {
                                 forNode=new ForLoopNode(new ElementVariable(a), new ElementString(b),
                                         new ElementString(c));
                                 if(d!="END"){
                                     if(token.getType()==RealTokenType.TEXT){

                                     }
                                 }
                             }
                             forLoop=true;
                         }
                     }
                 }
             }
           // documentNode.addChildNode();
        }while (token.getType()!= RealTokenType.EOF);
    }

    /**
     *
     * @return text stored in the documentNode
     */
    @Override
    public String toString() {

        return documentNode.toString();
    }
}
