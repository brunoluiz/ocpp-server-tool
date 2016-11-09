package chargesystem;

import ocpp.cp.CommandsDispatcher;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import ocpp.CentralSystemClient15;
import ocpp.CentralSystemServer15;
import ocpp.cp._2012._06.ChargePointService;
import ocpp.cs._2012._06.CentralSystemService;

public class AppInjector extends AbstractModule {
    public static String CHARGEPOINT_SERVER_ENDPOINT    = "http://192.168.7.2";
    public static String CHARGEPOINT_SERVER_PATH        = "/ocpp/ChargePoint";

    public static String CENTRALSYSTEM_SERVER_ENDPOINT  = "http://192.168.7.1:9000";
    public static String CENTRALSYSTEM_SERVER_PATH      = "/ocpp/CentralSystem";

    public static Integer OCPP_POOLING_PERIOD           = 500;

    protected void configureConstantsBindings() {
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

    protected void configureOcppBindings() {
        // Server bindings
        bind(ChargePointService.class)
                .annotatedWith(Names.named("CentralSystemClient"))
                .to(CentralSystemClient15.class)
                .asEagerSingleton();
        bind(CentralSystemService.class)
                .to(CentralSystemServer15.class)
                .asEagerSingleton();
    }

    protected void configureSingletonBindings() {
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