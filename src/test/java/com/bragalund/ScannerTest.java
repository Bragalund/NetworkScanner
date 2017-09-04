package com.bragalund;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ScannerTest {

    public void writeToSystem(String input) {
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
    }

    /*@Test
    public void findOtherDevicesOnNetwork() {
        Scanner scanner = new Scanner();

        Map IPsOfOtherDevicesOnNetwork = scanner.findIPOfOtherDevicesOnNetwork();
        writeToSystem("n");
        assertNotNull(IPsOfOtherDevicesOnNetwork);
    }*/
}
