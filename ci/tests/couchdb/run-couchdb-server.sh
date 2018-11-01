#!/bin/bash


# while sleep 9m; do echo -e '\n=====[ Gradle build is still running ]====='; done &

echo "Running CouchDb docker image..."
docker run -d -p 5984:5984 --name="couchdb-server" apache/couchdb:2.2

docker ps | grep "couchdb-server"
retVal=$?
if [ $retVal == 0 ]; then
    echo "CouchDb docker image is running."
else
    echo "CouchDb docker image failed to start."
    exit $retVal
fi

echo "Waiting for CouchDb server to come online..."
sleep 10
until $(curl -X PUT http://127.0.0.1:5984/_node/couchdb@127.0.0.1/_config/admins/cas -d '"password"' --fail); do
    printf '.'
    sleep 1
done

test=$(curl --fail http://127.0.0.1/_membership)
retVal=$?
#if [ $retVal == 0 ]; then
#    echo "CouchDb admin initialized."
#else
    echo "CouchDb admin failed to initialize. ${test}"
    exit $retVal
#fi
