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
public class DataTransferCommand implements OcppCommand {
    private final Logger log = LoggerFactory.getLogger(DataTransferCommand.class);

    private final ChargePointService chargePointService;
    private String chargeBoxId;
    private String messageId;
    private String vendorId;
    private String data;

    @Inject
    public DataTransferCommand(@Assisted String parameters,
                               ChargePointService chargePointService) throws Exception {
        this.chargePointService = chargePointService;
        parseParameters(parameters);
    }

    private void parseParameters(String parameters) throws Exception {
        // create Options object
        Options options = new Options();
        Option msgid = new Option("msgid", "messageid", true, "message id");
        msgid.setRequired(true);
        options.addOption(msgid);

        Option data = new Option("d", "data", true, "data");
        data.setRequired(true);
        options.addOption(data);

        Option vendorid = new Option("vid", "vendor", true, "vendor id");
        vendorid.setRequired(true);
        options.addOption(vendorid);

        Option cpid = new Option("cpid", "chargepoint", true, "charge point id");
        cpid.setRequired(true);
        options.addOption(cpid);

        CommandLineParser parser = new DefaultParser();
        String[] parametersOptions = parameters.split(" ");

        try {
            CommandLine cmd = parser.parse(options, parametersOptions);
            this.messageId = cmd.getOptionValue("msgid");
            this.data = cmd.getOptionValue("data");
            this.vendorId = cmd.getOptionValue("vid");
            this.chargeBoxId = cmd.getOptionValue("cpid");
        } catch (ParseException e) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp(getClass().getSimpleName(), options);
            throw e;
        }
    }

    @Override
    public Object execute() throws Exception {
        DataTransferRequest request = new DataTransferRequest();
        request.setMessageId(messageId);
        request.setData(data);
        request.setVendorId(vendorId);

        DataTransferResponse response = chargePointService.dataTransfer(request, chargeBoxId);

        if (response == null) {
            throw new Exception("Request unsuccessful... maybe the chargebox (id) is not connected");
        }

        log.info("Received status: {}", response.getStatus());
        if (response.getStatus().equals(DataTransferStatus.ACCEPTED)) {
            log.info(" - Data: {}", response.getData());
        }

        return response;
    }
}
