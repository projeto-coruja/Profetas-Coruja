package persistence.model.base;

import com.google.common.base.Strings;

public class EntityUtility {

    private EntityUtility() {
	// classe estática
    }

    public static boolean notNull(Object... obj) {
	for (Object o : obj) {
	    if (o == null) {
		return false;
	    }
	}
	return true;
    }

    public static boolean notEmpty(String... strings) {
	for (String s : strings) {
	    if (Strings.isNullOrEmpty(s))
		return false;
	}
	return true;
    }

}
