package me.glassware.states;

import me.glassware.handlers.GameStateManager;
import me.glassware.main.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Loading extends GameState
{
	private float progress;
	private BitmapFont font;
	
	public Loading(GameStateManager gsm)
	{
		super(gsm);
		progress=0;
		font = new BitmapFont();
		
		Game.manager.load("res/images/assets.pack", TextureAtlas.class); //Game's Texture Atlas
		//Game.atlas.findRegions("LoadingTextures");
		//Make animation of loading textures
		//animated loading textures with progress float
		Game.manager.load("res/songs/testmusic.ogg", Music.class); //Menu music
		Game.manager.load("res/sounds/magic154.ogg", Sound.class);
	}
	public void handleInput()
	{

	}
	public void update(float dt)
	{
		progress=Game.manager.getProgress(); // gets a % of loading completed
		if(Game.manager.update())
		{
			Game.atlas=Game.manager.get("res/images/assets.pack");
			gsm.setState(gsm.MENU);
			gsm.disposeState(gsm.LOADING);
		}

			
	}
	public void render()
	{
		sb.begin();
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);		
		font.draw(sb, Float.toString(progress), 50, 50);
		sb.end();
	}
	public void dispose()
	{

	}

}
