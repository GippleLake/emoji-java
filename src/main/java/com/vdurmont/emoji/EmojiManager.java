package com.vdurmont.emoji;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Holds the loaded emojis and provides search functions.
 *
 * @author Vincent DURMONT [vdurmont@gmail.com]
 */
public class EmojiManager {

    private static final String PATH = "/emojis.json";
    private static final EmojiTrie EMOJI_TRIE;

    static {
        try {
            InputStream stream = EmojiLoader.class.getResourceAsStream(PATH);
            List<Emoji> emojis = EmojiLoader.loadEmojis(stream);
            EMOJI_TRIE = new EmojiTrie(emojis);
            stream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * No need for a constructor, all the methods are static.
     */
    private EmojiManager() {
    }


    /**
     * Returns the {@link com.vdurmont.emoji.Emoji} for a given unicode.
     *
     * @param unicode the the unicode
     * @return the associated {@link com.vdurmont.emoji.Emoji}, null if the unicode is unknown
     */
    public static Emoji getByUnicode(String unicode) {
        if (unicode == null) {
            return null;
        }
        return EMOJI_TRIE.getEmoji(unicode);
    }


    /**
     * Checks if sequence of chars contain an emoji.
     *
     * @param sequence Sequence of char that may contain emoji in full or partially.
     * @return &lt;li&gt; Matches.EXACTLY if char sequence in its entirety is an emoji &lt;/li&gt; &lt;li&gt;
     * Matches.POSSIBLY if char sequence matches prefix of an emoji &lt;/li&gt; &lt;li&gt; Matches.IMPOSSIBLE if char
     * sequence matches no emoji or prefix of an emoji &lt;/li&gt;
     */
    public static EmojiTrie.Matches isEmoji(char[] sequence) {
        return EMOJI_TRIE.isEmoji(sequence);
    }

}
