package ocpp.cp;

import ocpp.cp._2012._06.ChargePointService;

/**
 * Created by bds on 09/11/2016.
 */
public abstract class ChargePointCommand {
    protected ChargePointService chargePointService = null;

    public abstract Object execute() throws Exception;
    public void setClient(ChargePointService client) {
        chargePointService = client;
    }
}
