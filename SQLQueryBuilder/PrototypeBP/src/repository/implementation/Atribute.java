package repository.implementation;

import lombok.Data;
import lombok.ToString;
import repository.enums.AttributeType;
import repository.main.DBNode;
import repository.main.DBNodeComposite;

@Data
//@ToString(callSuper = true)
public class Atribute extends DBNodeComposite{
	
	private AttributeType attributeType;
	private int lenght;
	private Atribute inRelationWith;
	
	public Atribute(String name, DBNode parent)
	{
		super(name, parent);
	}
	
	public Atribute(String name, DBNode parent, AttributeType attributeType, int lenght)
	{
		super(name, parent);
	}

	@Override
	public void addChild(DBNode child) 
	{
		if(child != null && child instanceof AttributeConstraint)
		{
			AttributeConstraint attributeConstraint = (AttributeConstraint) child;
			this.getChildren().add(attributeConstraint);
		}
		
	}
	
	
	

}
