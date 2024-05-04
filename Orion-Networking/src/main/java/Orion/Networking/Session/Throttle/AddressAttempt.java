package Orion.Networking.Session.Throttle;

import Orion.Api.Networking.Session.Throttle.IAddressAttempt;

import java.util.concurrent.atomic.AtomicInteger;

public class AddressAttempt implements IAddressAttempt {
    private long lastAttemptTime;

    private final AtomicInteger attempts;

    public AddressAttempt() {
        this.attempts = new AtomicInteger(0);
        this.lastAttemptTime = System.currentTimeMillis();
    }

    public void increment() {
        this.attempts.incrementAndGet();
        this.lastAttemptTime = System.currentTimeMillis();
    }

    public boolean shouldBlockConnection() {
        return this.attempts.get() >= 1000 && (System.currentTimeMillis() - this.lastAttemptTime) < 2000;
    }
}
