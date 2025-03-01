package hr.fer.oprpp1.hw08.vjezba;

public class LocalizationProviderBridge extends AbstractLocalizationProvider{
    private boolean connected;
    private String currentLanguage;
    private String rez;
    ILocalizationProvider provider;
    ILocalizationListener listener;
    public LocalizationProviderBridge(ILocalizationProvider provider) {
        this.provider = provider;
        currentLanguage=provider.getLanguage();
    }

    public void connect(){
      if(!connected){
          connected=true;
          if(!currentLanguage.equals(provider.getLanguage())){
              currentLanguage=provider.getLanguage();
              this.fire();
          }
          listener=this.list.get(0);

          provider.addLocalizationListener(()->this.fire());

      }
    }
    public void disconnect(){
        if(connected){

            provider=null;
        }

    }
    @Override
    public String getString(String key) {
        return this.provider.getString(key);
    }

    @Override
    public String getLanguage() {
        return currentLanguage;
    }
}
