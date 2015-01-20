package com.tleilaxu.math;

public class Matrix {
	Vector[] vectors;

	public Matrix(Vector... vectors) {
		this.vectors = vectors;
	}

	public void multiplyMatrix(Matrix m) {
		vectors = getMultipliedMatrix(m).getVectors();
	}

	public Matrix getMultipliedMatrix(Matrix m) {
		if (getWidth() != m.getHeight())
			throw new IllegalArgumentException();
		int w = getWidth();
		if (getWidth() > m.getWidth())
			w = m.getWidth();
		Vector[] v = getHorizontalVectors();
		Vector[] returnVectors = new Vector[w];
		for (int j = 0; j < w; j++) {
			double[] vals = new double[getHeight()];
			for (int i = 0; i < getHeight(); i++) {
				vals[i] = v[i].getDot(m.getVector(j));
			}
			returnVectors[j] = new Vector(vals.clone());

		}
		return new Matrix(returnVectors);
	}
	public int getHeight() {
		return vectors[0].getSize();
	}

	public int getWidth() {
		return vectors.length;
	}

	public Vector getVector(int i) {
		return vectors[i];
	}

	public double getElementAt(int x, int y) {
		return vectors[x].getValues()[y];
	}

	public Vector[] getVectors() {
		return vectors;
	}

	public Vector[] getHorizontalVectors() {
		Vector[] v = new Vector[getHeight()];
		for (int i = 0; i < v.length; i++) {
			Vector v1 = new Vector(new double[getWidth()]);
			for (int j = 0; j < getWidth(); j++) {
				v1.setValue(j, getVector(j).getValues()[i]);
			}
			v[i] = v1;
		}
		return v;
	}

	public String toString() {
		String returnString = "------- \n";
		;
		for (int i = 0; i < getHeight(); i++) {
			for (int j = 0; j < getWidth(); j++) {
				returnString += getElementAt(j, i) + " ";
			}
			returnString += "\n";
		}
		return returnString + "-------";
	}
}
