# SQLQueryBuilder

# Custom SQL Query Generator with Java Swing

## Project Overview

This project is a Java-based library that helps generate SQL queries using a custom, user-friendly query syntax. It comes with a graphical user interface (GUI) built using Java Swing, which allows users to write queries in a simple manner, validate them, and then translate them into real SQL queries for execution. The resulting tables are displayed in the GUI.

The library is divided into two main components:
1. **Compiler** - Translates user input into valid SQL queries.
2. **Validator** - Validates user input to ensure that the queries are syntactically correct.

### Example Query

- **User Input:**
    ```java
    var query = new Query("Departments")
                  .OrderBy("manager_id")
                  .Where("department_id", ">", 50)
                  .OrWhere("department_name", "like", "C%");
    ```

- **Generated SQL:**
    ```sql
    SELECT * FROM [Departments] 
    WHERE [department_id] > 50 
    OR [department_name] LIKE 'C%' 
    ORDER BY [manager_id]
    ```

## Key Features

- **Custom SQL Query Syntax**: Users can write queries in a simplified, object-oriented style and generate SQL code.
- **GUI Integration**: The project features a GUI where users can input queries and view the resulting SQL and tables.
- **Validation**: The user input is validated before being compiled into SQL to prevent errors and ensure correctness.
## Query Builder Documentation

- **Query Creation**:  
  `new Query(table_name)`  
  Equivalent to `SELECT * FROM [table_name]`.

- **Projection**:  
  `.Select(column_list)`  
  Specify the columns to retrieve.

- **Sorting**:  
  `.OrderBy(column_list)`  
  `.OrderByDesc(column_list)`  
  Sort the results by columns in ascending or descending order.

- **Filtering**:  
  `.Where(column_name, operator, criteria)`  
  `.OrWhere(column_name, operator, criteria)`  
  `.AndWhere(column_name, operator, criteria)`  
  Apply `WHERE` conditions to filter rows.

- **Advanced Filtering**:  
  `.WhereBetween(column_name, int1, int2)`  
  `.WhereIn(column_name).ParametarList(p1,p2,...,pn)`  
  Specify range or list-based filters.

- **Table Joins**:  
  `.Join(table_name).On(column_name1, operator, column_name2)`  
  Perform SQL `JOIN` operations between tables.

- **String Operations**:  
  `.WhereEndsWith(column_name, pattern)`  
  `.WhereStartsWith(column_name, pattern)`  
  `.WhereContains(column_name, pattern)`  
  Filter results based on string patterns.

- **Aggregation Functions**:  
  `.Avg(column_name, alias)`  
  `.Count(column_name, alias)`  
  `.Min(column_name, alias)`  
  `.Max(column_name, alias)`  
  Apply aggregate functions to data.

- **Grouping**:  
  `.GroupBy(column_list)`  
  `.Having(alias, operator, criteria)`  
  `.AndHaving(alias, operator, criteria)`  
  `.OrHaving(alias, operator, criteria)`  
  Group results and apply `HAVING` clauses.

- **Subqueries**:  
  `.WhereInQ(column_name, query)`  
  `.WhereEqQ(column_name, query)`  
  Use subqueries inside `WHERE` conditions.




