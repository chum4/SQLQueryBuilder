package database.main;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import repository.data.Row;
import repository.main.DBNode;

@Data
@AllArgsConstructor
public class DatabaseImplementation implements Database{
	
	private Repository repository;

	@Override
	public DBNode loadResource() 
	{
		return repository.getSchema();
	}

	@Override
	public List<Row> readDataFromTable(String tableName) 
	{
		return repository.get(tableName);
	}
	
	

}
