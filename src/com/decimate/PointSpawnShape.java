package com.decimate;

public class PointSpawnShape implements SpawnShape {

	private final ParticleVector vector;

	public PointSpawnShape(ParticleVector vector) {
		this.vector = vector;
	}

	public final ParticleVector divide() {
		return vector.clone();
	}
}