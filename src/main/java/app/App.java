package app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by bds on 09/11/2016.
 */
public class App {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        LOGGER.debug("debug msg");
        LOGGER.info("info msg");
        System.out.println("Hello World!"); // Display the string.
    }
}
