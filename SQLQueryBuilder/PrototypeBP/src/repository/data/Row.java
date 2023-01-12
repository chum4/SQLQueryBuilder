package repository.data;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class Row {
	
	private String name;
	private Map<String, Object> fields;
	
	public Row()
	{
		this.fields = new HashMap<String, Object>();
	}
	
	public void addField(String key, Object value)
	{
		this.fields.put(key, value);
	}
	
	public void removeField(String key)
	{
		this.fields.remove(key);
	}
	

}
