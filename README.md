# Camel Rest Spike

This is a little spike for testing Camel rest support.

Run container with  
``` docker-compose up ```

Try to do a request to  
``` POST http://localhost:8080/upload ```

When you put a valid XML into body you get a 200 with "OK!"  
When you put everything else into body you get a 422
