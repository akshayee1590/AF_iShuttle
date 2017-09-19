#!/bin/bash
export HUBHOST=10.10.10.120
java -jar selenium-server-standalone-2.48.2.jar -role hub http://$HUBHOST:4444/grid/register
