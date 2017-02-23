package app;

import chargesystem.UserInputManager;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class AppInjector extends AbstractModule {
    private static final Logger log = LoggerFactory.getLogger(AppInjector.class);
    private Properties props = new Properties();

    private void configureConstantsBindings() {
        bindConstant()
                .annotatedWith(Names.named("chargepoint.endpoint"))
                .to(props.getProperty("chargepoint.endpoint", "http://192.168.1.2:9000/ocpp/ChargePoint"));

        bindConstant()
                .annotatedWith(Names.named("centralsystem.endpoint"))
                .to(props.getProperty("centralsystem.endpoint", "http://0.0.0.0:9000/ocpp/CentralSystem"));

        bindConstant().annotatedWith(Names.named("ocpp.period"))
                .to(Integer.parseInt(props.getProperty("ocpp.period", "100")));

        bindConstant().annotatedWith(Names.named("ocpp.retries"))
                .to(Integer.parseInt(props.getProperty("ocpp.retries", "0")));
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
        try {
            File jarPath = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath());
            String propertiesPath = jarPath.getParentFile().getAbsolutePath();
            props.load(new FileInputStream(propertiesPath+"/settings.properties"));
        } catch (Exception e) {
            log.error("No settings.properties found on the root folder! Exiting...");
            System.exit(1);
            return ;
        }

        configureConstantsBindings();
        configureOcppBindings();
        configureSingletonBindings();

        install(new FactoryModuleBuilder().build(ChargePointCommandFactory.class));
    }
}