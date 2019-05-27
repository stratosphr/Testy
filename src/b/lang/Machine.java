package b.lang;

import b.lang.bobjectvisitors.IBObjectVisitor;
import b.lang.defs.ConstDef;
import b.lang.defs.FunDef;
import b.lang.defs.SetDef;
import b.lang.defs.VarDef;
import b.lang.exprs.ASymbol;
import b.lang.substitutions.ASubstitution;
import b.lang.substitutions.Skip;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by gvoiron on 12/05/19.
 * Time : 01:06
 */
public final class Machine extends AObject {

    private final String name;
    private final Set<ConstDef> constDefs;
    private final Set<SetDef> setDefs;
    private final Set<VarDef> varDefs;
    private final Set<FunDef> funDefs;
    private final LinkedHashSet<Event> events;
    private final Map<String, ASymbol> symbolsTable;
    private ASubstitution initialisation;

    public Machine(String name) {
        this.name = name;
        this.constDefs = new LinkedHashSet<>();
        this.setDefs = new LinkedHashSet<>();
        this.varDefs = new LinkedHashSet<>();
        this.funDefs = new LinkedHashSet<>();
        this.initialisation = new Skip();
        this.events = new LinkedHashSet<>();
        this.symbolsTable = new LinkedHashMap<>();
    }

    public String getName() {
        return name;
    }

    public ASymbol getSymbol(String name) {
        return symbolsTable.get(name);
    }

    public Set<ConstDef> getConstDefs() {
        return constDefs;
    }

    public void addConstDef(ConstDef constDef) {
        constDefs.add(constDef);
        symbolsTable.put(constDef.getName(), constDef.getSymbol());
    }

    public Set<SetDef> getSetDefs() {
        return setDefs;
    }

    public void addSetDef(SetDef setDef) {
        setDefs.add(setDef);
        symbolsTable.put(setDef.getName(), setDef.getSymbol());
    }

    public Set<VarDef> getVarDefs() {
        return varDefs;
    }

    public void addVarDef(VarDef varDef) {
        varDefs.add(varDef);
        symbolsTable.put(varDef.getName(), varDef.getSymbol());
    }

    public Set<FunDef> getFunDefs() {
        return funDefs;
    }

    public void addFunDef(FunDef funDef) {
        funDefs.add(funDef);
        symbolsTable.put(funDef.getName(), funDef.getSymbol());
    }

    public ASubstitution getInitialisation() {
        return initialisation;
    }

    public void setInitialisation(ASubstitution initialisation) {
        this.initialisation = initialisation;
    }

    public LinkedHashSet<Event> getEvents() {
        return events;
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    @Override
    public String accept(IBObjectVisitor visitor) {
        return visitor.visit(this);
    }

}
