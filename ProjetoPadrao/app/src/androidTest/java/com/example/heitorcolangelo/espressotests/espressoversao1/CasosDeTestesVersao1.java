package com.example.heitorcolangelo.espressotests.espressoversao1;

import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.heitorcolangelo.espressotests.R;
import com.example.heitorcolangelo.espressotests.ui.activity.LoginActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static android.support.test.espresso.matcher.RootMatchers.isDialog;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.example.heitorcolangelo.espressotests.util.ValidationUtils.clickViewWithId;
import static com.example.heitorcolangelo.espressotests.util.ValidationUtils.viewWithIdHasHint;
import static com.example.heitorcolangelo.espressotests.util.ValidationUtils.viewWithIdHasText;
import static com.example.heitorcolangelo.espressotests.util.ValidationUtils.viewWithIdIsDisplayed;
import static com.example.heitorcolangelo.espressotests.util.ValidationUtils.viewWithTextIsDisplayed;
import static org.hamcrest.Matchers.allOf;

/**
 * Created by Artur on 30/12/2019.
 */
@RunWith(AndroidJUnit4.class)
public class CasosDeTestesVersao1 {

    @Rule
    public ActivityTestRule<LoginActivity> loginRule = new ActivityTestRule<>(LoginActivity.class, false, true);

    @Test
    public void visualizarCamposIniciais() {
        checkarCamposIniciais();
    }

    @Test
    public void clicarBotaoLoginSemPreencherOsDados_falha_apresentadoDialog() {
        clickViewWithId(R.id.login_button);
        checkarDialogs();
    }

    @Test
    public void clicarBotaoLoginPreenchendoApenasNomeDoUsuario_falha_apresentadoDialog() {
        onView(withId(R.id.login_username)).perform(typeText("username"));
        // Sempre depois de um input de texto deve ser fechado o teclado
        closeSoftKeyboard();
        clickViewWithId(R.id.login_button);
        checkarDialogs();
    }

    @Test
    public void clicarBotaoLoginPreenchendoApenasSenha_falha_apresentadoDialog() {
        onView(withId(R.id.login_password)).perform(typeText("password"));
        // Sempre depois de um input de texto deve ser fechado o teclado
        closeSoftKeyboard();
        clickViewWithId(R.id.login_button);
        checkarDialogs();
    }

    @Test
    public void clicarBotaoLoginPreenchendoOsCampos_sucesso_apresentarTelaDeUsuarios() {
        logar();
        viewWithTextIsDisplayed(R.string.employees_list);
    }

    @Test
    public void clicarBotaoLoginPreenchendoOsCamposECliclarBotaoVoltar_sucesso_apresentarTelaDeUsuarios() {
        logar();
        pressBack();
        checkarCamposIniciais();
    }

    @Test
    public void telaDeUsuarioScrollarAListaDeUsuario() throws InterruptedException {
        logar();
//        onView(withId(R.id.recycler_view)).perform(swipeUp(), click());
        onView(withId(R.id.recycler_view)).perform(scrollToPosition(5));
        onView(withId(R.id.recycler_view))
                .check(matches(withViewAtPosition(5, hasDescendant(allOf(withId(R.id.user_view_image), isDisplayed())))));
    }

    private void checkarCamposIniciais() {
        viewWithIdIsDisplayed(R.id.login_image);
        viewWithIdIsDisplayed(R.id.login_username);
        viewWithIdIsDisplayed(R.id.login_password);
        viewWithIdIsDisplayed(R.id.login_button);
        viewWithTextIsDisplayed(R.string.app_name);

        viewWithIdHasHint(R.id.login_username, R.string.login_username);
        viewWithIdHasHint(R.id.login_password, R.string.login_password);
        viewWithIdHasText(R.id.login_button, R.string.login_button_text);
    }

    private void checkarDialogs() {
        onView(withText(R.string.important))
                .inRoot(isDialog())
                .check(matches(isDisplayed()));

        onView(withText(R.string.validation_message))
                .inRoot(isDialog())
                .check(matches(isDisplayed()));
    }

    private void logar() {
        onView(withId(R.id.login_username)).perform(typeText("username"));
        closeSoftKeyboard();
        onView(withId(R.id.login_password)).perform(typeText("password"));
        closeSoftKeyboard();
        clickViewWithId(R.id.login_button);
    }

    public static Matcher<View> withViewAtPosition(final int position, final Matcher<View> itemMatcher) {
        return new BoundedMatcher<View, RecyclerView>(RecyclerView.class) {
            @Override
            public void describeTo(Description description) {
                itemMatcher.describeTo(description);
            }

            @Override
            protected boolean matchesSafely(RecyclerView recyclerView) {
                final RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(position);
                return viewHolder != null && itemMatcher.matches(viewHolder.itemView);
            }
        };
    }
}
