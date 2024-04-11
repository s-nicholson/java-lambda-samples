# AWS Lambdas in various ways

Each one implements a really pointless fizzbuzz service in more or less the same way to allow comparison of the frameworks.

## Testing

Tested using an API Gateway event with:
```json
  "queryStringParameters": {
    "end": "25"
  },
```

e.g.
```sh
aws lambda invoke --function-name <function> \
        --cli-binary-format raw-in-base64-out \
        --payload '{ "queryStringParameters": { "end": 10 } }' /dev/stdout
```

## Deploying

Deploy via CDK:
```sh
mvn clean package
cd cdk
npx cdk deploy
```

## Stats

### Manual Dependency Injection

- Shaded jar size: 1.3M

- Cold start:
  ```
  Duration: 46.64 ms	Billed Duration: 47 ms	Memory Size: 1024 MB	Max Memory Used: 109 MB	Init Duration: 603.71 ms
  ```

- Warm start:
  ```
  Duration: 1.90 ms	Billed Duration: 2 ms	Memory Size: 1024 MB	Max Memory Used: 109 MB
  ```

### Dagger2

- Shaded jar size: 1.3M

- Cold start:
  ```
  Duration: 137.84 ms	Billed Duration: 138 ms	Memory Size: 1024 MB	Max Memory Used: 109 MB	Init Duration: 534.27 ms
  ```

- Warm start:
  ```
  Duration: 2.46 ms	Billed Duration: 3 ms	Memory Size: 1024 MB	Max Memory Used: 109 MB
  ```

### Micronaut

- Shaded jar size: 16M

- Cold start:
  ```
  Duration: 720.93 ms	Billed Duration: 721 ms	Memory Size: 1024 MB	Max Memory Used: 152 MB	Init Duration: 2462.59 ms
  ```

- Warm start:
  ```
  Duration: 3.53 ms	Billed Duration: 4 ms	Memory Size: 1024 MB	Max Memory Used: 153 MB
  ```

### Spring Cloud Functions

- Shaded jar size: 22M

- Cold start:
  ```
  Duration: 470.33 ms	Billed Duration: 471 ms	Memory Size: 1024 MB	Max Memory Used: 174 MB	Init Duration: 3663.04 ms
  ```

- Warm start:
  ```
  Duration: 3.31 ms	Billed Duration: 4 ms	Memory Size: 1024 MB	Max Memory Used: 174 MB
  ```
