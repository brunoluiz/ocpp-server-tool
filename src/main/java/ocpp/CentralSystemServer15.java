package ocpp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;
import javax.xml.ws.BindingType;
import javax.xml.ws.Endpoint;
import javax.xml.ws.soap.Addressing;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import ocpp.cs._2012._06.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.DateUtil;

@WebService(endpointInterface = "ocpp.cs._2012._06.CentralSystemService")
@BindingType(value = "http://www.w3.org/2003/05/soap/bindings/HTTP/")
@SOAPBinding(style = Style.DOCUMENT, use = Use.LITERAL)
@Addressing(enabled = true, required = true)
public class CentralSystemServer15 implements CentralSystemService {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private List<Integer> activeTransactions = new ArrayList<>();

    @Inject
    public CentralSystemServer15(@Named("centralsystem.endpoint") String centralSystemServerEndpoint) {
        log.info("Starting central system OCPP server @ {}", centralSystemServerEndpoint);

        Endpoint endpoint = Endpoint.publish(centralSystemServerEndpoint, this);
        if (!endpoint.isPublished()) {
            log.error("Could not publish central system OCPP server!");
        } else {
            log.info("Central system OCPP server is published!");
        }
    }

    private Integer generateTransactionId() {
        return (int) (System.currentTimeMillis() / 1000L);
    }

    private void logReceivedRequest(Object request) {
        log.info("Received " + request.getClass().getName());
    }

    @Override
    public AuthorizeResponse authorize(AuthorizeRequest parameters, String chargeBoxId) {
        logReceivedRequest(parameters);

        // It will authorize any tag
        IdTagInfo tagInfo = new IdTagInfo();
        String userTag = parameters.getIdTag();
        if (userTag.equals(""))
            tagInfo.setStatus(AuthorizationStatus.BLOCKED);
        else
            tagInfo.setStatus(AuthorizationStatus.ACCEPTED);

        AuthorizeResponse response = new AuthorizeResponse();
        response.setIdTagInfo(tagInfo);

        return response;
    }

    @Override
    public BootNotificationResponse bootNotification(BootNotificationRequest parameters, String chargeBoxId) {
        logReceivedRequest(parameters);

        BootNotificationResponse response = new BootNotificationResponse();
        response.setStatus(RegistrationStatus.ACCEPTED);
        response.setCurrentTime(DateUtil.getCurrentDate());
        response.setHeartbeatInterval(30);

        return response;
    }

    @Override
    public DataTransferResponse dataTransfer(DataTransferRequest parameters, String chargeBoxId) {
        logReceivedRequest(parameters);

        log.info("{}-{}: {}", parameters.getVendorId(), parameters.getMessageId(), parameters.getData());

        DataTransferResponse response = new DataTransferResponse();
        response.setData("");
        response.setStatus(DataTransferStatus.ACCEPTED);

        return response;
    }

    @Override
    public DiagnosticsStatusNotificationResponse diagnosticsStatusNotification(
            DiagnosticsStatusNotificationRequest parameters, String chargeBoxId) {
        logReceivedRequest(parameters);

        log.info("Status: {}", parameters.getStatus());

        return new DiagnosticsStatusNotificationResponse();
    }

    @Override
    public FirmwareStatusNotificationResponse firmwareStatusNotification(FirmwareStatusNotificationRequest parameters, String chargeBoxId) {
        logReceivedRequest(parameters);

        log.info("Status: {}", parameters.getStatus());

        return new FirmwareStatusNotificationResponse();
    }

    @Override
    public HeartbeatResponse heartbeat(HeartbeatRequest parameters, String chargeBoxId) {
        logReceivedRequest(parameters);

        HeartbeatResponse response = new HeartbeatResponse();
        response.setCurrentTime(DateUtil.getCurrentDate());

        return response;
    }

    @Override
    public MeterValuesResponse meterValues(MeterValuesRequest parameters, String chargeBoxId) {
        log.trace("Received " + MeterValuesRequest.class.getName());

        List<MeterValue> meterValues = parameters.getValues();
        for (Iterator<MeterValue> it_meterValues = meterValues.iterator(); it_meterValues.hasNext(); ) {
            MeterValue value = it_meterValues.next();
            List<MeterValue.Value> measures = value.getValue();
            for (Iterator<MeterValue.Value> it_measures = measures.iterator(); it_measures.hasNext(); ) {
                MeterValue.Value measure = it_measures.next();
                log.trace("MeterValue {}: {} {}\n",
                        measure.getMeasurand(),
                        measure.getValue(),
                        measure.getUnit()
                );
            }
        }

        return new MeterValuesResponse();
    }

    @Override
    public StartTransactionResponse startTransaction(StartTransactionRequest parameters, String chargeBoxId) {
        logReceivedRequest(parameters);

        IdTagInfo tagInfo = new IdTagInfo();
        tagInfo.setStatus(AuthorizationStatus.ACCEPTED);

        Integer transactionId = generateTransactionId();
        activeTransactions.add(transactionId);

        StartTransactionResponse response = new StartTransactionResponse();
        response.setIdTagInfo(tagInfo);
        response.setTransactionId(transactionId);

        log.info("{}: transaction id = {}", getClass().getSimpleName(), transactionId);

        return response;
    }

    @Override
    public StatusNotificationResponse statusNotification(StatusNotificationRequest parameters, String chargeBoxId) {
        logReceivedRequest(parameters);

        log.info("Connector: {}", parameters.getConnectorId());
        log.info("- Status: {}", parameters.getStatus());
        log.info("- Error code: {}", parameters.getErrorCode());
        log.info("- Info: {}", parameters.getInfo());

        if (parameters.getErrorCode().equals(ChargePointErrorCode.OTHER_ERROR)) {
            log.info("- Vendor Id: {}", parameters.getVendorId());
            log.info("- Vendor Error Code: {}", parameters.getVendorErrorCode());
        }

        return new StatusNotificationResponse();
    }

    @Override
    public StopTransactionResponse stopTransaction(StopTransactionRequest parameters, String chargeBoxId) {
        logReceivedRequest(parameters);

        Integer transactionId = parameters.getTransactionId();
        IdTagInfo tagInfo = new IdTagInfo();
        if (!activeTransactions.contains(transactionId)) {
            tagInfo.setStatus(AuthorizationStatus.INVALID);
        } else {
            activeTransactions.remove(transactionId);
        }

        tagInfo.setStatus(AuthorizationStatus.ACCEPTED);

        StopTransactionResponse response = new StopTransactionResponse();
        response.setIdTagInfo(tagInfo);
        return response;
    }

}