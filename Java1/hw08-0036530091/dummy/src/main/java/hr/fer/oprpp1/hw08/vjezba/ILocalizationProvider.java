package hr.fer.oprpp1.hw08.vjezba;

public interface ILocalizationProvider {
    String getString(String key);
    String getLanguage();
    void addLocalizationListener(ILocalizationListener listener);
    void removeLocalizationListener(ILocalizationListener listener);

}
