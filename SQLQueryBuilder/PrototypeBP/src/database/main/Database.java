package database.main;

import java.util.List;

import repository.data.Row;
import repository.main.DBNode;

public interface Database {
	
	DBNode loadResource();
	
	List<Row> readDataFromTable(String tableName);

}
