#!/bin/bash
USER_NAME="YOUR_USERNAME";
PASSWORD="YOUR_PASSWORD";
cd `dirname $0`
mysql -u $USER_NAME -p$PASSWORD < GamificationDB.sql