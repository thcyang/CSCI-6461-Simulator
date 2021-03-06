package increment.simulator;

/**
 * A clock register.
 * 
 * A clock register will have two inputs:
 * 		* load[1]
 * 		* input[width]
 * A clock register will have one output:
 * 		* output[width]
 * 
 * @author Xu Ke
 *
 */
public class ClockRegister extends Chip {
	// I'm using a Cable class for storage here. It's just because it fits here.
	protected Cable data;
	/**
	 * Constructor. Creating a register of width of ```width```.
	 * A clock register will have two inputs:
	 * 		* load[1]
	 * 		* input[width]
	 * A clock register will have one output:
	 * 		* output[width]
	 * @param width
	 */
	public ClockRegister(int width){
		data = new SingleCable(width);
		addPort("load", 1);
		addPort("input", width);
		addPort("output", width);
	}
	/**
	 * When timer ticks, if input[0] is true, we move data of input to data.
	 */
	public void tick(){
		if (getPort("load").getBit(0)) {
			data.assign(getPort("input"));
		}
	}
	/**
	 * When evaluates, we move data to output.
	 */
	public boolean evaluate(){
		if (getPort("output").assign(data))
			return true;
		return false;
	}
	/**
	 * Turns chip value into a readable way.
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Register value: ");
		sb.append(data.toInteger());
		return sb.toString();
	}
	/**
	 * Replaces the value inside with i.
	 * @param i
	 */
	public void setValue(long i) {
		data.putValue(i);
	}
}
