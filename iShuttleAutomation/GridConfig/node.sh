java -jar selenium-server-standalone-2.48.2.jar -role node -hub http://10.10.10.120:4444/grid/register -hubHost 10.10.10.120 -host 10.10.10.120 -port 5555 -browser browserName=firefox -browser browserName=safari -browser browserName=chrome -Dwebdriver.chrome.driver=chromedriver