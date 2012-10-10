package br.unifesp.coruja.meta.general;

import java.io.Serializable;

public final class SimpleDate implements Serializable{
	
	/*
	 * Seção pública
	 */

	
	public final static short TRACKS_ONLY_YEAR = 1;
	public final static short TRACKS_YEAR_AND_MONTH = 2;
	public final static short TRACKS_FULL_DATE = 3;
	
	public SimpleDate(short year, short month, short day) {
		this(year, month, day, TRACKS_FULL_DATE);
	}
	
	public SimpleDate(short year, short month) {
		this(year, month, NOT_DEFINED, TRACKS_YEAR_AND_MONTH);
	}
	
	public SimpleDate(short year) {
		this(year, NOT_DEFINED, NOT_DEFINED, TRACKS_ONLY_YEAR);
	}
	
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
	
	public short tracks() {
		return dateMode;
	}
	
	public short getYear() {
		return year;
	}
	
	public short getMonth() {
		if(dateMode == TRACKS_ONLY_YEAR) {
			throw new IllegalAccessError("Instance doesn't track month");
		} else {
			return month;
		}
	}
	
	public short getDay() {
		if(dateMode == TRACKS_ONLY_YEAR || dateMode == TRACKS_YEAR_AND_MONTH) {
			throw new IllegalAccessError("Instance doesn't track days");
		} else {
			return day;
		}
	}
	
	public String toString() {
		return format();
	}
	
	public static SimpleDate parse(String strDate) throws IllegalArgumentException {
		String[] parts = strDate.split("/");
		switch (parts.length) {
		case 1:	
			if(parts[0].length() == 4) {
				return new SimpleDate(Short.parseShort(parts[0]));
			}
			else {
				throw new IllegalArgumentException("Invalid format for string (YYYY)");
			}
		
		case 2:	
			if(parts[0].length() == 4 && parts[1].length() == 2) {
				return new SimpleDate(Short.parseShort(parts[0]), Short.parseShort(parts[1]));
			}
			else {
				throw new IllegalArgumentException("Invalid format for string (YYYY-MM)");
			}
		
		case 3:	
			if(parts[0].length() == 4 && parts[1].length() == 2 && parts[2].length() == 2) {
				return new SimpleDate(Short.parseShort(parts[0]), Short.parseShort(parts[1]), Short.parseShort(parts[2]));
			}
			else {
				throw new IllegalArgumentException("Invalid format for string (YYYY-MM-DD)");
			}
			
		default:
			throw new IllegalArgumentException("Invalid format for string (YYYY[-MM[-DD]]");
		}
	}
	
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
			else if(dateMode == TRACKS_ONLY_YEAR)
				return (aDate.year == this.year && aDate.month == this.month);
			else if(dateMode == TRACKS_ONLY_YEAR)
				return (aDate.year == this.year && aDate.month == this.month && aDate.day == this.day);
			else
				throw new RuntimeException("dateMode in SimpleDate instance is invalid");
		}
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + dateMode;
		result = prime * result + day;
		result = prime * result + month;
		result = prime * result + year;
		return result;
	}
	
	/*
	 * Sessão privada
	 */
	
	private static final long serialVersionUID = 8790883863690026543L;
	
	private final short year;
	private final short month;
	private final short day;
	private final short dateMode;
	
	private final static short NOT_DEFINED = -1; 
	
	private SimpleDate(short year, short month, short day, short dateMode) {
		this.year = year;
		this.month = month;
		this.day = day;
		this.dateMode = dateMode;
	}
	
	private String str(short s) {
		return Short.toString(s);
	}

}
