# movilizer-spring-connector-example-project

This is an example project for usage of the movilizer-spring-connector.

Currently several examples are implemented as Configurations called from the com.movilizer.connector.Application.java class.
To set up the connector please under eclipse:

1. Checkout the project and the [movilizer-spring-connector](https://github.com/Movilizer/movilizer-spring-connector) in the same directory.
2. Import both Gradle projects in your workspace (import Gradle project under eclipse)
3. Edit the application-example-project.properties
  1. Enter the system you want to connect to in movilizer.env: (demo, prod, etc.).
  2. Enter also the system id and the system password.
4. Choose the example you want to use and enter the Configuration class of this example under com.movilizer.connector.Application.java

There for in the following listing you can change MasterDataSendingConfiguration.class to DatacontainerProcessorConfiguration.java etc.
...
@SpringBootApplication
@Import(MasterDataSendingConfiguration.class)
public class Application extends SpringBootServletInitializer implements WebApplicationInitializer {

    private static Log logger = LogFactory.getLog(Application.class);
...
