SET HUBHOST=10.10.10.120
IF NOT [%1] == [] SET HUBHOST=%1
start java -jar selenium-server-standalone-2.48.2.jar -role hub http://%HUBHOST%:4444/grid/register
Exit
