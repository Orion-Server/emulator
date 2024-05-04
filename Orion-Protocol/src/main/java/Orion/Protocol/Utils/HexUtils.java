package Orion.Protocol.Utils;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public abstract class HexUtils {
    public static String getRandom(int length) {
        final Random r = ThreadLocalRandom.current();
        final StringBuilder string = new StringBuilder();

        while(string.length() < length) {
            string.append(Integer.toHexString(r.nextInt()));
        }

        return string.substring(0, length);
    }
}
