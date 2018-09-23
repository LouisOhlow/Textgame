

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class GameTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class GameTest
{
    private Item item1;
    private Continent continen1;

    /**
     * Default constructor for test class GameTest
     */
    public GameTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        item1 = new Item("TestItem", "This is a test Item", 5);
        continen1 = new Continent("TestContinent", item1);
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }

    @Test
    public void Test1()
    {
        continen1.addItem("TestItem", item1);
        assertEquals("TestItem", item1.getItemName());
        assertEquals(5, item1.getItemWeight());
    }
}

