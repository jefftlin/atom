package com.lamp.atom.schedule.api.deploy;

public class KubernetesOperatorConstants {

    // Operator naming
    public static final String STANDALONE_OPERATOR_NAME_PREFIX = "atom-runtime-standalone-";

    public static final String SESSION_OPERATOR_NAME_PREFIX = "atom-runtime-session-";

    // Configuration naming
    public static final String DEFAULT_NAMESPACE = "default";

    public static final String DEFAULT_CPU_CONTAINER_NAME = "default";

    public static final String DEFAULT_GPU_CONTAINER_NAME = "default";

    public static final String DEFAULT_NAMING_PREFIX = "atom-";

    public static final String NAMESPACE_PREFIX = DEFAULT_NAMING_PREFIX + "namespace-";

    public static final String CPU_CONTAINER_NAME_PREFIX = DEFAULT_NAMING_PREFIX + "cpu_container-";

    public static final String GPU_CONTAINER_NAME_PREFIX = DEFAULT_NAMING_PREFIX + "gpu_container-";

}
