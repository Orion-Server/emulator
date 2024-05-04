package Orion.Api.Networking.Session.Throttle;

public interface IAddressAttempt {
    void increment();

    boolean shouldBlockConnection();
}
