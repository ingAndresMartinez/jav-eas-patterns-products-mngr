# jav-eas-patterns-products-mngr

### Pontificia Universidad Javeriana. Bogotá.

Manager para administración de productos que se ofertan en la aplicación para al trabajo "socialización II" de la asignatura patrones 
de diseño, en la Especialización de Arquitectura de Software Empresarial 2020 I.

### Integrantes:

* Andres Martinez Cobos
* Fabian Acero
* Robinson Torres

* * *

### Recursos:

<table>
    <tr>
        <td>PATH</td>
        <td>DESCRIPCIÓN</td>
        <td>VERBO</td>
        <td>HTTP CODE OK</td>
        <td>HTTP CODES FAILED</td>
    </tr>
    <tr>
        <td>/product-service</td>
        <td>Recupera el listado de productos o servicios total</td>
        <td>GET</td>
        <td>200 - OK -</td>
        <td>404 - NOT_FOUND - No presenta productos o servicios creados</td>
    </tr>
    <tr>
        <td>/product-service/category/{eCategory}</td>
        <td>Recupera el listado de productos o servicios por una categoria dada</td>
        <td>GET</td>
        <td>200 - OK -</td>
        <td>400 - BAD_REQUEST - Categoria no existente <br>
            404 - NOT_FOUND - Categoria no presenta productos asociados
        </td>
    </tr>
    <tr>
        <td>/product-service/sub-category/{subCategoryId}</td>
        <td>Recupera el listado de productos o servicios por una sub-categoria dada</td>
        <td>GET</td>
        <td>200 - OK -</td>
        <td>400 - BAD_REQUEST - Sub-Categoria no existente <br>
            404 - NOT_FOUND - Categoria no presenta productos asociados
        </td>
    </tr>
    <tr>
        <td>/product-service</td>
        <td>Crea un nuevo producto o servicio</td>
        <td>POST</td>
        <td>201 - CREATED -</td>
        <td>406 - NOT_ACCEPTABLE - Actualización invalida</td>
    </tr>
    <tr>
        <td>/product-service</td>
        <td>Actualiza un producto o servicio</td>
        <td>PUT</td>
        <td>202 - ACCEPTED -</td>
        <td>406 - NOT_ACCEPTABLE - Actualización invalida</td>
    </tr>
    <tr>
            <td>/sub-category</td>
            <td>Recupera el listado de sub-categoria total</td>
            <td>GET</td>
            <td>200 - OK -</td>
            <td>404 - NOT_FOUND - No se encuentrar sub-categorias creadas</td>
        </tr>
        <tr>
            <td>/sub-category/{eCategory}</td>
            <td>Recupera el listado de sub-categorias por una categoria dada</td>
            <td>GET</td>
            <td>200 - OK -</td>
            <td>400 - BAD_REQUEST - Categoria no existente <br>
                404 - NOT_FOUND - Categoria no presenta sub-categorias asociadas
            </td>
        </tr>
        <tr>
            <td>/sub-category</td>
            <td>Crea una nueva sub-categoria</td>
            <td>POST</td>
            <td>201 - CREATED -</td>
            <td>406 - NOT_ACCEPTABLE - Actualización invalida</td>
        </tr>
</table>