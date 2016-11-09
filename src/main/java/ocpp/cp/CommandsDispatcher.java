package ocpp.cp;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import ocpp.cp._2012._06.ChargePointService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by bds on 09/11/2016.
 */
public class CommandsDispatcher implements Runnable {
    protected ArrayList<ChargePointCommand> queue = new ArrayList<ChargePointCommand>();
    private Thread thread = null;
    private static final Logger LOGGER = LoggerFactory.getLogger(CommandsDispatcher.class);

    @Inject
    @Named("ChargePointClient")
    ChargePointService client = null;

    @Inject
    @Named("OcppPoolingPeriod")
    private final Integer POOLING_PERIOD = 500;

    public void queue(ChargePointCommand command) {
        command.setClient(client);
        queue.add(command);
    }

    public void start() {
        if (thread == null) {
            LOGGER.info("Starting CommandsDispatcher...");
            thread = new Thread(this);
            thread.start();
        }
    }

    public void run() {
        while(true) {
            process();
            try {
                Thread.sleep(POOLING_PERIOD);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void process() {
        for (Iterator<ChargePointCommand> it = queue.iterator(); it.hasNext(); ) {
            ChargePointCommand command = it.next();
            try {
                command.execute();
                it.remove();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
