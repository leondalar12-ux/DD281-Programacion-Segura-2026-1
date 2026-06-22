# S3 - GUIA DE TRABAJO DEL ESTUDIANTE
## Autenticacion, Gestion de Cookies y Niveles de Acceso

| Campo | Detalle |
|---|---|
| **Curso** | Programacion Segura (DD281) - Semana 3 |
| **Nombre del estudiante** | Hidgar Orellano Huerta |
| **Codigo** | 2221892872 |
| **Seccion** | 6 |
| **Fecha de entrega** | 21/06/2026 |
| **Tiempo estimado** | 1.5 horas |
| **Puntaje total** | 100 puntos |

---

## SECCION A - OPCION MULTIPLE

**Pregunta 1**
HTTP es un protocolo sin estado. Esto significa que:

- a) El servidor guarda automaticamente el estado de cada usuario entre peticiones
- b) Cada peticion HTTP es independiente y el servidor no recuerda peticiones anteriores
- c) El cliente debe reenviar su contrasena en cada peticion para identificarse
- d) Solo las peticiones POST mantienen el estado del usuario

**Respuesta: [X] b**

---

**Pregunta 2**
Cual de los siguientes mecanismos es el MAS SEGURO para almacenar el session ID de un usuario?

- a) `localStorage` del navegador
- b) `sessionStorage` del navegador
- c) Cookie con atributos `HttpOnly` y `Secure`
- d) Variable global de JavaScript en el cliente

**Respuesta: [X] c**

---

**Pregunta 3**
El atributo `HttpOnly` en una cookie:

- a) Garantiza que la cookie solo se transmita por HTTPS
- b) Impide que JavaScript del navegador pueda leer el valor de la cookie
- c) Limita la cookie a peticiones del mismo dominio unicamente
- d) Establece la fecha de expiracion automatica de la cookie

**Respuesta: [X] b**

---

**Pregunta 4**
El atributo `Secure` en una cookie garantiza que:

- a) La cookie no puede ser modificada por el usuario
- b) La cookie solo se transmite sobre conexiones HTTPS, nunca HTTP
- c) JavaScript no puede acceder al valor de la cookie
- d) La cookie expira automaticamente al cerrar el navegador

**Respuesta: [X] b**

---

**Pregunta 5**
Un desarrollador implementa el logout eliminando la cookie del navegador del usuario, pero no invalida el session ID en el servidor. Cual es el riesgo?

- a) El usuario tendra que iniciar sesion dos veces la proxima vez
- b) Un atacante con una copia previa del session ID puede seguir usandolo para acceder al sistema
- c) La base de datos quedara con registros de sesion corruptos
- d) El servidor dejara de funcionar correctamente despues del logout

**Respuesta: [X] b**

---

**Pregunta 6**
Que ataque especifico previene el atributo `SameSite=Strict` en una cookie?

- a) SQL Injection en formularios de autenticacion
- b) XSS en paginas dinamicas
- c) CSRF desde dominios externos
- d) Brute force en el formulario de login

**Respuesta: [X] c**

---

**Pregunta 7**
En el ataque Session Fixation, el atacante:

- a) Adivina el session ID del usuario usando fuerza bruta
- b) Fuerza al usuario a utilizar un session ID ya conocido por el atacante antes de autenticarse
- c) Inyecta codigo JavaScript para robar la cookie del usuario
- d) Intercepta el trafico de red para capturar el session ID

**Respuesta: [X] b**

---

**Pregunta 8**
En RBAC, el Principio de Minimo Privilegio establece que:

- a) Los administradores deben tener acceso a todos los recursos para gestionar el sistema
- b) Cada usuario debe tener unicamente los permisos estrictamente necesarios para su funcion
- c) Los permisos se asignan individualmente a cada usuario segun su antiguedad
- d) Los roles deben definirse con el maximo de permisos posibles para no limitar la productividad

**Respuesta: [X] b**

---

**Pregunta 9**
Un sistema lee el rol del usuario desde un campo oculto del formulario HTML. Cual es la vulnerabilidad?

- a) Inyeccion SQL, porque el campo contiene texto sin parametrizar
- b) Parameter tampering: el usuario puede editar el campo con DevTools y darse el rol admin
- c) CSRF, porque el formulario puede ser enviado desde otro dominio
- d) Session Fixation, porque el rol esta en el cliente antes de la autenticacion

**Respuesta: [X] b**

---

**Pregunta 10**
Un navegador moderno recibe `Set-Cookie: session=abc; SameSite=None` sin el atributo `Secure`. Que ocurre?

