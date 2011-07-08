package de.ist_dresden.bt.easymock.demo;

import java.util.Date;

public class WriteThroughParameterClass {

	/**
	 * Increment the given Date by one year. I used these deprecated Date
	 * setters because it was an easy way of having a mutable class.
	 * 
	 * @param date
	 *            input date, shall be incremented by one year.
	 */
	@SuppressWarnings("deprecation")
	public void increment(Date date) {
		date.setYear(date.getYear() + 1);
	}

	/**
	 * A Method which creates a local variable and then leaves this variable to
	 * another method for modification.
	 * 
	 * @return if the modified date is after the current date.
	 */
	public boolean methodUnderTest() {
		Date date = new Date();
		increment(date);
		Date now = new Date();
		return date.after(now);
	}

}
