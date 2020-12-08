package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer(3);

        assertTrue(arb.isEmpty());
        assertFalse(arb.isFull());

        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(3);

        assertTrue(arb.isFull());
        assertTrue(arb.dequeue() == 1);
        assertTrue(arb.peek() == 2);
        assertTrue(arb.dequeue() == 2);
        assertTrue(arb.dequeue() == 3);
        assertTrue(arb.isEmpty());
    }

    @Test
    public void testIterator(){
        ArrayRingBuffer<Integer> arrays = new ArrayRingBuffer<>(3);
        int[] nums = {3, 4, 5};
        for (int i = 0; i < nums.length; i++) {
            arrays.enqueue(nums[i]);
        }
        int i = 0;
        for (Integer num: arrays) {
            assertEquals((int)num, nums[i++]);
        }
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
