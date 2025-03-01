package hr.fer.oprpp1.hw08.jnotepadpp.translate;

public interface ILocalizationProvider {
    String getString(String key);
    String getLanguage();
    void addLocalizationListener(ILocalizationListener listener);
    void removeLocalizationListener(ILocalizationListener listener);

}
