package com.vdurmont.emoji;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class EmojiParserTest {


    @Test
    public void removeAllEmojis_removes_all_the_emojis_from_the_string() {
        // GIVEN
        String input = "An 😀awesome 😃string 😄with " +
                       "a few 😉emojis!";

        // WHEN
        String result = EmojiParser.removeAllEmojis(input);

        // THEN
        String expected = "An awesome string with a few emojis!";
        assertEquals(expected, result);
    }

    @Test
    public void testContainsEmoji() {
        String input = "An 😀awesome 😃string 😄with " +
                       "a few 😉emojis!";
        assertTrue(EmojiParser.containsEmojis(input));
    }

    @Test
    public void testContainsEmoji2() {
        String input = "An awesome string with " +
                       "a few emojis!";
        assertFalse(EmojiParser.containsEmojis(input));
    }
}
