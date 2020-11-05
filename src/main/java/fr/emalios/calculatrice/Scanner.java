package fr.emalios.calculatrice;

import fr.emalios.calculatrice.stream.StringBiStream;
import fr.emalios.calculatrice.token.NumberToken;
import fr.emalios.calculatrice.token.Token;
import fr.emalios.calculatrice.token.TokenType;

import java.util.ArrayList;
import java.util.List;

/**
 * Class who converts a source as String into a list of tokens.
 */
public class Scanner {

    /**
     * TODO: fill this description
     * @param source source to convert to list of tokens.
     * @return list of tokens.
     */
    /*
    This method scan one by one character in source and associate the character(s) to a token.
    To do that, we use the utility class StringBiStream. We iterate while current don't be at the end.
     */
    public List<Token> scanTokens(String source) {
        List<Token> tokens = new ArrayList<>();
        StringBiStream stream = new StringBiStream(source);
        while (stream.isCurrentValid()) {
            /*
            Firstly, we check that current character isn't a number, if is a number, we iterate
            while next character is a number to obtain one NumberToken with a value of the number funded.
            */
            char character = stream.next().get(); //current : 1, ensuite 1
            System.out.println("Character is : " + character);
            if(Character.isDigit(character)) {
                /*
                If character is a digit, firstly, we add it to a variable number who contains the entire number.
                 */
                StringBuilder builder = new StringBuilder(String.valueOf(character));
                System.out.println("Character before while : " + stream.peek());
                /*
                 We check if the character is the last character to avoid empty problem,
                 if yes we parse this character as int and add it to token list as number token and we end lexing.
                 */
                if(!stream.isCurrentValid()) {
                    System.out.println("Continue with number as string : " + builder);
                    tokens.add(NumberToken.of(Integer.parseInt(builder.toString())));
                    break;
                }
                if(!Character.isDigit(stream.peek().get())) {
                    System.out.println("Character isn't a digit : " + stream.peek());
                    tokens.add(NumberToken.of(Integer.parseInt(builder.toString())));
                    continue;
                }
                /*
                At this point we already have the first character inside the StringBuilder
                So we need to iterate while the stream is valid and the after character isn't a digit
                 */
                while (stream.isCurrentValid() && Character.isDigit(character = stream.next().get())) {
                    System.out.println("While with character : " + character);
                    builder.append(character);
                }
                tokens.add(NumberToken.of(Integer.parseInt(builder.toString())));
                /*
                If the current indice of the stream is valid, we need to go back.
                 */
                if(stream.isCurrentValid()) {
                    stream.previous();
                }
                continue;
            }
            /*
            Then, we check if current character equals to a character of token types. If yes, we add this token in the list
             */
            switch (character) {
                case '/':
                    tokens.add(TokenType.DIV);
                    break;
                case '*':
                    tokens.add(TokenType.MUL);
                    break;
                case '-':
                    tokens.add(TokenType.SUB);
                    break;
                case '+':
                    tokens.add(TokenType.ADD);
                    break;
                default:
                    //If the character don't match with any characters then we throw an error.
                    throw new IllegalStateException("Unexpected value: " + character);
            }

        }
        return tokens;
    }
}
