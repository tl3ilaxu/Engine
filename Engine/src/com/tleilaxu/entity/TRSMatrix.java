package com.tleilaxu.entity;

import com.tleilaxu.math.Matrix;
import com.tleilaxu.math.Vector;

public class TRSMatrix {
	private Matrix m, t, r, s;
	//TODO: fix translation values
	//TODO: check illegalArgs
	public static Matrix generateTranslationMatrix(int size, Vector translation){
		Vector transaltionVector = new Vector(size);
		transaltionVector.setValue(0, 0);
		for (int i = 1; i < size; i++) {
			transaltionVector.setValue(i, 1);
		}
		Matrix returnMatrix = Matrix.getIdent(size);
		returnMatrix.setVector(size-1, transaltionVector);
		return returnMatrix;
	}
	public static Matrix generateRotationMatrix(int size, double a){
		Matrix returnMatrix = Matrix.getIdent(size);
		Vector v1 = returnMatrix.getVector(0);
		v1.setValue(0, Math.cos(a));
		v1.setValue(1, Math.sin(a));
		Vector v2 = returnMatrix.getVector(0);
		v2.setValue(0,-Math.sin(a));
		v2.setValue(1, Math.cos(a));
		returnMatrix.setVector(0, v1);
		returnMatrix.setVector(1, v2);
		return returnMatrix;
	}
}
