WHAT IS LOG PARSER?
Log parser is small java applications that parses apache HTTP access log files and creates a corresponding tsv(Tab Separated Values) file.
It provides an easily readable view of log data. The generated tsv file can also be imported in a database and queried for analysis purpose.


HOW TO RUN LOG PARSER?
1. Save the jar on your machine

2. For windows:
		a. Open command prompt (Windows--> run -->cmd)
		b. Go to folder where you have saved logparser.jar
		c. Run command java -jar logparser.jar {inputFileName} (inputFileName is the fully qualified name of log file, for e.g. C:\logs\test-access.log)
   For linux/unix:
		a. Go to folder where you have saved logparser.jar
		b. Run command java -jar logparser.jar {inputFileName} (inputFileName is the fully qualified name of log file, for e.g. temp/logs/test-access.log)

3. Output file is generated in output folder inside the folder where logParser.jar was placed. The file is named as httpLog-yyyymmdd.tsv.



PREREQUISITES TO RUN THE JAR
1. JRE(min version 1.5) should be installed on the machine and should be added to classpath.
