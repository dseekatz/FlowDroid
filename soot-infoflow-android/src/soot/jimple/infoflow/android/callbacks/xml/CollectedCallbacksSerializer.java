package soot.jimple.infoflow.android.callbacks.xml;

import java.io.FileOutputStream;
import java.io.IOException;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.Serializer;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import soot.Scene;
import soot.SootClass;
import soot.SootMethod;
import soot.jimple.infoflow.android.InfoflowAndroidConfiguration.CallbackConfiguration;

/**
 * Class for serializing collected callbacks to disk for later re-use
 * 
 * @author Steven Arzt
 *
 */
public class CollectedCallbacksSerializer {

	/**
	 * Serializer for {@link SootMethod} instances
	 * 
	 * @author Steven Arzt
	 *
	 */
	private static class SootMethodSerializer extends Serializer<SootMethod> {

		@Override
		public void write(Kryo kryo, Output output, SootMethod object) {
			output.writeString(object.getSignature());
		}

		@Override
		public SootMethod read(Kryo kryo, Input input, Class<? extends SootMethod> type) {
			String sig = input.readString();
			return Scene.v().grabMethod(sig);
		}

	}

	/**
	 * Serializer for {@link SootClass} instances
	 * 
	 * @author Steven Arzt
	 *
	 */
	private static class SootClassSerializer extends Serializer<SootClass> {

		@Override
		public void write(Kryo kryo, Output output, SootClass object) {
			output.writeString(object.getName());
		}

		@Override
		public SootClass read(Kryo kryo, Input input, Class<? extends SootClass> type) {
			String className = input.readString();
			return Scene.v().getSootClassUnsafe(className);
		}

	}

	/**
	 * Serializes the given data object
	 * 
	 * @param callbacks The object to serialize
	 * @param config    The configuration that defines how and where to store the
	 *                  data
	 * @throws IOException
	 */
	public static void serialize(CollectedCallbacks callbacks, CallbackConfiguration config) throws IOException {
		Kryo kryo = new Kryo();
		kryo.register(SootMethod.class, new SootMethodSerializer());
		kryo.register(SootClass.class, new SootClassSerializer());
		try (Output output = new Output(new FileOutputStream(config.getCallbacksFile()))) {
			kryo.writeClassAndObject(output, callbacks);
		}
	}

}