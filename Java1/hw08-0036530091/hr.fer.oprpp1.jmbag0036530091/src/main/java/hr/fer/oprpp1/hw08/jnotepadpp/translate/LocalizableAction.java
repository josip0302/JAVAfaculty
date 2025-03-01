package hr.fer.oprpp1.hw08.jnotepadpp.translate;

import javax.swing.*;

public abstract class LocalizableAction extends AbstractAction {
    private static final long serialVersionUID = 1L;
    String login;
    public LocalizableAction(String login, FormLocalizationProvider flp) {
        this.login=login;
        putValue(login,flp.getString(login));

    }


}
