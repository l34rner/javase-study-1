package com.edgar.demo;

import org.apache.zookeeper.KeeperException;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2015/9/18.
 */
public class ResilientConfigUpdater {
    public static final String PATH = "/config";

    private ActiveKeyValueStore store;
    private Random random = new Random();

    public ResilientConfigUpdater(String hosts) throws IOException, InterruptedException {
        store = new ActiveKeyValueStore();
        store.connect(hosts);
    }

    public void run() throws KeeperException, InterruptedException {
        while (true) {
            String value = random.nextInt(100) + "";
            store.write(PATH, value);
            System.out.printf("Set %s to %s\n", PATH, value);
            TimeUnit.SECONDS.sleep(random.nextInt(10));
        }
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        while (true) {
            try {
                ResilientConfigUpdater updater = new ResilientConfigUpdater("192.168.149.131:2181");
                updater.run();
            } catch (KeeperException.SessionExpiredException e) {
                //start a new session
            } catch (KeeperException e) {
                //already retried, so exit
                e.printStackTrace();
                break;
            }

        }
    }
}