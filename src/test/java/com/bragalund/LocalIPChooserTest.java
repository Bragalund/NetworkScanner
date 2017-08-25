package com.bragalund;


import junit.framework.TestCase;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.InetAddress;

import static org.junit.Assert.*;

public class LocalIPChooserTest{

    public void writeToSystem(String input) {
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
    }

    @Test
    public void testLocalInetAddress() {
        LocalIPChooser localIPChooser = new LocalIPChooser();
        InetAddress inetAddress = localIPChooser.getIPOfHostOnNetwork();
        assertNotNull(inetAddress);
    }

    @Test
    public void testValidationOfUserIPInput() {
        LocalIPChooser localIPChooser = new LocalIPChooser();
        String validInput = "192.168.0.12";

        assertTrue(localIPChooser.validUserIP(validInput));
    }

    @Test
    public void testValidationOfUserIPInputWhenWrongInput() {
        LocalIPChooser localIPChooser = new LocalIPChooser();
        String validInput = "132.168.0.12";

        assertFalse(localIPChooser.validUserIP(validInput));
    }

    @Test
    public void testNoInternetWithCorrectInput() {
        LocalIPChooser searcher = new LocalIPChooser();
        String input = "192.168.0.12";
        writeToSystem(input);

        InetAddress answerInetAddress = searcher.getIpFromUserInput();

        assertNotNull(answerInetAddress);
        assertEquals(input, answerInetAddress.getHostAddress().toLowerCase());
    }

    @Test
    public void testNoInternetWithQuit() {
        LocalIPChooser localIPChooser = new LocalIPChooser();
        writeToSystem("q");

        InetAddress answerInetAddress = localIPChooser.getIpFromUserInput();

        assertNull(answerInetAddress);
    }


}
