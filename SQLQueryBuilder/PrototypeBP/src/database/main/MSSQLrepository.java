package database.main;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import database.settings.Settings;
import error.ErrorDialog;
import repository.data.Row;
import repository.enums.AttributeType;
import repository.implementation.Atribute;
import repository.implementation.Entity;
import repository.implementation.Information;
import repository.main.DBNode;

public class MSSQLrepository implements Repository{
	
	private Settings settings;
	private Connection connection;
	
	public MSSQLrepository(Settings settings)
	{
		this.settings = settings;
	}
	
	private void initConnection() throws SQLException, ClassNotFoundException
	{
		Class.forName("net.sourceforge.jtds.jdbc.Driver");
		String ip = (String) settings.getParameter("mssql_ip");
		String database = (String) settings.getParameter("mssql_database");
		String username = (String) settings.getParameter("mssql_username");
		String password = (String) settings.getParameter("mssql_password");
		Class.forName("net.sourceforge.jtds.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:jtds:sqlserver://"+ip+"/"+database,username,password);
	}
	
	private void closeConnection()
	{
		try 
		{
			connection.close();
		} catch (Exception e) 
		{
			e.printStackTrace();
		}finally 
		{
			connection = null;
		}
	}

	@Override
	public DBNode getSchema() 
	{
		try 
		{
			this.initConnection();
			
			DatabaseMetaData metaData = connection.getMetaData();
			Information i = new Information("RAF_BP_Primer");
			
			String tableType[] = {"TABLE"};
			ResultSet tables = metaData.getTables(connection.getCatalog(), null, null, tableType);
			
			while(tables.next())
			{
				String tableName = tables.getString("TABLE_NAME");
				Entity newTable = new Entity(tableName, i);
				i.addChild(newTable);
				
				ResultSet columns = metaData.getColumns(connection.getCatalog(), null, tableName, null);
				
				while(columns.next())
				{
					String columnName = columns.getString("COLUMN_NAME");
					String columnType = columns.getString("TYPE_NAME");
					int columnSize = Integer.parseInt(columns.getString("COLUMN_SIZE"));
					Atribute attribute = new Atribute(columnName, newTable, AttributeType.valueOf(columnType.toUpperCase()), columnSize);
					newTable.addChild(attribute);
				}
			}
			
			return i;
		} catch (Exception e) 
		{
			e.printStackTrace();
		}finally
		{
			this.closeConnection();
		}
		
		return null;
	}

	@Override
	public List<Row> get(String from) 
	{
		List<Row> rows = new ArrayList<Row>();
		
		try 
		{
			this.initConnection();
			
			String query = from;
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next())
			{
				Row row = new Row();
				row.setName(from);
				
				ResultSetMetaData resultSetMetaData = rs.getMetaData();
				for(int i = 1; i <= resultSetMetaData.getColumnCount(); i++)
				{
					row.addField(resultSetMetaData.getColumnName(i), rs.getString(i));
				}
				rows.add(row);
			}
		} catch (Exception e) 
		{
			//e.printStackTrace();
			ErrorDialog er = new ErrorDialog("Moguce greske:\nPogresno unet parametar/naziv u funkciju\\nZaboravljeni navodnici.");
		}finally
		{
			this.closeConnection();
		}
		
		return rows;

	}

}
