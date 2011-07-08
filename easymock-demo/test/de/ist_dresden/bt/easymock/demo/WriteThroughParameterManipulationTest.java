package de.ist_dresden.bt.easymock.demo;

import java.util.Date;

import junit.framework.Assert;

import org.easymock.EasyMock;
import org.easymock.IAnswer;
import org.easymock.IExpectationSetters;
import org.junit.Test;

/**
 * Testcase which demonstrates the Usage of
 * {@link EasyMock#getCurrentArguments()} and
 * {@link IExpectationSetters#andAnswer(IAnswer)}
 * 
 * @author bjoern
 * 
 */
public class WriteThroughParameterManipulationTest {

	/**
	 * Test the real implementation. This should do the opposite (increment ->
	 * true) of the test with easymock (decrement -> false).
	 */
	@Test
	public void sanityCheck() {
		WriteThroughParameterClass instance = new WriteThroughParameterClass();
		boolean result = instance.methodUnderTest();
		Assert.assertTrue(result);
	}

	/**
	 * Test if a local variable can be altered in a mocked method.
	 */
	@Test
	public void testManipulateByMockedMethod() {
		WriteThroughParameterClass instance = EasyMock
				.createMockBuilder(WriteThroughParameterClass.class)
				.addMockedMethod("increment").createMock();
		instance.increment(EasyMock.isA(Date.class));
		EasyMock.expectLastCall().andAnswer(decrementDateInFirstParameter());
		EasyMock.replay(instance);
		boolean result = instance.methodUnderTest();
		Assert.assertFalse(result);
	}

	/**
	 * {@link IAnswer} which takes the first argument, casts it to {@link Date},
	 * and decrements the year of the Date by 1.
	 * 
	 * @return An {@link IAnswer} manipulating the first Parameter.
	 */
	private IAnswer<Void> decrementDateInFirstParameter() {
		return new AnswerDecrementDate();
	}

	/**
	 * Class which takes the first argument in {@link #answer()}, casts it to
	 * {@link Date}, and decrements the year of the Date by 1.
	 * 
	 * @author bjoern
	 */
	private static final class AnswerDecrementDate implements IAnswer<Void> {

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Void answer() throws Throwable {
			Date myDate = ((Date) EasyMock.getCurrentArguments()[0]);
			decrement(myDate);
			return null;
		}

		/**
		 * Decrement the given year by 1.
		 * 
		 * @param date
		 *            the date to manipulate.
		 * @return oh, nothing ...
		 */
		@SuppressWarnings("deprecation")
		private void decrement(Date date) {
			date.setYear(date.getYear() - 1);
		}
	}
}
