#!/bin/bash

build_service() {
  service_name=$1
  echo "Building and packaging $service_name..."
  (
    cd "$service_name"
    mvn clean package
  )
}

services=("config-service" "discovery-service" "gateway-service" "sum-calculator-service" "percentage-service")
for service in "${services[@]}"
do
  build_service "$service"
done