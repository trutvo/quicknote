#!/bin/sh

set -e

hash=$(echo $RANDOM | md5sum | head -c 16)

echo "Create the test note $hash"
curl -f -H "Content-Type: application/json" "http://localhost:8080/notes/$hash" -d "note $hash"
echo "OK"
echo ""

echo "List notes, grep for the test note, count the lines and check if it is 1"
curl --silent -f http://localhost:8080/notes | grep "$hash" | wc -l | xargs test 1 -eq
echo "OK"
echo ""

echo "Get the test note"
curl --silent -f "http://localhost:8080/notes/$hash"
echo ""
echo "OK"
echo ""

echo "Update a the test note"
curl --silent -f -H "Content-Type: application/json" "http://localhost:8080/notes/$hash" -d "note $hash --- update"
echo "OK"
echo ""

echo "Get the test note again and grep for '$hash --- update'"
curl --silent -f "http://localhost:8080/notes/$hash" | grep "$hash --- update" | wc -l | xargs test 1 -eq
echo ""
echo "OK"
echo ""

echo "Delete the test note"
curl --silent -f -X DELETE "http://localhost:8080/notes/$hash"
echo "OK"

echo ""
echo "SUCCESS"
