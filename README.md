# Javatodo
Todo List using java. Add and Remove tasks in java console.... atleast for now.
# Prerequisites
1. Install derby database (Search online)
2. Start ij tool
3. Enter below commands

```
connect 'jdbc:derby:[Location of database]\\[Database name]';
```
<b>Example: </b>
```
connect 'jdbc:derby:C:\\Somewhere\\Somefolder\\Database';
```
```
create table todo (id int not null generated always as identity, task varchar(255);
```
```
create table todo2 (id int not null generated always as identity, task varchar(255);
```
<b>Change location of database in java file tooo</b>
# How to use
a: [Task] - To add task <br/>
r: [Task no.] - To remove task <br/>
<b>Example: </b> <br/>
<br/>
![image](https://user-images.githubusercontent.com/53462309/144736230-404475c0-e27a-4ffd-bff1-28c75b8061df.png) <br/>
<br/>
![image](https://user-images.githubusercontent.com/53462309/144736253-81af1597-5b20-43e4-be17-9843defbda0c.png)
