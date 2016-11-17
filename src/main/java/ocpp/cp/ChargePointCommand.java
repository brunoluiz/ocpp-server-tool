package ocpp.cp;

import ocpp.OcppCommand;
import ocpp.cp._2012._06.ChargePointService;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;

/**
 * Created by bds on 09/11/2016.
 */
public abstract class ChargePointCommand implements OcppCommand {
    protected ChargePointService chargePointService = null;
    protected String chargeBoxId = "1";

    protected ChargePointCommand(String parameters) throws Exception {
        parseParameters(parameters);
    }

    protected abstract void parseParameters(String parameters) throws Exception;
    public abstract Object execute() throws Exception;

    public void setClient(Object client) {
        chargePointService = (ChargePointService)client;
    }

    protected void printHelp(Options options) {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp(getClass().getSimpleName(), options );
    }
}
