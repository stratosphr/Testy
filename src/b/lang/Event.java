package b.lang;

import b.lang.bobjectvisitors.IBObjectVisitor;
import b.lang.substitutions.ASubstitution;

/**
 * Created by gvoiron on 27/05/19.
 * Time : 23:18
 */
public final class Event extends AObject {

    private final String name;
    private final ASubstitution substitution;

    public Event(String name, ASubstitution substitution) {
        this.name = name;
        this.substitution = substitution;
    }

    public String getName() {
        return name;
    }

    public ASubstitution getSubstitution() {
        return substitution;
    }

    @Override
    public String accept(IBObjectVisitor visitor) {
        return visitor.visit(this);
    }

}
