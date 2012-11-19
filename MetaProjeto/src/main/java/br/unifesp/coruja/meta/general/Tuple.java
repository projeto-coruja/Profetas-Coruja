package br.unifesp.coruja.meta.general;

public class Tuple<E, T> {
	public final E fst;
	public final T snd;
	
	public Tuple(E fst, T snd) {
		this.fst = fst;
		this.snd = snd;
	}
}