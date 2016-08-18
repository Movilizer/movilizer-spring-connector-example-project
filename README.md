# movilizer-spring-connector-example-project

This is an example project for usage of the movilizer-spring-connector.

Requironments
* Eclipse best Spring Tool Suite or any other IDE.
* Gradle and Gradle plugin for Eclipse

Currently several examples are implemented as Configurations called from the com.movilizer.connector.Application.java class.
To set up the connector under eclipse do the following:

1. Checkout the movilizer-spring-connector-example-project project and the [movilizer-spring-connector](https://github.com/Movilizer/movilizer-spring-connector) project to the same root directory (your workspace directory under eclipse).
2. Import both Gradle projects in your workspace (import Gradle project under eclipse)
3. Edit the application-example-project.properties
  1. Enter the system you want to connect to in movilizer.env: (demo, prod, etc.).
  2. Enter also the system id and the system password.
4. Choose the example you want to use and enter the Configuration class of this example under com.movilizer.connector.Application.java

There for in the following listing you can change MasterDataSendingConfiguration.class to DatacontainerProcessorConfiguration.java etc.

    @SpringBootApplication
    @Import(MasterDataSendingConfiguration.class)
    public class Application extends SpringBootServletInitializer implements WebApplicationInitializer {
      private static Log logger = LogFactory.getLog(Application.class);

