package ocpp;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import ocpp.cp._2012._06.*;
import org.slf4j.LoggerFactory;

import java.net.URL;

public class CentralSystemClient15 implements ChargePointService {
	
	private static String CHARGE_POINT_WSDL_LOCATION = "wsdl/ocpp_chargepointservice_1.5_final.wsdl"; // FIXME
	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(CentralSystemClient15.class);

	private String centralSystemServerEndpoint = "";
	private ChargePointService chargePointService = null;
	
	@Inject
	public CentralSystemClient15(@Named("ChargePointServerEndpoint") String centralSystemServerEndpoint) {
		LOGGER.info("Starting Central System OCPP client @ {}}", centralSystemServerEndpoint);
		this.centralSystemServerEndpoint = centralSystemServerEndpoint;
		setup();
	}
	
	private void setup() {
		try {
			URL url = CentralSystemClient15.class.getClassLoader().getResource(CHARGE_POINT_WSDL_LOCATION);
			chargePointService = new ChargePointService_Service(url).getChargePointServiceSoap12();
			
			BindingProvider bp = (BindingProvider)chargePointService;
			bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, centralSystemServerEndpoint);

			LOGGER.info("Central System OCPP client has connected!");
		} catch (Exception e) {
			LOGGER.error("Could not connect Central System OCPP Client!");
			e.printStackTrace();
		}
	}

	private void logSendRequest(Object request) {
		LOGGER.info("Sending "+request.getClass().getName());
	}

	public UnlockConnectorResponse unlockConnector(UnlockConnectorRequest parameters, String chargeBoxId) {
		logSendRequest(parameters);
		return null;
	}


	public ResetResponse reset(ResetRequest parameters, String chargeBoxId) {
		logSendRequest(parameters);
		return null;
	}


	public ChangeAvailabilityResponse changeAvailability(ChangeAvailabilityRequest parameters, String chargeBoxId) {
		logSendRequest(parameters);
		return null;
	}


	public GetDiagnosticsResponse getDiagnostics(GetDiagnosticsRequest parameters, String chargeBoxId) {
		logSendRequest(parameters);
		return null;
	}


	public ClearCacheResponse clearCache(ClearCacheRequest parameters, String chargeBoxId) {
		logSendRequest(parameters);
		return null;
	}


	public UpdateFirmwareResponse updateFirmware(UpdateFirmwareRequest parameters, String chargeBoxId) {
		logSendRequest(parameters);
		return null;
	}


	public ChangeConfigurationResponse changeConfiguration(ChangeConfigurationRequest parameters, String chargeBoxId) {
		logSendRequest(parameters);
		return null;
	}


	public RemoteStartTransactionResponse remoteStartTransaction(RemoteStartTransactionRequest parameters, String chargeBoxId) {
		logSendRequest(parameters);
		RemoteStartTransactionResponse response = chargePointService.remoteStartTransaction(parameters, "");

		return response;
	}


	public RemoteStopTransactionResponse remoteStopTransaction(RemoteStopTransactionRequest parameters, String chargeBoxId) {
		logSendRequest(parameters);
		RemoteStopTransactionResponse response = chargePointService.remoteStopTransaction(parameters, "");

		return response;
	}


	public CancelReservationResponse cancelReservation(CancelReservationRequest parameters, String chargeBoxId) {
		logSendRequest(parameters);
		return null;
	}


	public DataTransferResponse dataTransfer(DataTransferRequest parameters, String chargeBoxId) {
		logSendRequest(parameters);
		DataTransferResponse response = chargePointService.dataTransfer(parameters, "");

		LOGGER.info("Data transfer:" + response.getStatus().toString() + " - " + response.getData());
		return response;
	}


	public GetConfigurationResponse getConfiguration(GetConfigurationRequest parameters, String chargeBoxId) {
		logSendRequest(parameters);
		return null;
	}

	public GetLocalListVersionResponse getLocalListVersion(GetLocalListVersionRequest parameters, String chargeBoxId) {
		logSendRequest(parameters);
		return null;
	}


	public ReserveNowResponse reserveNow(ReserveNowRequest parameters, String chargeBoxId) {
		logSendRequest(parameters);
		return null;
	}


	public SendLocalListResponse sendLocalList(SendLocalListRequest parameters, String chargeBoxId) {
		logSendRequest(parameters);
		return null;
	}
}