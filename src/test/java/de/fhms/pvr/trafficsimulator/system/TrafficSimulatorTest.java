package de.fhms.pvr.trafficsimulator.system;

import org.junit.Test;

import static org.junit.Assert.*;

public class TrafficSimulatorTest {

    private Vehicle[][] testStreet;

    private Vehicle[][] expectedStreet;

    private TrafficSimulator trafficSimulator;

    @Test
    public void testSimulateAcceleration() {
        testStreet = new Vehicle[1][10];
        testStreet[0][0] = new Vehicle(4);
        testStreet[0][5] = new Vehicle(2);
        testStreet[0][9] = new Vehicle(5);

        expectedStreet = new Vehicle[1][10];
        expectedStreet[0][0] = new Vehicle(5);
        expectedStreet[0][5] = new Vehicle(3);
        expectedStreet[0][9] = new Vehicle(5);

        trafficSimulator = new TrafficSimulator(testStreet);
        trafficSimulator.simulateAcceleration();
        assertArrayEquals(expectedStreet, trafficSimulator.getStreet());
    }

    @Test
    public void testSimulateDeceleration() {
        testStreet = new Vehicle[1][10];
        testStreet[0][0] = new Vehicle(5);
        testStreet[0][1] = new Vehicle(2);
        testStreet[0][3] = new Vehicle(4);
        testStreet[0][8] = new Vehicle(5);

        expectedStreet = new Vehicle[1][10];
        expectedStreet[0][0] = new Vehicle(0);
        expectedStreet[0][1] = new Vehicle(1);
        expectedStreet[0][3] = new Vehicle(4);
        expectedStreet[0][8] = new Vehicle(1);

        trafficSimulator = new TrafficSimulator(testStreet);
        trafficSimulator.simulateDeceleration();
        assertArrayEquals(expectedStreet, trafficSimulator.getStreet());
    }

    @Test
    public void testSimulateSlowDawdling() {
        testStreet = new Vehicle[1][10];
        testStreet[0][0] = new Vehicle(1);
        testStreet[0][4] = new Vehicle(5);
        testStreet[0][7] = new Vehicle(2);

        expectedStreet = new Vehicle[1][10];
        expectedStreet[0][0] = new Vehicle(0);
        expectedStreet[0][4] = new Vehicle(5);
        expectedStreet[0][7] = new Vehicle(2);

        trafficSimulator = new TrafficSimulator(testStreet);
        trafficSimulator.setSlowDawdleProbability(1.0);
        trafficSimulator.simulateDawdling();
        assertArrayEquals(expectedStreet, trafficSimulator.getStreet());
    }

    @Test
    public void testFastDawdling() {
        testStreet = new Vehicle[1][10];
        testStreet[0][0] = new Vehicle(1);
        testStreet[0][4] = new Vehicle(5);
        testStreet[0][7] = new Vehicle(2);

        expectedStreet = new Vehicle[1][10];
        expectedStreet[0][0] = new Vehicle(1);
        expectedStreet[0][4] = new Vehicle(4);
        expectedStreet[0][7] = new Vehicle(1);

        trafficSimulator = new TrafficSimulator(testStreet);
        trafficSimulator.setFastDawdleProbability(1.0);
        trafficSimulator.simulateDawdling();
        assertArrayEquals(expectedStreet, trafficSimulator.getStreet());
    }

    @Test
    public void testSimulateMovement() {
        testStreet = new Vehicle[1][10];
        testStreet[0][0] = new Vehicle(3);
        testStreet[0][4] = new Vehicle(2);
        testStreet[0][8] = new Vehicle(2);
        testStreet[0][9] = new Vehicle(3);

        expectedStreet = new Vehicle[1][10];
        expectedStreet[0][3] = new Vehicle(3);
        expectedStreet[0][6] = new Vehicle(2);
        expectedStreet[0][0] = new Vehicle(2);
        expectedStreet[0][2] = new Vehicle(3);

        trafficSimulator = new TrafficSimulator(testStreet);
        trafficSimulator.setIteration(1);
        trafficSimulator.simulateMovement();
        assertArrayEquals(expectedStreet, trafficSimulator.getStreet());
    }

    @Test
    public void testSimulateTrackSwitchingIsPossible() {
        testStreet = new Vehicle[2][20];
        testStreet[0][5] = new Vehicle(5);
        testStreet[0][11] = new Vehicle(3);

        expectedStreet = new Vehicle[2][20];
        expectedStreet[1][5] = new Vehicle(5);
        expectedStreet[0][11] = new Vehicle(3);


        trafficSimulator = new TrafficSimulator(testStreet);
        trafficSimulator.setSwitchProbability(1.0);
        trafficSimulator.simulateTrackSwitching();
        assertArrayEquals(expectedStreet, trafficSimulator.getStreet());
    }

    @Test
    public void testSimulateTrackSwitchingIsNotPossibleInBack() {
        testStreet = new Vehicle[2][20];
        testStreet[0][5] = new Vehicle(5);
        testStreet[0][8] = new Vehicle(3);
        testStreet[1][1] = new Vehicle(2);

        expectedStreet = new Vehicle[2][20];
        expectedStreet[0][5] = new Vehicle(5);
        expectedStreet[0][8] = new Vehicle(3);
        expectedStreet[1][1] = new Vehicle(2);

        trafficSimulator = new TrafficSimulator(testStreet);
        trafficSimulator.setSwitchProbability(1.0);
        trafficSimulator.simulateTrackSwitching();
        assertArrayEquals(expectedStreet, trafficSimulator.getStreet());
    }

    @Test
    public void testSimulateTrackSwitchingIsNotPossibleInFront() {
        testStreet = new Vehicle[2][20];
        testStreet[0][5] = new Vehicle(5);
        testStreet[0][8] = new Vehicle(3);
        testStreet[1][11] = new Vehicle(2);

        expectedStreet = new Vehicle[2][20];
        expectedStreet[0][5] = new Vehicle(5);
        expectedStreet[0][8] = new Vehicle(3);
        expectedStreet[1][11] = new Vehicle(2);

        trafficSimulator = new TrafficSimulator(testStreet);
        trafficSimulator.setSwitchProbability(1.0);
        trafficSimulator.simulateTrackSwitching();
        assertArrayEquals(expectedStreet, trafficSimulator.getStreet());
    }

    @Test
    public void testSimulateTrackSwitchingIsPossibleWithThreeTracks() {
        testStreet = new Vehicle[3][20];
        testStreet[2][2] = new Vehicle(5);
        testStreet[2][5] = new Vehicle(3);
        testStreet[0][5] = new Vehicle(1);

        expectedStreet = new Vehicle[3][20];
        expectedStreet[1][2] = new Vehicle(5);
        expectedStreet[2][5] = new Vehicle(3);
        expectedStreet[0][5] = new Vehicle(1);

        trafficSimulator = new TrafficSimulator(testStreet);
        trafficSimulator.setSwitchProbability(1.0);
        trafficSimulator.simulateTrackSwitching();
        assertArrayEquals(expectedStreet, trafficSimulator.getStreet());
    }


}
