# pr05-sign-in-responsive-adaptative-deanmartin_andreu
# MotionBox register & login account.
Este proyecto tiene el objetivo de la gestión de usuarios mediante **registro e inicio de sesión**, aplicando conceptos de **diseño responsive y adaptive**.

En el enfoque visual debemos asegurarnos que la interfaz se adapte a los **tamaños de pantalla o el tipo de dispositivo**.

---

## Pantalla de Inicio de Sesión
La primera pantalla del proyecto corresponde al **inicio de sesión del usuario**. Aquí solicitamos el **email o nombre de usuario** y la **contraseña**.

Esta pantalla además contiene **dos componentes** que tienen importancia para hacer funcionar los **métodos y las redirecciones** creadas dentro de **rutas** y el **MainActivity**.

El primer elemento es el **botón principal para iniciar sesión** y luego un **botón secundario para registrarse**, ambos con estilos distintos para poder dar a entender esta **diferencia de prioridad**.

Estos campos só obligatorios de manera que se le aplican validadores para asegurarnos que el usuario rellena los campos.
<img width="346" height="613" alt="Captura de pantalla 2025-12-18 a las 4 35 14 p  m" src="https://github.com/user-attachments/assets/c2be0b15-2d4c-43ae-ab1f-d88660e5865d" />

### Validación y control de errores

Para poder guiar al usuario a través de la pantalla y asegurar la correcta viabilidad de esta adaptamos los diferentes composables. Por ejemplo:

- El botón principal, **creado para iniciar sesión en tu cuenta**, está deshabilitado hasta que el usuario o rellene los campos como anteriormente mencionado.
- Consultando **dentro del data class del formulario** almacenado dentro del viewmodel mediante una función se comprueba si los datos que se han rellenado en los campos concuerdan con los **datos almacenados** en el momento del **registro del usuario**.
<img width="340" height="612" alt="Captura de pantalla 2025-12-18 a las 4 40 14 p  m" src="https://github.com/user-attachments/assets/5ffbdb87-39c2-48a1-a660-43e0a4fb92e9" />
<img width="344" height="612" alt="Captura de pantalla 2025-12-18 a las 4 40 46 p  m" src="https://github.com/user-attachments/assets/ba5ee008-7771-4bf2-91fd-df15dc471092" />

### Ayudas al usuario
Aportamos al usuario un texto para indicarle que, en caso de que no tenga una cuenta, puede pulsar el **botón secundario de registro** para crear su cuenta.

### Navegación
Dentro de los botones, estos contienen en la función **`onClick`** las **condiciones necesarias** para poder redirigir la pantalla que se muestra a la deseada colocando la **ruta correspondiente**.

### Diseño Responsive y Adaptive
Para cumplir con los requisitos del ejercicio, adaptamos las **medidas** y **distribución de los componentes** a través de la pantalla. Importamos diversos **frames** para observar los cambios y como estos quedan en las **vistas**.
<img width="382" height="614" alt="Captura de pantalla 2025-12-18 a las 4 36 12 p  m" src="https://github.com/user-attachments/assets/a2fc7c78-edf3-485b-9a9a-f4951129d192" />
<img width="921" height="432" alt="Captura de pantalla 2025-12-18 a las 4 36 36 p  m" src="https://github.com/user-attachments/assets/966bc27c-f2e4-4d73-9134-c3bf25e4d8fe" />

### Persistencia de datos
Gracias al **`rememberSaveable`**, al girar la pantalla los datos colocados dentro de los componentes *TextField* **no se pierden**, de manera que el usuario no tenga que volver a repetir el proceso constantemente ni que pierda lo ya escrito.
<img width="838" height="472" alt="Captura de pantalla 2025-12-18 a las 4 41 40 p  m" src="https://github.com/user-attachments/assets/393d25a5-4d43-42d8-a6f8-9f27fbcf0549" />

