package increment.simulator;

/**
 * A constant chip that has one Output, which produces a constant value.
 * 
 * 
 * A constant chip will have no inputs.
 * A constant chip will have one output:
 * 		* output[width]
 * 
 * @author Xu Ke
 *
 */
public class ConstantChip extends Chip {
	private Cable data;
	public ConstantChip(int width, long value) {
		data = new Cable(width);
		data.putValue(value);
		addOutput("output", width);
	}
	
	public ConstantChip(int width) {
		this(width, 0l);
	}
	
	public void evaluate() {
		if (outputs.get("output") != null)
			outputs.get("output").assign(data);
	}
}