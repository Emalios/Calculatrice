package fr.emalios.calculatrice.stream;

import java.util.Optional;

/**
 * Class who represent a bidirectional stream.
 */
public interface BiStream {

    Optional<Character> next();

    Optional<Character> previous();

    Optional<Character> peek();

    boolean isCurrentValid();

}
