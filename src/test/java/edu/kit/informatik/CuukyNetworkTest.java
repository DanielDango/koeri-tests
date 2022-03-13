package edu.kit.informatik;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Some tests I created in addition to the already existing koeri-tests.
 * No guarantee for duplication.
 */
class CuukyNetworkTest {

    @Test
    public void networkConnectChangeTest() throws ParseException {
        Network network1 = new Network("(1.1.1.1 2.2.2.2)");
        Network network2 = new Network("(3.3.3.3 4.4.4.4)");

        assertTrue(network1.add(network2));

        Network change = new Network("(2.2.2.2 3.3.3.3)");

        assertTrue(network1.add(change));
    }

    @Test
    public void multipleAddTest() throws ParseException {
        Network network1 = new Network("(1.1.1.1 2.2.2.2)");
        Network network2 = new Network("(3.3.3.3 4.4.4.4)");

        assertTrue(network1.add(network2));
        assertFalse(network1.add(network2));
    }

    @Test
    public void reverseOrderEqualsTest() throws ParseException {
        Network network1a = new Network("(1.1.1.1 2.2.2.2)");
        Network network2a = new Network("(3.3.3.3 4.4.4.4)");
        Network network3a = new Network("(2.2.2.2 3.3.3.3)");

        Network network1b = new Network("(1.1.1.1 2.2.2.2)");
        Network network2b = new Network("(3.3.3.3 4.4.4.4)");
        Network network3b = new Network("(2.2.2.2 3.3.3.3)");

        network1a.add(network2a);
        network1a.add(network3a);

        network3b.add(network1b);
        network3b.add(network2b);

        assertEquals(network3b, network1a);
    }

    @Test
    public void notEqualConnectionButEqualKnotsTest() throws ParseException {
        Network network1 = new Network("(1.1.1.1 (2.2.2.2 3.3.3.3))");
        Network network2 = new Network("(1.1.1.1 2.2.2.2 3.3.3.3)");

        assertNotEquals(network1, network2);
    }

    @Test
    public void addNoChangeTest() throws ParseException {
        Network network1 = new Network("(1.1.1.1 2.2.2.2)");
        Network network2 = new Network("(1.1.1.1 3.3.3.3)");
        Network network3 = new Network("(3.3.3.3 4.4.4.4)");

        assertTrue(network1.add(network2));
        assertTrue(network2.add(network3));

        assertEquals(1, network1.getHeight(new IP("1.1.1.1")));
    }

    @Test
    public void addToEachOtherTest() throws ParseException {
        Network network1 = new Network("(1.1.1.1 2.2.2.2)");
        Network network2 = new Network("(1.1.1.1 3.3.3.3)");

        assertTrue(network1.add(network2));
        assertTrue(network2.add(network1));
        assertEquals(network1, network2);
    }

    @Test
    public void noDisconnectTest() throws ParseException {
        Network network = new Network("(1.1.1.1 2.2.2.2)");
        assertFalse(network.disconnect(new IP("1.1.1.1"), new IP("2.2.2.2")));
    }

    @Test
    public void disconnectBothDirectionTest() throws ParseException {
        Network network = new Network("(1.1.1.1 (2.2.2.2 3.3.3.3) 4.4.4.4)");
        IP one = new IP("1.1.1.1");
        IP two = new IP("2.2.2.2");

        assertTrue(network.disconnect(one, two));
        assertEquals(1, network.getHeight(one));
        assertEquals(1, network.getHeight(two));
    }
}