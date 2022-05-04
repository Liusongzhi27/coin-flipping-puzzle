package coins.state;

import org.junit.jupiter.api.Test;

import java.util.BitSet;

import static org.apache.commons.math3.util.CombinatoricsUtils.binomialCoefficient;
import static org.junit.jupiter.api.Assertions.*;

class CoinsTest {

    private Coins state1 = new Coins(7, 3); // the original initial state

    private Coins state2; // the goal state
    {
        BitSet bs = new BitSet(7);
        bs.set(0, 7);
        state2 = new Coins(7, 3, bs);
    }

    @Test
    void isGoalTest() {
        assertFalse(state1.isGoal());
        assertTrue(state2.isGoal());
    }

    @Test
    void canFlipTest(){
        BitSet testFlip1 = new BitSet(7);
        testFlip1.set(0);
        testFlip1.set(4);
        testFlip1.set(5);
        testFlip1.set(7);

        BitSet testFlip2 = new BitSet(8);

        BitSet testFlip3 = new BitSet(7);
        testFlip3.set(0);
        testFlip3.set(1);
        testFlip3.set(3);

        assertFalse(state1.canFlip(testFlip1));
        assertFalse(state1.canFlip(testFlip2));
        assertTrue(state1.canFlip(testFlip3));
    }

    @Test
    void flipTest() {
        BitSet testFlip1 = new BitSet(7);
        testFlip1.set(0);
        testFlip1.set(4);
        testFlip1.set(5);

        state1.flip(testFlip1);
        assertEquals(testFlip1, state1.getCoins());
    }

    @Test
    void generateFlipsTest() {
        assertEquals(binomialCoefficient(7, 3), state1.getFlips().size());
    }

    @Test
    void equalsTest() {
        Coins testCoins1 = new Coins(7, 3);
        Coins testCoins2 = new Coins(6,3);
        Coins testCoins3 = new Coins(7, 2);

        assertNotEquals(state1, state2);
        assertNotEquals(state1, testCoins2);
        assertNotEquals(state1, testCoins3);
        assertEquals(state1, testCoins1);
    }

    @Test
    void hashCodeTest() {
        Coins testCoins1 = new Coins(7, 3);

        assertEquals(testCoins1.hashCode(), state1.hashCode());
        assertNotEquals(state2.hashCode(), state1.hashCode());
    }

    @Test
    void cloneTest() {
        Coins testCoins1 = state1.clone();

        assertEquals(testCoins1, state1);
    }

    @Test
    void toStringTest() {
        assertEquals("O|O|O|O|O|O|O", state1.toString());
        assertEquals("1|1|1|1|1|1|1", state2.toString());
    }

    @Test
    void checkArgumentsTest() {
        assertThrows(IllegalArgumentException.class, () -> {new Coins(3, 0);});
        assertThrows(IllegalArgumentException.class, () -> {new Coins(0, 3);});
        assertThrows(IllegalArgumentException.class, () -> {new Coins(3, 4);});
    }
}