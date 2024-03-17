package com.centurion.studio.rqbuilder.ast;

import com.centurion.studio.rqbuilder.RQBuilder;

import java.util.List;
// Logical and
public class Conjunction extends Connective {

    public Conjunction(List<Formula> children) {
        super(children);
    }

    @Override
    public <R, A> R accept(RQBuilder<R, A> visitor, A param) {
        return visitor.visit(this, param);
    }
}
