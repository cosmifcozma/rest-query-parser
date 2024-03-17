package com.centurion.studio.rqbuilder.scanner;

public final class Token {
    private final Type type;
    private final String lexeme;
    private final int column;

    public Token(Type type, String lexeme, int column) {
        this.type = type;
        this.lexeme = lexeme;
        this.column = column;
    }

    public Type getType() {
        return type;
    }

    public String getLexeme() {
        return lexeme;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public String toString() {
        return "[" + type.name() + ", " + lexeme + ", " + column + ']';
    }

    public enum Type {
        L_PAREN, // (
        R_PAREN, // )

        EQUAL, // =
        AND, // &
        OR, // |

        IDENTIFIER,

        EOF
    }
}
