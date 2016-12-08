package chargesystem;

import com.google.inject.assistedinject.FactoryModuleBuilder;
import ocpp.CommandsDispatcher;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import ocpp.CentralSystemClient15;
import ocpp.CentralSystemServer15;
import ocpp.cp.ChargePointCommandFactory;
import ocpp.cp._2012._06.ChargePointService;
import ocpp.cs._2012._06.CentralSystemService;

public class AppInjector extends AbstractModule {
    private void configureConstantsBindings() {
        bindConstant()
                .annotatedWith(Names.named("ChargePointServerEndpoint"))
                .to("http://192.168.7.2:9000/ocpp/ChargePoint");

        bindConstant()
                .annotatedWith(Names.named("CentralSystemServerEndpoint"))
                .to("http://192.168.7.1:9000/ocpp/CentralSystem");

        bindConstant()
                .annotatedWith(Names.named("OcppPoolingPeriod"))
                .to(500);
    }

    private void configureOcppBindings() {
        bind(ChargePointService.class)
                .to(CentralSystemClient15.class)
                .asEagerSingleton();
        bind(CentralSystemService.class)
                .to(CentralSystemServer15.class)
                .asEagerSingleton();
    }

    private void configureSingletonBindings() {
        bind(UserInputManager.class).in(Singleton.class);
        bind(CommandsDispatcher.class).in(Singleton.class);
    }

    @Override
    protected void configure() {
        configureConstantsBindings();
        configureOcppBindings();
        configureSingletonBindings();

        install(new FactoryModuleBuilder().build(ChargePointCommandFactory.class));
    }
}