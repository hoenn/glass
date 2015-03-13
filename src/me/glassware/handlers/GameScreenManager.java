package me.glassware.handlers;

import java.util.Stack;

import me.glassware.main.Game;
import me.glassware.screens.GameScreen;
import me.glassware.screens.Loading;
import me.glassware.screens.Menu;
import me.glassware.screens.Pause;

public class GameScreenManager 
{
	private Game game;
	
	private GameScreen[] gameScreens;
	
	public final int NUMSTATES=3;
	public final int LOADING=0;
	public final int MENU = 1;
	public final int PAUSE=2;
	
	private int currentScreen;
	
	private GameScreen currentGameScreen;
	
	public GameScreenManager(Game game)
	{
		this.game = game;
		gameScreens = new GameScreen[NUMSTATES];
		
		currentScreen=0; //Set First State
		setScreen(currentScreen);
	}
	public Game getGame()
	{
		return game; 
	}
	public void update(float dt)
	{
		currentGameScreen.update(dt);
	}	
	public void render()
	{
		currentGameScreen.render();
	}
	private GameScreen getScreen(int screen)
	{
		if(screen==LOADING) return new Loading(this);
		if(screen==MENU) return new Menu(this);
		if(screen==PAUSE) return new Pause(this);
		return null;
	}
	public void setScreen(int screen)
	{
		if(gameScreens[screen]==null) //If the desired Screen does not exist
		{
			gameScreens[screen]=getScreen(screen); //Make it
			currentGameScreen=gameScreens[screen]; //Set current Screen to it
		}
		else //If the desired Screen does exist
		{
			currentGameScreen.dispose(); //Dispose the calling Screen
			currentGameScreen=gameScreens[screen];//Set current Screen to desired
			currentGameScreen.resume();//Resume the Desired Screen (because it is exists and wasn't destroyed)
		}
	}
	public void setScreenPauseThis(int screen)
	{
		if(gameScreens[screen]==null)//If the desired Screen does not exist
		{
			currentGameScreen.pause(); //Pause calling Screen
			gameScreens[screen]=getScreen(screen); //Generate Desired Screen
			currentGameScreen=gameScreens[screen]; //Set screen to Desired Screen
		}
		else //If the desired Screen exists
		{
			currentGameScreen.pause(); //Pause Calling Screen
			currentGameScreen=gameScreens[screen]; //Set current Screen to desired
			currentGameScreen.resume(); //Resume Desired
		}
	}
	
	public void disposeScreen(int screen)
	{
		GameScreen g= gameScreens[screen]; //Instantiate Desired Screen
		gameScreens[screen]=null; //Set to Null for Garbage Collection
		g.dispose(); //Proper dispose
	}
}