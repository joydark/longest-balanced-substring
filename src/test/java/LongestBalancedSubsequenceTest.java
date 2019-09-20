import org.testng.Assert;
import org.testng.annotations.Test;

public class LongestBalancedSubsequenceTest {

    @Test
    public void emptyStringShouldGiveNothing() {
        Assert.assertEquals("", LongestBalancedSubsequence.find(""));
    }

    @Test
    public void openingBracketShouldGiveNothing() {
        Assert.assertEquals("", LongestBalancedSubsequence.find("{"));
        Assert.assertEquals("", LongestBalancedSubsequence.find("["));
        Assert.assertEquals("", LongestBalancedSubsequence.find("("));
    }

    @Test
    public void closingBracketShouldGiveNothing() {
        Assert.assertEquals("", LongestBalancedSubsequence.find("}"));
        Assert.assertEquals("", LongestBalancedSubsequence.find("]"));
        Assert.assertEquals("", LongestBalancedSubsequence.find(")"));
    }

    @Test
    public void pairShouldEndUpInResult() {
        Assert.assertEquals("{}", LongestBalancedSubsequence.find("{}"));
        Assert.assertEquals("[]", LongestBalancedSubsequence.find("[]"));
        Assert.assertEquals("()", LongestBalancedSubsequence.find("()"));
    }

    @Test
    public void incorrectPairShouldGiveNothing() {
        Assert.assertEquals("", LongestBalancedSubsequence.find("{]"));
        Assert.assertEquals("", LongestBalancedSubsequence.find("{)"));
        Assert.assertEquals("", LongestBalancedSubsequence.find("[}"));
        Assert.assertEquals("", LongestBalancedSubsequence.find("[)"));
        Assert.assertEquals("", LongestBalancedSubsequence.find("(}"));
        Assert.assertEquals("", LongestBalancedSubsequence.find("(]"));
    }

    @Test
    public void nonBracketsShouldBeIgnored() {
        Assert.assertEquals("", LongestBalancedSubsequence.find("ABC"));
    }

    @Test
    public void twoIndependentGroupsShouldBeBothInResult() {
        Assert.assertEquals("()()", LongestBalancedSubsequence.find("()()("));
        Assert.assertEquals("()()", LongestBalancedSubsequence.find("()())"));
        Assert.assertEquals("()()", LongestBalancedSubsequence.find("{()()"));
        Assert.assertEquals("()()", LongestBalancedSubsequence.find("}()()"));
        Assert.assertEquals("(){}", LongestBalancedSubsequence.find("(){}["));
    }

    @Test
    public void correctStartingSequenceShouldBeInResult() {
        Assert.assertEquals("{}", LongestBalancedSubsequence.find("{}(((}(("));
    }

    @Test
    public void correctEndingSequenceShouldBeInResult() {
        Assert.assertEquals("[]", LongestBalancedSubsequence.find("([(((([]"));
    }

    @Test
    public void correctSequenceInTheMiddleShouldBeInResult() {
        Assert.assertEquals("()", LongestBalancedSubsequence.find("{{]{{(){{]{{"));
    }

    @Test
    public void testComplexSequences() {
        Assert.assertEquals("({})", LongestBalancedSubsequence.find("({})"));
        Assert.assertEquals("({})[()]", LongestBalancedSubsequence.find("({})[()]"));
        Assert.assertEquals("(()(()))", LongestBalancedSubsequence.find("(()(()))"));
        Assert.assertEquals("(()(()))", LongestBalancedSubsequence.find("(()({}}))99(()(()))]"));
    }
}
