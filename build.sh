#!/usr/bin/env bash

echo "**********************************"
echo "  Create pokemonspeare-api dockerImage"
echo "**********************************"

    docker-compose -f docker-compose.yml build
    docker-compose -f docker-compose.yml up
