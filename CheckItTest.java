
import org.testng.annotations.Test;


import static org.testng.AssertJUnit.assertEquals;

public class CheckItTest {

    // Predicate Coverage
    @Test
    public void whenPredicateIsTrue() {
        // Test where the predicate is true (true || (false && false))
        assertEquals("P is true", CheckIt.checkIt(true, false, false));
    }

    @Test
    public void whenPredicateIsFalse() {
        // Test where the predicate is false (false || (false && false))
        assertEquals("P isn't true", CheckIt.checkIt(false, false, false));
    }

    // Clause Coverage
    @Test
    public void testClauses() {
        // Test each clause independently

        // Clause 'a' is true
        assertEquals("P is true", CheckIt.checkIt(true, false, false));

        // Clause 'b' is true, 'c' is false, but 'b && c' is false
        assertEquals("P isn't true", CheckIt.checkIt(false, true, false));

        // Clause 'c' is true, 'b' is false, but 'b && c' is false
        assertEquals("P isn't true", CheckIt.checkIt(false, false, true));

        // Clauses 'b' and 'c' are both true
        assertEquals("P is true", CheckIt.checkIt(false, true, true));
    }

    // CACC
    @Test
    public void testCACC() {
        // Each test case should change the decision outcome by changing a single clause

        // 'a' changes the outcome
        assertEquals("P is true", CheckIt.checkIt(true, false, false));
        assertEquals("P isn't true", CheckIt.checkIt(false, false, false));

        // 'b && c' changes the outcome
        assertEquals("P is true", CheckIt.checkIt(false, true, true));
        assertEquals("P isn't true", CheckIt.checkIt(false, true, false));
    }

    // RACC
    @Test
    public void testRACC() {
        // Related clauses are 'b' and 'c' in the 'b && c' part of the predicate

        // Neither 'b' nor 'c' is true, so the outcome is false
        assertEquals("P isn't true", CheckIt.checkIt(false, false, false));

        // Both 'b' and 'c' are true, which is related to 'b && c' being true
        assertEquals("P is true", CheckIt.checkIt(false, true, true));

        // One of the related clauses 'b' or 'c' is false, so 'b && c' is false
        assertEquals("P isn't true", CheckIt.checkIt(false, true, false));
        assertEquals("P isn't true", CheckIt.checkIt(false, false, true));

        // The true value of 'a' dominates and makes the predicate true regardless of 'b && c'
        assertEquals("P is true", CheckIt.checkIt(true, false, true));
        assertEquals("P is true", CheckIt.checkIt(true, true, false));
    }
}
