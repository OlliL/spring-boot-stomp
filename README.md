## How To Use it
![Screenshot](https://github.com/OlliL/spring-boot-stomp/raw/main/HTML_page.JPG)
- Start net.salatschuessel.Myserver
- Navigate to [http://localhost:8080/html/index.html](http://localhost:8080/html/index.html)
- Click Button [get JWT Token]
- Click Button [get CSRF Token]
- You can test working CSRF + JWT over HTTP by clicking the 3 following buttons [test ...] which should result in HTTP Status 403, 403 and 200.
- Click Button [connect websocket] which should show connected beside it to indicate that the connection was established successfuly

## Description
The workarounds suggested in https://github.com/spring-projects/spring-security/issues/12378 have been already applied.
Without them CSRF does not work.

## Configuration
To disable CSRF at all just specify websocket.csrf.enable=0 in application.properties