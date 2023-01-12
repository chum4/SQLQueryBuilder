package database.main;

import java.util.List;

import repository.data.Row;
import repository.main.DBNode;

public interface Repository {
	
	DBNode getSchema();
	
	List<Row> get(String from);

}
