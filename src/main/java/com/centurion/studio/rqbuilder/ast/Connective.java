package com.centurion.studio.rqbuilder.ast;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public abstract class Connective implements Formula, Iterable<Formula> {

    private final List<Formula> children;

    protected Connective(List<Formula> children) {
        this.children = children;
    }

    /**
     * Iterate over children nodes. The underlying collection is unmodifiable!
     */
    @Override
    public Iterator<Formula> iterator() {
        return children.iterator();
    }

    /**
     * Returns a copy of the children nodes.
     */
    public List<Formula> getChildren() {
        return new ArrayList<>(children);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Connective nodes = (Connective) o;
        return Objects.equals(children, nodes.children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(children);
    }
}
