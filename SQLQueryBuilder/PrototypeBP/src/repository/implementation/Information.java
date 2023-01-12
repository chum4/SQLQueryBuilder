package repository.implementation;

import lombok.Data;
import lombok.ToString;
import repository.main.DBNode;
import repository.main.DBNodeComposite;

@Data
//@ToString(callSuper = true)
public class Information extends DBNodeComposite{
	
	public Information(String name)
	{
		super(name, null);
	}

	@Override
	public void addChild(DBNode child)
	{
		if(child != null && child instanceof Entity)
		{
			Entity entity = (Entity) child;
			this.getChildren().add(entity);
		}
		
	}
	
	

}
