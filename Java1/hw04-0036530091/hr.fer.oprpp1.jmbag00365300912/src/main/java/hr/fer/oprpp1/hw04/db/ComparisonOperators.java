package hr.fer.oprpp1.hw04.db;

import javax.management.InvalidAttributeValueException;

/**
 * Operators for comparing guery elements
 */
public class ComparisonOperators {

    public static final IComparisonOperator LESS = new IComparisonOperator() {
        /**
         * @param value1
         * @param value2
         * @return true if value1 is less than value2 and false otherwise
         */
        @Override
        public boolean satisfied(String value1, String value2) {
            return value1.compareTo(value2)<0;
        }
    };
    public static final IComparisonOperator LESS_OR_EQUALS=new IComparisonOperator() {
        /**
         * @param value1
         * @param value2
         * @return true if value1 is less or equals than value2 and false otherwise
         */
        @Override
        public boolean satisfied(String value1, String value2) {
            return value1.compareTo(value2)<=0;
        }
    };
    public static final IComparisonOperator GREATER= new IComparisonOperator() {
        /**
         * @param value1
         * @param value2
         * @return true if value1 is greater than value2 and false otherwise
         */
        @Override
        public boolean satisfied(String value1, String value2) {
            return value1.compareTo(value2)>0;
        }
    };
    public static final IComparisonOperator GREATER_OR_EQUALS= new IComparisonOperator() {
        /**
         * @param value1
         * @param value2
         * @return true if value1 is greater or equals than value2 and false otherwise
         */
        @Override
        public boolean satisfied(String value1, String value2) {
            return value1.compareTo(value2)>=0;
        }
    };
    public static final IComparisonOperator EQUALS= new IComparisonOperator() {
        /**
         * @param value1
         * @param value2
         * @return true if value1 equals value2 and false otherwise
         */
        @Override
        public boolean satisfied(String value1, String value2) {
            return value1.equals(value2);
        }
    };
    public static final IComparisonOperator NOT_EQUALS= new IComparisonOperator() {
        /**
         * @param value1
         * @param value2
         * @return true if value1 equals value2 and false otherwise
         */
        @Override
        public boolean satisfied(String value1, String value2) {
            return !value1.equals(value2);
        }
    };
    public static final IComparisonOperator LIKE= new IComparisonOperator() {
        /**
         * @param value1
         * @param value2
         * @return true if value1 equals value2 with * operator which replaces strings
         * @throws InvalidAttributeValueException if value2 has multiple * operators
         */
        @Override
        public boolean satisfied(String value1, String value2) throws InvalidAttributeValueException {
            int count1=0;
            String newvalue1;
            String newvalue2;
            if(value1.charAt(0)=='*'){
                newvalue1=value1.replace("*", "");
                return value2.endsWith(newvalue1);
            }else if(value1.charAt(value1.length()-1)=='*'){
                newvalue1=value1.replace("*", "");
                return value2.startsWith(newvalue1);
            }else if(value2.charAt(0)=='*'){
                newvalue2=value2.replace("*", "");
                return value1.endsWith(newvalue2);
            }else if(value2.charAt(value2.length()-1)=='*'){
                newvalue2=value2.replace("*", "");
                return value1.startsWith(newvalue2);
            }

            for (int i = 0; i < value1.length(); i++) {
                if (value1.charAt(i) == '*') {
                    count1++;
                }
            }

            int count2=0;
            for (int i = 0; i < value2.length(); i++) {
                if (value2.charAt(i) == '*') {
                    count2++;
                }
            }

            if(count1==1){
                String[] polje=value1.split("\\*");
                return value2.startsWith(polje[0]) && value2.endsWith(polje[1]) && (value1.length()-1)<=value2.length();
            } else if (count1==0) {
                newvalue1=value1;
            }else {
                throw new InvalidAttributeValueException("second attribute can't contain more than one *");
            }
            if(count2==1){
                String[] polje=value2.split("\\*");
                return value1.startsWith(polje[0]) && value1.endsWith(polje[1]) && (value2.length()-1)<=value1.length();
            } else if (count2==0) {
                newvalue2=value2;
            }else {
                throw new InvalidAttributeValueException("second attribute can't contain more than one *");
            }
            return newvalue1.equals(newvalue2);
        }
    };

}
