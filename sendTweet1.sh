#!/bin/bash

curl -X POST -H "Content-Type: application/json" -d @tweet1.json http://localhost:8080/tweets 
