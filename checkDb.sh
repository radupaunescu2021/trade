#!/bin/bash

while ! exec 6<>/dev/tcp/${DATABASE_HOST}/${DATABASE_PORT}; do
   echo "Ttying to connect to MtSQL at ${DATABASE_HOST}:${DATABASE_PORT}.."
   sleep 10
done
echo ">> connected to MySQL database! <<"
java -jar httpApi.jar   