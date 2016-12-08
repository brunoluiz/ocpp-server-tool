package chargesystem;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import ocpp.CommandsDispatcher;
import ocpp.cp.ChargePointCommandFactory;
import ocpp.cp.commands.*;
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

        dispatcher.setRetries(0);
        dispatcher.start();
    }

    public void run() {
        while(true) {
            //create the Scanner
            System.out.printf("ocpp@cs:cp# ");
            Scanner input = new Scanner(System.in);

            //read input
            String commandStr = input.nextLine().toLowerCase();
            if(commandStr.equals("")) continue;

            try {
                if (commandStr.contains("remotestart")) {
                    dispatcher.queue(commandFactory.createRemoteStartTransaction(commandStr));
                } else if (commandStr.contains("remotestop")) {
                    dispatcher.queue(commandFactory.createRemoteStopTransaction(commandStr));
                } else if (commandStr.contains("changeavailability")) {
                    dispatcher.queue(commandFactory.createChangeAvailability(commandStr));
                } else if (commandStr.contains("getconfiguration")) {
                    dispatcher.queue(commandFactory.createGetConfiguration(commandStr));
                } else if (commandStr.contains("changeconfiguration")) {
                    dispatcher.queue(commandFactory.createChangeConfiguration(commandStr));
                } else {
                    log.warn("Invalid command or not implemented");
                }
            } catch (Exception e) {
                log.warn("Command not executed");
            }
        }
    }
}
