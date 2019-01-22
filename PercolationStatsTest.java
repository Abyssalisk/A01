package a01.tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import a01.PercolationStats;

class PercolationStatsTest
{

	PercolationStats p = new PercolationStats(200, 100); // PercolationStats
															// Object

	@BeforeEach
	void setUp() throws Exception
	{
	}

	@Test
	public void testStatsMean()
	{
		double expected = p.percolationMean();
		assertTrue(p.percolationMean() == expected);
	}

	@Test
	public void testStatsStdDev()
	{
		double expected = p.standarDeviation();
		assertTrue(p.standarDeviation() == expected);
	}

	@Test
	public void testStatsConfidenceLow()
	{
		double expected = p.confidenceLow();
		assertTrue(p.confidenceLow() == expected);
	}

	@Test
	public void testStatsConfidenceHigh()
	{
		double expected = p.confidenceHigh();
		assertTrue(p.confidenceHigh() == expected);
	}

}
