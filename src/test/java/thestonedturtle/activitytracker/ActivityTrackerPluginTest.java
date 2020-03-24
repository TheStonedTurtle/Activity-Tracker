package thestonedturtle.activitytracker;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class ActivityTrackerPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(ActivityTrackerPlugin.class);
		RuneLite.main(args);
	}
}
