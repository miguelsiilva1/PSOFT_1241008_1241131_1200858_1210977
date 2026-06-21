package com.marslps.AISafeFMS.vo;

import com.marslps.AISafeFMS.model.vo.RouteID;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RouteIDTest {

    @Test
    void testValidRouteID() {
        RouteID routeID = new RouteID("TSA-TSB");

        assertEquals("TSA-TSB", routeID.id());
    }

    @Test
    void testEqualsWithSameRouteID() {
        RouteID routeID1 = new RouteID("TSA-TSB");
        RouteID routeID2 = new RouteID("TSA-TSB");

        assertEquals(routeID1, routeID2);
        assertEquals(routeID1.hashCode(), routeID2.hashCode());
    }

    @Test
    void testNotEqualsWithDifferentRouteID() {
        RouteID routeID1 = new RouteID("TSA-TSB");
        RouteID routeID2 = new RouteID("TSA-LIS");

        assertNotEquals(routeID1, routeID2);
    }
}