package com.bragalund;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner();
        scanner.findIPOfOtherDevicesOnNetwork();

        OutputWriter outputWriter = new OutputWriter();
        outputWriter.writeContentOfMap(scanner.getAddresses());

    }
}
