package me.glassware.entities;

public class Item
{
	public enum ItemType{Potion, Sword}

	private String name;
	private int damage;
	private int tier;
	private int type;
	private int id;
	
	public Item () {}
	public Item(String name)
	{
		
		this.name=name;
		//Something along the lines of
		//Object temp =Game.itemRes.lookUp(name)
		//damage=Integer.parseInt(temp.next());
		//tier = temp.nextInt();
		//id = temp.nextField();
		
	}
	public String getName()
	{
		return name;
	}
	public void useItem()
	{
		//Do shit
		switch(name)
		{
			case "sword":	System.out.println("Die, foul beast!");
							break;
			case "potion":  Entity.player.healDamage(5);
							break;
		}
	}
}
