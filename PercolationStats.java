/************************************************
 * Author(s): Gavin Rosenvall, Spencer Rosenvall
 * Class: CSIS 2420
 * Professor: Frau Posch
 * Assignment: A01_Percolation
 ************************************************/

package a01;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * Class PercolationStats generates statistics from class Percolation.java.
 * 
 * @author GavinR, SpencerR
 *
 */
public class PercolationStats
{

	double[] averages;

	/**
	 * Performs T independent experiments on an N x N grid.
	 * 
	 * @param N
	 * @param T
	 */
	public PercolationStats(int N, int T)
	{
		if (N <= 0 || T <= 0)
		{
			throw new IllegalArgumentException();
		} else
		{
			this.averages = new double[T];
			startPercolation(N, T);
		}
	}

	/**
	 * Starts the percolation from class Percolation.
	 * 
	 * @param n
	 * @param t
	 * @return
	 */
	private double[] startPercolation(int N, int T)
	{
		int index = 0;
		for (int i = 0; i < T; i++)
		{
			Percolation percolation = new Percolation(N);

			while (!percolation.percolates())
			{

				int row = StdRandom.uniform(0, N);
				int column = StdRandom.uniform(0, N);
				if (!percolation.isOpen(row, column))
				{
					percolation.open(row, column);
					index++;
				}
			}
			averages[i] = ((double) index) / (N * N);
			index = 0;
		}
		return averages;
	}

	/**
	 * Samples the mean of the percolation threshold.
	 * 
	 * @return
	 */
	public double percolationMean()
	{
		return StdStats.mean(averages);

	}

	/**
	 * Samples the standard deviation of the percolation threshold.
	 * 
	 * @return
	 */
	public double standarDeviation()
	{
		return StdStats.stddev(averages);
	}

	/**
	 * Formula for the half value needed for the 95% low and high confidence
	 * levels.
	 * 
	 * @return
	 */
	private double calculateHalf()
	{
		return 1.96 * standarDeviation() / Math.sqrt(averages.length);
	}

	/**
	 * Provides the low end point of 95% confidence interval.
	 * 
	 * @return
	 */
	public double confidenceLow()
	{
		return percolationMean() - calculateHalf();
	}

	/**
	 * Provides the high end point of 95% confidence interval.
	 * 
	 * @return
	 */
	public double confidenceHigh()
	{
		return percolationMean() + calculateHalf();

	}

	public static void main(String[] args)
	{

		PercolationStats ps = new PercolationStats(200, 100);

		StdOut.println("Test Results: ");
		StdOut.println();
		StdOut.printf("%-30s %.10f\n", "Mean:", ps.percolationMean());
		StdOut.printf("%-30s %.10f\n", "Standard Deviation:", ps.standarDeviation());
		StdOut.printf("%-30s %.10f\n", "Confidence Low:", ps.confidenceLow());
		StdOut.printf("%-30s %.10f\n", "Confidence High:", ps.confidenceHigh());
	}
}
