package ocpp;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by bds on 09/11/2016.
 */
public class CommandsDispatcher implements Runnable {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommandsDispatcher.class);

    private List<OcppCommand> queue = new ArrayList<>();
    private Thread thread = null;
    private Integer retries = 0;

    @Inject @Named("OcppPoolingPeriod")
    private Integer POOLING_PERIOD = 500;

    public void queue(OcppCommand command) {
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
