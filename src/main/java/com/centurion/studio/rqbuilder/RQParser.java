package com.centurion.studio.rqbuilder;

import com.centurion.studio.rqbuilder.scanner.Scanner;
import com.centurion.studio.rqbuilder.scanner.Token;
import com.centurion.studio.rqbuilder.ast.Conjunction;
import com.centurion.studio.rqbuilder.ast.Disjunction;
import com.centurion.studio.rqbuilder.ast.Formula;
import com.centurion.studio.rqbuilder.ast.Predicate;

import java.util.ArrayList;
import java.util.List;

public class RQParser {

    private final Scanner scanner;
    private List<Token> tokens;
    private int current = 0;

    public RQParser() {
        this.scanner = new Scanner();
    }

    public Formula parse(String value) throws RQException {
        this.tokens = scanner.scan(value);
        this.current = 0;
        return disjunction();
    }

    private Formula disjunction() throws RQException {
        List<Formula> formulas = new ArrayList<>();
        formulas.add(conjunction());
        while (checkAndAdvance(Token.Type.OR)) {
            formulas.add(conjunction());
        }

        return new Disjunction(formulas);
    }

    private Formula conjunction() throws RQException {
        List<Formula> formulas = new ArrayList<>();
        formulas.add(group());
        while (checkAndAdvance(Token.Type.AND)) {
            formulas.add(group());
        }

        return new Conjunction(formulas);
    }

    private Formula group() throws RQException {
        if (checkAndAdvance(Token.Type.L_PAREN)) {
            Formula formula = disjunction();
            requireToken(Token.Type.R_PAREN);
            return formula;
        }

        return predicate();
    }

    private Formula predicate() throws RQException {
        Token selector = consume();
        requireToken(Token.Type.EQUAL);
        Token operator = consume();
        requireToken(Token.Type.EQUAL);
        Token value = consume();
        return new Predicate(selector, operator, value);
    }

    private Token consume() throws RQException {
        Token token = tokens.get(current);
        if (token.getType() == Token.Type.IDENTIFIER) {
            this.current++;
            return token;
        }

        throw new RQException(message(token.getColumn(), Token.Type.IDENTIFIER, token.getType()));
    }

    private void requireToken(Token.Type type) throws RQException {
        Token token = tokens.get(current);
        if (token.getType() == type) {
            this.current++;
            return;
        }

        throw new RQException(message(token.getColumn(), type, token.getType()));
    }


    private boolean checkAndAdvance(Token.Type type) {
        Token currentToken = tokens.get(current);
        if (currentToken.getType() != type) {
            return false;
        }

        current++;
        return true;
    }

    private String message(int column, Token.Type expected, Token.Type actual) {
        return String.format(
                "Expected token of type %s at column %d. %s was found instead",
                expected.name(),
                column,
                actual.name()
        );
    }
}
