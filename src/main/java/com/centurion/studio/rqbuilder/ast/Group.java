package com.centurion.studio.rqbuilder.ast;

import com.centurion.studio.rqbuilder.RQBuilder;

public class Group implements Formula {

    private final Formula formula;

    public Group(Formula formula) {
        this.formula = formula;
    }

    public Formula getFormula() {
        return formula;
    }

    @Override
    public <R, A> R accept(RQBuilder<R, A> visitor, A param) {
        return visitor.visit(this, param);
    }
}
