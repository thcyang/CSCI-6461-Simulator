package increment.simulator;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * A factory for chips. This class is full of reflection tricks so further development can be more focused on coding.
 * @author Xu Ke
 *
 */
public class ChipFactory {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Chip makeChip(String chipName, Object[] args) {
		if (!chipName.contains("."))
			chipName = ChipFactory.class.getPackage().getName() + "." + chipName;
		Class chipClass = null;
		try {
			chipClass = Class.forName(chipName);
		} catch (ClassNotFoundException e) {
			System.err.println("Cannot find chip of name \"" + chipName + "\".");
			System.exit(-1);
		}
		Class[] argClasses = new Class[args.length];
		for (int i = 0; i < args.length; ++i) {
			argClasses[i] = args[i].getClass();
			if (argClasses[i] == Integer.class)
				argClasses[i] = int.class;
			else if (argClasses[i] == Short.class)
				argClasses[i] = short.class;
			else if (argClasses[i] == Long.class)
				argClasses[i] = long.class;
			else if (argClasses[i] == Byte.class)
				argClasses[i] = byte.class;
			else if (argClasses[i] == Boolean.class)
				argClasses[i] = boolean.class;
			else if (argClasses[i] == Character.class)
				argClasses[i] = char.class;
			else if (argClasses[i] == Float.class)
				argClasses[i] = float.class;
			else if (argClasses[i] == Double.class)
				argClasses[i] = double.class;
		}
		Constructor constructor = null;
		try {
			constructor = chipClass.getConstructor(argClasses);
		} catch (NoSuchMethodException | SecurityException e) {
			System.err.println("Invalid arguments for chip \"" + chipName + "\".");
			System.exit(-1);
		}
		Chip chip = null;
		try {
			chip = (Chip) constructor.newInstance(args);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			System.err.println("Invalid arguments for chip \"" + chipName + "\".");
			System.exit(-1);
		} catch (ClassCastException e) {
			System.err.println("\"" + chipName + "\" is not a chip.");
			System.exit(-1);
		}
		return chip;
	}
}
