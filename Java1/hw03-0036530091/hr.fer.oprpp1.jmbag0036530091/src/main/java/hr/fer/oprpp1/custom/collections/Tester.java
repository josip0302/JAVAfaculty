package hr.fer.oprpp1.custom.collections;

public interface Tester<T> {
     /**
      *
      * @param obj
      * @return true if obj passed the test and false otherwise
      */
     boolean test(T obj);
}
