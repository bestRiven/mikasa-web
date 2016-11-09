package com.mikasa.core.zookeeper;

/**
 * Created by sherlock on 2016/11/1.
 */
public interface LockListener {

    void lockAcquired() throws InterruptedException;

    void lockReleased();
}
