package fr.emalios.calculatrice;

import fr.emalios.calculatrice.token.NumberToken;
import fr.emalios.calculatrice.token.Token;
import fr.emalios.calculatrice.token.TokenType;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class LexerTest {

    private Scanner scanner;

    @Before
    public void before() {
        this.scanner = new Scanner();
    }

    @Test
    public void test_lexingOfSimpleNumber() {
        String source = "1";
        List<Token> tokens = this.scanner.scanTokens(source);
        assertEquals("Wrong size", 1 , tokens.size());
        assertEquals("Wrong token", NumberToken.of(1), tokens.get(0));
    }

    @Test
    public void test_lexingOfSimpleOperators() {
        String source = "+";
        List<Token> tokens = this.scanner.scanTokens(source);
        assertEquals("Wrong size", 1 , tokens.size());
        assertEquals("Wrong token", TokenType.ADD, tokens.get(0));

        source = "-";
        tokens = this.scanner.scanTokens(source);
        assertEquals("Wrong size", 1 , tokens.size());
        assertEquals("Wrong token", TokenType.SUB, tokens.get(0));

        source = "*";
        tokens = this.scanner.scanTokens(source);
        assertEquals("Wrong size", 1 , tokens.size());
        assertEquals("Wrong token", TokenType.MUL, tokens.get(0));

        source = "/";
        tokens = this.scanner.scanTokens(source);
        assertEquals("Wrong size", 1 , tokens.size());
        assertEquals("Wrong token", TokenType.DIV, tokens.get(0));
    }

    @Test
    public void test_lexingOfSimpleOperation() {
        String source = "1+9";
        List<Token> tokens = this.scanner.scanTokens(source);
        List<Token> expected = new ArrayList<>();
        expected.add(NumberToken.of(1));
        expected.add(TokenType.ADD);
        expected.add(NumberToken.of(9));
        assertEquals("Wrong size", 3, tokens.size());
        assertEquals("Wrong token", expected, tokens);

        System.out.println("-----------------------------------------------------");
        source = "11+99";
        tokens = this.scanner.scanTokens(source);
        expected = new ArrayList<>();
        expected.add(NumberToken.of(11));
        expected.add(TokenType.ADD);
        expected.add(NumberToken.of(99));
        assertEquals("Wrong size", 3, tokens.size());
        assertEquals("Wrong token", expected, tokens);

        System.out.println("-----------------------------------------------------");
        source = "11+9";
        tokens = this.scanner.scanTokens(source);
        expected = new ArrayList<>();
        expected.add(NumberToken.of(11));
        expected.add(TokenType.ADD);
        expected.add(NumberToken.of(9));
        assertEquals("Wrong size", 3, tokens.size());
        assertEquals("Wrong token", expected, tokens);

        System.out.println("-----------------------------------------------------");
        source = "1+99";
        tokens = this.scanner.scanTokens(source);
        expected = new ArrayList<>();
        expected.add(NumberToken.of(1));
        expected.add(TokenType.ADD);
        expected.add(NumberToken.of(99));
        assertEquals("Wrong size", 3, tokens.size());
        assertEquals("Wrong token", expected, tokens);
    }

}
