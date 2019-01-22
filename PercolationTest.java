package a01.tests;

/************************************************
 * Author(s): Gavin Rosenvall, Spencer Rosenvall
 * Class: CSIS 2420
 * Professor: Frau Posch
 * Assignment: A01_Percolation
 ************************************************/

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import a01.Percolation;

/**
 * Class PercolationTests performs JUnit tests on classes PercolationStats.java
 * and Percolation.java.
 * 
 * @author GavinR, SpencerR
 *
 */
class PercolationTest
{
	private Percolation p4 = new Percolation(4); // Percolation Object

	@BeforeEach
	void setUp() throws Exception
	{
	}

	@Test
	public void testPercolation()
	{
		Percolation p3 = new Percolation(3);
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				assertFalse(p3.isOpen(i, j));
			}
		}
	}

	@Test
	void testPercolation_gridSize()
	{
		Percolation p3 = new Percolation(3);
		assertThrows(Exception.class, () ->
		{
			p3.isOpen(3, 3); // (3, 3)should not exist.
		});
	}

	@Test
	public void testOpen()
	{
		assertFalse(p4.isOpen(0, 0));
		p4.open(0, 0);
		assertTrue(p4.isOpen(0, 0));
	}

	@Test
	public void testOpen_01()
	{
		p4.open(0, 1);
		assertTrue(p4.isOpen(0, 1));
	}

	@Test
	public void testIsOpen()
	{
		assertFalse(p4.isOpen(3, 3));
		p4.open(3, 3);
		assertTrue(p4.isOpen(3, 3));
	}

	@Test
	public void testIsFull_01()
	{
		p4.open(0, 1);
		assertTrue(p4.isFull(0, 1));
	}

	@Test
	public void testIsFull_diagonal()
	{
		p4.open(0, 1);
		p4.open(1, 2);
		assertFalse(p4.isFull(1, 2));
	}

	@Test
	public void testIsFull_flowAroundCorners()
	{
		p4.open(0, 1);
		p4.open(1, 1);
		p4.open(1, 2);
		p4.open(2, 2);
		assertTrue(p4.isFull(2, 2));
	}

	@Test
	public void testPercolates_allClosed()
	{
		assertFalse(p4.percolates());
	}

	@Test
	public void testPercolates_touchingBottom()
	{
	}

}
