package com.idea3d.idea3d.data.model


import com.idea3d.idea3d.R

class problemasModel {

    companion object{

        val guiaErrores: List<Problemas> = listOf(
            Problemas("No extruye en el inicio de la impresión", "No sale nada de plástico durante el inicio de la impresión.", "No sale plástico del nozzle", "El extrusor está tapado", "Nozzle 'raya' la cama", R.drawable.mujer),
            Problemas("La primer capa no se adhiere a la base", "En el comienzo de la impresión, la primera capa no se pega a la cama caliente", "Cama caliente y nozzle", "Velocidad primera capa", "Temperatura", R.drawable.bati),
            Problemas("Sale poco plástico del nozzle", "Falta plástico para unir correctamente los perimetros y el relleno", "Diámetro de filamento", "Multiplicador", null, R.drawable.bati),
            Problemas("Sale mucho plástico del nozzle", "Exceso de plástico en las capas superiores", "Parámetro Multiplicador", null, null, R.drawable.bati),
            Problemas("Agujeros en las capas superiores", "Huecos en la cara superior de las piezas", "Capas superiores", "Relleno bajo", "Poca extrusión", R.drawable.bati),
            Problemas("Hilos en el exterior de la pieza", "Restos de hilos que desprende el cabezal y se pegan a la pieza",  "Retracción", "Temperatura alta", "Grandes desplazamientos", R.drawable.bati),
            Problemas("Deformación por exceso de temperatura", "Imperfecciones en los detalles por sobrecalentamiento",  "Refrigeración de capa", "Temperatura alta", "Alta velocidad", R.drawable.bati),
            Problemas("Desplazamiento de capas", "Se nota una mala calibración de los ejes desalineados",  "Hotend", "Correas", "Electrónica", R.drawable.bati),
            Problemas("Filamento Atascado", "Durante la impresión el plástico no avanza y el filamento se nota mordido", "Hotend", "Velocidad", "Temperatura", R.drawable.bati),
            Problemas("Extrusor atascado", "No sale pláctico de la boquilla", "Temperatura incorrecta ", "Filamento defectuoso ", "Desarmar hotend", R.drawable.mujer),
            Problemas("Deja de extruir plástico ", "Se interrumpe la extrusión en medio de la impresión", "Tubo de teflón dañado", "Filamento mordido","Motor sobrecalentado", R.drawable.mujer),
            Problemas("Relleno deficiente", "El relleno es muy fino y sus paredes no se unen correctamente", "Velocidad de relleno", "Cambiar de relleno", "Flujo de material", R.drawable.mujer),
            Problemas("Plástico pegado a la pieza", "Se forman 'grumos' de plástico y se pegan a la pieza", "Retracción", "Temperatura", null, R.drawable.mujer),
            Problemas("Huecos en las capas exteriores", "Se forman agujeros en las capas superiores y en los laterales", "Relación perímetro/relleno", "Velocidad de impresión", "Capas 'Top'", R.drawable.mujer),
            Problemas("Warping", "En medio de la impresión, la pieza empieza a despegarse de la cama caliente", "Despegue primeras capas", "Flujo de aire", "Recomendaciones ABS", R.drawable.mujer),
            Problemas("Capa superior 'rayada'", "El nozzle deja marcas en la superficie de la pieza", "Exceso de plástico", "Eje Z", null, R.drawable.mujer),
            Problemas("Desfazaje de capas", "Ondulaciones laterales en toda la pieza", "Eje Z", null, null, R.drawable.mujer),
            Problemas("Desplazamiento de capas", "Lineas y acabado irregular en toda la pieza", "Velocidad de impresión", "Aceleración", "Mecánica", R.drawable.mujer),
            Problemas("Falta de relleno", "Falta de material en paredes finas", "Relleno", "Perímetros", null, R.drawable.mujer),
            Problemas("Poca resolución", "Los pequeños detalles no se imprimen o no lo hacen correctamente", "Boquilla incorrecta", "Configuración de boquilla", "Rediseñar la pieza", R.drawable.mujer),
            Problemas("Capas muy separadas", "Las capas no se pegan entre sí", "Altura de capa", "Temperatura baja", null, R.drawable.mujer),
            Problemas("Exceso de plástico", "Se forman 'gotas' de plástico durante la impresión", "Desajuste de Hotend", null, null, R.drawable.mujer),
            Problemas("Primer capa deficiente", "Las lineas no se unen, se forman grietas y el material se despega de la base", "Altura de capa", "Temperatura baja", "Lineas muy juntas", R.drawable.mujer)

        )
    }
}