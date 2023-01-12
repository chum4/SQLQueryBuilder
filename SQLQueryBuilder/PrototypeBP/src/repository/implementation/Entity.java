package repository.implementation;

import repository.main.DBNode;
import repository.main.DBNodeComposite;

public class Entity extends DBNodeComposite{
	
	public Entity(String name, DBNode parent)
	{
		super(name, parent);
	}

	@Override
	public void addChild(DBNode child) 
	{
		if(child != null && child instanceof Atribute)
		{
			Atribute attribute = (Atribute) child;
			this.getChildren().add(attribute);
		}
		
	}

	
}
