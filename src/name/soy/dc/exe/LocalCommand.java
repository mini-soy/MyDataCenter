package name.soy.dc.exe;

import name.soy.dc.tasks.Executable;
import name.soy.dc.tasks.Parameters;

public class LocalCommand implements Executable {

	@Override
	public boolean execute() {
		return false;
	}

	@Override
	public Parameters needParameters() {
		return null;
	}

}
