package com.Wolfaton.game;

import java.nio.FloatBuffer;

public class Matrix4 {
    private float r1c1, r1c2, r1c3, r1c4;
    private float r2c1, r2c2, r2c3, r2c4;
    private float r3c1, r3c2, r3c3, r3c4;
    private float r4c1, r4c2, r4c3, r4c4;

    public Matrix4(Vector4 c1, Vector4 c2, Vector4 c3, Vector4 c4) {
        r1c1 = c1.x;
        r2c1 = c1.y;
        r3c1 = c1.z;
        r4c1 = c1.w;

        r1c2 = c2.x;
        r2c2 = c2.y;
        r3c2 = c2.z;
        r4c2 = c2.w;

        r1c3 = c3.x;
        r2c3 = c3.y;
        r3c3 = c3.z;
        r4c3 = c3.w;

        r1c4 = c4.x;
        r2c4 = c4.y;
        r3c4 = c4.z;
        r4c4 = c4.w;
    }

    public Matrix4() {
        identity();
    }

    public void identity() {
        r1c1 = 1f;
        r1c2 = 0f;
        r1c3 = 0f;
        r1c4 = 0f;

        r2c1 = 0f;
        r2c2 = 1f;
        r2c3 = 0f;
        r2c4 = 0f;

        r3c1 = 0f;
        r3c2 = 0f;
        r3c3 = 1f;
        r3c4 = 0f;

        r4c1 = 0f;
        r4c2 = 0f;
        r4c3 = 0f;
        r4c4 = 1f;
    }

    public Matrix4 negate() {
        return multiplyScalar(-1f);
    }

    public Matrix4 subtract(Matrix4 inputMatrix) {
        return this.add(inputMatrix.negate());
    }


    public Matrix4 add(Matrix4 inputMatrix) {
        Matrix4 result = new Matrix4();

        result.r1c1 = this.r1c1 + inputMatrix.r1c1;
        result.r1c2 = this.r1c2 + inputMatrix.r1c2;
        result.r1c3 = this.r1c3 + inputMatrix.r1c3;
        result.r1c4 = this.r1c4 + inputMatrix.r1c4;

        result.r2c1 = this.r2c1 + inputMatrix.r2c1;
        result.r2c2 = this.r2c2 + inputMatrix.r2c2;
        result.r2c3 = this.r2c3 + inputMatrix.r2c3;
        result.r2c4 = this.r2c4 + inputMatrix.r2c4;

        result.r3c1 = this.r3c1 + inputMatrix.r3c1;
        result.r3c2 = this.r3c2 + inputMatrix.r3c2;
        result.r3c3 = this.r3c3 + inputMatrix.r3c3;
        result.r3c4 = this.r3c4 + inputMatrix.r3c4;

        result.r4c1 = this.r4c1 + inputMatrix.r4c1;
        result.r4c2 = this.r4c2 + inputMatrix.r4c2;
        result.r4c3 = this.r4c3 + inputMatrix.r4c3;
        result.r4c4 = this.r4c4 + inputMatrix.r4c4;

        return result;
    }

    public Matrix4 multiplyScalar(float scalar) {
        Matrix4 result = new Matrix4();

        result.r1c1 = this.r1c1 * scalar;
        result.r1c2 = this.r1c2 * scalar;
        result.r1c3 = this.r1c3 * scalar;
        result.r1c4 = this.r1c4 * scalar;

        result.r2c1 = this.r2c1 * scalar;
        result.r2c2 = this.r2c2 * scalar;
        result.r2c3 = this.r2c3 * scalar;
        result.r2c4 = this.r2c4 * scalar;

        result.r3c1 = this.r3c1 * scalar;
        result.r3c2 = this.r3c2 * scalar;
        result.r3c3 = this.r3c3 * scalar;
        result.r3c4 = this.r3c4 * scalar;

        result.r4c1 = this.r4c1 * scalar;
        result.r4c2 = this.r4c2 * scalar;
        result.r4c3 = this.r4c3 * scalar;
        result.r4c4 = this.r4c4 * scalar;

        return result;
    }

