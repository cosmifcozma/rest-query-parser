package com.centurion.studio.rqbuilder;

import com.centurion.studio.rqbuilder.ast.*;

/**
 * An interface for visiting AST nodes of the REST Query Builder.
 *
 * @param <R> Return type of the visitor's method.
 * @param <A> Type of optional parameter passed to the visitor's method.
 */
public interface RQBuilder<R, A> {

    R visit(Conjunction node, A param);

    R visit(Disjunction node, A param);

    R visit(Group node, A param);

    R visit(Predicate node, A param);
}
