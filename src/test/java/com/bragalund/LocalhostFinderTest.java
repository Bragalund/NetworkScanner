package com.bragalund;


import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.InetAddress;

import static org.junit.Assert.*;

public class LocalhostFinderTest {

    public void writeToSystem(String input) {
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
    }

    /*@Test
    public void testLocalInetAddress() {
        LocalhostFinder localhostFinder = new LocalhostFinder();
        InetAddress inetAddress = localhostFinder.getIPOfHostOnNetwork();
        assertNotNull(inetAddress);
    }*/

    @Test
    public void testValidationOfUserIPInput() {
        LocalhostFinder localhostFinder = new LocalhostFinder();
        String validInput = "192.168.0.12";

        assertTrue(localhostFinder.validUserIP(validInput));
    }

    @Test
    public void testValidationOfUserIPInputWhenWrongInput() {
        LocalhostFinder localhostFinder = new LocalhostFinder();
        String validInput = "132.168.0.12";

        assertFalse(localhostFinder.validUserIP(validInput));
    }

    @Test
    public void testNoInternetWithCorrectInput() {
        LocalhostFinder searcher = new LocalhostFinder();
        String input = "192.168.0.12";
        writeToSystem(input);

        InetAddress answerInetAddress = searcher.getIpFromUserInput();

        assertNotNull(answerInetAddress);
        assertEquals(input, answerInetAddress.getHostAddress().toLowerCase());
    }

    @Test
    public void testNoInternetWithQuit() {
        LocalhostFinder localhostFinder = new LocalhostFinder();
        writeToSystem("q");

        InetAddress answerInetAddress = localhostFinder.getIpFromUserInput();

        assertNull(answerInetAddress);
    }


}
