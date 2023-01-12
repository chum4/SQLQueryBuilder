# SQLQueryBuilder
Library that generates SQL queries..
It has GUI for writing queries and showing resulting tables.
Library has two components compailer and validator.
Compailer generates SQL queries from user input.
Validator checks if the user input is correct.
Example:

  User written query:
    var query = new Query("Departments").OrderBy("manager_id")
	    .Where("department_id", ">", 50).OrWhere("department_name","like","C%")
  Generated SQL:
     SELECT * FROM [Departments] WHERE [department_id] > 50 OR [department_name] like 'C%' ORDER BY [manager_id]

Documentation:
1. Query: new Query(table_name), equivalent SELECT * FROM [Departments]
2. Projetion: .select(column_list)
2. Sorting: .OrderBy(column_list), .OrderByDesc(column_list)
3. Filtering: .Where(column_name, operator, criteria), .OrWhere(column_name, operator, criteria), .AndWhere(column_name, operator, criteria), .WhereBetween(column_name,   int1, int2), .WhereIn(column_name).ParametarList(p1,p2,p3,p4, …, pn)
4. Table joins: .Join(table_name).On(column_name1, operator, column_name2)
5. String operations: .WhereEndsWith(column_name, pattern),  .WhereStartsWith(column_name, pattern),  .WhereContains(column_name, pattern)
6. Aggregation functions: .Avg(column_name, alias), .Count(column_name, alias), .Min(column_name,alias), .Max(column_name, alias), .GroupBy(column_list), .Having(alias, operator, criteria, ), .AndHaving(alias, operator, criteria), .OrHaving(alias, operator, criteria) 
7. Subqueries: .WhereInQ(column_name, query)., .WhereEqQ(column_name, query)
