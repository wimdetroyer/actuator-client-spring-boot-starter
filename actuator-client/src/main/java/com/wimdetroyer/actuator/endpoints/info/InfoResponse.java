package com.wimdetroyer.actuator.endpoints.info;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.wimdetroyer.actuator.endpoints.info.details.BuildInfo;
import com.wimdetroyer.actuator.endpoints.info.details.GitInfo;
import com.wimdetroyer.actuator.endpoints.info.details.JavaInfo;
import com.wimdetroyer.actuator.endpoints.info.details.OsInfo;
import com.wimdetroyer.actuator.endpoints.info.details.ProcessInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * Response from the info endpoint with explicitly typed fields.
 */
public final class InfoResponse {
    private JavaInfo java;
    private OsInfo os;
    private BuildInfo build;
    private GitInfo git;
    private ProcessInfo process;
    private final Map<String, Object> additionalProperties = new HashMap<>();

    public InfoResponse() {
    }

    public InfoResponse(JavaInfo java, OsInfo os, BuildInfo build, GitInfo git, ProcessInfo process) {
        this.java = java;
        this.os = os;
        this.build = build;
        this.git = git;
        this.process = process;
    }

    public JavaInfo java() {
        return java;
    }

    public void setJava(JavaInfo java) {
        this.java = java;
    }

    public OsInfo os() {
        return os;
    }

    public void setOs(OsInfo os) {
        this.os = os;
    }

    public BuildInfo build() {
        return build;
    }

    public void setBuild(BuildInfo build) {
        this.build = build;
    }

    public GitInfo git() {
        return git;
    }

    public void setGit(GitInfo git) {
        this.git = git;
    }

    public ProcessInfo process() {
        return process;
    }

    public void setProcess(ProcessInfo process) {
        this.process = process;
    }

    public Map<String, Object> additionalProperties() {
        return additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String key, Object value) {
        additionalProperties.put(key, value);
    }
}
