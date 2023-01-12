package query;

public class QueryBuilder {
	
	private QueryValidator qValidator;
	private QueryCompiler qCompiler;
	private String query;
	
	public QueryBuilder(String s)
	{
		this.qValidator = new QueryValidator(s);
		//this.qValidator.ValidateQuery();
		
		//this.qCompiler = new QueryCompiler(functionCode);
	}
	
	public void ExecuteQuery()
	{
		this.qValidator.ValidateQuery();
		this.qCompiler = new QueryCompiler(this.qValidator.getFunctionCode());
		this.qCompiler.functionOrderer();
		System.out.println(qCompiler.getQuery());
		this.query = qCompiler.getQuery();
	}
	
	public String getQuery()
	{
		return this.query;
	}
	
	

}
