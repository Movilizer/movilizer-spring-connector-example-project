package com.movilizer.connector.example;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;

import com.movilizer.connector.MovilizerConnectorConfig;

@Configuration
@Import(MovilizerConnectorConfig.class)
@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.ASPECTJ, pattern = "com.movilizer.connector.examples.*"))
public class ExcludeExamplesConfiguration {

}
