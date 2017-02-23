package chargesystem;

import com.google.inject.Inject;
import ocpp.CommandsDispatcher;
import ocpp.cp.ChargePointCommandFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

/**
 * Created by bds on 09/11/2016.
 */
public class UserInputManager implements Runnable {
    private static final Logger log = LoggerFactory.getLogger(UserInputManager.class);
    private Thread thread = null;

    private final CommandsDispatcher dispatcher;
    private final ChargePointCommandFactory commandFactory;

    @Inject
    public UserInputManager(CommandsDispatcher dispatcher,
                            ChargePointCommandFactory commandFactory) {
        this.dispatcher = dispatcher;
        this.commandFactory = commandFactory;
    }

    public void start() {
        if (thread == null) {
            log.info("Starting UserInputManager...");
            thread = new Thread(this);
            thread.start();
        }
    }

    public void run() {
        while (thread != null) {
            //create the Scanner
            System.out.printf("ocpp@cs:cp# ");
            Scanner input = new Scanner(System.in);

            //read input
            String commandStr = input.nextLine().toLowerCase();
            if (commandStr.equals("")) continue;

            execute(commandStr);
        }
    }

    protected void execute(String input) {
        try {
            if (input.contains("remotestart")) {
                dispatcher.queue(commandFactory.createRemoteStartTransaction(input));
            } else if (input.contains("remotestop")) {
                dispatcher.queue(commandFactory.createRemoteStopTransaction(input));
            } else if (input.contains("changeavailability")) {
                dispatcher.queue(commandFactory.createChangeAvailability(input));
            } else if (input.contains("getconfiguration")) {
                dispatcher.queue(commandFactory.createGetConfiguration(input));
            } else if (input.contains("changeconfiguration")) {
                dispatcher.queue(commandFactory.createChangeConfiguration(input));
            } else if (input.contains("unlockconnector")) {
                dispatcher.queue(commandFactory.createUnlockConnector(input));
            } else if (input.contains("datatransfer")) {
                dispatcher.queue(commandFactory.createDataTransfer(input));
            } else if (input.contains("reset")) {
                dispatcher.queue(commandFactory.createReset(input));
            } else if (input.contains("clearcache")) {
                dispatcher.queue(commandFactory.createClearCache(input));
            } else if (input.contains("exit") || input.contains("quit")) {
                System.exit(0);
            } else {
                log.warn("Invalid command or not implemented");
            }
        } catch (Exception e) {
            log.warn("Command not executed");
        }
    }
}
