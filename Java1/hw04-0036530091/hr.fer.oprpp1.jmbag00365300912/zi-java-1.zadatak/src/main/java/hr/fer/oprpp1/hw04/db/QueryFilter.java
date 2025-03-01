package hr.fer.oprpp1.hw04.db;

import javax.management.InvalidAttributeValueException;
import java.util.List;

public class QueryFilter implements IFilter{
    List<ConditionalExpression> list;

    public QueryFilter(List<ConditionalExpression> list) {
        this.list = list;
    }

    /**
     * @param record
     * @return checks if record passes all conditional expression if
     * record fails one or more tests returns false and true otherwise
     */
    @Override
    public boolean accepts(StudentRecord record) {
        for(ConditionalExpression expression:list){
           try {
              if(!expression.getComparisonOperator().satisfied(
                       expression.getFieldGetter().get(record),
                       expression.getStringLiteral())){
                  return false;
              }

            } catch (InvalidAttributeValueException e) {
                System.out.println(e.getMessage());
                return false;
            }

        }
        return true;
    }
}
