package thestonedturtle.activitytracker;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

@Slf4j
@PluginDescriptor(
	name = "Activity Tracker"
)
public class ActivityTrackerPlugin extends Plugin
{
	@Inject
	private ActivityTrackerConfig config;

	@Provides
	ActivityTrackerConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(ActivityTrackerConfig.class);
	}
}
