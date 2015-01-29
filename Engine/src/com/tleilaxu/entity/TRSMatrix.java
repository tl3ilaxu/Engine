package com.tleilaxu.entity;

import com.tleilaxu.math.Matrix;
import com.tleilaxu.math.Vector;

public class TRSMatrix {
	private Matrix m, t, r, s;
	public TRSMatrix(Vector translation, Vector scale, double angle) {
		t = generateTranslationMatrix(translation);
		// System.out.println(t);
		r = generateRotationMatrix(translation.getSize() + 1, angle);
		s = generateScaleMatrix(scale);
		m = new Matrix(t.getVectors());
		m.multiplyMatrix(r);
		m.multiplyMatrix(s);

	}
	public Vector getTranslation() {
		double[] vals = new double[t.getHeight() - 1];
		for (int i = 0; i < t.getHeight() - 1; i++) {
			vals[i] = t.getVector(t.getWidth() - 1).getValue(i+1);
		}
		return new Vector(vals);
	}
	public Vector setTranslation(Vector translation) {
		double[] vals = new double[translation.getSize()];
		vals[0] = 0;
		for (int i = 1; i < vals.length; i++) {
			vals[i] = translation.getValue(i - 1);
		}
		return new Vector(vals);
	}
	public Matrix getTranslationMatrix(){
		return t;
	}
	public static Matrix generateTranslationMatrix(Vector translation) {
		Vector transaltionVector = new Vector(translation.getSize() + 1);
		transaltionVector.setValue(0, 0);
		for (int i = 1; i < transaltionVector.getSize(); i++) {
			transaltionVector.setValue(i, translation.getValues()[i - 1]);
		}
		Matrix returnMatrix = Matrix.getIdent(transaltionVector.getSize());
		returnMatrix.setVector(transaltionVector.getSize() - 1, transaltionVector);
		return returnMatrix;
	}
	public static Matrix generateRotationMatrix(int size, double a) {
		Matrix returnMatrix = Matrix.getIdent(size);
		Vector v1 = returnMatrix.getVector(0);
		v1.setValue(0, Math.cos(a));
		v1.setValue(1, Math.sin(a));
		Vector v2 = returnMatrix.getVector(0);
		v2.setValue(0, -Math.sin(a));
		v2.setValue(1, Math.cos(a));
		returnMatrix.setVector(0, v1);
		returnMatrix.setVector(1, v2);
		return returnMatrix;
	}
	public static Matrix generateScaleMatrix(Vector scale) {
		Matrix returnMatrix = Matrix.getIdent(scale.getSize() + 1);
		for (int i = 0; i < scale.getSize(); i++) {
			Vector v = returnMatrix.getVector(i);
			v.setValue(i, scale.getValues()[i]);
			returnMatrix.setVector(i, v);
		}
		return returnMatrix;
	}
}
