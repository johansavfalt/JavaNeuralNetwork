package se.johansavfalt.JavaNeuralNetwork;

/**
 * Neural layer class, creates a "layer" with a randomly initialized wieght matrix and bias class and performec
 * feedforward ,backward propagaton, and updates on them
 */

public class NeuralLayer {

	private final int inputs;
	private final int units;
	private Matrix weights;
	private Matrix bias;
	private Matrix Activation_prev;
	private Matrix Activation_curr;
	private Matrix Z_curr;
	private ActivationFunction activation;
	private Matrix deltaWeights;
	private Matrix deltaBias;
	private Matrix deltaCurr;
	private Matrix deltaCurr1;
	private Matrix deltaCurr2;

	/**
	 *  Contructor, takes matrix dimensions and creates randomly initialized matrices
	 * @param inputs
	 * @param units
	 * @param activation
	 */
	public NeuralLayer(int inputs, int units, ActivationFunction activation){
		this.inputs = inputs;
		this.units = units;
		this.activation = activation;
		this.weights = Matrix.random(inputs, units);
		this.bias = new Matrix(1 , units);
		this.deltaBias = new Matrix(1, units);
		this.bias.fillwith(1);
	};

	/**
	 * Forward propagation of the layer
	 * @param Activation_prev
	 * @return
	 */

	public Matrix layer_forward_propagation(Matrix Activation_prev){
		this.Activation_prev = Activation_prev;
		this.Z_curr = this.Activation_prev.times(this.weights).plus(this.bias);
		this.Activation_curr = this.activation.activation(Z_curr);
		return this.Activation_curr;

	}

	/**
	 * Backward propagation of the layer
	 * @param delta_Aprev
	 * @return
	 */

	public Matrix layer_backward_propagation(Matrix delta_Aprev) {
		this.deltaWeights = this.Activation_prev.transpose().times(delta_Aprev);
		double sum = 0.0;
		for (int i = 0; i < delta_Aprev.getColumns(); i++) {
			sum += delta_Aprev.getData()[0][i];
		}
		this.deltaBias.fillwith(sum);

		this.deltaCurr = delta_Aprev.times(this.weights.transpose());
		this.deltaCurr1 = this.deltaCurr.hadamanproduct(this.activation.activation_derivative(this.Activation_prev));
		return this.deltaCurr1;
	}

	/**
	 * Update parameters of the layer
	 * @param learningRate
	 */
	public void updateParameters(double learningRate) {
		this.weights = this.weights.product(this.deltaWeights.timesConstant(-learningRate));
		this.bias = this.bias.product(this.deltaBias.timesConstant(-learningRate));

		//TODO implement momentum https://engmrk.com/gradient-descent-with-momentum/

	}




}
