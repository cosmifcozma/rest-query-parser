package com.centurion.studio.rqbuilder.scanner;

import java.util.ArrayList;
import java.util.List;

public class Scanner {

    private static final char[] lexemes = new char[]{'(', ')', '=', ';', ','};


    // STATUS
    private int current = 0;
    private int start = 0;
    public String source;

    public List<Token> scan(String source) {
        List<Token> tokens = new ArrayList<>();
        this.current = this.start = 0;
        this.source = source;

        while (current < source.length()) {
            // We are at the beginning of the next lexeme.
            this.start = this.current;
            tokens.add(nexToken());
        }

        tokens.add(new Token(Token.Type.EOF, "", current));
        return tokens;
    }

    private Token nexToken() {
        char currentCharacter = source.charAt(current++);

        // Ignore whitespace.
        while (currentCharacter == ' ' || currentCharacter == '\r' || currentCharacter == '\t') {
            currentCharacter = source.charAt(current++);
        }

        switch (currentCharacter) {
            case '(':
                return new Token(Token.Type.L_PAREN, "(", current);
            case ')':
                return new Token(Token.Type.R_PAREN, ")", current);
            case '=':
                return new Token(Token.Type.EQUAL, "=", current);
            case '&':
                return new Token(Token.Type.AND, "&", current);
            case '|':
                return new Token(Token.Type.OR, "|", current);
            default:
                while (current < source.length() && !in(source.charAt(current))) {
                    current++;
                }

                String value = source.substring(start, current);
                return new Token(Token.Type.IDENTIFIER, value, start);
        }
    }


    private static boolean in(char value) {
        for (char character : lexemes) {
            if (character == value) {
                return true;
            }
        }
        return false;
    }
}
