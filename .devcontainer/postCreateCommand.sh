#!/bin/bash

# Install k6 CLI for load testing
sudo gpg -k
sudo gpg --no-default-keyring --keyring /usr/share/keyrings/k6-archive-keyring.gpg --keyserver hkp://keyserver.ubuntu.com:80 --recv-keys C5AD17C747E3415A3642D57D77C6C491D6AC1D69
echo "deb [signed-by=/usr/share/keyrings/k6-archive-keyring.gpg] https://dl.k6.io/deb stable main" | sudo tee /etc/apt/sources.list.d/k6.list
sudo apt-get update
sudo apt-get install k6

# Install k9s CLI  to manage k8s clusters 
curl -sS https://webinstall.dev/k9s | bash

# Install httpyac CLI for API testing
npm install -g httpyac

echo "----------------------------------------------"
java -version

echo "----------------------------------------------"
mvn --version

echo "----------------------------------------------" 
npm --version
echo "----------------------------------------------" 