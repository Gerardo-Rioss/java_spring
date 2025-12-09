### **`REGISTROS DE LAS MODIFICACIONES - TPI JAVA - INFORMATORIO 2025`**

## ✅ **DTO de salida**

### **`EntradaDiariaSimpleDto`**

Objeto que se devuelve al llamar al endpoint.

Contiene la información simplificada de una entrada diaria: id, usuarioId, fecha, emoción, reflexión y hábitos.

## ✅ **Mapper**

### **`toSimpleDto / toSimpleDtoList`**

Funciones encargadas de convertir:

- Una entidad `EntradaDiaria` → `EntradaDiariaSimpleDto`
- Una lista de entidades → lista de DTOs

Se usan para que la API no devuelva las entidades completas.

## ✅ **EntradaDiariaRepository**

Repositorio que maneja la entidad `EntradaDiaria`.

Hereda de:

- **JpaRepository** → operaciones CRUD básicas.
- **JpaSpecificationExecutor** → permite ejecutar filtros dinámicos con Specifications.

✅ Specifications
EntradaDiariaSpecification
Clase que define filtros dinámicos para consultas en la base de datos usando Spring Data JPA.
Filtros disponibles:
porUsuarioId(UUID usuarioId)
Devuelve solo entradas que pertenecen a ese usuario.
fechaDesde(LocalDate desde)
Filtra entradas cuya fecha es mayor o igual a la indicada.
fechaHasta(LocalDate hasta)
Filtra entradas cuya fecha es menor o igual a la indicada.

## ✅ **GlobalExceptionHandler**

Maneja centralizadamente todos los errores que pueden ocurrir en la API.

Devuelve respuestas claras y con formato uniforme usando `ApiError`.

### **`1. handleValidationErrors (400)`**

Captura errores de validación en DTOs (`@Valid`).

Devuelve los campos inválidos y sus mensajes.

### **`2. handleTypeMismatch (400)`**

Se activa cuando un parámetro de ruta o query no coincide con el tipo esperado

(ej: String en lugar de UUID o formato de fecha incorrecto).

### **`3. handleDateTimeParse (400)`**

Maneja errores cuando una fecha tiene formato inválido.

Indica el formato correcto: `yyyy-MM-dd`.

### **`4. handleResourceNotFound (404)`**

Atrapa la excepción personalizada `ResourceNotFoundException`.

Se usa cuando un usuario, entrada o recurso no existe.

### **`5. handleNoHandlerFound (404)`**

Se ejecuta cuando la URL no coincide con ningún endpoint.

Devuelve un error indicando que la ruta no existe.

## ✅ **Excepción personalizada**

### **`ResourceNotFoundException`**

- Extiende de `RuntimeException`.
- Se usa cuando un recurso no existe (usuario, hábito, entrada, etc.).
- Permite devolver mensajes claros al cliente.

## ✅ **Capa de Servicio**

### **`obtenerEntradasDeUsuario(id, desde, hasta)`**

Método que:

1. Verifica que el usuario exista.
2. Construye los filtros dinámicos (usuario + fechas).
3. Consulta la BD usando Specifications.
4. Devuelve las entradas convertidas a DTO simple.

## ✅ **Controlador**

### **`UsuarioController – Endpoint GET`**

`GET /{usuarioId}/entradas`

Permite obtener las entradas diarias de un usuario, con filtros opcionales por:

- Fecha desde
- Fecha hasta

Devuelve una lista de DTOs.
