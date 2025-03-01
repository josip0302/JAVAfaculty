package hr.fer.oprpp1.hw08.jnotepadpp.translate;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocalizationProvider extends AbstractLocalizationProvider{
    private static final LocalizationProvider instance=new LocalizationProvider();
    private String language;
    private ResourceBundle bundle;
    private LocalizationProvider(){
        super();
        language=null;


    }



    public static LocalizationProvider getInstance(){
        return instance;
    }

    public void setLanguage(String language) {
        this.language = language;
        Locale locale = Locale.forLanguageTag(language);
        this.bundle =ResourceBundle.getBundle("prijevodi", locale);
        this.fire();
    }

    @Override
    public String getString(String key) {
        return this.bundle.getString(key);
    }

    @Override
    public String getLanguage() {
        return language;
    }
}
