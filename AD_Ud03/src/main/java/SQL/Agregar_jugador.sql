DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `Agregar_Jugador`(
    IN p_id INT,
    IN p_nombre VARCHAR(50),
    IN p_apellidos VARCHAR(50),
    IN p_altura INT,
    IN p_edad INT,
    IN p_posicion VARCHAR(30),
    IN p_equipo VARCHAR(50)
)
BEGIN
    INSERT INTO baloncesto (id, nombre, apellidos, altura, edad, posicion, equipo)
    VALUES (p_id, p_nombre, p_apellidos, p_altura, p_edad, p_posicion, p_equipo);

    SELECT CONCAT('Jugador agregado exitosamente: ', p_nombre, ' ', p_apellidos) AS mensaje;
END$$
DELIMITER ;
