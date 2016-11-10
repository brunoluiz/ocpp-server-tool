package ocpp;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by bds on 09/11/2016.
 */
public class CommandsDispatcher implements Runnable {
    protected ArrayList<OcppCommand> queue = new ArrayList<OcppCommand>();
    private Thread thread = null;
    private Integer retries = 0;
    private static final Logger LOGGER = LoggerFactory.getLogger(CommandsDispatcher.class);

    @Inject
    @Named("ClientService")
    Object client = null;

    @Inject
    @Named("OcppPoolingPeriod")
    private final Integer POOLING_PERIOD = 500;

    public void queue(OcppCommand command) {
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

    public void setRetries(int retries) {
        this.retries = retries;
    }

    private void process() {
        for (Iterator<OcppCommand> it = queue.iterator(); it.hasNext(); ) {
            OcppCommand command = it.next();

            for(int totalRetries = 0; totalRetries < (this.retries+1); totalRetries++) {
                try {
                    command.execute();
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            it.remove();
        }
    }
}
