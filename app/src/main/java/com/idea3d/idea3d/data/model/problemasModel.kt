package com.idea3d.idea3d.data.model


import com.idea3d.idea3d.R

class problemasModel {

    companion object{

        val guiaErrores: List<Problemas> = listOf(
            Problemas("No extruye en el inicio de la impresión", "No sale nada de plástico durante el inicio de la impresión.", "No sale plástico del nozzle", "El extrusor está tapado", "Nozzle 'raya' la cama", R.drawable.no_arranca),
            Problemas("La primer capa no se adhiere a la base", "En el comienzo de la impresión, la primera capa no se pega a la cama caliente", "Cama caliente y nozzle", "Velocidad primera capa", "Temperatura", R.drawable.primera_capa_nopega),
            Problemas("Sale poco plástico del nozzle", "Falta plástico para unir correctamente los perimetros y el relleno", "Diámetro de filamento", "Multiplicador", null,R.drawable.sale_poco_plastico),
            Problemas("Sale mucho plástico del nozzle", "Exceso de plástico en las capas superiores", "Parámetro Multiplicador", null, null, R.drawable.sale_mucho_plastico),
            Problemas("Agujeros en las capas superiores", "Huecos en la cara superior de las piezas", "Capas superiores", "Relleno bajo", "Poca extrusión", R.drawable.agujeros_capas_superiores),
            Problemas("Hilos en el exterior de la pieza", "Restos de hilos que desprende el cabezal y se pegan a la pieza",  "Retracción", "Temperatura alta", "Grandes desplazamientos", R.drawable.hilos),
            Problemas("Deformación por exceso de temperatura", "Imperfecciones en los detalles por sobrecalentamiento",  "Refrigeración de capa", "Temperatura alta", "Alta velocidad", R.drawable.def_exc_temp),
            Problemas("Desplazamiento de capas", "Se nota una mala calibración de los ejes desalineados",  "Hotend", "Correas", "Electrónica", R.drawable.desplazamiento_capas),
            Problemas("Filamento Atascado", "Durante la impresión el plástico no avanza y el filamento se nota mordido", "Hotend", "Velocidad", "Temperatura", R.drawable.fila_mordido),
            Problemas("Extrusor atascado", "No sale pláctico de la boquilla", "Temperatura incorrecta ", "Filamento defectuoso ", "Desarmar hotend", R.drawable.ext_obstruido),
            Problemas("Deja de extruir plástico ", "Se interrumpe la extrusión en medio de la impresión", "Tubo de teflón dañado", "Filamento mordido","Motor sobrecalentado", R.drawable.deja_de_imprimir),
            Problemas("Relleno deficiente", "El relleno es muy fino y sus paredes no se unen correctamente", "Velocidad de relleno", "Cambiar de relleno", "Flujo de material", R.drawable.relleno_deficiente),
            Problemas("Plástico pegado a la pieza", "Se forman 'grumos' de plástico y se pegan a la pieza", "Retracción", "Temperatura", null, R.drawable.plastico_pegado),
            Problemas("Huecos en las capas exteriores", "Se forman agujeros en las capas superiores y en los laterales", "Relación perímetro/relleno", "Velocidad de impresión", "Capas 'Top'", R.drawable.huecos_capas_ext),
            Problemas("Warping", "En medio de la impresión, la pieza empieza a despegarse de la cama caliente", "Despegue primeras capas", "Flujo de aire", "Recomendaciones ABS", R.drawable.warping),
            Problemas("Capa superior 'rayada'", "El nozzle deja marcas en la superficie de la pieza", "Exceso de plástico", "Eje Z", null, R.drawable.cara_sup_rayada),
            Problemas("Desfazaje de capas", "Ondulaciones laterales en toda la pieza", "Eje Z", null, null, R.drawable.desfazaje_capas),
            Problemas("Lineas en la pieza", "Lineas y acabado irregular en toda la pieza", "Velocidad de impresión", "Aceleración", "Mecánica", R.drawable.lineas_impresion),
            Problemas("Falta de relleno", "Falta de material en paredes finas", "Relleno", "Perímetros", null, R.drawable.falta_relleno),
            Problemas("Poca resolución", "Los pequeños detalles no se imprimen o no lo hacen correctamente", "Boquilla incorrecta", "Configuración de boquilla", "Rediseñar la pieza", R.drawable.poca_resolucion            ),
            Problemas("Capas muy separadas", "Las capas no se pegan entre sí", "Altura de capa", "Temperatura baja", null, R.drawable.capas_muy_separadas),
            Problemas("Exceso de plástico", "Se forman 'gotas' de plástico durante la impresión", "Desajuste de Hotend", null, null, R.drawable.gotas),
            Problemas("Primer capa deficiente", "Las lineas no se unen, se forman grietas y el material se despega de la base", "Altura de capa", "Temperatura baja", "Lineas muy juntas", R.drawable.primera_capa_deficiente)

        )
    }
}