package ocpp.cp.commands;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import ocpp.OcppCommand;
import ocpp.cp._2012._06.*;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by bds on 09/12/2016.
 */
public class ClearCacheCommand implements OcppCommand {
    private final Logger log = LoggerFactory.getLogger(ClearCacheCommand.class);

    private final ChargePointService chargePointService;
    private String chargeBoxId = null;

    @Inject
    public ClearCacheCommand(@Assisted String parameters,
                                      ChargePointService chargePointService) throws Exception {
        this.chargePointService = chargePointService;
        parseParameters(parameters);
    }

    private void parseParameters(String parameters) throws Exception {
        // create Options object
        Options options = new Options();
        Option cpid = new Option("cpid", "chargepoint", true, "charge point id");
        cpid.setRequired(true);
        options.addOption(cpid);

        CommandLineParser parser = new DefaultParser();
        String[] parametersOptions = parameters.split(" ");

        try {
            CommandLine cmd = parser.parse(options, parametersOptions);
            this.chargeBoxId = cmd.getOptionValue("cpid");
        } catch (ParseException e) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp(getClass().getSimpleName(), options);
            throw e;
        }
    }

    public Object execute() throws Exception {
        ClearCacheRequest request = new ClearCacheRequest();

        ClearCacheResponse response = chargePointService.clearCache(request, chargeBoxId);

        if (response == null) {
            throw new Exception("Request unsuccessful... maybe the chargebox (id) is not connected");
        }

        log.info("Received status: {}", response.getStatus());

        return response;
    }
}