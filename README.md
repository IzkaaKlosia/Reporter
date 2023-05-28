# Reporter
The following script assumes different options:
  1. "-Report_1" caluclates the time spent by all employees on each project available. 
  2. "-Report_2" indicates the collective amount of times spent on all the projects by each employee.

CLI command to run previously generated .jar file:
java -jar test_passing_system_parameters.jar -Report_1 
                      or
java -jar test_passing_system_parameters.jar -Report_2

In order to create .jar file please follow below steps:
  1. Project structure -> Artifacts -> Click "+" -> Select "JAR" from dropdown -> Select 'modules with dependency' -> 
  choose "Main" class in "Main Class" 
  2. Build -> Build artifacts -> Build
  3. Your .jar file will be created in 'out' folder 
  4. Open your terminal/command window and type "java -jar test_passing_system_parameters "-Report_1" or "-Report_2".
