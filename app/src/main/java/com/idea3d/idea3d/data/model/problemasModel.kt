package com.idea3d.idea3d.data.model


import com.idea3d.idea3d.R

class problemasModel {
    companion object{

        val guiaErrores: List<Problemas> = listOf(
            Problemas("No extruye en el inicio de la impresión", "No sale nada de plástico durante el inicio de la impresión.", "No sale plástico del nozzle", "El extrusor está tapado", "la vida", R.drawable.mujer),
            Problemas("La primer capa no se adhiere a la base", "En el comienzo de la impresión, la primera capa no se pega a la cama caliente", "No sale plástico del nozzle", "El extrusor está tapado", "la vida", R.drawable.bati),
            Problemas("Sale poco plástico del nozzle", "Falta plástico para unir correctamente los perimetros y el relleno", "No sale plástico del nozzle", "El extrusor está tapado", "la vida", R.drawable.mujer),
            Problemas("Sale mucho plástico del nozzle", "Exceso de plástico en las capas superiores", "No sale plástico del nozzle", "El extrusor está tapado", "la vida", R.drawable.mujer),
            Problemas("Agujeros en las capas superiores", "Huecos en la cara superior de las piezas", "No sale plástico del nozzle", "El extrusor está tapado", "la vida", R.drawable.mujer),
            Problemas("Hilos en el exterior de la pieza", "Restos de hilos que desprende el cabezal y se pegan a la pieza", "No sale plástico del nozzle", "El extrusor está tapado", "la vida", R.drawable.mujer),
            Problemas("Deformación por exceso de temperatura", "Imperfecciones en los detalles por sobrecalentamiento", "No sale plástico del nozzle", "El extrusor está tapado", "la vida", R.drawable.mujer),
            Problemas("Desplazamiento de capas", "Se nota una mala calibración de los ejes desalineados", "No sale plástico del nozzle", "El extrusor está tapado", "la vida", R.drawable.mujer),
            Problemas("Filamento Atascado", "Durante la impresión el plástico no avanza y el filamento se nota mordido", "No sale plástico del nozzle", "El extrusor está tapado", "la vida", R.drawable.mujer),

        )
    }
}