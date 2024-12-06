CREATE TABLE `baloncesto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `apellidos` varchar(50) NOT NULL,
  `altura` int(11) NOT NULL,
  `edad` int(11) NOT NULL,
  `posicion` enum('Base','Escolta','Alero','Ala-Pívot','Pívot') NOT NULL,
  `equipo` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ;
