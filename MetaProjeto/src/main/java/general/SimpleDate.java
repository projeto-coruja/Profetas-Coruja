package general;

import java.io.Serializable;

/**
 * <code>SimpleDate</code> is a NIH class to store very simple dates. It is limited to hold
 * only up to three values: the year, the month, and the day. However, it can also hold only 
 * the year and month values, or only the year. Exists mainly because Calendar sucks for anything
 * that is not a multi-localizated, real-time, high-load, online time checking system.
 * <br><br>
 * <code>SimpleDate</code> is immutable, so if you want to update a SimpleDate field in a object, you will need
 * to create a new instance using the old instance values if necessary.
 * <br><br>
 * <code>SimpleDate</code> is hardcoded to only accept years from 0 to 9999.
 * 
 * @author Daniel Gracia
 * @see general.SimpleDateHibernateType
 * @since Milestone 1
 */

public final class SimpleDate implements Serializable {
	
	/** Public stuff */
	
	/**
	 * Simple constants used only to make code a bit more readable. Can be accessed from outside
	 * to check what kind of date a certain instance of SimpleDate is tracking, along with <code>tracks()</code>.
	 */
	public final static short TRACKS_ONLY_YEAR = 1;
	public final static short TRACKS_YEAR_AND_MONTH = 2;
	public final static short TRACKS_FULL_DATE = 3;
		
	
	/**
	 * Constructs a full date <code>SimpleDate</code> instance.
	 * 
	 * @param year
	 * @param month
	 * @param day
	 */
	public SimpleDate(short year, short month, short day) {
		this(year, month, day, TRACKS_FULL_DATE);
	}
	
	/**
	 * Constructs a year/month <code>SimpleDate</code> instance
	 * 
	 * @param year
	 * @param month
	 */
	public SimpleDate(short year, short month) {
		this(year, month, NOT_DEFINED, TRACKS_YEAR_AND_MONTH);
	}
	
	/**
	 * Constructs a year only <code>SimpleDate</code> instance
	 * 
	 * @param year
	 */
	public SimpleDate(short year) {
		this(year, NOT_DEFINED, NOT_DEFINED, TRACKS_ONLY_YEAR);
	}
	
	/**
	 * Converts whatever value is been stored by the instance into a human-readable string.
	 * 
	 * @return a <code>String</code> representing the date.
	 */
	public String format() {
		String result = null;
		switch (dateMode) {
		case TRACKS_FULL_DATE:
			result = str(year) + "/" + str(month) + "/" + str(day);
			break;
			
		case TRACKS_YEAR_AND_MONTH:
			result = str(year) + "/" + str(month);
			break;
			
		case TRACKS_ONLY_YEAR:
			result = str(year);
			break;

		default:
			break;
		}
		return result;
	}
	
	/**
	 * Returns a short value representing the tracking mode of the instance.<br>
	 * Can be used together with the <code>TRACKS_</code> constants to check <br>
	 * what kind of date does the instance stores.
	 * 
	 * @return the instance's tracking mode (either TRACKS_FULL_DATE, TRACKS_YEAR_AND_MONTH or TRACKS_ONLY_YEAR)
	 */
	public short tracks() {
		return dateMode;
	}
	
	/**
	 * Returns the stored year value.
	 * @return the year as a <code>short</code>.
	 */
	public short getYear() {
		return year;
	}
	
	/**
	 * Returns the stored month value.
	 * 
	 * @throws IllegalAccessError if the instance doesn't track months.
	 * @return the month as a <code>short</code>.
	 */
	public short getMonth() {
		if(dateMode == TRACKS_ONLY_YEAR) {
			throw new IllegalAccessError("Instance doesn't track month");
		} else {
			return month;
		}
	}
	
	/**
	 * Returns the stored day value.
	 * 
	 * @throws IllegalAccessError if the instance doesn't track days.
	 * @return the day as a <code>short</code>.
	 */
	public short getDay() {
		if(dateMode == TRACKS_ONLY_YEAR || dateMode == TRACKS_YEAR_AND_MONTH) {
			throw new IllegalAccessError("Instance doesn't track days");
		} else {
			return day;
		}
	}
	