- a) El navegador acepta la cookie y la envia en todas las peticiones
- b) El navegador rechaza y descarta la cookie automaticamente
- c) El navegador convierte la cookie a SameSite=Lax automaticamente
- d) La cookie funciona normalmente pero genera una advertencia en la consola

**Respuesta: [X] b**

---

## SECCION B - COMPLETAR Y RELACIONAR

### B1 - Completar espacios en blanco

1. HTTP es un protocolo **stateless** porque no recuerda peticiones anteriores entre cliente y servidor.
2. El atributo **Secure** garantiza que la cookie de sesion no sea transmitida sobre conexiones HTTP no cifradas.
3. El modelo de control de acceso **RBAC** asigna permisos a traves de roles, no directamente a usuarios individuales.
4. En un logout correcto, ademas de eliminar la cookie del cliente, el **servidor** debe invalidar el session ID en su propio almacen.
5. Para prevenir Session Fixation, despues de una autenticacion exitosa se debe ejecutar **session.clear()** para limpiar la sesion previa.

### B2 - Relacionar columnas

| Columna A | Respuesta | Columna B |
|---|---:|---|
| 1. `HttpOnly` | c | Previene que JavaScript lea el valor de la cookie |
| 2. `Secure` | f | La cookie solo viaja sobre conexiones HTTPS |
| 3. `SameSite=Lax` | a | Controla si la cookie se envia en peticiones cross-site |
| 4. Session Hijacking | e | Robo de un session ID valido para suplantar al usuario |
| 5. Session Fixation | d | El atacante forza un session ID conocido antes del login |
| 6. Stateless | b | El servidor no puede recordar peticiones anteriores por si solo |
| 7. Minimo Privilegio | h | Cada usuario tiene solo los permisos que necesita |
| 8. Rol | g | Conjunto de permisos asignados a un tipo de usuario |

---

## SECCION C - ANALISIS Y REFLEXION

**Pregunta C1**
Guardar el session ID en `localStorage` es riesgoso porque cualquier JavaScript que se ejecute en la pagina podria leerlo. Si existe una vulnerabilidad XSS, el atacante podria extraer ese valor y reutilizarlo para suplantar la sesion del usuario. La alternativa correcta es usar una cookie de sesion configurada con `HttpOnly`, `Secure` y `SameSite`, porque reduce el acceso desde JavaScript, fuerza el transporte seguro por HTTPS y limita el envio en solicitudes de otros sitios.

**Pregunta C2**
Session Hijacking ocurre cuando el atacante roba o captura un session ID valido que ya pertenece a un usuario autenticado. Session Fixation ocurre antes del login: el atacante intenta que la victima use un session ID conocido y luego espera que inicie sesion. Ambos buscan suplantar al usuario dentro del sistema. Para prevenir hijacking se usan HTTPS, `HttpOnly`, `Secure`, alta entropia y expiracion; para prevenir fixation se debe regenerar o limpiar la sesion con `session.clear()` antes de asignar los datos del usuario autenticado.

**Pregunta C3a**
RetailFacil presenta varios problemas: guarda `uid` y `role` directamente en una cookie manipulable, envia el precio como campo oculto del formulario y no configura expiracion de sesion. Un atacante podria cambiar el rol en el cliente, modificar el precio con DevTools antes de pagar y mantener una sesion abierta por tiempo indefinido si logra acceder al navegador o a la cookie. El problema central es confiar en datos controlados por el cliente.

**Pregunta C3b**
El sistema debe reimplementarse guardando solo un identificador de sesion opaco y aleatorio en una cookie segura, mientras que el rol y permisos se consultan en el servidor. El precio debe recalcularse en backend desde la base de datos usando el ID del producto, nunca desde un campo oculto. La sesion debe tener expiracion por inactividad y logout que invalide el estado servidor. Asi se aplican minimo privilegio, validacion del lado servidor y no confiar en el cliente.

---

## SECCION D - PREGUNTAS AVANZADAS Y DE CASO

**Pregunta D1**
En SaludNet Peru aparecen varias vulnerabilidades OWASP. La primera es A01:2021 Broken Access Control, porque un medico puede cambiar `paciente_id` en la URL y acceder a historias clinicas de otros pacientes. Tambien existe A02:2021 Cryptographic Failures, porque el sistema funciona por HTTP y expone datos sensibles sin cifrado en transito. Ademas hay riesgo asociado a A05:2021 Security Misconfiguration por cookies sin `HttpOnly` ni `Secure`, y A03:2021 Injection/XSS si JavaScript malicioso puede leer la cookie de sesion.

