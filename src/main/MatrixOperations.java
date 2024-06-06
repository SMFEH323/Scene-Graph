package main;

/**
 * @author Sayf Elhawary
 */
public class MatrixOperations {
	public float[][] matrix;

	// Constructor for a 4x4 matrix
	public MatrixOperations () {
		matrix = new float[4][4];
	}

	// Method to set this matrix as an identity matrix
	public void setIdentity () {
		for ( int i = 0 ; i < 4 ; i++ ) {
			for ( int j = 0 ; j < 4 ; j++ ) {
				matrix[i][j] = (i == j) ? 1 : 0;
			}
		}
	}

	// Method for matrix multiplication
	public MatrixOperations multiply ( MatrixOperations other ) {
		MatrixOperations result = new MatrixOperations();
		for ( int i = 0 ; i < 4 ; i++ ) {
			for ( int j = 0 ; j < 4 ; j++ ) {
				for ( int k = 0 ; k < 4 ; k++ ) {
					result.matrix[i][j] += this.matrix[i][k] * other.matrix[k][j];
				}
			}
		}
		return result;
	}

	public Vector4 multiply ( Vector4 vec ) {
		float[] result = new float[4];
		for ( int i = 0 ; i < 4 ; i++ ) {
			result[i] = this.matrix[i][0] * vec.x + this.matrix[i][1] * vec.y
			    + this.matrix[i][2] * vec.z + this.matrix[i][3] * vec.w;
		}
		return new Vector4(result[0],result[1],result[2],result[3]);
	}

	public float[][] multiply ( float[][] verticesCoords ) {
		Vector4[] vectorVertices = new Vector4[verticesCoords.length];
		for ( int i = 0 ; i < verticesCoords.length ; i++ ) {
			float[] v = verticesCoords[i];
			vectorVertices[i] = new Vector4(v[0],v[1],v[2],1.0f);
		}
		for ( int i = 0 ; i < vectorVertices.length ; i++ ) {
			vectorVertices[i] = multiply(vectorVertices[i]);
		}
		float[][] transformedVertices = new float[vectorVertices.length][3];
		for ( int i = 0 ; i < vectorVertices.length ; i++ ) {
			Vector4 v = vectorVertices[i];
			transformedVertices[i][0] = v.x;
			transformedVertices[i][1] = v.y;
			transformedVertices[i][2] = v.z;
		}
		return transformedVertices;
	}

	public double[][] multiply ( double[][] verticesCoords ) {
		Vector4[] vectorVertices = new Vector4[verticesCoords.length];
		for ( int i = 0 ; i < verticesCoords.length ; i++ ) {
			double[] v = verticesCoords[i];
			vectorVertices[i] =
			    new Vector4((float) v[0],(float) v[1],(float) v[2],1.0f);
		}
		for ( int i = 0 ; i < vectorVertices.length ; i++ ) {
			vectorVertices[i] = multiply(vectorVertices[i]);
		}
		double[][] transformedVertices = new double[vectorVertices.length][3];
		for ( int i = 0 ; i < vectorVertices.length ; i++ ) {
			Vector4 v = vectorVertices[i];
			transformedVertices[i][0] = v.x;
			transformedVertices[i][1] = v.y;
			transformedVertices[i][2] = v.z;
		}
		return transformedVertices;
	}

	public static MatrixOperations createShearX ( float shearY, float shearZ ) {
		MatrixOperations shearMatrix = new MatrixOperations();
		shearMatrix.setIdentity();
		shearMatrix.matrix[1][0] = shearY; // Shearing along Y as a function of X
		shearMatrix.matrix[2][0] = shearZ; // Shearing along Z as a function of X
		return shearMatrix;
	}

	public static MatrixOperations createShearY ( float shearX, float shearZ ) {
		MatrixOperations shearMatrix = new MatrixOperations();
		shearMatrix.setIdentity();
		shearMatrix.matrix[0][1] = shearX; // Shearing along X as a function of Y
		shearMatrix.matrix[2][1] = shearZ; // Shearing along Z as a function of Y
		return shearMatrix;
	}

	public static MatrixOperations createShearZ ( float shearX, float shearY ) {
		MatrixOperations shearMatrix = new MatrixOperations();
		shearMatrix.setIdentity();
		shearMatrix.matrix[0][2] = shearX; // Shearing along X as a function of Z
		shearMatrix.matrix[1][2] = shearY; // Shearing along Y as a function of Z
		return shearMatrix;
	}

	// Convert to a float array for JOGL
	public float[] toFloatArray () {
		float[] result = new float[16];
		for ( int i = 0 ; i < 4 ; i++ ) {
			System.arraycopy(matrix[i],0,result,i * 4,4);
		}
		return result;
	}

	// String representation for debugging
	public String toString () {
		StringBuilder sb = new StringBuilder();
		for ( float[] row : matrix ) {
			for ( float element : row ) {
				sb.append(element).append("\t");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	public class Vector4 {
		public float x, y, z, w;

		public Vector4 ( float x, float y, float z, float w ) {
			this.x = x;
			this.y = y;
			this.z = z;
			this.w = w;
		}
	}
}