	/**
	 * Parses a string and transforms it in a valid <code>SimpleDate</code> instance.
	 * This method accepts the formats YYYY, YYYY/MM and YYYY/MM/DD for the argument.
	 * 
	 * @param strDate a string represating a date
	 * @return a new SimpleDate instance
	 * @throws IllegalArgumentException whenever the string passed as an argument is wrongly formatted
	 */
	public static SimpleDate parse(String strDate) throws IllegalArgumentException {
		String[] parts = strDate.split("/");
		if(parts.length == 1) {
			short year = Short.parseShort(parts[0]);
			if(year < 0 || year > 9999) throw new IllegalArgumentException("Invalid format for string (YYYY)");
			else return new SimpleDate(year);
		}
		else if(parts.length == 2) {
			short year = Short.parseShort(parts[0]);
			short month = Short.parseShort(parts[1]);
			if((year < 0 || year > 9999) && (month < 1 || month > 12)) throw new IllegalArgumentException("Invalid format for string (YYYY/MM)");
			else return new SimpleDate(year, month);
		}
		else if(parts.length == 3) {
			short year = Short.parseShort(parts[0]);
			short month = Short.parseShort(parts[1]);
			short day = Short.parseShort(parts[2]);
			if((year < 0 || year > 9999) && (month < 1 || month > 12) && (day < 1 || day > 31)) throw new IllegalArgumentException("Invalid format for string (YYYY/MM/DD)");
			else return new SimpleDate(year, month, day);
		}
		else {
			throw new IllegalArgumentException("Invalid format for string (YYYY[/MM[/DD]]");
		}
		
	}
	
	/**
	 * Compares this <code>SimpleDate</code> instance to another object. The result is
	 * {@code true} if and only if the argument is not {@code null} and is a
	 * {@code SimpleDate} onject representing the same date as this object.
	 * 
	 * @return {@code true} if the given object is a <code>SimpleDate</code>
	 * equivalent to this string. {@code false} otherwise.
	 * @throws RuntimeException if the tracking mode of any of the objects is
	 * undefined. Should never happen.
	 */
	public boolean equals(Object anObject) {
		if(!(anObject instanceof SimpleDate)){
			return false;
		}
		else if(this == anObject) {
			return true;
		}
		else {
			SimpleDate aDate = (SimpleDate) anObject;
			if(aDate.tracks() != dateMode) return false;
			
			if(dateMode == TRACKS_ONLY_YEAR)
				return (aDate.year == this.year);
			else if(dateMode == TRACKS_YEAR_AND_MONTH)
				return (aDate.year == this.year && aDate.month == this.month);
			else if(dateMode == TRACKS_FULL_DATE)
				return (aDate.year == this.year && aDate.month == this.month && aDate.day == this.day);
			else
				throw new RuntimeException("dateMode in SimpleDate instance is invalid");
		}
	}
	
	/**
	 * @see general.SimpleDateHibernateType
	 */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + dateMode;
		result = prime * result + day;
		result = prime * result + month;
		result = prime * result + year;
		return result;
	}
	
	/**
	 * Returns a string representation of the instance. An alias for {@code format()}.
	 * @see #format()
	 */
	public String toString() {
		return format();
	}
	
	/** Private stuff */
	
	/** Serializable shenanigans (auto-generated by Eclipse) */
	private static final long serialVersionUID = 6146765174562204074L;

	
	private final short year;
	private final short month;
	private final short day;
	private final short dateMode;
	
	/** Used when creating a year/month or year-only instance */
	private final static short NOT_DEFINED = 0;
	
	/** Checks if value represents a short (30 days) month */
	private boolean isShortMonth(short month) {
		return (month == 4 || month == 6 || month == 9 || month == 11);
	}
	
	/** Checks if value represents February */
	private boolean isFebruary(short month) {
		return (month == 2);
	}
	
	/** Checks if value represents a leap year */
	private boolean isLeapYear(short year) {
		return (year % 4 == 0);
	}
	
	/**
	 * Alias for {@code Short.toString(short s)}.
	 * 
	 * @param s
	 * @return
	 */
	private String str(short s) {
		return Short.toString(s);
	}
	
	/**
	 * "Real" constructor for {@code SimpleDate}, validate all inputs.
	 * 
	 * @throws IllegalArgumentException if the values are invalid
	 * @param year
	 * @param month
	 * @param day
	 * @param dateMode the tracking mode defined by the public constructors
	 */
	private SimpleDate(short year, short month, short day, short dateMode) {
		boolean suceed = false;
		if((year >= 0 && year <= 9999)) {
			if(isShortMonth(month)) {
				if((day >= 0) && (day <= 30)) {
					suceed = true;
				}
			}
			else if(isFebruary(month)) {
				if(isLeapYear(year)) {
					if((day >= 0) && (day <= 29)) {
						suceed = true;
					}
				}
				else {
					if((day >= 0) && (day <= 28)) {
						suceed = true;
					}
				}
			}
			else if(month >= 0 && month <= 12) {
				if((day >= 0) && (day <= 31)) {
					suceed = true;
				}
			}
		}
		
		if(suceed) {
			this.year = year;
			this.month = month;
			this.day = day;
			this.dateMode = dateMode;
		}
		else throw new IllegalArgumentException("Invalid values");
	}

}
