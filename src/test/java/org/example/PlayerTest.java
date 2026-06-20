package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    void getMarkerReturnsX() {
        Player player = new Player('X');
        assertEquals('X', player.getMarker());
    }

    @Test
    void getMarkerReturnsO() {
        Player player = new Player('O');
        assertEquals('O', player.getMarker());
    }

    @Test
    void differentMarkersAreNotEqual() {
        Player x = new Player('X');
        Player o = new Player('O');
        assertNotEquals(x.getMarker(), o.getMarker());
    }
}
