package com.teamabnormals.endergetic.core.interfaces;

import com.teamabnormals.endergetic.common.entity.bolloom.BolloomBalloon;

/**
 * Implemented on entities to have them have their balloon attachments be unique.
 *
 * @author SmellyModder (Luke Tonon)
 */
public interface CustomBalloonPositioner {
	/**
	 * Called when the balloon attaches to this entity.
	 * This is called from BOTH sides.
	 *
	 * @param balloon - The balloon being attached.
	 */
	void onBalloonAttached(BolloomBalloon balloon);

	/**
	 * Called when a balloon detaches from this entity.
	 *
	 * @param balloon - The balloon detaching.
	 */
	void onBalloonDetached(BolloomBalloon balloon);

	/**
	 * Called when the balloon is attached to this entity every tick.
	 *
	 * @param balloon - The balloon attached.
	 */
	void updateAttachedPosition(BolloomBalloon balloon);
}
