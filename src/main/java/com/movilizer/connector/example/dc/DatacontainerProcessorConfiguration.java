package com.movilizer.connector.example.dc;


import com.movilitas.movilizer.v14.MovilizerUploadDataContainer;
import com.movilizer.connector.MovilizerConnectorAPI;
import com.movilizer.connector.example.ExcludeExamplesConfiguration;
import com.movilizer.connector.mapper.direct.GenericDataContainerMapperImpl;
import com.movilizer.connector.model.MovilizerCallback;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Configuration
@Import(ExcludeExamplesConfiguration.class)
public class DatacontainerProcessorConfiguration {
    private static Log logger = LogFactory.getLog(DatacontainerProcessorConfiguration.class);

    class ReadPoint {
        public String name;
        public Integer value;
        public Date date;

        @Override
        public String toString() {
            return "ReadPoint{" +
                    "name='" + name + '\'' +
                    ", value=" + value +
                    ", date=" + date +
                    '}';
        }
    }

    @Component
    @DependsOn("genericDataContainerMapperImpl")
    public class AppLogic {
        private MovilizerConnectorAPI movilizerConnector;
        private GenericDataContainerMapperImpl mapper;

        @Autowired
        public AppLogic(MovilizerConnectorAPI movilizerConnector, GenericDataContainerMapperImpl mapper) {
            this.movilizerConnector = movilizerConnector;
            this.mapper = mapper;
        }

        @PostConstruct
        void registerCallbacks() {
            logger.info("Registering processor");

            movilizerConnector.registerCallback(new MovilizerCallback() {
                @Override
                public void execute() {
                    List<MovilizerUploadDataContainer> dcList = movilizerConnector.getAllDataContainers();
                    List<String> dcKeys = new ArrayList<>();
                    for (MovilizerUploadDataContainer container : dcList) {
                        dcKeys.add(container.getContainer().getKey());
                        if (mapper.isType(container.getContainer().getData(), ReadPoint.class)) {
                            ReadPoint readPoint = mapper.fromDataContainer(container.getContainer().getData(), ReadPoint.class);
                            logger.info("New readpoint! " + readPoint.toString());
                        }
                    }
                    movilizerConnector.setDataContainersAsProcessed(dcKeys);
                }
            });
        }
    }
}
