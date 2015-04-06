package com.tleilaxu.math;

public class Matrix {
	Vector[] vectors;

	public Matrix(Vector... vectors) {
		this.vectors = vectors;
	}

	public void multiplyMatrix(Matrix m) {
		vectors = getMultipliedMatrix(m).getVectors();
	}
	public void addMatrix(Matrix m) {
		if (m.getHeight() != getHeight() || m.getWidth() != getWidth())
			throw new IllegalArgumentException("Cannot add matrixes of different sizes");
		vectors = getAddedMatrix(m).getVectors();
	}
	public Matrix getAddedMatrix(Matrix m) {
		Vector[] vectors = new Vector[this.vectors.length];
		for (int i = 0; i < vectors.length; i++) {
			vectors[i] = this.vectors[i].getAddedVec(m.getVector(i));
		}
		return new Matrix(vectors);
	}
	public Matrix getMultipliedMatrix(Matrix m) {
		if (getWidth() != m.getHeight())
			throw new IllegalArgumentException("Matrix heights not the same");
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
	public void setVector(int i, Vector v) {
		vectors[i] = v;
	}

	public String toString() {
		String returnString = "------- \n";;
		for (int i = 0; i < getHeight(); i++) {
			for (int j = 0; j < getWidth(); j++) {
				returnString += getElementAt(j, i) + " ";
			}
			returnString += "\n";
		}
		return returnString + "-------";
	}
	public static Matrix getIdent(int s) {
		Vector[] vect = new Vector[s];

		for (int i = 0; i < vect.length; i++) {
			double[] vals = new double[s];
			for (int j = 0; j < s; j++) {
				vals[j] = 0;
				if (j == i)
					vals[j] = 1;
			}
			vect[i] = new Vector(vals);
		}
		return new Matrix(vect);
	}
}
