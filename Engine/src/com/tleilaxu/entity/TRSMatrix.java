package com.tleilaxu.entity;

import com.tleilaxu.math.Matrix;
import com.tleilaxu.math.Vector;

public class TRSMatrix {
	private Matrix m, t, r, s;
	public TRSMatrix(Vector translation, Vector scale, double angle) {
		t = generateTranslationMatrix(translation);
		r = generateRotationMatrix(translation.getSize() + 1, angle);
		s = generateScaleMatrix(scale);
		rebuild();
	}
	public void rebuild() {
		m = new Matrix(t.getVectors());
		m.multiplyMatrix(r);
		m.multiplyMatrix(s);
	}
	public void rotate(double a) {
		r.addMatrix(generateRotationMatrix(r.getHeight(), a));
	}
	public Vector getScale() {
		double[] vals = new double[s.getHeight()];
		for (int i = 0; i < t.getHeight() - 1; i++) {
			vals[i] = t.getVector(t.getWidth() - 1).getValue(i);
		}
		return new Vector(vals);
	}
	public Vector getTranslation() {
		double[] vals = new double[t.getHeight() - 1];
		for (int i = 0; i < t.getHeight() - 1; i++) {
			vals[i] = t.getVector(t.getWidth() - 1).getValue(i);
		}
		return new Vector(vals);
	}
	public Vector setTranslation(Vector translation) {
		double[] vals = new double[translation.getSize()];
		for (int i = 0; i < vals.length - 1; i++) {
			vals[i] = translation.getValue(i);
		}
		vals[vals.length - 1] = 1;
		return new Vector(vals);
	}
	public Matrix getTranslationMatrix() {
		return t;
	}
	public Matrix getRotationMatrix() {
		return r;
	}
	public Matrix getScaleMatrix() {
		return s;
	}
	public Matrix getMatrix() {
		return m;
	}
	public Matrix getRSMatrix() {
		return r.getMultipliedMatrix(s);
	}
	public void setRotation(double a) {
		r = generateRotationMatrix(r.getHeight(), a);
	}
	public static Matrix generateTranslationMatrix(Vector translation) {
		Vector transaltionVector = new Vector(translation.getSize() + 1);
		transaltionVector.setValue(translation.getSize(), 1);
		for (int i = 0; i < transaltionVector.getSize() - 1; i++) {
			transaltionVector.setValue(i, translation.getValues()[i]);
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
		Vector v2 = returnMatrix.getVector(1);
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
