package com.Wolfaton.game;

import java.nio.FloatBuffer;

public class Vector4 {
    public float x, y, z, w;

    public Vector4(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Vector4() {
        this.x = 0f;
        this.y = 0f;
        this.z = 0f;
        this.w = 0f;
    }

    public float squaredLength() {
        return x * x + y * y + z * z + w * w;
    }

    public float length() {
        return (float) Math.sqrt(squaredLength());
    }

    public Vector4 add(Vector4 inputVec) {
        float x = this.x + inputVec.x;
        float y = this.y + inputVec.y;
        float z = this.z + inputVec.z;
        float w = this.w + inputVec.w;
        return new Vector4(x, y, z, w);
    }

    public Vector4 multiplyScalar(float scalar) {
        float x = this.x * scalar;
        float y = this.y * scalar;
        float z = this.z * scalar;
        float w = this.w * scalar;
        return new Vector4(x, y, z, w);
    }

    public Vector4 negate() {
        return multiplyScalar(-1f);
    }

    public Vector4 subtract(Vector4 inputVec) {
        return this.add(inputVec.negate());
    }

    public Vector4 divide(float scalar) {
        return multiplyScalar(1f / scalar);
    }

    public float dotProduct(Vector4 inputVec) {
        return this.x * inputVec.x + this.y * inputVec.y + this.z * inputVec.z + this.w * inputVec.w;
    }

    public void toBuffer(FloatBuffer buffer) {
        buffer.put(x).put(y);
        buffer.flip();
    }
}
