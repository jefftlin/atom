package com.lamp.atom.schedule.k8s.operator.builder;

import io.fabric8.kubernetes.api.builder.Builder;


abstract public class AbstractOperatorKubernetesBuilder<T extends Builder, U> implements OperatorKubernetesBuilder {

    protected T job;

    protected void preBuild() {
    }

    public U getOperator() {
        this.job();
        this.metadata();
        this.spec();

        this.preBuild();

        return (U)this.job.build();
    }
}
