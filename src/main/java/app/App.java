package app;

import chargesystem.UserInputManager;
import com.google.inject.Guice;
import com.google.inject.Injector;
import ocpp.CommandsDispatcher;

public class App {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new AppInjector());
        injector.getInstance(UserInputManager.class).start();
        injector.getInstance(CommandsDispatcher.class).start();
    }
}
