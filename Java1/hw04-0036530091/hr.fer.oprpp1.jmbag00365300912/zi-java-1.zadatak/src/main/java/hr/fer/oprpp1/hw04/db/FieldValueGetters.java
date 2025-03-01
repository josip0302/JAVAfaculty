package hr.fer.oprpp1.hw04.db;

public class FieldValueGetters {
    public static final IFieldValueGetter FIRST_NAME = new IFieldValueGetter() {

        /**
         * @param record
         * @return returns first name from record
         */
        @Override
        public String get(StudentRecord record) {
            return record.getFirstName();
        }
    };
    public static final IFieldValueGetter LAST_NAME=new IFieldValueGetter() {

        /**
         * @param record
         * @return returns last name from student record
         */
        @Override
        public String get(StudentRecord record) {
            return record.getLastName();
        }
    };
    public static final IFieldValueGetter JMBAG=new IFieldValueGetter() {

        /**
         * @param record
         * @return jmbag from Student record
         */
        @Override
        public String get(StudentRecord record) {
            return record.getJmbag();
        }
    };
}
