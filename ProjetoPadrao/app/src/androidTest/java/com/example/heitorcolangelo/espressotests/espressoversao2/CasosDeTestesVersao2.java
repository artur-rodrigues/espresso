package com.example.heitorcolangelo.espressotests.espressoversao2;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.heitorcolangelo.espressotests.R;
import com.example.heitorcolangelo.espressotests.espressoversao2.robot.RobotsVersao2;
import com.example.heitorcolangelo.espressotests.ui.activity.LoginActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.example.heitorcolangelo.espressotests.util.ValidationUtils.clickViewWithId;

/**
 * Formato de PageObjects
 * */
@RunWith(AndroidJUnit4.class)
public class CasosDeTestesVersao2 {

    private RobotsVersao2 robot;

    @Rule
    public ActivityTestRule<LoginActivity> loginRule = new ActivityTestRule<>(LoginActivity.class, false, true);

    @Before
    public void setUp() {
        robot = new RobotsVersao2();
    }

    @After
    public void after() {
        robot = null;
    }

    @Test
    public void visualizarCamposIniciais() {
        robot.verificarBotaoLogin()
                .verificarCampoLogin()
                .verificarCampoSenha()
                .verificarTituloNaTelaDeLogin()
                .verificarCampoLogin()
                .verificarCampoSenha()
                .verificarElementoImagem()
                .verificarBotaoLogin();
    }

    @Test
    public void clicarBotaoLoginSemPreencherOsDados_falha_apresentadoDialog() {
        robot.clicarBtnLogin()
                .verificarPopup();
    }

    @Test
    public void clicarBotaoLoginPreenchendoApenasNomeDoUsuario_falha_apresentadoDialog() {
        robot.escreverEmail()
                .clicarBtnLogin()
                .verificarPopup();
    }

    @Test
    public void clicarBotaoLoginPreenchendoApenasSenha_falha_apresentadoDialog() {
        robot.escreverSenha()
                .clicarBtnLogin()
                .verificarPopup();
    }

    @Test
    public void clicarBotaoLoginPreenchendoOsCampos_sucesso_apresentarTelaDeUsuarios() {
        robot.escreverEmail()
                .escreverSenha()
                .clicarBtnLogin()
                .verificarTituloTelaDeUsuarios();
    }

    @Test
    public void clicarBotaoLoginPreenchendoOsCamposECliclarBotaoVoltar_sucesso_apresentarTelaDeUsuarios() {
        robot.escreverEmail()
                .escreverSenha()
                .clicarBtnLogin()
                .clicarEmVoltar()
                .verificarTituloNaTelaDeLogin()
                .verificarCampoLogin()
                .verificarCampoSenha()
                .verificarElementoImagem()
                .verificarBotaoLogin();
    }

    @Test
    public void telaDeUsuarioScrollarAListaDeUsuario() throws InterruptedException {
        robot.escreverEmail()
                .escreverSenha()
                .clicarBtnLogin()
                .sleep()
                .scrolarParaBaixo()
                .sleep();
    }
}
