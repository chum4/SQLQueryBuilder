package query;

import java.util.Map;

public class QueryCompiler {
	
	private Map<String, String> functionCode;
	private String query;
	
	public QueryCompiler(Map<String, String> functionCode)
	{
		this.functionCode = functionCode;
		this.query = "";
	}
	
	public void functionOrderer()
	{
		for(FunctionNames n: FunctionNames.values())
		{
			if(this.functionCode.containsKey(n.name()))
			{
				if(n.name().equalsIgnoreCase("Query")) fQuery(n.name());
				if(n.name().equalsIgnoreCase("Select")) fSelect(n.name());
				if(n.name().equalsIgnoreCase("Where")) fWhere(n.name());
				if(n.name().equalsIgnoreCase("OrWhere")) fOrWhere(n.name());
				if(n.name().equalsIgnoreCase("AndWhere")) fAndWhere(n.name());
				if(n.name().equalsIgnoreCase("WhereBetween")) fWhereBetween(n.name());
				if(n.name().equalsIgnoreCase("WhereIn")) fWhereIn(n.name());
				if(n.name().equalsIgnoreCase("ParametarList")) fParametarList(n.name());
				//if(n.name().equalsIgnoreCase("Join")) fQuery(n.name());
				//if(n.name().equalsIgnoreCase("On")) fOrderBy(n.name());
				if(n.name().equalsIgnoreCase("WhereEndsWith")) fWhereEndsWith(n.name());
				if(n.name().equalsIgnoreCase("WhereStartsWith")) fWhereStartsWith(n.name());
				if(n.name().equalsIgnoreCase("WhereContains")) fWhereContains(n.name());
				if(n.name().equalsIgnoreCase("OrderBy")) fOrderBy(n.name());
				if(n.name().equalsIgnoreCase("OrderByDesc")) fOrderByDesc(n.name());
				
			}
		}
	}
	
	private void fQuery(String n)
	{
		String s = this.functionCode.get(n).replace("\"", "");
		this.query += "SELECT * FROM " + s;
	}
	
	private void fSelect(String n)
	{
		String s = this.functionCode.get(n).replace("\"", "");
		this.query = this.query.replace("*", s);
	}
	
	private void fOrderBy(String n)
	{
		String s = this.functionCode.get(n).replace("\"", "");
		this.query += " " + "ORDER BY " + s;
	}
	
	private void fOrderByDesc(String n)
	{
		String s = this.functionCode.get(n).replace("\"", "");
		this.query += " " + "ORDER BY " + s + " DESC";
	}
	
	private void fWhere(String n)
	{
		String s = this.functionCode.get(n).replace("\"", "");
		String [] s1 = s.split(",");
		this.query += " " + "WHERE " + s1[0] + " " + s1[1] + " " + s1[2];
	}
	
	private void fOrWhere(String n)
	{
		String s = this.functionCode.get(n).replace("\"", "");
		String [] s1 = s.split(",");
		this.query += " OR " + "WHERE " + s1[0] + " " + s1[1] + " " + s1[2];
	}
	
	private void fAndWhere(String n)
	{
		String s = this.functionCode.get(n).replace("\"", "");
		String [] s1 = s.split(",");
		this.query += " AND " + "WHERE " + s1[0] + " " + s1[1] + " " + s1[2];
	}
	
	private void fWhereBetween(String n)
	{
		String s = this.functionCode.get(n).replace("\"", "");
		String [] s1 = s.split(",");
		this.query +=" WHERE " + s1[0] + " " + " BETWEEN " +  s1[1] + " AND " + s1[2];
	}
	
	private void fWhereEndsWith(String n)
	{
		String s = this.functionCode.get(n).replace("\"", "");
		String [] s1 = s.split(",");
		this.query += " WHERE " + s1[0] + " LIKE '%" + s1[1] + "'"; 
	}
	
	private void fWhereStartsWith(String n)
	{
		String s = this.functionCode.get(n).replace("\"", "");
		String [] s1 = s.split(",");
		this.query += " WHERE " + s1[0] + " LIKE '" + s1[1] + "%'"; 
	}
	
	private void fWhereContains(String n)
	{
		String s = this.functionCode.get(n).replace("\"", "");
		String [] s1 = s.split(",");
		this.query += " WHERE " + s1[0] + " LIKE '%" + s1[1] + "%'"; 
	}
	
	private void fWhereIn(String n)
	{
		String s = this.functionCode.get(n).replace("\"", "");
		this.query += " WHERE " + this.functionCode.get(n) + " IN";
	}
	
	private void fParametarList(String n)
	{
		String s = this.functionCode.get(n).replace("\"", "'");
		this.query += " (" + s + ")";
	}
	
	
	public String getQuery()
	{
		return query;
	}

}
