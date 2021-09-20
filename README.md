# api-rest-rective

## objetivo del challenge

crear un proyecto del tipo un ***Api Rest rectivo*** utilizando Spring webFlux  

<a name="instalacion"></a>
## observacion sobre el proyecto

existen 3 versiones de esta Api Rest (basada en distintas necesidades del negocio):

- la primer version corresponde a la necesidad de de usar una base de datos relacional bloqueante, **esa decision impide tener una Api Rest reactiva***     

- la segunda version corresponde a la necesidad de usar una base de datos relacional pero con un comportamiento no bloqueante, esto se logra utilizando ***Spring Data R2DBC***

- la tercera version representa la necesidad de usar una base de datos NOSQL (mongoDB), esta opcion es la mas correcta ya que trabajamos con una DB que soporta la programcion reactiva de forma nativa    

 ## ramas de las distintas versiones

 - primera version: master ***reactivo-DB-bloqueante***  
 - segunda version: brach ***reactivo-DB-no-bloqueante***
 - segunda version: brach ***reactivo-mongoDB*** 
