package fr.emalios.calculatrice;

import fr.emalios.calculatrice.stream.StringBiStream;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class StringBiStreamTest {

    private StringBiStream stringBiStream;

    @Before
    public void before() {
        this.stringBiStream = new StringBiStream("Test.");
    }

    @Test
    public void test_nextMethod() {
        this.stringBiStream.next();
        assertEquals(Optional.of('e'), this.stringBiStream.next());
    }

    @Test
    public void test_peekMethod() {
        assertEquals(Optional.of('T'), this.stringBiStream.peek());
    }

    @Test
    public void test_previousMethod() {
        this.stringBiStream.next();
        this.stringBiStream.next();
        //Now, the current character is 's'.
        this.stringBiStream.previous();
        assertEquals(Optional.of('e'), this.stringBiStream.previous());
    }

    @Test
    public void test_isAtEndMethod() {
        this.stringBiStream.previous();
        assertFalse(this.stringBiStream.isCurrentValid());
        this.stringBiStream.next();
        this.stringBiStream.next();
        this.stringBiStream.next();
        this.stringBiStream.next();
        this.stringBiStream.next();
        this.stringBiStream.next();
        //Now, the current character don't exist.
        assertFalse(this.stringBiStream.isCurrentValid());
    }

}
