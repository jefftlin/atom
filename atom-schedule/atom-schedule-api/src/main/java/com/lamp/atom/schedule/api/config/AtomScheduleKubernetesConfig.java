package com.lamp.atom.schedule.api.config;

public class AtomScheduleKubernetesConfig {

    private boolean isUser = true;

    private String masterUrl;

    private String configName = "kubernetesConfig.yaml";

    private String configYaml;

    public boolean isUser() {
        return isUser;
    }

    public void setUser(boolean user) {
        isUser = user;
    }

    public String getMasterUrl() {
        return masterUrl;
    }

    public void setMasterUrl(String masterUrl) {
        this.masterUrl = masterUrl;
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public String getConfigYaml() {
        return configYaml;
    }

    public void setConfigYaml(String configYaml) {
        this.configYaml = configYaml;
    }
}
