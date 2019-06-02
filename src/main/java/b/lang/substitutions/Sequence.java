package b.lang.substitutions;

import b.lang.bobjectvisitors.formatter.IBFormatter;
import b.lang.exprs.AAssignable;
import b.lang.exprs.bool.IBoolExpr;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by gvoiron on 27/05/19.
 * Time : 22:14
 */
public final class Sequence extends ASubstitution {

    private Set<ASubstitution> substitutions;

    public Sequence(Set<ASubstitution> substitutions) {
        this.substitutions = substitutions;
    }

    public Set<ASubstitution> getSubstitutions() {
        return substitutions;
    }

    private ASubstitution getSurrogateSubstitution() {
        if (substitutions.stream().anyMatch(substitution -> !(substitution instanceof AAssignment))) {
            throw new NotSupportedError("Unable to compute surrogate substitution for the following sequence substitution :\n" + toString());
        } else {
            return new Assignments(substitutions.stream().map(substitution -> (AAssignment) substitution).collect(Collectors.toCollection(LinkedHashSet::new)));
        }
    }

    @Override
    public String accept(IBFormatter visitor) {
        return visitor.visit(this);
    }

    @Override
    public IBoolExpr getPrd(Set<AAssignable> assignables) {
        return getSurrogateSubstitution().getPrd(assignables);
    }

}
