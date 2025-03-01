package hr.fer.oprpp1.hw04.db;

public interface IFilter {

    /**
     * @param record
     * @return true if record passes filter
     */
    public boolean accepts(StudentRecord record);
}
