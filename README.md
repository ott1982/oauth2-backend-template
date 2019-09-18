# OAuth2 Backend Template





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

