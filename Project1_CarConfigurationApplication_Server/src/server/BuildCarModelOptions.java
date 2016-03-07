/*
 * Hsuan Chen (hsuanc)
 */

package server;

import java.util.ArrayList;
import java.util.Properties;

import adapter.BuildAuto;
import model.Automobile;

public class BuildCarModelOptions implements AutoServer {
	private BuildAuto auto;

	@Override
	public void buildAutoProperties(String filename, Properties props) {
		auto = new BuildAuto();
		auto.buildAutoProperties(filename, props);
	}

	@Override
	public ArrayList<String> modelList() {
		auto = new BuildAuto();
		return auto.modelList();
	}

	@Override
	public Automobile sendAuto(String model) {
		if(auto==null) {
			auto = new BuildAuto();
		}
		return auto.sendAuto(model);
	}

}
