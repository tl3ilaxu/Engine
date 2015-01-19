package com.tleilaxu.math;

public class Matrix {
	Vector[] vectors;
	public Matrix(Vector...vectors){
		this.vectors = vectors;
	}
	public Matrix getMultipliedMatrix(Matrix m){
		Vector[] v = new Vector[getHeight()];
		for (int i = 0; i < v.length; i++) {
			Vector v1 = new Vector(new double[getWidth()]);
			for (int j = 0; j < getWidth(); j++) {
				v1.setValue(j, getVector(j).getValues()[i]);
			}
			v[i] = v1;
		}
		return null;
		// TODO:abandon dot product idea
	}
	public int getHeight(){
		return vectors[0].getSize();
	}
	public int getWidth(){
		return vectors.length;
	}
	public Vector getVector(int i){
		return vectors[i];
	}
}
