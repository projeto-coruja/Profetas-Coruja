package general;

/**
 * A very simple class for representing doubles called {@code Tuple} because I can't call my class
 * {@code Double} and {@code Pair} doesn't sound as cool.
 * 
 * Immutable and generic.
 * 
 * @author Daniel Gracia
 * @since Milestone 1
 *
 * @param <E> the first item class
 * @param <T> the second item class
 */

public class Tuple<E, T> {
	public final E fst;
	public final T snd;
	
	public Tuple(E fst, T snd) {
		this.fst = fst;
		this.snd = snd;
	}
}