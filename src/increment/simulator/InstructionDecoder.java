package increment.simulator;
/**
 * This is the instruction decoder. It decodes a 16-bit wide instruction into several parts.
 * 		* OpCode: [0..5]
 * 		* ...other instruction information.
 * 
 * The decoder has one input:
 * 		* input[16]
 * The decoder now has five outputs:
 * 		* opcode[6]
 * 		* IX[2]
 * 		* R[2]
 * 		* I[1]
 * 		* address[5]
 * @author Xu Ke
 *
 */
public class InstructionDecoder extends Chip {
	public InstructionDecoder() {
		addPort("input", 16);
		addPort("opcode", 6);
		addPort("IX", 2);
		addPort("R", 2);
		addPort("I", 1);
		addPort("address", 5);
	}
	public boolean evaluate() {
		boolean vary = false;
		vary |= getPort("opcode").partialAssign(0, getPort("input"), 10, 6);
		vary |= getPort("R").partialAssign(0, getPort("input"), 8, 2);
		vary |= getPort("IX").partialAssign(0, getPort("input"), 6, 2);
		vary |= getPort("I").partialAssign(0, getPort("input"), 5, 1);
		vary |= getPort("address").partialAssign(0, getPort("input"), 0, 5);
		return vary;
	}
}
