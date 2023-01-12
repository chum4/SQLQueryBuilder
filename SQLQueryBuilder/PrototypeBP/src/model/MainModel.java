package model;



import database.main.Database;
import database.main.DatabaseImplementation;
import database.main.MSSQLrepository;
import database.settings.Settings;
import database.settings.SettingsImplementation;
import lombok.Data;
import observer.Notification;
import observer.NotificationEnum;
import observer.PublisherImplementation;
import query.QueryBuilder;
import repository.implementation.Information;
import table.model.TableModel;

@Data
public class MainModel extends PublisherImplementation{
	
	private Database database;
	private Settings settings;
	private TableModel tableModel;
	private QueryBuilder builder;
	
	public MainModel()
	{
		this.settings = initSettings();
		this.database = new DatabaseImplementation(new MSSQLrepository(this.settings));
		tableModel = new TableModel();
	}
	
	private Settings initSettings()
	{
		Settings settingsImplementation = new SettingsImplementation();
        settingsImplementation.addParameter("mssql_ip", "147.91.175.155");
        settingsImplementation.addParameter("mssql_database", "bp2021_t1");
        settingsImplementation.addParameter("mssql_username", "bp2021_t1_readonly");
        settingsImplementation.addParameter("mssql_password", "bp2021_t1_readonly");
        return settingsImplementation;
	}
	
	public void loadResource()
	{
		Information i = (Information) this.database.loadResource();
		this.notifySubscribers(new Notification(NotificationEnum.RESOURCE_LOADED, i));
	}
	
	public void readDataFromTable(String fromTable)
	{
		tableModel.setRows(this.database.readDataFromTable(fromTable));
	}
	
	public TableModel getTableModel()
	{
		return tableModel;
	}
	
	public void setTableModel(TableModel tableModel)
	{
		this.tableModel = tableModel;
	}
	
	public String buildQuery(String query)
	{
		this.builder = new QueryBuilder(query);
		this.builder.ExecuteQuery();
		return this.builder.getQuery();
	}
	

}
