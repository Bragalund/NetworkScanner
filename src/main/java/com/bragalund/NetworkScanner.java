package com.bragalund;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;

public class NetworkScanner {
    public HashMap<String, InetAddress> getAddresses() {
        return addresses;
    }

    private HashMap<String, InetAddress> addresses = new HashMap<String, InetAddress>();

    public InetAddress getLocalIp() {
        LocalIPChooser localIPChooser = new LocalIPChooser();
        return localIPChooser.getInetAddress();
    }

    public HashMap<String, InetAddress> findIPOfOtherDevicesOnNetwork() {
        String ownIpAddress = getLocalIp().getCanonicalHostName();
        System.out.println("Do you want to scan all subnets?");
        if (Input.yesAnswerFromUser()) {
            iterateOverAllOfIPs(ownIpAddress);
        } else {
            iterateOverOwnSubnet(ownIpAddress);
        }
        return addresses;
    }

    private void iterateOverOwnSubnet(String ownIpAddress) {
        for (int i = 0; i < 256; i++) {
            String otherAddress = ownIpAddress.substring(0, ownIpAddress.lastIndexOf('.')) + "." + i;
            callIpAddress(otherAddress, ownIpAddress);
        }
    }


    private void iterateOverAllOfIPs(String ownIpAddress) {
        for (int j = 0; j < 256; j++) {
            String tempIpAddress = ownIpAddress.substring(0, 8) + j + ".";
            for (int i = 0; i < 256; i++) {
                String otherAddress = tempIpAddress + i;
                callIpAddress(otherAddress, ownIpAddress);
            }
        }
    }

    private void callIpAddress(String otherAddress, String ownIpAddress) {
        try {
            System.out.println("trying: " + otherAddress);
            if (InetAddress.getByName(otherAddress).isReachable(50)) {
                if (!(ownIpAddress.equals(otherAddress))) {
                    System.out.println("[-- info --] Found other device with IP: " + otherAddress);
                    addresses.put(otherAddress, InetAddress.getByName(otherAddress));
                }
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
