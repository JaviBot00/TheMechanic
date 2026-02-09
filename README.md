# TrabajoTaller

## Descripción del Proyecto

Vamos a realizar, desde cero, las clases necesarias para implementar en Java con consola un sistema de control de los
trabajos de un taller de reparación mecánica de vehículos a motor (motos, coches, furgones y camiones).

## Requisitos del Sistema

El sistema debe permitir:

- Un vehículo entrará al taller y le tomaremos los siguientes datos:
    - Matrícula
    - Modelo
    - Nombre y Apellidos del dueño
    - DNI del dueño
    - Fecha de entrada
    - Hora de entrada

- Una vez incluido en el sistema, nuestros mecánicos tendrán que evaluar el vehículo determinando el problema que señala
  el client (o la necesidad que plantea) y la posible solución. También se determinará un número de horas previsto de
  trabajo para realización de la reparación/mantenimiento. Por lo tanto, un "trabajoTaller" tendrá una descripción del
  problema, solución prevista, número de horas previstas, número de horas reales, y si ha sido paid o no.

- Una vez el vehículo esté reparado/acondicionado, se apuntarán las horas reales realizadas en el proceso (pueden
  diferir de las calculadas originalmente), y se procederá a efectuar un cobro, calculado a partir de las horas
  realizadas y del tipo de vehículo (en principio nuestro sistema sólo se encarga de facturar horas de trabajo hombre).

- El tipo de cobro será:
    - Motos: 20€/hora
    - Coches: 25€/hora
    - Furgones:30€/hora + un fijo de dificultad de 30€
    - Camiones: 40€/hora + un fijo de dificultad de 50€

## TRABAJO

Realizar las clases:

- TrabajoTaller
- Vehiculo
- Moto
- Coche
- Furgon
- Camion
- MainTaller

Se realizará el siguiente interfaz:

- Consola System in/out: añadir interfaz de texto para poder introducir vehículos, de los cuatro tipos, trabajos a cada
  uno de los vehículos introducidos y poder calcular cobros

## CompareTo

- negative → this < other
- zero → this == other
- positive → this > other
