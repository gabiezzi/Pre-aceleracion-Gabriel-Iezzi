

<h1>ENTREGA DE CHALLENGE BACKEND - Java Spring Boot (API) 2022</h1>

<ul>

<li><h2>Challenge de ingreso a aceleración de alkemy</h2></li>

<p>Se ha desarrollado una API en la cual se puede recorrer y administrar distintas 
peliculas y personajes del universo de Disney.</p>


<li><h3>Herramientas utilizadas &#9889</h3></li>

Lenguajes: Java 11, MySQL
Frameworks: Spring Boot, Hibernate, Maven.
IDE: Intellij Idea

<li><h3> Database &#9997</h3></li>

Base de datos utilizada MySQL. Para configurar, editar los siguientes campos el archivo: `resources/application.properties`</br>
spring.datasource.url=jdbc:mysql://localhost:Puerto/disney
spring.datasource.username=username</br>
spring.datasource.password=password

Fue incluido en la carpeta del proyecto el archivo "/Documents" para ser importado en el BDM.

<li><h3>EndPoints &#10024</h3></li>

En cuanto a los Endpoints, fue incluido el archivo postman en `/documents`.


Movies:</br>
<p>Para dar de alta a una película el atributo `"categoryID"` es requerido, por lo tanto es necesario crear una categoría previamente.
También es posible agregar personajes y asociarlos al mismo tiempo que se da de alta una pelicula, con la propiedad `cast`.

Por otro lado, en caso de no dar de alta personajes de esta forma,  se puede agregar o remover personajes y relacionarlos a la película mediante los endpoints `/add` y `/remove`
Será necesario tanto el id de la película como del personaje a vincular o remover.
`("/{idMovie}/character/add/{idCharacter}")`
`("/{idMovie}/character/remove/{idCharacter}")`


Categories</br>
<p>Se pueden dar de alta, baja modificar y listar los categorias mediante el endpoint `/categories`.</p>


Characters:</br>
<p>Se puede remover un personaje sin afectar en el registro de pelicula asociado.</p>


<li><h3>Email Sender 🌐</h3></li>
<p>Para utilizar la funcionalidad de envío de emails se deberá utilizar una API KEY propia, ya que por cuestiones de seguridad SendGrid no permite que esta esté publicada en el código dentro de un repositorio en github. 

Para ello deberá declarar una variable de Environment llamada EMAIL_API_KEY y setear la key respectiva, también deberá editar la propiedad alkemy.disney.email.sender dentro de application.properties con su propio correo registrado en Sendgrid.</p>

</ul>

