import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import Gui.MesonetFrame;

/**
 * The main program is Executed here, and it prints out MapData statistics for
 * the speicfied year, month, day, hour, minute.
 * 
 * @author Feras salous version 2018-10-03 Lab Section 13
 */

public class Driver {

	public static void main(String[] args) throws IOException {
		SwingUtilities.invokeLater(new Runnable()
				{
					public void run()
					{
						new MesonetFrame();
						
					}
				});
		

		final int YEAR = 2018;
		final int MONTH = 8;
		final int DAY = 30;
		final int HOUR = 17;
		final int MINUTE = 45;

		final String directory = "Data";

		MapData mapData = new MapData(YEAR, MONTH, DAY, HOUR, MINUTE, directory);

		mapData.parseFile();
		System.out.println(mapData);

	}

}