**Pregunta D2**
Roles propuestos: `paciente`, `medico` y `administrador`. El paciente solo puede ver sus propios resultados e historia. El medico puede ver historias de pacientes asignados a su atencion, no de cualquier paciente. El administrador gestiona usuarios, roles y auditoria, pero el acceso a historias debe estar justificado y registrado.

```python
from functools import wraps
from flask import session, abort

PACIENTES_ASIGNADOS = {
    "medico_01": {"1023", "1024"},
}

def puede_ver_historia(user, paciente_id):
    if user["role"] == "administrador":
        return True
    if user["role"] == "paciente":
        return user["paciente_id"] == paciente_id
    if user["role"] == "medico":
        return paciente_id in PACIENTES_ASIGNADOS.get(user["id"], set())
    return False

def requiere_historia(f):
    @wraps(f)
    def wrapper(paciente_id, *args, **kwargs):
        user = session.get("user")
        if not user:
            abort(401)
        if not puede_ver_historia(user, paciente_id):
            abort(403)
        return f(paciente_id, *args, **kwargs)
    return wrapper
```

**Pregunta D3**
Si un atacante logra ejecutar XSS en el navegador de un paciente y la cookie no tiene `HttpOnly`, puede usar `document.cookie` para leer el session ID. Luego copia ese valor y lo coloca en su propio navegador o en una herramienta HTTP como cookie de sesion. Si el servidor no valida otros controles, aceptara la cookie y lo reconocera como el paciente. El atributo que hubiera prevenido la lectura desde JavaScript es `HttpOnly`; ademas se requiere HTTPS, expiracion, rotacion de sesion y monitoreo.

**Pregunta D4**

```python
from datetime import timedelta
from functools import wraps
from flask import Flask, session, redirect, url_for, request, abort
import secrets

app = Flask(__name__)
app.config.update(
    SECRET_KEY=secrets.token_hex(32),
    PERMANENT_SESSION_LIFETIME=timedelta(minutes=15),
    SESSION_COOKIE_HTTPONLY=True,   # mitiga robo por XSS
    SESSION_COOKIE_SECURE=True,     # solo HTTPS en produccion
    SESSION_COOKIE_SAMESITE="Strict" # reduce CSRF en operaciones bancarias
)

USERS = {
    "cliente@test.com": {"password": "Cliente2026!", "role": "cliente"},
    "operador@test.com": {"password": "Operador2026!", "role": "operador"},
    "admin@test.com": {"password": "Admin2026!", "role": "admin"},
}

def require_role(*roles):
    def decorator(fn):
        @wraps(fn)
        def wrapper(*args, **kwargs):
            if "user_id" not in session:
                return redirect(url_for("login"))
            session.permanent = True
            if session.get("role") not in roles:
                abort(403)
            return fn(*args, **kwargs)
        return wrapper
    return decorator

@app.post("/login")
def login():
    user = USERS.get(request.form.get("email", ""))
    if not user or user["password"] != request.form.get("password", ""):
        abort(401)
    session.clear()  # previene Session Fixation
    session["user_id"] = request.form["email"]
    session["role"] = user["role"]
    session.permanent = True
    return redirect(url_for("dashboard"))

@app.post("/logout")
def logout():
    session.clear()  # invalida datos de sesion en servidor/framework
    response = redirect(url_for("login"))
    response.delete_cookie(app.config["SESSION_COOKIE_NAME"])
    return response

@app.get("/cuentas")
@require_role("cliente", "operador", "admin")
def cuentas():
    return "Modulo de cuentas"

@app.get("/operaciones")
@require_role("operador", "admin")
def operaciones():
    return "Modulo de operaciones"

@app.get("/admin")
@require_role("admin")
def admin():
    return "Administracion bancaria"
```

**Pregunta D5**
Aunque una cookie tenga `HttpOnly` y `Secure`, un session ID con baja entropia sigue siendo vulnerable. Si los valores son secuenciales como `1001`, `1002` o `1003`, un atacante podria adivinar sesiones validas probando numeros cercanos hasta encontrar una activa. Esto habilita prediccion de sesiones o session guessing. El estandar correcto es generar IDs aleatorios criptograficamente seguros, largos, no predecibles y con suficiente entropia, por ejemplo usando `secrets.token_urlsafe(32)` o mecanismos seguros del framework.

---

*Universidad Autonoma del Peru - DD281 Programacion Segura - Semana 3 - 2026-1*
