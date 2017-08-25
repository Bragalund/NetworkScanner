package com.bragalund;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Scanner;

import static java.lang.System.out;
import static java.net.NetworkInterface.getNetworkInterfaces;

public class LocalhostFinder {

    private InetAddress inetAddress;

    public LocalhostFinder() {
        this.inetAddress = null;
    }

    public InetAddress getIPOfHostOnNetwork() {
        try {
            if (!InetAddress.getLocalHost().isLoopbackAddress()) {
                inetAddress = InetAddress.getLocalHost();
            } else if (getNetworkInterfaces().hasMoreElements()) {
                Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();

                for (NetworkInterface networkInterface : Collections.list(nets)) {

                    if (IPIsValid(getInetAddressFromNetworkInterface(networkInterface))) {
                        inetAddress = getInetAddressFromNetworkInterface(networkInterface);
                    }
                }
                System.out.println("[-- info --] " + inetAddress.getHostAddress() + " is your address on the network..." + "\n");

            } else {
                System.out.println("Couldnt find any valid IP-addresses." + "\n");
                System.out.println("Are you connected to internet?");
                if (Input.yesAnswerFromUser()) {
                    if (getIpFromUserInput() != null) {
                        inetAddress = getIpFromUserInput();
                    } else {
                        System.out.println("Local IP was not set.");
                    }
                } else {
                    System.out.println("You need to be connected to internet to use this program.");
                    System.out.println("Quitting...");
                    System.exit(0);
                }

            }

        } catch (
                UnknownHostException e)

        {
            e.printStackTrace();
        } catch (
                SocketException e)

        {
            e.printStackTrace();
        }

        return inetAddress;
    }

    public InetAddress getInetAddressFromNetworkInterface(NetworkInterface netint) {
        InetAddress IP = null;
        Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
        for (InetAddress inetAddress : Collections.list(inetAddresses)) {
            IP = inetAddress;
        }
        return IP;
    }

    public boolean IPIsValid(InetAddress inetAddress) {
        if (inetAddress.getHostAddress().startsWith("192.")) {
            return true;
        }
        return false;
    }

    public boolean validUserIP(String ipv4) {
        if (ipv4.startsWith("192.") || ipv4.startsWith("172.") || ipv4.startsWith("10.")) {
            if (ipv4.length() > 9 && ipv4.length() < 16) {
                return true;
            }
        }
        return false;
    }

    public InetAddress getIpFromUserInput() {
        String ipv4FromUser = "";
        while (!validUserIP(ipv4FromUser)) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Please write your ipv4 address or quit with 'q'");
            ipv4FromUser = sc.nextLine();
            System.out.println(ipv4FromUser);
            sc.close();

            if (validUserIP(ipv4FromUser)) {
                try {
                    return InetAddress.getByName(ipv4FromUser);
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                System.out.println("IP was valid and is set to: " + ipv4FromUser);
            } else if (ipv4FromUser.toLowerCase().equals("q")) {
                return null;
            } else {
                System.out.println("IP was not valid.");
            }
        }
        return null;
    }

    public InetAddress getInetAddress() {
        if (inetAddress == null) {
            getIPOfHostOnNetwork();
        }
        return inetAddress;
    }
}
