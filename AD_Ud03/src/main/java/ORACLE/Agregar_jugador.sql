CREATE OR REPLACE PROCEDURE Agregar_Jugador (
   p_id IN NUMBER,
   p_nombre IN VARCHAR2,
   p_apellidos IN VARCHAR2,
   p_altura IN NUMBER,
   p_edad IN NUMBER,
   p_posicion IN VARCHAR2,
   p_equipo IN VARCHAR2
) AS
BEGIN
   INSERT INTO baloncesto (id, nombre, apellidos, altura, edad, posicion, equipo)
   VALUES (p_id, p_nombre, p_apellidos, p_altura, p_edad, p_posicion, p_equipo);

   DBMS_OUTPUT.PUT_LINE('Jugador agregado exitosamente: ' || p_nombre || ' ' || p_apellidos);
END Agregar_Jugador;
/
