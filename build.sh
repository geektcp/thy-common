#!/bin/sh

git pull
start_time=`date + '%Y-%m-%d %H:%M:%S'`

mvn clean deploy -P release,gpg

end_time=`date + '%Y-%m-%d %H:%M:%S'`
sc=$(date --date="${start_time}" +%s)
ec=$(date --date="${end_time}" +%s)

delta=ec-sc
duration_minute=$((delta/60))
duration_second=$((delta%60))
echo "duration: ${duration_minute} minute ${duration_second} second"