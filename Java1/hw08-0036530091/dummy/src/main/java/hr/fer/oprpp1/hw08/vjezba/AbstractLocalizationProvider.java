package hr.fer.oprpp1.hw08.vjezba;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractLocalizationProvider implements ILocalizationProvider{
    List<ILocalizationListener> list;

    public AbstractLocalizationProvider() {

        this.list = new ArrayList<>();
    }

    @Override
    public void addLocalizationListener(ILocalizationListener listener) {
        list.add(listener);
    }

    @Override
    public void removeLocalizationListener(ILocalizationListener listener) {
      list.remove(listener);
    }
    void fire(){
        for (ILocalizationListener listener:list){
            listener.localizationChanged();
        }

    }
}
