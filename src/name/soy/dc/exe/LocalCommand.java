package name.soy.dc.exe;

import name.soy.dc.tasks.Executable;
import name.soy.dc.tasks.Pramas;

public class LocalCommand implements Executable {

	@Override
	public boolean execute() {
		
		return false;
	}

	@Override
	public Pramas needPramas() {
		return null;
	}

}
