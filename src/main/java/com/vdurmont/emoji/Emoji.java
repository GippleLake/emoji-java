package com.vdurmont.emoji;

import java.io.UnsupportedEncodingException;

/**
 * This class represents an emoji.<br>
 * <br>
 * This object is immutable so it can be used safely in a multithreaded context.
 *
 * @author Vincent DURMONT [vdurmont@gmail.com]
 */
public class Emoji {

    private String emoji;

    public Emoji() {
    }


    /**
     * Constructor for the Emoji.
     *
     * @param bytes the bytes that represent the emoji
     */
    protected Emoji(
        byte... bytes
    ) {
        try {
            this.emoji = new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int hashCode() {
        return emoji.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        return !(other == null || !(other instanceof Emoji)) &&
               ((Emoji) other).getEmoji().equals(getEmoji());
    }

    /**
     * Returns the unicode representation of the emoji
     *
     * @return the unicode representation
     */
    public String getEmoji() {
        return this.emoji;
    }

    public void setEmoji(String emoji) {
        this.emoji = emoji;
    }

    /**
     * @return the string representation
     */
    @Override
    public String toString() {
        return "Emoji{" +
               ", unicode='" + emoji + '\'' +
               '}';
    }
}
