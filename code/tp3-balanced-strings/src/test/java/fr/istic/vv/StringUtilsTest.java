package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static fr.istic.vv.StringUtils.isBalanced;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StringUtilsTest {
    @ParameterizedTest
    @ValueSource(strings = {"", "{}", "[]", "()", "{[]}", "([])", "({})", "{()[]}", "([]{})", "[({})]"
            , "Hello World !"
            , "{a}", "[a]", "(a)"
            , "{a[a]}", "(a[a])", "(a{a})", "{a(a)[a]}", "(a[a]{a})"})
    void testIsBalanced(String input) {
        assertTrue(isBalanced(input));
    }

    @ParameterizedTest
    @ValueSource(strings = { "}", "]", ")", "}]", ")]", "{", "[", "(", "{[", "[(", "{]", "[)", "(}", "{)[]", "([)]"
            , "{}]", "()[}", "{[])", "{})[]"
            , "a}", "a]a", ")a", "a}a]", "a)a]"
            , "a{", "a[", "a(", "a{a[", "a[a("
            , "{a]", "[a)", "(a}", "{a)[a]", "a(a[a)]"
            , "a{a}]", "a(a)[a}", "a{a[a])", "a{a}a)a[]"})
    void testIsNotBalanced(String input) {
        assertFalse(isBalanced(input));
    }

}