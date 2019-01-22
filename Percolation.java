/************************************************
 * Author(s): Gavin Rosenvall, Spencer Rosenvall
 * Class: CSIS 2420
 * Professor: Frau Posch
 * Assignment: A01_Percolation
 ************************************************/

package a01;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Class percolation uses a variety of algorithms to demonstrate percolation
 * within N by N grid.
 * 
 * @author GavinR, SpencerR
 *
 */
public class Percolation
{

	int n, size, top, bottom, openSites;
	WeightedQuickUnionUF uf;
	boolean[][] grid;

	/**
	 * Create NbyN grid with all sides blocked.
	 * 
	 * @param N
	 */
	public Percolation(int N)
	{
		n = N;
		if (n < 0)
		{
			throw new java.lang.IllegalArgumentException();
		}
		size = n * n;
		grid = new boolean[n][n];
		uf = new WeightedQuickUnionUF(size + 1);
		top = n * n;
		bottom = (n * n) - 1;

		for (int i = 0; i < (grid.length); i++)
		{
			for (int j = 0; j < (grid[i].length); j++)
			{
				grid[i][j] = true;
			}
		}
	}

	/**
	 * Opens site (row i, column j) if it is not open already.
	 * 
	 * @param i
	 * @param j
	 */
	public void open(int i, int j)
	{
		if (checkRange(i, j))
		{
			if (!isOpen(i, j))
			{
				grid[i][j] = false;
				unionToNeighbors(i, j);
				openSites++;
			}
		} // If the site isn't open, It is opened and then checked for neighbors
			// to union.
	}

	/**
	 * Determines whether the site (row i, column j) is open.
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	public boolean isOpen(int i, int j)
	{
		if (checkRange(i, j))
		{
			if (grid[i][j] == false)
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * Determines whether this site (row i, column j) is full.
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	public boolean isFull(int i, int j)
	{
		if (checkRange(i, j))
		{
			return uf.connected((siteID(i, j)), top); // check if it's full.
		} // In order to be full the site must be touching the top.
		return true;
	}

	/**
	 * Determines whether the system percolates.
	 * 
	 * @return
	 */
	public boolean percolates()
	{
		return uf.connected(top, bottom); // check for the connection between
											// the top and bottom rows
	}

	private boolean checkRange(int i, int j)
	{
		if (i < 0 || j < 0)
		{
			throw new java.lang.IndexOutOfBoundsException();
		} else
		{
			return true;
		}
	}

	/**
	 * Returns a unique id for a site where i = row and j = column.
	 * 
	 * @param
	 * @param
	 * @return
	 */
	private int siteID(int i, int j)
	{
		return (i * n) + (j);
	}

	/**
	 * Checks if there is a union to neighbors. i = row, j = column.
	 * 
	 * @param i
	 * @param j
	 */
	private void unionToNeighbors(int i, int j)
	{
		if (j != 0)
		{
			if (isOpen(i, j - 1))
			{
				uf.union((siteID(i, j)), siteID(i, j - 1));
			}
		}

		if (j != (n - 1))
		{
			if (isOpen(i, j + 1))
			{
				uf.union((siteID(i, j)), siteID(i, j + 1));
			}
		}

		if (i != 0)
		{
			if (isOpen(i - 1, j))
			{
				uf.union((siteID(i, j)), siteID(i - 1, j));
			}
		}

		if (i != (n - 1))
		{
			if (isOpen(i + 1, j))
			{
				uf.union((siteID(i, j)), siteID(i + 1, j));
			}
		}

		if (i == 0)
		{
			uf.union((siteID(i, j)), top);
		}

		if (i == (n - 1))
		{
			uf.union((siteID(i, j)), bottom);
		}

	}

	/**
	 * Returns the number of open sites for the visualizer display.
	 * 
	 * @return
	 */
	public int numberOfOpenSites()
	{
		return openSites;
	}
}
