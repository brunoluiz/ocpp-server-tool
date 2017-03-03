package ocpp.cp.commands;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import ocpp.OcppCommand;
import ocpp.cp._2012._06.*;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.DateUtil;
import utils.StringUtil;

import javax.xml.datatype.XMLGregorianCalendar;

public class ReserveNowCommand implements OcppCommand {
    private final Logger log = LoggerFactory.getLogger(ReserveNowCommand.class);

    private final ChargePointService chargePointService;
    private String chargeBoxId = null;
    private int connector;
    private String tag;
    private int reservation;
    private String parentTag;
    private XMLGregorianCalendar expireDate;

    @Inject
    public ReserveNowCommand(@Assisted String parameters,
                             ChargePointService chargePointService) throws Exception {
        this.chargePointService = chargePointService;
        parseParameters(parameters);
    }

    private void parseParameters(String parameters) throws Exception {
        // create Options object
        Options options = new Options();
        Option reservation = new Option("rid", "reservation", true, "reservation id");
        reservation.setRequired(true);
        options.addOption(reservation);

        Option tag = new Option("t", "tag", true, "tagid");
        tag.setRequired(true);
        options.addOption(tag);

        Option connector = new Option("c", "connector", true, "connector id");
        connector.setRequired(true);
        options.addOption(connector);

        Option expireDate = new Option("e", "expiredate", true, "expire date (in unix timestamp)");
        expireDate.setRequired(true);
        options.addOption(expireDate);

        Option parentTag = new Option("pt", "parenttag", true, "parent tag");
        parentTag.setRequired(false);
        options.addOption(parentTag);

        Option cpid = new Option("cpid", "chargepoint", true, "charge point id");
        cpid.setRequired(true);
        options.addOption(cpid);

        CommandLineParser parser = new DefaultParser();
        String[] parametersOptions = parameters.split(" ");

        try {
            CommandLine cmd = parser.parse(options, parametersOptions);
            this.reservation = Integer.parseInt(cmd.getOptionValue("reservation"));
            this.tag = cmd.getOptionValue("tag");
            this.connector = Integer.parseInt(cmd.getOptionValue("connector"));
            this.expireDate = DateUtil.parseDate(Long.valueOf(cmd.getOptionValue("expiredate")));
            this.parentTag = cmd.getOptionValue("parenttag");
            this.chargeBoxId = cmd.getOptionValue("cpid");
        } catch (ParseException e) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp(getClass().getSimpleName(), options);
            throw e;
        } catch (IllegalArgumentException e) {
            log.warn("'Type' argument is not valid!");
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public Object execute() throws Exception {
        ReserveNowRequest request = new ReserveNowRequest();
        request.setReservationId(reservation);
        request.setIdTag(tag);
        request.setConnectorId(connector);
        request.setExpiryDate(expireDate);
        request.setParentIdTag(parentTag);

        log.info("Expire date: {}-{}-{} {}:{}", expireDate.getDay(),
                expireDate.getMonth(),
                expireDate.getYear(),
                expireDate.getHour(),
                expireDate.getMinute());

        ReserveNowResponse response = chargePointService.reserveNow(request, chargeBoxId);

        if (response == null) {
            throw new Exception("Request unsuccessful... maybe the chargebox (id) is not connected");
        }

        log.info("Received status: {}", response.getStatus());

        return null;
    }
}
