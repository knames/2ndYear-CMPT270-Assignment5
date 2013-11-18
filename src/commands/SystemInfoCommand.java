package commands;

import containers.KennelAccess;
import entities.Kennel;

/** The command to access and return the information about the system. */
public class SystemInfoCommand extends CommandStatus 
{
		/** The information about the system. */
		private String systemInfo;
		
		/** Obtain the information about the system and assign it to the systemInfo field. */
		public void accessSystemInfo()
		{
			Kennel kennel = KennelAccess.getKennel();
			systemInfo = kennel.toString();
			successful = true;
		}
		
		/** 
		 * @precond wasSuccessful()
		 * @return the information about the system
		 */
		public String getSystemInfo()
		{
			if (!wasSuccessful())
				throw new RuntimeException("Cannot obtain the system information unless the "
				                           + "systemInfo() method has been successfully executed.");

			return systemInfo;
		}
	}
