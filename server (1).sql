-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 16-05-2024 a las 16:53:08
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `server`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `amigos`
--

CREATE TABLE `amigos` (
  `ID_Amigo` int(11) NOT NULL,
  `Usuario_Amigo1` int(11) DEFAULT NULL,
  `Usuario_Amigo2` int(11) DEFAULT NULL,
  `confirmacion` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `amigos`
--

INSERT INTO `amigos` (`ID_Amigo`, `Usuario_Amigo1`, `Usuario_Amigo2`, `confirmacion`) VALUES
(1, 1, 6, 1),
(2, 1, 2, 1),
(3, 4, 3, 1),
(16, 1, 3, 1),
(23, 2, 3, 0),
(24, 3, 2, 1),
(25, 3, 2, 0),
(26, 3, 2, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `chat_grupal`
--

CREATE TABLE `chat_grupal` (
  `ID_Grupo` int(11) NOT NULL,
  `Nombre` varchar(32) DEFAULT NULL,
  `Descripcion` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `chat_grupal`
--

INSERT INTO `chat_grupal` (`ID_Grupo`, `Nombre`, `Descripcion`) VALUES
(1, 'Chat1', 'Chat 1'),
(2, 'chat2', 'chat 2'),
(3, 'CHAT 3 final', 'chat 3\r\n');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `grupos`
--

CREATE TABLE `grupos` (
  `ID` int(11) NOT NULL,
  `Usuario` int(11) DEFAULT NULL,
  `Grupo` int(11) DEFAULT NULL,
  `confirmacion` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `grupos`
--

INSERT INTO `grupos` (`ID`, `Usuario`, `Grupo`, `confirmacion`) VALUES
(1, 1, 1, 1),
(2, 2, 1, 1),
(4, 1, 2, 1),
(5, 1, 3, 0),
(6, 5, 3, 1),
(7, 2, 3, 1),
(8, 3, 3, 1),
(9, 4, 3, 1),
(10, 6, 3, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mensajes_directos`
--

CREATE TABLE `mensajes_directos` (
  `ID_Mensaje` int(11) NOT NULL,
  `Remitente` int(11) DEFAULT NULL,
  `Destinatario` int(11) DEFAULT NULL,
  `Contenido` varchar(255) DEFAULT NULL,
  `Fecha` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `mensajes_directos`
--

INSERT INTO `mensajes_directos` (`ID_Mensaje`, `Remitente`, `Destinatario`, `Contenido`, `Fecha`) VALUES
(40, 3, 4, 'hola', '2024-05-16 08:19:17'),
(41, 3, 4, 'hola', '2024-05-16 08:21:37');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mensajes_grupales`
--

CREATE TABLE `mensajes_grupales` (
  `ID_Mensaje` int(11) NOT NULL,
  `Remitente` int(11) DEFAULT NULL,
  `Destinatario` int(11) DEFAULT NULL,
  `Contenido` varchar(255) DEFAULT NULL,
  `Fecha` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `perfil`
--

CREATE TABLE `perfil` (
  `ID_Perfil` int(11) NOT NULL,
  `Nombre` varchar(55) DEFAULT NULL,
  `APEP` varchar(55) DEFAULT NULL,
  `APEM` varchar(55) DEFAULT NULL,
  `Mail` varchar(55) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `perfil`
--

INSERT INTO `perfil` (`ID_Perfil`, `Nombre`, `APEP`, `APEM`, `Mail`) VALUES
(1, 'Daniel', 'Lopez', 'Aguilera', 'Danylop07@gmail.com'),
(2, 'Fernando', 'Garcial', 'Apolinar', 'Fergarapo@Gmail.com'),
(3, 'Gabriela', 'Arias', 'Gomez', 'Gaby@Gmail.com'),
(4, 'Melanie', 'Mendoza', 'Gonzales', 'Melanie@Gmail.com'),
(5, 'Osman', 'Riso', 'Bocanegra', 'Osman@Gmail.com'),
(6, 'Cesar', 'Torrers', 'Villalvazo', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `ID_Usuario` int(11) NOT NULL,
  `Perfil` int(11) DEFAULT NULL,
  `Contrasena` varchar(16) DEFAULT NULL,
  `Username` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `amigos`
--
ALTER TABLE `amigos`
  ADD PRIMARY KEY (`ID_Amigo`),
  ADD KEY `Usuario_Amigo1` (`Usuario_Amigo1`),
  ADD KEY `Usuario_Amigo2` (`Usuario_Amigo2`);

--
-- Indices de la tabla `chat_grupal`
--
ALTER TABLE `chat_grupal`
  ADD PRIMARY KEY (`ID_Grupo`);

--
-- Indices de la tabla `grupos`
--
ALTER TABLE `grupos`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `Usuario` (`Usuario`),
  ADD KEY `Grupo` (`Grupo`);

--
-- Indices de la tabla `mensajes_directos`
--
ALTER TABLE `mensajes_directos`
  ADD PRIMARY KEY (`ID_Mensaje`),
  ADD KEY `Remitente` (`Remitente`),
  ADD KEY `Destinatario` (`Destinatario`);

--
-- Indices de la tabla `mensajes_grupales`
--
ALTER TABLE `mensajes_grupales`
  ADD PRIMARY KEY (`ID_Mensaje`),
  ADD KEY `Remitente` (`Remitente`),
  ADD KEY `Destinatario` (`Destinatario`);

--
-- Indices de la tabla `perfil`
--
ALTER TABLE `perfil`
  ADD PRIMARY KEY (`ID_Perfil`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`ID_Usuario`),
  ADD KEY `Perfil` (`Perfil`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `amigos`
--
ALTER TABLE `amigos`
  MODIFY `ID_Amigo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT de la tabla `chat_grupal`
--
ALTER TABLE `chat_grupal`
  MODIFY `ID_Grupo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `grupos`
--
ALTER TABLE `grupos`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `mensajes_directos`
--
ALTER TABLE `mensajes_directos`
  MODIFY `ID_Mensaje` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;

--
-- AUTO_INCREMENT de la tabla `mensajes_grupales`
--
ALTER TABLE `mensajes_grupales`
  MODIFY `ID_Mensaje` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT de la tabla `perfil`
--
ALTER TABLE `perfil`
  MODIFY `ID_Perfil` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `ID_Usuario` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `amigos`
--
ALTER TABLE `amigos`
  ADD CONSTRAINT `amigos_ibfk_1` FOREIGN KEY (`Usuario_Amigo1`) REFERENCES `perfil` (`ID_Perfil`),
  ADD CONSTRAINT `amigos_ibfk_2` FOREIGN KEY (`Usuario_Amigo2`) REFERENCES `perfil` (`ID_Perfil`);

--
-- Filtros para la tabla `grupos`
--
ALTER TABLE `grupos`
  ADD CONSTRAINT `grupos_ibfk_1` FOREIGN KEY (`Usuario`) REFERENCES `perfil` (`ID_Perfil`),
  ADD CONSTRAINT `grupos_ibfk_2` FOREIGN KEY (`Grupo`) REFERENCES `chat_grupal` (`ID_Grupo`);

--
-- Filtros para la tabla `mensajes_directos`
--
ALTER TABLE `mensajes_directos`
  ADD CONSTRAINT `mensajes_directos_ibfk_1` FOREIGN KEY (`Remitente`) REFERENCES `perfil` (`ID_Perfil`),
  ADD CONSTRAINT `mensajes_directos_ibfk_2` FOREIGN KEY (`Destinatario`) REFERENCES `perfil` (`ID_Perfil`);

--
-- Filtros para la tabla `mensajes_grupales`
--
ALTER TABLE `mensajes_grupales`
  ADD CONSTRAINT `mensajes_grupales_ibfk_1` FOREIGN KEY (`Remitente`) REFERENCES `perfil` (`ID_Perfil`),
  ADD CONSTRAINT `mensajes_grupales_ibfk_2` FOREIGN KEY (`Destinatario`) REFERENCES `chat_grupal` (`ID_Grupo`);

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`Perfil`) REFERENCES `perfil` (`ID_Perfil`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
