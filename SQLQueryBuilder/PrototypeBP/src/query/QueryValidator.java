package query;

import java.util.HashMap;
import java.util.Map;

import error.ErrorDialog;

public class QueryValidator {
	private String code;
	private Map<String, String> functionCode;
	public QueryValidator(String code)
	{
		this.code = code;
		this.functionCode = new HashMap<String, String>();
		
	}
	
	public void ValidateQuery()
	{
		ValidateSyntax(this.code);
	}
	
	private void ValidateSyntax(String code)
	{
		//code = code.replace("\"", "");
		String [] s = code.split(" ");
		if(s[0].equals("var") && s[2].equals("=") && s[3].equals("new"))
		{
			ValidateFunctions(s[4]);
			
		}else
		{
			ErrorDialog er = new ErrorDialog("Moguce greske:\nIzostavljena/vise prazna mesta izmedju kljucnih reci.\nPogresno napisane klucne reci");
		}
	}
	
	private void ValidateFunctions(String code)
	{
		String [] s = code.split("\\.");
		for(String j: s)
		{
			System.out.println(j);
		}
		for(String i: s)
		{
			String [] tmp = i.split("\\("); 
			boolean flag = false;
			for(FunctionNames n: FunctionNames.values())
			{
				if(n.name().equalsIgnoreCase(tmp[0]))
				{
					flag = true;
					break;
				}
			}
			if(!flag)
			{
				ErrorDialog er = new ErrorDialog("Moguce greske:\nPogresno deklarisana funkcija.\nZaborabljene tacke izmedju funkcija.");
				break;
			}
			functionCode.put(tmp[0].toUpperCase(), tmp[1].replace(")", "").replace(";", ""));
		}
		System.out.println(functionCode.toString());
	}
	
	public Map<String, String> getFunctionCode()
	{
		return this.functionCode;
	}

}
