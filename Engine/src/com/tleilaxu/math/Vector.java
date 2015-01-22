package com.tleilaxu.math;

public class Vector {
	private double[] c;

	public Vector(double... ds) {
		c = ds;
	}

	public Vector(int s) {
		double[] vals = new double[s];
		c = vals;
	}

	public void scale(double s) {
		for (int i = 0; i < c.length; i++) {
			c[i] *= s;
		}
	}

	public void divide(double scale) {
		for (int i = 0; i < c.length; i++) {
			c[i] /= scale;
		}
	}

	public void addVec(Vector v) {
		if (!Vector.areEqualSize(this, v))
			throw new IllegalArgumentException();
		for (int i = 0; i < v.getSize(); i++) {
			c[i] += v.getValues()[i];
		}
	}

	public void subVec(Vector v) {
		if (!Vector.areEqualSize(this, v))
			throw new IllegalArgumentException();
		for (int i = 0; i < v.getSize(); i++) {
			c[i] -= v.getValues()[i];
		}
	}

	public void multiplyVec(Vector v) {
		if (!Vector.areEqualSize(this, v))
			throw new IllegalArgumentException();
		for (int i = 0; i < v.getSize(); i++) {
			c[i] *= v.getValues()[i];
		}
	}

	public void normalize() {
		divide(getLenght());
	}

	public void flip() {
		for (int i = 0; i < c.length; i++) {
			c[i] = -c[i];
		}
	}

	public int getSize() {
		return c.length;
	}

	public double[] getValues() {
		return c;
	}

	public void setValue(int i, double v) {
		c[i] = v;
	}

	public double getLenght() {
		double lenght = 0;
		for (int i = 0; i < c.length; i++) {
			lenght += Math.pow(c[i], 2);
		}
		return Math.sqrt(lenght);
	}

	public double getLenghtSqr() {
		double lenght = 0;
		for (int i = 0; i < c.length; i++) {
			lenght += Math.pow(c[i], 2);
		}
		return lenght;
	}

	public Vector getAddedVec(Vector v) {
		if (!Vector.areEqualSize(this, v))
			throw new IllegalArgumentException();
		double[] vals = new double[c.length];
		for (int i = 0; i < v.getSize(); i++) {
			vals[i] = c[i] + v.getValues()[i];
		}
		return new Vector(vals);
	}

	public Vector getSubbedVec(Vector v) {
		if (!Vector.areEqualSize(this, v))
			throw new IllegalArgumentException();
		double[] vals = new double[c.length];
		for (int i = 0; i < v.getSize(); i++) {
			vals[i] = c[i] - v.getValues()[i];
		}
		return new Vector(vals);
	}

	public Vector getScaled(double s) {
		double[] vals = new double[c.length];
		for (int i = 0; i < c.length; i++) {
			vals[i] = c[i] * s;
		}
		return new Vector(vals);
	}

	public Vector getDivided(double s) {
		double[] vals = new double[c.length];
		for (int i = 0; i < c.length; i++) {
			vals[i] = c[i] / s;
		}
		return new Vector(vals);
	}

	public Vector getNormalized() {
		return getDivided(getLenght());
	}

	public Vector getCross(Vector v) {
		if (!Vector.areEqualSize(this, v))
			throw new IllegalArgumentException();
		Vector rv = new Vector(new double[c.length]);
		Vector v1 = this;
		Vector v2 = v;
		for (int i = 0; i < c.length; i++) {
			int i1 = i + 1;
			if (i1 >= c.length) {
				i1 = i1 - c.length;
			}
			int i2 = i + 2;
			if (i2 >= c.length) {
				i2 = i2 - c.length;
			}
			rv.setValue(i,
					v1.getValues()[i1] * v2.getValues()[i2]
							- v1.getValues()[i2] * v2.getValues()[i1]);
		}
		return rv;
	}

	public double getDot(Vector v) {
		if (!Vector.areEqualSize(this, v))
			throw new IllegalArgumentException();
		double dot = 0;
		for (int i = 0; i < c.length; i++) {
			dot += c[i] * v.getValues()[i];
		}
		return dot;
	}

	public Vector getMultipliedByMatrix(Matrix m) {
		double[] vals = new double[m.getWidth()];
		Vector[] mv = m.getVectors();
		for (int i = 0; i < vals.length; i++) {
			vals[i] = getDot(mv[i]);
		}
		return new Vector(vals);
	}

	public static boolean areEqualSize(Vector... vs) {
		int lenght = vs[0].getSize();
		for (int i = 0; i < vs.length; i++) {
			if (vs[i].getSize() != lenght)
				return false;
		}
		return true;
	}

	public double getProjectedVector(Vector v) {
		return getDot(v) / getDot(this);
	}

	public Vector getPerpendicular() {
		double[] vals = c.clone();
		vals[0] = -vals[0];
		return new Vector(vals);
	}

	public Vector getFlipped() {
		double[] vals = new double[c.length];
		for (int i = 0; i < vals.length; i++) {
			vals[i] = -c[i];
		}
		return new Vector(vals);
	}

	public String toString() {
		String s = "------- \n";
		for (int i = 0; i < c.length; i++) {
			s += "  " + c[i] + "\n";
		}
		return s + "-------";
	}

}
