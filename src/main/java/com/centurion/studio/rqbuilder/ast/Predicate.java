package com.centurion.studio.rqbuilder.ast;

import com.centurion.studio.rqbuilder.scanner.Token;
import com.centurion.studio.rqbuilder.RQBuilder;

public class Predicate implements Formula {

    private final Token selector;
    private final Token operator;
    private final Token value;

    public Predicate(Token selector, Token operator, Token value) {
        this.selector = selector;
        this.operator = operator;
        this.value = value;
    }

    public Token getSelector() {
        return selector;
    }

    public Token getOperator() {
        return operator;
    }

    public Token getValue() {
        return value;
    }

    @Override
    public <R, A> R accept(RQBuilder<R, A> visitor, A param) {
        return visitor.visit(this, param);
    }
}
