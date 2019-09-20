
import java.util.Arrays;
import java.util.Stack;

/**
 * Найти подстроку максимальной длины состоящую из правильной
 * последовательности открывающихся и закрывающихся квадратных,
 * круглых и фигурных скобок.
 *
 * N + N*N. Likely could be improved.
 */
class LongestBalancedSubsequence {
    public static String find(String string) {
        // Find all balanced substrings ignoring type of brackets.
        // matchingPositions holds -1 for non-closing brackets and
        // position of opening bracket for closing brackets.
        int[] matchingPositions = new int[string.length()];
        Arrays.fill(matchingPositions, -1);
        Stack<Integer> openingBracketPositionsStack = new Stack<>();
        for (int i = 0; i < string.length(); i++) {
            char character = string.charAt(i);
            if (isOpeningBracket(character)) {
                openingBracketPositionsStack.push(i);
            }
            else if (!openingBracketPositionsStack.isEmpty()) {
                int openingBracketPosition = openingBracketPositionsStack.pop();
                matchingPositions[i] = openingBracketPosition;
                if (openingBracketPosition > 0 && isClosingBracket(string.charAt(openingBracketPosition - 1))) {
                    if (matchingPositions[openingBracketPosition - 1] != -1) {
                        matchingPositions[i] = matchingPositions[openingBracketPosition - 1];
                    }
                }
            }
        }

        // find longest substring
        int maxLength = 0;
        String maxSubstring = "";

        for (int i = 0; i < string.length(); i++) {
            if (matchingPositions[i] != -1) {
                String substring = string.substring(matchingPositions[i], i + 1);
                if (isBalancedCorrectly(substring)) {
                    if (maxLength < i - matchingPositions[i] + 1) {
                        maxLength = i - matchingPositions[i] + 1;
                        maxSubstring = substring;
                    }
                }
            }
        }
        return maxSubstring;
    }

    private static boolean isOpeningBracket(char character) {
        return character == '(' || character == '{' || character == '[';
    }

    private static boolean isClosingBracket(char character) {
        return character == ')' || character == '}' || character == ']';
    }

    private static Boolean isBalancedCorrectly(String testString) {
        if (testString.isEmpty()) {
            return true;
        }

        Stack<Character> openingBracketsStack = new Stack<>();

        char[] stringToCharArray = testString.toCharArray();

        for (Character character : stringToCharArray) {
            if (isOpeningBracket(character)) {
                openingBracketsStack.push(character);
            }
            else if (!openingBracketsStack.empty()) {
                char characterFromStack = openingBracketsStack.pop();
                if (
                        (character == ']' && characterFromStack != '[') ||
                        (character == '}' && characterFromStack != '{') ||
                        (character == ')' && characterFromStack != '(')
                ) {
                    return false;
                }
            }
        }
        return openingBracketsStack.empty();
    }
}




