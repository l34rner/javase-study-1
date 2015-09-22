import io.vertx.core.Vertx;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
import org.apache.curator.x.discovery.ServiceInstance;
import org.apache.curator.x.discovery.ServiceProvider;

/**
 * Created by Administrator on 2015/9/22.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        CuratorFramework client = CuratorFrameworkFactory.newClient("192.168.149.131:2181", new RetryNTimes(5, 1000));
        client.start();

        ServiceDiscovery discovery = ServiceDiscoveryBuilder.builder(Void.class)
                .basePath("load-balancing-example")
                .client(client)
                .build();
        discovery.start();

        ServiceProvider provider = discovery.serviceProviderBuilder().serviceName("worker")
                .build();
        provider.start();

        for (int i = 0; i < 10; i ++) {
            ServiceInstance instance = provider.getInstance();
            String address = instance.getAddress();
            System.out.println("get : " + instance.getAddress() + ":" + instance.getPort());
            Vertx.vertx().createHttpClient().getNow(instance.getPort(), instance.getAddress(), "/", response -> {
                response.bodyHandler(body -> {
                    System.out.println(body.toString());
                });
            });
        }
    }
}
