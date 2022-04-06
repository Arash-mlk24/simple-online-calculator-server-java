package model.builder.webServer;

import model.WebServer;

public class WebServerBuilder {

    public WebServerBuilder() {}

    public static ISpecifyPort builder() {
        return new Impl();
    }

    private static class Impl implements
            ISpecifyPort,
            IBuildWebServer
    {

        private WebServer webServer = new WebServer();

        @Override
        public IBuildWebServer withPort(int port) {
            webServer.setPort(port);
            return this;
        }

        @Override
        public WebServer build() {
            return webServer;
        }

    }

}
