/*
 * Copyright (c) 2020, TheStonedTurtle <https://github.com/TheStonedTurtle>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package thestonedturtle.activitytracker.activities;

import java.time.Duration;
import java.time.Instant;
import lombok.Getter;

public abstract class PauseableActivity extends BasicActivity
{
	@Getter
	Duration timePaused = Duration.ZERO;

	@Override
	public boolean isTracking()
	{
		return endTime == null;
	}

	@Override
	public void reset()
	{
		super.reset();
		timePaused = Duration.ZERO;
	}

	public void pause()
	{
		endTime = Instant.now();
	}

	public void unpause()
	{
		if (endTime == null)
		{
			return;
		}

		final Duration pauseDuration = Duration.between(endTime, Instant.now());
		startTime = startTime.plus(pauseDuration);
		timePaused = timePaused.plus(pauseDuration);
		endTime = null;
	}

	public void togglePause()
	{
		if (endTime == null)
		{
			pause();
		}
		else
		{
			unpause();
		}
	}
}
