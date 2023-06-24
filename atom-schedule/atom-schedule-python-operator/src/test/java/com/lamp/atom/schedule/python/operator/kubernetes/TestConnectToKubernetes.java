package com.lamp.atom.schedule.python.operator.kubernetes;

import com.lamp.atom.schedule.api.config.AtomScheduleKubernetesConfig;
import com.lamp.atom.schedule.api.config.DefaultOperatorScheduleKubernetesConfig;
import io.fabric8.kubernetes.api.model.*;
import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.dsl.NonNamespaceOperation;
import io.fabric8.kubernetes.client.dsl.PodResource;
import org.junit.Test;

import java.io.InputStream;

public class TestConnectToKubernetes {

    public KubernetesClient getClientWithFile() {
        AtomScheduleKubernetesConfig operatorScheduleKubernetesConfig = new DefaultOperatorScheduleKubernetesConfig();
        try {
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("kubernetesConfig.yaml");
            byte[] data = new byte[inputStream.available()];
            inputStream.read(data);
            operatorScheduleKubernetesConfig.setConfigYaml(new String(data));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Config config = Config.fromKubeconfig(operatorScheduleKubernetesConfig.getConfigYaml());
        KubernetesClient client = new DefaultKubernetesClient(config);
        return client;
    }

    public KubernetesClient getClientWithURL() {
        KubernetesClient client = new DefaultKubernetesClient("https://101.42.236.13:6443");
        return client;
    }

    public void createPod(KubernetesClient client) {
        Pod aPod = new PodBuilder().withNewMetadata().withName("demo-pod1").endMetadata()
                .withNewSpec()
                .addNewContainer()
                .withName("nginx")
                .withImage("nginx:1.7.9")
                .addNewPort().withContainerPort(80).endPort()
                .endContainer()
                .endSpec()
                .build();
        Pod createdPod = client.pods().inNamespace("default").create(aPod);
        // Create or Replace some Pod with some existing object:
        client.pods().inNamespace("default").createOrReplace(aPod);
    }

    @Test
    public void testConnect() throws Exception {
//        KubernetesClient client = new DefaultKubernetesClient("https://124.223.198.143:6443");

//        Config config = new ConfigBuilder()
//                .withMasterUrl("https://101.42.236.13:6443")
//                .withOauthToken("eyJhbGciOiJSUzI1NiIsImtpZCI6IjRYOC1kM3dfZGZ6R00wLVdLNFhlNE1xaXdlVTRDeXB2b25MLUVQUTd2NncifQ.eyJpc3MiOiJrdWJlcm5ldGVzL3NlcnZpY2VhY2NvdW50Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9uYW1lc3BhY2UiOiJkZWZhdWx0Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9zZWNyZXQubmFtZSI6ImplZmZ0LXRva2VuLWdtOW5rIiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9zZXJ2aWNlLWFjY291bnQubmFtZSI6ImplZmZ0Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9zZXJ2aWNlLWFjY291bnQudWlkIjoiNjQwZmI5MjAtZDgyNi00MmZlLTg1ODUtY2MxY2I4YWRhMzQxIiwic3ViIjoic3lzdGVtOnNlcnZpY2VhY2NvdW50OmRlZmF1bHQ6amVmZnQifQ.OrSqrToGf2WvX0EIE1Jvcxa7qt2inReOW01ZocVkxItp_8Y4RyPnTNqGDi-zQdeU5gK2dI1Ery0ruyD-0tGWPnw1c9nYIs7HQ4n34XfC8cuzDSOBYkg15x6n2Qpc9OtY3WvsrsataXWPPp0N70yWk1G_Dh297Yo3bJKDMm5XgUL2BEqERMrbTKIExUSqHgFVdvZxrUzl-Yz_zLCCBv4aNfCjYjdgXz_iexiTE7mM02Evx9ndwnTYghoUgF_w1ALEsCAu4AVdCtVn9P-eEufuFhJLSJY4suQd_Hs6Sja71LUiKPfWS3JdopT1C1QWu_CJ8-6JkK18yZOANsrWfSXLkw")
//                .withTrustCerts(true)
//                .build();
//        KubernetesClient client = new DefaultKubernetesClient(config);
//        KubernetesClient client = new DefaultKubernetesClient();

        KubernetesClient client = getClientWithFile();


        PodList list = client.pods().inNamespace("kube-system").list();
        System.out.println(list);

        NonNamespaceOperation<Pod, PodList, PodResource<Pod>> podPodListPodResourceNonNamespaceOperation = client.pods().inNamespace("kube-system");
        System.out.println(podPodListPodResourceNonNamespaceOperation);
//        createPod(client);
//
//        NamespaceList namespaceList = client.namespaces().list();
//        namespaceList.getItems()
//                .forEach(namespace ->
//                        System.out.println(namespace.getMetadata().getName() + ":" + namespace.getStatus().getPhase()));

        // 查看Pod
        ListOptions options = new ListOptions();
        options.setLabelSelector("app=nginx");
        Pod nginx = client.pods().inNamespace("pkslow")
                .list(options)
                .getItems()
                .get(0);
        System.out.println(nginx);
    }

    @Test
    public void testConnectWithURL() {
        KubernetesClient client = getClientWithFile();

        NamespaceList namespaceList = client.namespaces().list();
        namespaceList.getItems()
                .forEach(namespace ->
                        System.out.println(namespace.getMetadata().getName() + ":" + namespace.getStatus().getPhase()));
//
//        PodList list = client.pods().inNamespace("kube-system").list();
//        System.out.println(list);
    }

}
