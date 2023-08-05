package entities;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link ExamCenter}.
 * This class contains test cases to verify the behavior of the {@link ExamCenter} class methods.
 */
class ExamCenterTest {
    static ExamCenter examCenter;

    /**
     * Set up the test environment before each test case.
     * Creates a new {@link ExamCenter} instance.
     */
    @BeforeAll
    static void setup(){
        examCenter = new ExamCenter(101);
    }

    /**
     * Test the {@link ExamCenter#getBlockName()} method to ensure it correctly returns the name of the ExamCenter block.
     */
    @Test
    void testGetBlockName() {
        assertEquals("examcenter", examCenter.getBlockName());
    }
}