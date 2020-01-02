package com.example.heitorcolangelo.espressotests.espressoversao2.robot;

import com.example.heitorcolangelo.espressotests.R;
import com.example.heitorcolangelo.espressotests.common.ScreenRobot;

import static com.example.heitorcolangelo.espressotests.espressoversao2.constants.ConstantsVersao2.*;

public class RobotsVersao2 extends ScreenRobot<RobotsVersao2> {

    private static final int IMAGEM_LOGIN = R.id.login_image;
    private static final int CAMPO_EMAIL = R.id.login_username;
    private static final int CAMPO_SENHA = R.id.login_password;
    private static final int BTN_LOGIN = R.id.login_button;
    private static final int RECYCLER_VIEW = R.id.recycler_view;

    public RobotsVersao2 verificarElementoImagem() {
        return checkIsDisplayed(IMAGEM_LOGIN);
    }

    public RobotsVersao2 verificarCampoLogin() {
        return checkViewHasHint(CAMPO_EMAIL, HINT_EMAIL);
    }

    public RobotsVersao2 verificarCampoSenha() {
        return checkViewHasHint(CAMPO_SENHA, HINT_SENHA);
    }

    public RobotsVersao2 verificarBotaoLogin() {
        return checkIsDisplayed(BTN_LOGIN);
    }

    public RobotsVersao2 verificarTituloNaTelaDeLogin() {
        return checkViewContainsText(TITLE_LOGIN);
    }

    public RobotsVersao2 clicarBtnLogin() {
        return clickOnView(BTN_LOGIN);
    }

    public RobotsVersao2 verificarPopup() {
        return checkDialogWithTextIsDisplayed(DIALOG_TITLE)
                .checkDialogWithTextIsDisplayed(DIALOG_MESSAGE);
    }

    public RobotsVersao2 escreverEmail() {
        return enterTextIntoView(CAMPO_EMAIL, EMAIL);
    }

    public RobotsVersao2 escreverSenha() {
        return enterTextIntoView(CAMPO_SENHA, SENHA);
    }

    public RobotsVersao2 verificarTituloTelaDeUsuarios() {
        return checkViewContainsText(TITLE_LIST_OF_USER);
    }

    public RobotsVersao2 clicarEmVoltar() {
        return pressBack();
    }

    public RobotsVersao2 scrolarParaBaixo() {
        return scrollViewDown(RECYCLER_VIEW);
    }
}
