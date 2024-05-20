package Orion;

import Orion.Api.Server.Boot.IEmulator;
import Orion.Boot.Emulator;

public class Bootstrap {
    public static IEmulator emulator;

    public static void main(String[] args) {
        // TODO: args...

        emulator = new Emulator();
    }
}
