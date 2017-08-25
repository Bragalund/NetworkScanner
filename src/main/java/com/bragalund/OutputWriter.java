package com.bragalund;

import java.net.InetAddress;
import java.util.HashMap;

public class OutputWriter {

    public void writeContentOfMap(HashMap hashMap) {
        if (hashMap.isEmpty()) {
            System.out.println("There are no other devices on the network.");
        } else {
            System.out.println("Here are the other devices on the local network: \n\n");
            for(Object o : hashMap.keySet()){
                String key = o.toString();
                InetAddress inetAddress = (InetAddress) hashMap.get(key);
                System.out.println(inetAddress.getCanonicalHostName());
            }
        }

    }
}
