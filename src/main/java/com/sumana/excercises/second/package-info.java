package com.sumana.excercises.second;

/*
Problema de los lectores y escritores:
Enunciado:
        Existen dos tipos de procesos, lectores y escritores, los cuales requieren del acceso a una base de datos.

        Se deben considerar las siguientes reglas de acceso a la base de datos:

        Solamente puede haber un escritor al mismo tiempo, si un escritor o un lector quiere utilizar la base de datos y  hay un escritor utilizándola, entonces debe  esperar.

        Pueden haber  varios lectores utilizando la base de datos al mismo tiempo, pero si un escritor quiere usar la base de datos mientras los lectores la usan, deberá esperar.

        El último lector en usar la base de datos debe habilitarla para que pueda ingresar el siguiente escritor que estaba esperando la base de datos o en todo caso al siguiente proceso que requiera usarla.*/
