package chargesystem;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Created by bds on 09/11/2016.
 */
public class App {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new AppInjector());
        injector.getInstance(UserInputManager.class).start();
    }
}
