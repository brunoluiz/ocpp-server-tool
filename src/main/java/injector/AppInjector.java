package injector;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import ocpp.CentralSystemClient15;
import ocpp.CentralSystemServer15;
import ocpp.cp._2012._06.ChargePointService;
import ocpp.cs._2012._06.CentralSystemService;

public class AppInjector extends AbstractModule {
    public static String CHARGEPOINT_SERVER_ENDPOINT    = "http://localhost";
    public static String CHARGEPOINT_SERVER_PATH        = "/ocpp/ChargePoint";

    public static String CENTRALSYSTEM_SERVER_ENDPOINT  = "http://localhost:9000";
    public static String CENTRALSYSTEM_SERVER_PATH      = "/";

    protected void configureConstantsBindings() {
        bindConstant()
                .annotatedWith(Names.named("ChargePointServerEndpoint"))
                .to(CHARGEPOINT_SERVER_ENDPOINT + CHARGEPOINT_SERVER_PATH);

        bindConstant()
                .annotatedWith(Names.named("CentralSystemServerEndpoint"))
                .to(CENTRALSYSTEM_SERVER_ENDPOINT + CENTRALSYSTEM_SERVER_PATH);
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

    }

    @Override
    protected void configure() {
        configureConstantsBindings();
        configureOcppBindings();
        configureSingletonBindings();
    }
}