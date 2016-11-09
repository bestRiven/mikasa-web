package com.mikasa.core.zookeeper;

import org.apache.zookeeper.ZooKeeper;

/**
 * Created by sherlock on 2016/11/1.
 */
public class Driver {

    public static void main(String[] args) throws InterruptedException {
        ZooKeeper zk = ZkUtil.connectServer();
        while (true) {
            System.out.println("while");
            final DistributedLock distributedLock = new DistributedLock();
            distributedLock.init(zk, new LockListener() {
                @Override
                public void lockAcquired() throws InterruptedException {
                    System.out.println("|| get lock, do something");
                    Thread.sleep(10000);
                    distributedLock.unLock();
                }

                @Override
                public void lockReleased() {
                    System.out.println("|| lock released");
                }
            });
            distributedLock.getLock();
        }

    }
}
