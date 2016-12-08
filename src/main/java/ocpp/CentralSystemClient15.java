package ocpp;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import ocpp.cp._2012._06.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.ws.BindingProvider;
import java.net.URL;

public class CentralSystemClient15 implements ChargePointService {
	
	private static String CHARGE_POINT_WSDL_LOCATION = "wsdl/ocpp_chargepointservice_1.5_final.wsdl"; // FIXME
	private static final Logger log = LoggerFactory.getLogger(CentralSystemClient15.class);

	private String centralSystemServerEndpoint = "";
	private ChargePointService chargePointService = null;
	
	@Inject
	public CentralSystemClient15(@Named("ChargePointServerEndpoint") String centralSystemServerEndpoint) {
		log.info("Starting Central System OCPP client @ {}}", centralSystemServerEndpoint);

        this.centralSystemServerEndpoint = centralSystemServerEndpoint;
        try {
            URL url = CentralSystemClient15.class.getClassLoader().getResource(CHARGE_POINT_WSDL_LOCATION);
            chargePointService = new ChargePointService_Service(url).getChargePointServiceSoap12();

            BindingProvider bp = (BindingProvider)chargePointService;
            bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, centralSystemServerEndpoint);

            log.info("Central System OCPP client has connected!");
        } catch (Exception e) {
            log.error("Could not connect Central System OCPP Client!");
            e.printStackTrace();
        }
	}

	private void logSendRequest(Object request) {
		log.info("Sending "+request.getClass().getName());
	}

	@Override
	public UnlockConnectorResponse unlockConnector(UnlockConnectorRequest parameters, String chargeBoxId) {
		logSendRequest(parameters);
		return chargePointService.unlockConnector(parameters, chargeBoxId);
	}

	@Override
	public ResetResponse reset(ResetRequest parameters, String chargeBoxId) {
		logSendRequest(parameters);
		return chargePointService.reset(parameters, chargeBoxId);
	}

	@Override
	public ChangeAvailabilityResponse changeAvailability(ChangeAvailabilityRequest parameters, String chargeBoxId) {
		logSendRequest(parameters);
		return chargePointService.changeAvailability(parameters, chargeBoxId);
	}

	@Override
	public GetDiagnosticsResponse getDiagnostics(GetDiagnosticsRequest parameters, String chargeBoxId) {
		logSendRequest(parameters);
		return chargePointService.getDiagnostics(parameters, chargeBoxId);
	}

	@Override
	public ClearCacheResponse clearCache(ClearCacheRequest parameters, String chargeBoxId) {
		logSendRequest(parameters);
		return chargePointService.clearCache(parameters, chargeBoxId);
	}

	@Override
	public UpdateFirmwareResponse updateFirmware(UpdateFirmwareRequest parameters, String chargeBoxId) {
		logSendRequest(parameters);
		return chargePointService.updateFirmware(parameters, chargeBoxId);
	}

	@Override
	public ChangeConfigurationResponse changeConfiguration(ChangeConfigurationRequest parameters, String chargeBoxId) {
		logSendRequest(parameters);
		return chargePointService.changeConfiguration(parameters, chargeBoxId);
	}

	@Override
	public RemoteStartTransactionResponse remoteStartTransaction(RemoteStartTransactionRequest parameters, String chargeBoxId) {
		logSendRequest(parameters);
		return chargePointService.remoteStartTransaction(parameters, chargeBoxId);
	}

	@Override
	public RemoteStopTransactionResponse remoteStopTransaction(RemoteStopTransactionRequest parameters, String chargeBoxId) {
		logSendRequest(parameters);
		return chargePointService.remoteStopTransaction(parameters, chargeBoxId);
	}

	@Override
	public CancelReservationResponse cancelReservation(CancelReservationRequest parameters, String chargeBoxId) {
		logSendRequest(parameters);
		return chargePointService.cancelReservation(parameters, chargeBoxId);
	}

	@Override
	public DataTransferResponse dataTransfer(DataTransferRequest parameters, String chargeBoxId) {
		logSendRequest(parameters);
		return chargePointService.dataTransfer(parameters, chargeBoxId);
	}

	@Override
	public GetConfigurationResponse getConfiguration(GetConfigurationRequest parameters, String chargeBoxId) {
		logSendRequest(parameters);
		return chargePointService.getConfiguration(parameters, chargeBoxId);
	}

	@Override
	public GetLocalListVersionResponse getLocalListVersion(GetLocalListVersionRequest parameters, String chargeBoxId) {
		logSendRequest(parameters);
		return chargePointService.getLocalListVersion(parameters, chargeBoxId);
	}

	@Override
	public ReserveNowResponse reserveNow(ReserveNowRequest parameters, String chargeBoxId) {
		logSendRequest(parameters);
		return chargePointService.reserveNow(parameters, chargeBoxId);
	}

	@Override
	public SendLocalListResponse sendLocalList(SendLocalListRequest parameters, String chargeBoxId) {
		logSendRequest(parameters);
		return chargePointService.sendLocalList(parameters, chargeBoxId);
	}
}