package fr.emalios.calculatrice.stream;

import java.util.Optional;

public class StringBiStream implements BiStream {

    /**
     * Represent the source.
     */
    private final String source;

    /**
     * Represent the current indice.
     */
    private int current;

    /**
     * Constructor by default.
     * @param source string source.
     */
    public StringBiStream(String source) {
        this.source = source;
    }

    /**
     * Return current character and advance, empty if the current indice isn't valid.
     * @return current character.
     */
    @Override
    public Optional<Character> next() {
        var character= this.peek();
        this.current++;
        return character;
    }

    /**
     * Return current character and go back, empty if the current indice isn't valid.
     * @return current character.
     */
    @Override
    public Optional<Character> previous() {
        var character= this.peek();
        this.current--;
        return character;
    }

    /**
     * Return current character, empty if the current indice isn't valid.
     * @return current character.
     */
    @Override
    public Optional<Character> peek() {
        if(!this.isCurrentValid()) {
            return Optional.empty();
        }
        return Optional.of(this.source.charAt(this.current));
    }

    /**
     * Method who determines if indice is correct.
     * @return true if the current indice is between 0 and source length - 1 else, false.
     */
    @Override
    public boolean isCurrentValid() {
        return (this.current >= 0) && (this.current < this.source.length());
    }

    public int getCurrent() {
        return this.current;
    }
}
