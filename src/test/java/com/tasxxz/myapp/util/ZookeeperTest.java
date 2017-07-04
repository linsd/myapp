package com.tasxxz.myapp.util;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.junit.Test;

/**
 * Created by linshudeng on 2016/9/19.
 */
public class ZookeeperTest {

    @Test
    public void test1() throws Exception {
        ZooKeeper zk = new ZooKeeper("127.0.0.1:2181", 500000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {

            }
        });
    }
}
