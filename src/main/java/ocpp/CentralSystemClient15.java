package ocpp;

import javax.xml.ws.BindingProvider;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import ocpp.cp._2012._06.*;
import org.slf4j.LoggerFactory;

public class CentralSystemClient15 implements ChargePointService {
	
	private static String CHARGE_POINT_WSDL_LOCATION = "../../resources/wsdl/ocpp_chargepointservice_1.5_final.wsdl"; // FIXME
	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(CentralSystemClient15.class);

	protected String centralSystemServerEndpoint = "";
	protected ChargePointService chargePointService = null;
	
	@Inject
	public CentralSystemClient15(@Named("ChargePointServerEndpoint") String centralSystemServerEndpoint) {
		LOGGER.info("Starting Central System OCPP client @ {}}", centralSystemServerEndpoint);
		this.centralSystemServerEndpoint = centralSystemServerEndpoint;
		setup();
	}
	
	private void setup() {
		try {
			chargePointService = new ChargePointService_Service(getClass().getResource(CHARGE_POINT_WSDL_LOCATION))
					.getChargePointServiceSoap12();
			
			BindingProvider bp = (BindingProvider)chargePointService;
			bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, centralSystemServerEndpoint);

			LOGGER.info("Central System OCPP client has connected!");
		} catch (Exception e) {
			LOGGER.error("Could not connect Central System OCPP Client!");
			e.printStackTrace();
		}
	}


	public UnlockConnectorResponse unlockConnector(UnlockConnectorRequest parameters) {

		return null;
	}


	public ResetResponse reset(ResetRequest parameters) {

		return null;
	}


	public ChangeAvailabilityResponse changeAvailability(ChangeAvailabilityRequest parameters) {

		return null;
	}


	public GetDiagnosticsResponse getDiagnostics(GetDiagnosticsRequest parameters) {

		return null;
	}


	public ClearCacheResponse clearCache(ClearCacheRequest parameters) {

		return null;
	}


	public UpdateFirmwareResponse updateFirmware(UpdateFirmwareRequest parameters) {

		return null;
	}


	public ChangeConfigurationResponse changeConfiguration(ChangeConfigurationRequest parameters) {

		return null;
	}


	public RemoteStartTransactionResponse remoteStartTransaction(RemoteStartTransactionRequest parameters) {
		LOGGER.info("OCPP: sending 'remoteStartTransaction' request");
		RemoteStartTransactionResponse response = chargePointService.remoteStartTransaction(parameters);
		LOGGER.info("OCPP: received 'remoteStartTransaction' response");
		
		return response;
	}


	public RemoteStopTransactionResponse remoteStopTransaction(RemoteStopTransactionRequest parameters) {
		LOGGER.info("OCPP: sending 'remoteStopTransaction' request");
		RemoteStopTransactionResponse response = chargePointService.remoteStopTransaction(parameters);
		LOGGER.info("OCPP: received 'remoteStopTransaction' response");
		
		return response;
	}


	public CancelReservationResponse cancelReservation(CancelReservationRequest parameters) {

		return null;
	}


	public DataTransferResponse dataTransfer(DataTransferRequest parameters) {
		LOGGER.info("OCPP: sending 'dataTransfer' request");		
		DataTransferResponse response = chargePointService.dataTransfer(parameters);
		LOGGER.info("OCPP: received 'dataTransfer' response");
		
		LOGGER.info("Data transfer:" + response.getStatus().toString() + " - " + response.getData());
		return response;
	}


	public GetConfigurationResponse getConfiguration(GetConfigurationRequest parameters) {

		return null;
	}

	public GetLocalListVersionResponse getLocalListVersion(GetLocalListVersionRequest parameters) {
		return null;
	}


	public ReserveNowResponse reserveNow(ReserveNowRequest parameters) {

		return null;
	}


	public SendLocalListResponse sendLocalList(SendLocalListRequest parameters) {

		return null;
	}
}