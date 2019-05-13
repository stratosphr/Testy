package b.lang;

import b.bobjectvisitors.IBObjectVisitor;
import b.lang.defs.ConstDef;
import b.lang.defs.FunDef;
import b.lang.defs.SetDef;
import b.lang.defs.VarDef;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by gvoiron on 12/05/19.
 * Time : 01:06
 */
public final class Machine extends AObject {

    private final Set<ConstDef> constDefs;
    private final Set<SetDef> setDefs;
    private final Set<VarDef> varDefs;
    private final Set<FunDef> funDefs;

    public Machine(LinkedHashSet<ConstDef> constDefs, LinkedHashSet<SetDef> setDefs, LinkedHashSet<VarDef> varDefs, LinkedHashSet<FunDef> funDefs) {
        this.constDefs = constDefs;
        this.setDefs = setDefs;
        this.varDefs = varDefs;
        this.funDefs = funDefs;
    }

    public Set<ConstDef> getConstDefs() {
        return constDefs;
    }

    public Set<SetDef> getSetDefs() {
        return setDefs;
    }

    public Set<VarDef> getVarDefs() {
        return varDefs;
    }

    public Set<FunDef> getFunDefs() {
        return funDefs;
    }

    @Override
    public String accept(IBObjectVisitor visitor) {
        return visitor.visit(this);
    }

}
