package me.glassware.screens;


import me.glassware.handlers.GameScreenManager;
import me.glassware.main.Game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class GameScreen implements Screen
{
	protected GameScreenManager gsm;
	protected Game game;
	
	protected SpriteBatch sb;
	protected OrthographicCamera cam;
	protected OrthographicCamera hudCam;
	protected OrthographicCamera b2dCam;
	
	private final float[] zoomDepth = new float[]
			{.125f, .25f, .5f, .75f, 1f, 1.25f, 1.5f, 1.75f, 2f};
	private int currentZoomDepth=4; //1f
	
	protected GameScreen(GameScreenManager gsm)
	{
		this.gsm = gsm;
		game = gsm.getGame();
		sb=game.getSpriteBatch();
		cam=game.getCamera();
		hudCam = game.getHUDCamera();
		b2dCam=game.getb2dCamera();
	}
	protected void zoomIn()
	{
		if(currentZoomDepth>0)
		{
			cam.zoom= zoomDepth[currentZoomDepth-1];
			cam.update();
			
			b2dCam.zoom=zoomDepth[currentZoomDepth-1];
			b2dCam.update();
			
			currentZoomDepth--;
		}
	}
	protected void zoomOut()
	{
		if(currentZoomDepth<zoomDepth.length-1)
		{
			cam.zoom= zoomDepth[currentZoomDepth+1];
			cam.update();
			
			b2dCam.zoom=zoomDepth[currentZoomDepth+1];
			b2dCam.update();
			
			currentZoomDepth++;
		}
	}
	public abstract void handleInput();
	public abstract void update(float dt);
	public abstract void render();
	public abstract void dispose();

}
