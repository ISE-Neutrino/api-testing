SHELL := /bin/bash

.PHONY: help
.DEFAULT_GOAL := help

# Load environment file if exists
ENV_FILE := .env
ifeq ($(filter $(MAKECMDGOALS),config clean),)
	ifneq ($(strip $(wildcard $(ENV_FILE))),)
		ifneq ($(MAKECMDGOALS),config)
			include $(ENV_FILE)
			export
		endif
	endif
endif

help: ## 💬 This help message :)
	@grep -E '[a-zA-Z_-]+:.*?## .*$$' $(firstword $(MAKEFILE_LIST)) | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-23s\033[0m %s\n", $$1, $$2}'

build: ## 🔨 build the application and run unit-test 
	@echo -e "----\e[34mStart $@\e[0m----" || true
	@cd customer-api-java && mvn clean install
	@echo -e "----\e[34mCompleted\e[0m----"

run: ## 🏃 build the application and run unit-test 
	@echo -e "----\e[34mStart $@\e[0m----" || true
	@cd customer-api-java && mvn spring-boot:run
	@echo -e "----\e[34mCompleted\e[0m----"

e2e-init:## 🏃 Initialize e2e test environment by installing httpyac CLI
	@echo -e "----\e[34mStart $@\e[0m----" || true
	@npm install -g httpyac
	@echo -e "----\e[34mCompleted\e[0m----"

e2e-local:## 🏃 Run e2e-test on your local dev environment
	@echo -e "----\e[34mStart $@\e[0m----" || true
	@echo "Please run make run in a separate terminal to start the application"
	@httpyac testing/e2e-test/*.http --all -e local -o response
	@echo -e "----\e[34mCompleted\e[0m----"

e2e-dev:## 🏃 Run e2e-test on the dev environment
	@echo -e "----\e[34mStart $@\e[0m----" || true
	@httpyac testing/e2e-test/*.http --all -e development -o short 
	@echo -e "----\e[34mCompleted\e[0m----"

load-test: ## ⌛️ Load Test 
	@echo -e "----\e[34mStart $@\e[0m----" || true 
	@cd testing/load-test && k6 run \
		-e K6_ENV=local \
		-e CUSTOMERS_API_URL=http://localhost:8000/customers \
		customers-service.js
	@echo -e "----\e[34mCompleted\e[0m----"

load-test-dashboard: ## ⌛️ Load Test with visualizing the results in xk6 dashboard https://github.com/grafana/xk6-dashboard
	@echo -e "----\e[34mStart $@\e[0m----" || true 
	@docker pull ghcr.io/grafana/xk6-dashboard
	@docker run --rm -p 5665:5665 -v /workspaces/api-testing/testing/load-test:/src ghcr.io/grafana/xk6-dashboard:0.6.1 run \
	    --out 'dashboard=period=2s' \
		-e K6_ENV=local \
		-e CUSTOMERS_API_URL=http://host.docker.internal:8000/customers \
		/src/customers-service.js
	@echo -e "----\e[34mCompleted\e[0m----"