package b.lang.substitutions;

import b.lang.bobjectvisitors.IBObjectVisitor;

import java.util.Set;

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

    @Override
    public String accept(IBObjectVisitor visitor) {
        return visitor.visit(this);
    }

}
