package soot.jimple.infoflow.android.manifest.binary;

import soot.jimple.infoflow.android.axml.AXmlNode;
import soot.jimple.infoflow.android.manifest.IBroadcastReceiver;
import soot.jimple.infoflow.android.manifest.ProcessManifest;

/**
 * A broadcast receiver loaded from a binary Android manifest
 * 
 * @author Steven Arzt
 *
 */
public class BinaryManifestBroadcastReceiver extends AbstractBinaryAndroidComponent implements IBroadcastReceiver {

	/**
	 * Creates a new instance of the {@link BinaryManifestBroadcastReceiver} class
	 * 
	 * @param node     The binary XML node
	 * @param manifest The manifest
	 */
	public BinaryManifestBroadcastReceiver(AXmlNode node, ProcessManifest manifest) {
		super(node, manifest);
	}

}
