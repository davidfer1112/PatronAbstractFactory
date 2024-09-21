# Implementación del Patrón Abstract Factory

Este repositorio contiene una implementación del patrón de diseño Abstract Factory en Java, enfocado en la creación de servicios web tanto RESTful como SOAP (Web Services).

## Estructura del Proyecto

El proyecto utiliza Maven como sistema de gestión y construcción. La estructura del código se organiza en paquetes que representan diferentes familias de servicios y sus implementaciones específicas.

### Paquetes Principales

- `af.familiarest`: Contiene las implementaciones de servicios RESTful.
- `af.familiaws`: Contiene las implementaciones de servicios Web Services (SOAP).
- `servicios`: Define las interfaces para los servicios de artículos y empleados.
- `abstractfactory.bases`: Define la interfaz base para las fábricas abstractas.

### Clases Importantes

- `ImplementacionRestArticulos`: Implementación de la interfaz `IServiciosArticulos` para servicios RESTful.
  - [ImplementacionRestArticulos](https://github.com/davidfer1112/patronabstractfactory/tree/main/src/main/java/af/familiarest/ImplementacionRestArticulos.java)
- `ImplementacionWSArticulos`: Implementación de la interfaz `IServiciosArticulos` para servicios Web Services (SOAP).
  - [ImplementacionWSArticulos](https://github.com/davidfer1112/patronabstractfactory/tree/main/src/main/java/af/familiaws/ImplementacionWSArticulos.java)
- `ImplementacionFabricaRestful` y `ImplementacionFabricaWS`: Implementaciones de la interfaz `IServiciosBasesGenericos` que actúan como fábricas abstractas para crear objetos de servicios RESTful y Web Services, respectivamente.
  - [ImplementacionFabricaRestful](https://github.com/davidfer1112/patronabstractfactory/tree/main/src/main/java/af/familiarest/ImplementacionFabricaRestful.java)
  - [ImplementacionFabricaWS](https://github.com/davidfer1112/patronabstractfactory/tree/main/src/main/java/af/familiaws/ImplementacionFabricaWS.java)

### Configuración y Dependencias

El archivo `pom.xml` define las dependencias necesarias para el proyecto, incluyendo soporte para servicios RESTful (JAX-RS), servicios SOAP (JAX-WS), implementación de SAAJ, y cliente HTTP.

- [pom.xml](https://github.com/davidfer1112/patronabstractfactory/tree/main/pom.xml)

### Propiedades

El archivo `AbsFactoryPropiedades.properties` permite configurar la implementación de la fábrica a utilizar (RESTful o Web Services).

- [AbsFactoryPropiedades.properties](https://github.com/davidfer1112/patronabstractfactory/tree/main/src/main/resources/Propiedades/AbsFactoryPropiedades.properties)

## Uso

Para utilizar el patrón Abstract Factory implementado en este repositorio, se debe instanciar la fábrica concreta deseada (RESTful o Web Services) y luego utilizarla para crear los objetos de servicios específicos (artículos o empleados).

```java
IServiciosBasesGenericos fabrica = new ImplementacionFabricaRestful();
IServiciosArticulos servicioArticulos = fabrica.getArticulos();
String[] articulos = servicioArticulos.getArticulos();
Este enfoque permite cambiar fácilmente entre diferentes familias de implementaciones de servicios sin modificar el código que utiliza estos servicios.
```
