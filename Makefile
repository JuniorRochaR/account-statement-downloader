setup-env:
	docker-compose up -d
	sleep 2
	aws --endpoint-url=http://localhost:4566 s3 mb s3://ebanx.sta

build-project 1:
	mvn clean package -Dquarkus.profile=dev -DskipTests

exec: build-project upload-statement
	sam local start-api --template sam-dev.yaml

debug: build-project upload-statement
	sam local start-api --template sam-dev.yaml --debug --debug-port 5558

upload-statement:
	aws --endpoint-url=http://localhost:4566 s3 cp docs/example.csv s3://ebanx.sta/2023/9/6/1234.csv