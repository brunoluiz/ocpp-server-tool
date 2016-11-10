package chargesystem;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import ocpp.CommandsDispatcher;
import ocpp.cp.commands.RemoteStartTransactionCommand;
import ocpp.cp.commands.RemoteStopTransactionCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

/**
 * Created by bds on 09/11/2016.
 */
public class UserInputManager implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserInputManager.class);
    private Thread thread = null;

    @Inject
    private CommandsDispatcher dispatcher = null;

    @Inject @Named("CentralSystemServerEndpoint")
    String centralSystemServer = null;

    @Inject @Named("ChargePointServerEndpoint")
    String chargePointServer = null;

    public void start() {
        if (thread == null) {
            LOGGER.info("Starting UserInputManager...");
            thread = new Thread(this);
            thread.start();
        }

        dispatcher.setRetries(0);
        dispatcher.start();
    }

    public void run() {
        while(true) {
            //create the Scanner
            System.out.printf("ocpp@cs:cp# ", centralSystemServer, chargePointServer);
            Scanner input = new Scanner(System.in);

            //read input
            String commandStr = input.nextLine().toLowerCase();
            if(commandStr.equals("")) continue;

            if (commandStr.contains("remotestart")) {
                dispatcher.queue(new RemoteStartTransactionCommand(commandStr));
            }
            else if(commandStr.contains("remotestop")) {
                dispatcher.queue(new RemoteStopTransactionCommand(commandStr));
            }
            else {
                LOGGER.warn("Invalid command or not implemented");
            }
        }
    }
}
