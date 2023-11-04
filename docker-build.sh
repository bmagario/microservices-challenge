#!/bin/bash

docker build -t discovery-service-image:latest -f discovery-service/Dockerfile .
docker build -t gateway-service-image:latest -f gateway-service/Dockerfile .
docker build -t sum-calculator-service-image:latest -f sum-calculator-service/Dockerfile .
docker build -t percentage-service-image:latest -f percentage-service/Dockerfile .

# docker image tag discovery-service-image:latest bmagario/discovery-service-image:latest
# docker image tag gateway-service-image:latest bmagario/gateway-service-image:latest
# docker image tag sum-calculator-service-image:latest bmagario/sum-calculator-service-image:latest
# docker image tag percentage-service-image:latest bmagario/percentage-service-image:latest
#
# docker push bmagario/discovery-service-image:latest
# docker push bmagario/gateway-service-image:latest
# docker push bmagario/sum-calculator-service-image:latest
# docker push bmagario/percentage-service-image:latest