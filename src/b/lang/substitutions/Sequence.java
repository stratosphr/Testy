package b.lang.substitutions;

import b.lang.bobjectvisitors.IBObjectVisitor;

import java.util.List;

/**
 * Created by gvoiron on 27/05/19.
 * Time : 22:14
 */
public final class Sequence extends ASubstitution {

    private List<ASubstitution> substitutions;

    public Sequence(List<ASubstitution> substitutions) {
        this.substitutions = substitutions;
    }

    public List<ASubstitution> getSubstitutions() {
        return substitutions;
    }

    @Override
    public String accept(IBObjectVisitor visitor) {
        return visitor.visit(this);
    }

}