    public Matrix4 multiply(Matrix4 inputMatrix) {
        Matrix4 result = new Matrix4();

        result.r1c1 = this.r1c1 * inputMatrix.r1c1 + this.r1c2 * inputMatrix.r2c1
                + this.r1c3 * inputMatrix.r3c1 + this.r1c4 * inputMatrix.r4c1;
        result.r1c2 = this.r1c1 * inputMatrix.r1c2 + this.r1c2 * inputMatrix.r2c2
                + this.r1c3 * inputMatrix.r3c2 + this.r1c4 * inputMatrix.r4c2;
        result.r1c3 = this.r1c1 * inputMatrix.r1c3 + this.r1c2 * inputMatrix.r2c3
                + this.r1c3 * inputMatrix.r3c3 + this.r1c4 * inputMatrix.r4c3;
        result.r1c4 = this.r1c1 * inputMatrix.r1c4 + this.r1c2 * inputMatrix.r2c4
                + this.r1c3 * inputMatrix.r3c4 + this.r1c4 * inputMatrix.r4c4;

        result.r2c1 = this.r2c1 * inputMatrix.r1c1 + this.r2c2 * inputMatrix.r2c1
                + this.r2c3 * inputMatrix.r3c1 + this.r2c4 * inputMatrix.r4c1;
        result.r2c2 = this.r2c1 * inputMatrix.r1c2 + this.r2c2 * inputMatrix.r2c2
                + this.r2c3 * inputMatrix.r3c2 + this.r2c4 * inputMatrix.r4c2;
        result.r2c3 = this.r2c1 * inputMatrix.r1c3 + this.r2c2 * inputMatrix.r2c3
                + this.r2c3 * inputMatrix.r3c3 + this.r2c4 * inputMatrix.r4c3;
        result.r2c4 = this.r2c1 * inputMatrix.r1c4 + this.r2c2 * inputMatrix.r2c4
                + this.r2c3 * inputMatrix.r3c4 + this.r2c4 * inputMatrix.r4c4;

        result.r3c1 = this.r3c1 * inputMatrix.r1c1 + this.r3c2 * inputMatrix.r2c1
                + this.r3c3 * inputMatrix.r3c1 + this.r3c4 * inputMatrix.r4c1;
        result.r3c2 = this.r3c1 * inputMatrix.r1c2 + this.r3c2 * inputMatrix.r2c2
                + this.r3c3 * inputMatrix.r3c2 + this.r3c4 * inputMatrix.r4c2;
        result.r3c3 = this.r3c1 * inputMatrix.r1c3 + this.r3c2 * inputMatrix.r2c3
                + this.r3c3 * inputMatrix.r3c3 + this.r3c4 * inputMatrix.r4c3;
        result.r3c4 = this.r3c1 * inputMatrix.r1c4 + this.r3c2 * inputMatrix.r2c4
                + this.r3c3 * inputMatrix.r3c4 + this.r3c4 * inputMatrix.r4c4;

        result.r4c1 = this.r4c1 * inputMatrix.r1c1 + this.r4c2 * inputMatrix.r2c1
                + this.r4c3 * inputMatrix.r3c1 + this.r4c4 * inputMatrix.r4c1;
        result.r4c2 = this.r4c1 * inputMatrix.r1c2 + this.r4c2 * inputMatrix.r2c2
                + this.r4c3 * inputMatrix.r3c2 + this.r4c4 * inputMatrix.r4c2;
        result.r4c3 = this.r4c1 * inputMatrix.r1c3 + this.r4c2 * inputMatrix.r2c3
                + this.r4c3 * inputMatrix.r3c3 + this.r4c4 * inputMatrix.r4c3;
        result.r4c4 = this.r4c1 * inputMatrix.r1c4 + this.r4c2 * inputMatrix.r2c4
                + this.r4c3 * inputMatrix.r3c4 + this.r4c4 * inputMatrix.r4c4;

        return result;
    }

    public Vector4 multiply(Vector4 inputVector) {
        float x = this.r1c1 * inputVector.x + this.r1c2 * inputVector.y + this.r1c3 * inputVector.z + this.r1c4 * inputVector.w;
        float y = this.r2c1 * inputVector.x + this.r2c2 * inputVector.y + this.r2c3 * inputVector.z + this.r2c4 * inputVector.w;
        float z = this.r3c1 * inputVector.x + this.r3c2 * inputVector.y + this.r3c3 * inputVector.z + this.r3c4 * inputVector.w;
        float w = this.r4c1 * inputVector.x + this.r4c2 * inputVector.y + this.r4c3 * inputVector.z + this.r4c4 * inputVector.w;
        return new Vector4(x, y, z, w);
    }

    public Matrix4 transpose() {
        Matrix4 result = new Matrix4();

        result.r1c1 = this.r1c1;
        result.r1c2 = this.r2c1;
        result.r1c3 = this.r3c1;
        result.r1c4 = this.r4c1;

        result.r2c1 = this.r1c2;
        result.r2c2 = this.r2c2;
        result.r2c3 = this.r3c2;
        result.r2c4 = this.r4c2;

        result.r3c1 = this.r1c3;
        result.r3c2 = this.r2c3;
        result.r3c3 = this.r3c3;
        result.r3c4 = this.r4c3;

        result.r4c1 = this.r1c4;
        result.r4c2 = this.r2c4;
        result.r4c3 = this.r3c4;
        result.r4c4 = this.r4c4;

        return result;
    }

    public static Matrix4 orthographicMatrix(float left, float right, float bottom, float top, float near, float far) {
        Matrix4 result = new Matrix4();

        float tx = -(right + left) / (right - left);
        float ty = -(top + bottom) / (top - bottom);
        float tz = -(far + near) / (far - near);

        result.r1c1 = 2f / (right - left);
        result.r2c2 = 2f / (top - bottom);
        result.r3c3 = -2f / (far - near);
        result.r1c4 = tx;
        result.r2c4 = ty;
        result.r3c4 = tz;

        return result;
    }

    public void toBuffer(FloatBuffer buffer) {
        buffer.put(r1c1).put(r2c1).put(r3c1).put(r4c1);
        buffer.put(r1c2).put(r2c2).put(r3c2).put(r4c2);
        buffer.put(r1c3).put(r2c3).put(r3c3).put(r4c3);
        buffer.put(r1c4).put(r2c4).put(r3c4).put(r4c4);
        buffer.flip();
    }
}
