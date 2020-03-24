package thestonedturtle.activitytracker;

import com.google.inject.Provides;
import javax.annotation.Nullable;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.Player;
import net.runelite.api.events.HitsplatApplied;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import thestonedturtle.activitytracker.activities.Activity;

@Slf4j
@PluginDescriptor(
	name = "Activity Tracker"
)
public class ActivityTrackerPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private ActivityTrackerConfig config;

	@Provides
	ActivityTrackerConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(ActivityTrackerConfig.class);
	}

	@Nullable
	private Activity activity = null;

	@Subscribe
	protected void onHitsplatApplied(final HitsplatApplied e)
	{
		final Player localPlayer = client.getLocalPlayer();
		if (activity == null || localPlayer == null)
		{
			return;
		}

		switch (e.getHitsplat().getHitsplatType())
		{
			case DAMAGE_ME:
				if (localPlayer.equals(e.getActor()))
				{
					activity.addDamageTaken(e.getHitsplat().getAmount(), e.getActor());
				}
				else
				{
					activity.addDamageDealt(e.getHitsplat().getAmount(), e.getActor());
				}
				break;
			case VENOM:
			case POISON:
				if (localPlayer.equals(e.getActor()))
				{
					activity.addDamageTaken(e.getHitsplat().getAmount(), e.getActor());
				}
				break;
		}
	}
}
