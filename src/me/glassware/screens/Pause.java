package me.glassware.screens;

import me.glassware.handlers.Animation;
import me.glassware.handlers.GameInput;
import me.glassware.handlers.GameScreenManager;
import me.glassware.main.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Pause extends GameScreen
{
	private OrthographicCamera cam;
	
	private Animation ani;
	
	private String pauseMessage;
	private float pauseMessageX;
	private float pauseMessageY;
	
	private BitmapFont font;
	
	private Color color;
	
	public Pause(GameScreenManager gsm)
	{
		super(gsm);
		pauseMessage="Press ESC to Continue";
		pauseMessageX=Game.V_WIDTH/2-Game.font.getBounds(pauseMessage).width/2;
		pauseMessageY=Game.V_HEIGHT/2+Game.font.getBounds(pauseMessage).height/2;
	
		ani=new Animation();
		TextureRegion frames = Game.atlas.findRegion("gooSleep");
		ani.setFrames(frames.split(20,20)[0], .4f);
		

		font = new BitmapFont();
		color = new Color(Color.BLACK);
		
		cam = new OrthographicCamera();
		cam.setToOrtho(false, Game.V_WIDTH, Game.V_HEIGHT);
		
	}
	public void render()
	{
		sb.begin();
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);		
		font.setColor(color);
		font.draw(sb, pauseMessage, pauseMessageX, pauseMessageY);
		sb.draw(ani.getFrame(), Game.V_WIDTH/2, pauseMessageY-Game.V_HEIGHT/6);
		sb.end();
	}
	public void handleInput()
	{
		if(GameInput.isPressed(GameInput.BUTTON_ESC))
		{
			font.setColor(Color.WHITE);
			gsm.setScreen(gsm.MENU);
		}
	}

	public void update(float dt)
	{
		handleInput();
		updateFontColor();
		ani.update(dt);

	}
	private void updateFontColor()
	{
		color=color.lerp(Color.WHITE, .005f);
	}

	public void dispose(){}
	public void hide(){}
	public void pause(){}
	public void render(float arg0){}
	public void resize(int arg0, int arg1){}
	public void resume(){}
	public void show(){}
}