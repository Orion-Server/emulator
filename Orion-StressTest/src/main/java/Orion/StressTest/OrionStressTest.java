package Orion.StressTest;

import Orion.StressTest.Connection.Config.OrionClientConfig;
import Orion.StressTest.Connection.OrionClientConnection;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class OrionStressTest {
    public static boolean isRunning = true;

    private int botCount;

    private Map<Integer, AtomicInteger> rooms;

    private final EventLoopGroup clientLoopGroup = new NioEventLoopGroup(16);

    private List<OrionClientConnection> clients = new ArrayList<>();

    private final ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(8);

    public OrionStressTest(String[] args) {
        this.botCount = Integer.parseInt(args[0]);

        this.rooms = new ConcurrentHashMap<>();

        String[] roomIds = args[1].split(",");

        for (String roomId : roomIds) {
            this.rooms.put(Integer.parseInt(roomId), new AtomicInteger(0));
        }

        this.executorService.scheduleAtFixedRate(() -> {
            for(OrionClientConnection client : clients) {
                if(OrionStressTest.getRandom(1, 50) > 40) {
                    client.tick();
                }
            }
        }, 1000, 1000, TimeUnit.MILLISECONDS);
    }

    public void initialize() throws InterruptedException {
        for (int i = 0; i < botCount; i++) {
            OrionClientConnection client = new OrionClientConnection(
                    new OrionClientConfig("localhost", 30000, STR."orion-test-\{i}"),
                    this.clientLoopGroup
            );

            try {
                Thread.sleep(60);
            } catch (Exception e) {
                e.printStackTrace();
            }

            clients.add(client);
        }
    }

    public List<OrionClientConnection> getClients() {
        return this.clients;
    }

    public Map<Integer, AtomicInteger> getRooms() {
        return this.rooms;
    }

    public static void main(String[] args) {
        final OrionStressTest stressTest = new OrionStressTest(args);

        CommandHandler.init(stressTest);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            stressTest.getClients().forEach(OrionClientConnection::disconnect);
            isRunning = false;
        }));

        try {
            stressTest.initialize();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static int getRandom(int min, int max) {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }
}
