package Orion.StressTest;

import Orion.StressTest.Composer.RequestHeightmapComposer;
import Orion.StressTest.Composer.RequestRoomLoadComposer;
import Orion.StressTest.Connection.OrionClientConnection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class CommandHandler {
    public static void init(OrionStressTest test) {
        final Thread cmdThread = new Thread(() -> {
            while(OrionStressTest.isRunning) {
                if(!OrionStressTest.isRunning) break;

                try {
                    final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    final String line = reader.readLine();

                    if(line.equalsIgnoreCase("joinRoom")) {
                        for(OrionClientConnection client : test.getClients()) {
                            if(!client.isOnline()) continue;

                            for(Map.Entry<Integer, AtomicInteger> room : test.getRooms().entrySet()) {
                                room.getValue().incrementAndGet();

                                client.send(new RequestRoomLoadComposer(room.getKey(), ""));
                                client.send(new RequestHeightmapComposer());
                                client.setIsInRoom(true);
                            }
                        }
                    } else if(line.equalsIgnoreCase("walk")) {
                        for(OrionClientConnection client : test.getClients()) {
                            client.setIsWalk(!client.isWalk());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        cmdThread.start();
    }
}
