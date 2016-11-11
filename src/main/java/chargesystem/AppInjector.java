package chargesystem;

import ocpp.CommandsDispatcher;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import ocpp.CentralSystemClient15;
import ocpp.CentralSystemServer15;
import ocpp.cp._2012._06.ChargePointService;
import ocpp.cs._2012._06.CentralSystemService;

public class AppInjector extends AbstractModule {
    private static String CHARGEPOINT_SERVER_ENDPOINT    = "http://192.168.7.2:9000";
    private static String CHARGEPOINT_SERVER_PATH        = "/ocpp/ChargePoint";

    private static String CENTRALSYSTEM_SERVER_ENDPOINT  = "http://192.168.7.1:9000";
    private static String CENTRALSYSTEM_SERVER_PATH      = "/ocpp/CentralSystem";

    private static Integer OCPP_POOLING_PERIOD           = 500;

    private void configureConstantsBindings() {
        bindConstant()
                .annotatedWith(Names.named("ChargePointServerEndpoint"))
                .to(CHARGEPOINT_SERVER_ENDPOINT + CHARGEPOINT_SERVER_PATH);

        bindConstant()
                .annotatedWith(Names.named("CentralSystemServerEndpoint"))
                .to(CENTRALSYSTEM_SERVER_ENDPOINT + CENTRALSYSTEM_SERVER_PATH);

        bindConstant()
                .annotatedWith(Names.named("OcppPoolingPeriod"))
                .to(OCPP_POOLING_PERIOD);
    }

    private void configureOcppBindings() {
        bind(ChargePointService.class)
                .annotatedWith(Names.named("CentralSystemClient"))
                .to(CentralSystemClient15.class)
                .asEagerSingleton();
        bind(Object.class)
                .annotatedWith(Names.named("ClientService"))
                .to(CentralSystemClient15.class);

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
    }
}