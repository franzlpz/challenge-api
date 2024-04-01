# Challenge
API RESTFull para usuario

# Diagrama de solución

<image src="/Diagrama de solucion.png" alt="Descripción de la imagen">

# Link para ver los endpoint
http://localhost:8082/swagger-ui/index.html

# Link para ver la base datos
http://localhost:8082/h2-console/



# Probar registro de usuario:
consideraciones: Para el password deberá contener como mínimo 8 carácteres, empezar por una mayúscula, tener número y carácteres especiales.

curl --location 'http://localhost:8082/api/v1/users' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=F3665B578842CF25FCF74251234FB707' \
--data-raw '{
"name": "Juan Rodriguez",
"email": "juan2@rodriguez.org",
"password": "Foreigner127#",
"phones": [
    {
    "number": "1234567",
    "cityCode": "1",
    "countryCode": "57"
    }
    ]   
} 
'
 # Busqueda de usuario: Cambiar "c6b59003-0617-4988-9fa1-a36dfb457325" por generador en el paso anterior
 curl --location 'http://localhost:8082/api/v1/users/c6b59003-0617-4988-9fa1-a36dfb457325' \
--header 'Cookie: JSESSIONID=F3665B578842CF25FCF74251234FB707'


