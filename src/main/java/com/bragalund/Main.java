package com.bragalund;

import java.net.InetAddress;
import java.net.SocketException;

public class Main {
    public static void main(String[] args) {

        NetworkScanner networkScanner = new NetworkScanner();
        networkScanner.findIPOfOtherDevicesOnNetwork();

        OutputWriter outputWriter = new OutputWriter();
        outputWriter.writeContentOfMap(networkScanner.getAddresses());

    }
}
