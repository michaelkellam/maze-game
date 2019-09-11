package com.kellam.level;

import javax.sound.sampled.Clip;

import com.kellam.entities.Handler;
import com.kellam.main.SoundPlayer;

public class LevelProperties {
	
	/*
	 * These are the specific options for a specific level.
	 * There wasn't really anything to put here except
	 * for the sound cues, which are the two songs, one
	 * for the main menu and one for the actual gameplay.
	 */
	
	public static void executeProp(int level, String slevel) {
		
		if (level == 1 || slevel == "Help" && !SoundPlayer.clip.isActive()) {
			if (!Handler.gameStarted)
				SoundPlayer.play("./res/sounds/gaem.wav", Clip.LOOP_CONTINUOUSLY);
			Handler.gameStarted = true;
		} else if (level == 2)
			SoundPlayer.clip.close();
		if (level != 1) {
			if (!SoundPlayer.clip.isActive()) {
				SoundPlayer.play("./res/sounds/ouide_game.wav", Clip.LOOP_CONTINUOUSLY);
				
			}
		}
	}
}
