# MiniTwitter
Twitter clone built to showcase basic Spring Boot functionality

## Installing
```
Requirements: Needs JDK 8 and git properly installed and configured
Test credentials batman / user1Pass
1. git clone https://github.com/eduzol/MiniTwitter.git
2. cd MiniTwitter
3. gradlew.bat bootrun

Sample Requests:
curl -X GET \
  http://localhost:8080/ranking \
  -H 'Authorization: Basic YmF0bWFuOnVzZXIxUGFzcw==' \
  -H 'Cache-Control: no-cache' \
  -H 'Postman-Token: fe171a15-70c0-b0fb-ed50-3539ecb9f31f' \
  -d '{{\"query\":\"iOS\",\"maxResults\":10}}'
  
 curl -X GET \
  'http://localhost:8080/messages?page-size=10&page=1&search=eu' \
  -H 'Authorization: Basic YmF0bWFuOnVzZXIxUGFzcw==' \
  -H 'Cache-Control: no-cache' \
  -H 'Postman-Token: 7a209705-4e49-1d57-60dd-8c3832f2d8ae'
```
