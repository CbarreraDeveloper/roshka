package com.test.roshka.utilitarios;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class NoticiasUtilis {
	
	public static String getFechaFormatoIso(String fecha){

        String fechaISO ="";
        LocalDateTime localDateTime = null;
        
        if(fecha.contains("hace")){
            Pattern pattern = Pattern.compile(".+ [0-9]+ .+");
            java.util.regex.Matcher matcher = pattern.matcher(fecha);
            if (fecha.contains("hora")){
                System.out.println("contiene palabra hora");
                if(matcher.matches()) {
                    System.out.println("matcheado correctamente");
                    int cantidadHoras = Integer.parseInt(fecha.replaceAll("[^0-9]", ""));
                    localDateTime = LocalDateTime.now().minusHours(cantidadHoras);
                   
                }else{
                	
                    localDateTime = LocalDateTime.now().minusHours(1);
                    
                }
            }else if (fecha.contains("minuto")){
                System.out.println("contiene palabra minuto");
                if(matcher.matches()) {
                    int cantidadMinutos = Integer.parseInt(fecha.replaceAll("[^0-9]", ""));
                    localDateTime = LocalDateTime.now().minusMinutes(cantidadMinutos);
                    
                }else {
                    localDateTime = LocalDateTime.now().minusMinutes(1);
                    
                }

            }

        }else{
        	
            DateTimeFormatter format = DateTimeFormatter.ofPattern("d/M/yyyy HH:mm:ss");
            localDateTime = LocalDateTime.parse(fecha+":00", format);
            System.out.println(localDateTime.toString());
        }
        ZonedDateTime utcZoned = localDateTime.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("UTC"));
        fechaISO = utcZoned.toString();
        System.out.println(fechaISO);
        if(fechaISO.length()==22){
            System.out.println("fecha previamente"+fechaISO);
            fechaISO = fechaISO.replace("Z[UTC]",":00Z");
            System.out.println("fecha en formato ISO: "+fechaISO);
        }else if(localDateTime.toString().length()>25){
            fechaISO = fechaISO.substring(0,19);
            fechaISO = fechaISO + "Z";
        }
        return fechaISO;

    }
	
	public static boolean isNullOrEmpty(Object object) {
		return object == null || "".equals(object) ? Boolean.TRUE : Boolean.FALSE;
	}

}
