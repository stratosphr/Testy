package b.lang.substitutions;

import b.lang.bobjectvisitors.formatter.IBFormatter;
import b.lang.exprs.AAssignable;
import b.lang.exprs.bool.IBoolExpr;

import java.util.Set;

/**
 * Created by gvoiron on 02/06/19.
 * Time : 23:20
 */
public final class Assignments extends ASubstitution {

    private Set<AAssignment> assignments;

    public Assignments(Set<AAssignment> assignments) {
        this.assignments = assignments;
    }

    public Set<AAssignment> getAssignments() {
        return assignments;
    }

    @Override
    public IBoolExpr getPrd(Set<AAssignable> assignables) {
        return null;
    }

    @Override
    public String accept(IBFormatter visitor) {
        return null;
    }

}
