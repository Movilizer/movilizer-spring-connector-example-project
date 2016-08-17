package com.movilizer.connector.example.requestprint;


import com.movilitas.movilizer.v14.MovilizerResponse;
import com.movilizer.connector.MovilizerConnectorAPI;
import com.movilizer.connector.example.ExcludeExamplesConfiguration;
import com.movilizer.connector.model.Processor;
import com.movilizer.mds.webservice.services.MovilizerDistributionService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Configuration
@Import(ExcludeExamplesConfiguration.class)
public class RequestPrinterConfiguration {
    private static Log logger = LogFactory.getLog(RequestPrinterConfiguration.class);

    @Component
    private class AppLogic {
        private MovilizerConnectorAPI movilizerConnector;
        private MovilizerDistributionService mds;

        @Autowired
        public AppLogic(MovilizerConnectorAPI movilizerConnector, MovilizerDistributionService mds) {
            this.movilizerConnector = movilizerConnector;
            this.mds = mds;
        }

        @PostConstruct
        void registerCallbacks() {
            logger.info("Registering processor");
            movilizerConnector.registerProcessor(new Processor<MovilizerResponse>() {
                @Override
                public void process(MovilizerResponse response) {
                    logger.info("New response:\n\n" + mds.responseToString(response) + "\n\n");
                }
            }, MovilizerResponse.class);
        }
    }
}
