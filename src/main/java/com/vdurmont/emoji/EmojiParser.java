package com.vdurmont.emoji;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Provides methods to parse strings with emojis.
 *
 * @author Vincent DURMONT [vdurmont@gmail.com]
 */
public class EmojiParser {

    /**
     * Removes all emojis from a String
     *
     * @param input the string to process
     * @return the string without any emoji
     */
    public static String removeAllEmojis(String input) {
        int prev = 0;
        StringBuilder sb = new StringBuilder();
        List<UnicodeCandidate> replacements = getUnicodeCandidates(input);
        for (UnicodeCandidate candidate : replacements) {
            sb.append(input.substring(prev, candidate.getEmojiStartIndex()));
            prev = candidate.getEmojiEndIndex();
        }
        return sb.append(input.substring(prev)).toString();
    }

    /**
     * Generates a list UnicodeCandidates found in input string. A
     * UnicodeCandidate is created for every unicode emoticon found in input
     * string, additionally if Fitzpatrick modifier follows the emoji, it is
     * included in UnicodeCandidate. Finally, it contains start and end index of
     * unicode emoji itself (WITHOUT Fitzpatrick modifier whether it is there or
     * not!).
     *
     * @param input String to find all unicode emojis in
     * @return List of UnicodeCandidates for each unicode emote in text
     */
    private static List<UnicodeCandidate> getUnicodeCandidates(String input) {
        char[] inputCharArray = input.toCharArray();
        List<UnicodeCandidate> candidates = new ArrayList<UnicodeCandidate>();
        UnicodeCandidate next;
        for (int i = 0; (next = getNextUnicodeCandidate(inputCharArray, i)) != null;
             i = next.getEmojiEndIndex()) {
            candidates.add(next);
        }

        return candidates;
    }

    /**
     * Finds the next UnicodeCandidate after a given starting index
     *
     * @param chars char array to find UnicodeCandidate in
     * @param start starting index for search
     * @return the next UnicodeCandidate or null if no UnicodeCandidate is found after start index
     */
    private static UnicodeCandidate getNextUnicodeCandidate(char[] chars, int start) {
        for (int i = start; i < chars.length; i++) {
            int emojiEnd = getEmojiEndPos(chars, i);

            if (emojiEnd != -1) {
                Emoji emoji = EmojiManager.getByUnicode(new String(chars, i, emojiEnd - i));
                return new UnicodeCandidate(
                    emoji,
                    i
                );
            }
        }

        return null;
    }

    /**
     * Returns end index of a unicode emoji if it is found in text starting at
     * index startPos, -1 if not found.
     * This returns the longest matching emoji, for example, in
     * "\uD83D\uDC68\u200D\uD83D\uDC69\u200D\uD83D\uDC66"
     * it will find alias:family_man_woman_boy, NOT alias:man
     *
     * @param text     the current text where we are looking for an emoji
     * @param startPos the position in the text where we should start looking for an emoji end
     * @return the end index of the unicode emoji starting at startPos. -1 if not found
     */
    protected static int getEmojiEndPos(char[] text, int startPos) {
        int best = -1;
        for (int j = startPos + 1; j <= text.length; j++) {
            EmojiTrie.Matches status = EmojiManager.isEmoji(Arrays.copyOfRange(
                text,
                startPos,
                j
            ));

            if (status.exactMatch()) {
                best = j;
            } else if (status.impossibleMatch()) {
                return best;
            }
        }

        return best;
    }

    /**
     * check if input string contains emoji
     *
     * @param input string
     * @return boolean
     */
    public static boolean containsEmojis(String input) {
        List<UnicodeCandidate> unicodeCandidates = getUnicodeCandidates(input);
        return !unicodeCandidates.isEmpty();
    }

    public static class UnicodeCandidate {

        private final Emoji emoji;
        private final int startIndex;

        private UnicodeCandidate(Emoji emoji, int startIndex) {
            this.emoji = emoji;
            this.startIndex = startIndex;
        }

        public Emoji getEmoji() {
            return emoji;
        }


        public int getEmojiStartIndex() {
            return startIndex;
        }


        public int getEmojiEndIndex() {
            return startIndex + emoji.getEmoji().length();
        }
    }

}
