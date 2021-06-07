package com.company;

public class URL {
    String applicationName;
    String version;
    String serverURL;

    public URL(String applicationName, String version, String serverURL) {
        this.applicationName = applicationName;
        this.version = version;
        this.serverURL = serverURL;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public String getVersion() {
        return version;
    }

    public String getServerURL() {
        return serverURL;
    }
}
