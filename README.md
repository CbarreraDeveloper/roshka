# roshka
Este proyecto es una API RESTful que tiene como objetivo la apificación de la página web de ABC como test de conocimiento aplicada por la empresa Roshka en el proceso de selección.

Pasos a Seguir para probar la aplicacion:

Descargar el codigo de este repositorio.
Levantar en un entorno de desarrollo con java 11
Una vez arriba por defecto en el puerto :8080 preparamos nuestro request en nuestra API platform de nuestra preferencia (Postman, Visual Studio code, etc)
En el metodo GET copiamos la siguiente ruta= localhost:8080/test/consulta?q={TEXTO DE BUSCAR}
la aplicación enviara como resultado las noticias referente al TEXTO DE BUSQUEDA en un formato tipo json.
