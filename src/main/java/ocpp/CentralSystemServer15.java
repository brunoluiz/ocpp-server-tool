package ocpp;

import java.util.ArrayList;
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
import org.slf4j.LoggerFactory;

@WebService(endpointInterface="ocpp.cs._2012._06.CentralSystemService")
@BindingType(value = "http://www.w3.org/2003/05/soap/bindings/HTTP/" )
@SOAPBinding(style = Style.DOCUMENT, use = Use.LITERAL)
@Addressing(enabled=true, required=false)
public class CentralSystemServer15 implements CentralSystemService {

	protected String centralSystemServerEndpoint = "";
	protected Endpoint endpoint;
	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(CentralSystemServer15.class);
	
	private List<Integer> activeTransactions = new ArrayList<Integer>();
		
	@Inject
	public CentralSystemServer15(@Named("CentralSystemServerEndpoint") String centralSystemServerEndpoint) {
		this.centralSystemServerEndpoint = centralSystemServerEndpoint;
		
		start();
	}
	
	private void start() {
		LOGGER.info("Starting central system OCPP server @ {}", centralSystemServerEndpoint);
		endpoint = Endpoint.publish(centralSystemServerEndpoint, this);
		
		if (!endpoint.isPublished()) {
			LOGGER.error("Could not publish central system OCPP server!");
		} else {
			LOGGER.info("Central system OCPP server is published!");
		}
	}
	
	private Integer generateTransactionId() {
		return (int) (System.currentTimeMillis() / 1000L);
	}


	public AuthorizeResponse authorize(AuthorizeRequest parameters) {
		IdTagInfo tagInfo = new IdTagInfo();
		String userTag = parameters.getIdTag();
		if(userTag.equals("blockedtag"))
			tagInfo.setStatus(AuthorizationStatus.BLOCKED);
		else 
			tagInfo.setStatus(AuthorizationStatus.ACCEPTED);

		AuthorizeResponse response = new AuthorizeResponse();
		response.setIdTagInfo(tagInfo);
		
		return response;
	}


	public BootNotificationResponse bootNotification(BootNotificationRequest parameters) {
		LOGGER.info("OCPP: receiving 'bootNotification' request");
		
		BootNotificationResponse response = new BootNotificationResponse();
		response.setStatus(RegistrationStatus.ACCEPTED);
//		response.setCurrentTime(Misc.getCurrentDate());
		response.setHeartbeatInterval(500);

		return response;
	}


	public DataTransferResponse dataTransfer(DataTransferRequest parameters) {
		// TODO Auto-generated method stub
		return null;
	}


	public DiagnosticsStatusNotificationResponse diagnosticsStatusNotification(
			DiagnosticsStatusNotificationRequest parameters) {
		// TODO Auto-generated method stub
		return null;
	}


	public FirmwareStatusNotificationResponse firmwareStatusNotification(FirmwareStatusNotificationRequest parameters) {
		// TODO Auto-generated method stub
		return null;
	}


	public HeartbeatResponse heartbeat(HeartbeatRequest parameters) {
		HeartbeatResponse response = new HeartbeatResponse();
//		response.setCurrentTime(Misc.getCurrentDate());
		return response;
	}


	public MeterValuesResponse meterValues(MeterValuesRequest parameters) {
		// TODO Auto-generated method stub
		return null;
	}


	public StartTransactionResponse startTransaction(StartTransactionRequest parameters) {
		IdTagInfo tagInfo = new IdTagInfo();
		tagInfo.setStatus(AuthorizationStatus.ACCEPTED);

		Integer transactionId = generateTransactionId();
		activeTransactions.add(transactionId);
		
		StartTransactionResponse response = new StartTransactionResponse();
		response.setIdTagInfo(tagInfo);
		response.setTransactionId(transactionId); // FIXME: Should be generated
		return response;		
	}


	public StatusNotificationResponse statusNotification(StatusNotificationRequest parameters) {
		StatusNotificationResponse response = new StatusNotificationResponse();
		return response;
	}


	public StopTransactionResponse stopTransaction(StopTransactionRequest parameters) {
		Integer transactionId = parameters.getTransactionId();
		IdTagInfo tagInfo = new IdTagInfo();
		if(!activeTransactions.contains(transactionId)) {
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