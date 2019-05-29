package b.parser.astvisitors;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by gvoiron on 29/05/19.
 * Time : 18:29
 */
public class TypeCheckError extends Error {

    public TypeCheckError(List<String> errors) {
        super("The following errors occurred during type checking:\n" + errors.stream().collect(Collectors.joining("\n", "\t", "")));
    }

}
