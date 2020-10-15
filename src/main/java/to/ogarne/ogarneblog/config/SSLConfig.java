package to.ogarne.ogarneblog.config;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.ajp.AjpNioProtocol;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SSLConfig {

    @Bean
    TomcatServletWebServerFactory servletContainerFactory() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        Connector ajpConnector = new Connector("org.apache.coyote.ajp.AjpNioProtocol");
        AjpNioProtocol protocol = (AjpNioProtocol)ajpConnector.getProtocolHandler();
        protocol.setSecret("pojpoj");
        ajpConnector.setPort(9090);
        ajpConnector.setSecure(true);
        ajpConnector.setAllowTrace(false);
        ajpConnector.setScheme("http");
        tomcat.addAdditionalTomcatConnectors(ajpConnector);

        return tomcat;
    }



}
