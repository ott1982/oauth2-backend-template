# OAuth2 Backend Template

Minimal gradle project Springboot OAuth 2 Resouce Server + Authorization Server.

**Requirements**

- Pass as arguments application.yml properties.
- Generate new public key (apendix).
- Rewrite all TODO's in source code.
- Docker.
- [OAuth 2.0 Authentication Server](https://github.com/ott1982/oauth2-authorization-server-only).
- [OAuth 2.0 Resource Server](https://github.com/ott1982/oauth2-resource-server-only).

# Development

```sh
./gradlew clean bootRun
```

# Run

```
./gradlew clean build
docker build --tag oauth2-backend-template -f ./docker/Dockerfile ./
docker run -d \
--name oauth2-backend-template_1 \
-p 8083:8080 \
-v $HOME/git/oauth2-backend-template/keystore/mykeystore.keystore:/opt/oauth2-backend-template/mykeystore.keystore \
--env-file $HOME/git/oauth2-backend-template/docker/env_file.properties \
oauth2-backend-template
```

# Apendix

## Java keystore creation

```sh
keytool -genkey -v -keystore mykeystore.keystore -alias myalias -keyalg RSA -keysize 2048 -validity 10000
keytool -v -list -keystore mykeystore.keystore -alias myalias
```

## Prepare for Eclipse java project

```sh
./gradlew cleanEclipse eclipse
```

## Get public key

```sh
keytool -export -keystore mykeystore.keystore -alias myalias -file mycertificate.cer
openssl x509 -inform der -in mycertificate.cer -pubkey -noout
```

## Curl access token GET

```sh
curl -X POST -u myclientid:myclientsecret http://localhost:8083/oauth/token \
-H"content-type: application/x-www-form-urlencoded" \
-d"grant_type=password&username=myusername&password=mypassword" | jq .
```

## Curl end point GET securized

```sh
curl http://localhost:8083/securized \
-H "Authorization: Bearer $ACCESSTOKEN"
```

## Curl end point GET unsecurized

```sh
curl http://localhost:8083/unsecurized
```

