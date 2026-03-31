package Taller_1;

/*Integrantes del Taller:
 * 
 * Victor González Morales,  Rut: 220615529
 * Joaquín Torres Flores,  Rut: 215473708
 * 
 * */

//Importar librerias para axtivar las funciones
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class Main {

	public static void main(String[] args) {
		
		/*Generar Scanner Global, este lo emplearé durante todo el código
		 *para ahorrar espacio usandolo como una variable en los métodos*/
		Scanner scanner = new Scanner(System.in);
		
		/*Generar Arreglos los cuales me servirán como una memoria de cada dato
		 *presente en los txt con un largo de 300 según aparece en la pauta*/
		
		//Usuarios
		String[] UsuarioID = new String[300];
		String[] UsuarioContraseña = new String[300];
		
		//Registros
		String[] RegistroID = new String[300];
		String[] RegistroFecha = new String[300];
		int[] RegistroHoras = new int[300];
		String[] RegistroActividad = new String[300];
		
		/*Generar método el cual lea y rellene los Arreglos mediante lectura del txt de manera ordenada*/
		int a = 0; int b = 0;
		RellenarArreglo("Usuarios.txt", UsuarioID, UsuarioContraseña, RegistroID, RegistroFecha, RegistroHoras, RegistroActividad, a);
		RellenarArreglo("Registros.txt", UsuarioID, UsuarioContraseña, RegistroID, RegistroFecha, RegistroHoras, RegistroActividad, b);
		
		//Llamar al método menú principal para poder elegir entre los menús
		MenuPrincipal(UsuarioID, UsuarioContraseña, RegistroID, RegistroFecha, RegistroHoras, RegistroActividad, scanner);
		
		
		//cerrar scanner como una buena práctica :D
		scanner.close();
	
	}
	
	
	
	/* ------------------ Sección generada para la creación del menú global ------------------*/
	
	/*Método generado para gestionar el llamado de cada menú mediante la implementación 
	 *del Scanner y switch/case*/
	private static void MenuPrincipal(String[] usuarioID, String[] usuarioContraseña, String[] registroID,
			String[] registroFecha, int[] registroHoras, String[] registroActividad, Scanner scanner) {
		
		String Respuesta = "";
		
		while(!Respuesta.equals("3")) {
			
			Print("1) Menu de Usuarios\r\n"
					+ "2) Menu de Analisis\r\n"
					+ "3) Salir\n");
			
			Respuesta = scanner.nextLine();
		
			switch(Respuesta){
				
				case "1":
					identificadorUsuario(registroID, registroFecha, registroHoras, registroActividad, usuarioID, usuarioContraseña, scanner);
					break;
					
				case "2":
					MenuAnalisis(usuarioID, usuarioContraseña, registroID, registroFecha, registroHoras, registroActividad, scanner);
					break;
					
				default:
					if(!Respuesta.equals("3")) {
						Print("Opción incorrecta, intente nuevamente\n");
					}
					break;
				
			}

		}
		
		Print("Saliento del programa .....");

	}

	/*--------------------------------------------------------------------------------------------------------*/
	
	
	
	/*--------------------------------- Sección de Menú Usuarios----------------------------------------------*/

	/*Método el cual servirá para identificar usuario y entregar todos los valores en base a el*/
	private static void identificadorUsuario(String[] registroID, String[] registroFecha, int[] registroHoras, 
			String[] registroActividad, String[] usuarioID, String[] usuarioContraseña, Scanner scanner) {
		
		Boolean Encontrado = false;
		
		while(Encontrado == false) {
			
			Print("Usuario: ");
			String Usuario = scanner.nextLine();
			Print("Contraseña: ");
			String Contraseña = scanner.nextLine();
		
			for(int a = 0; a < usuarioID.length; a++) {
				
				if(usuarioID[a] != null && usuarioID[a].equals(Usuario) && usuarioContraseña[a] != null && usuarioContraseña[a].equals(Contraseña)) {
					
					Print("\nAcceso correcto!\n");
					MenuUsuarios(Usuario, registroID, registroFecha, registroHoras, registroActividad, usuarioID, usuarioContraseña, scanner);
					Encontrado = true;
					break;
					
				}
				
			}
			
			if(Encontrado == false) {
				
				Print("\nUsuario/ Contraseña no encontrad@, ingrese otr@ nuevamente\n");
				
			}
			
		}
		
		Print("\nSaliento del menú de Usuarios .....\n");
		
	}
	
	
	
	/*Método el cual imprimirá las opciones del menú de usuarios para luego comunicarse con los diferentes 
	 *métodos de cada opción que se solcite*/
	private static void MenuUsuarios(String usuario, String[] registroID, String[] registroFecha, int[] registroHoras,
			String[] registroActividad, String[] usuarioID, String[] usuarioContraseña, Scanner scanner) {
		
		String Respuesta = "";
		
		while(!Respuesta.equals("5")) {
			
			Print("Bienvenido " + usuario + "!\n");
			Print("Que deseas realizar?\r\n"
					+ "\r\n"
					+ "1) Registrar actividad.\r\n"
					+ "2) Modificar actividad.\r\n"
					+ "3) Eliminar actividad.\r\n"
					+ "4) Cambiar contraseña.\r\n"
					+ "5) Salir.\n");
			
			Respuesta = scanner.nextLine();
			
			switch(Respuesta) {
			
			case "1":
				RegistrarActividad("Registros.txt", scanner, usuario, registroID, registroFecha, registroHoras, registroActividad);
				break;
			
			case "2":
				ModificarActividad(usuario, registroID, registroFecha, registroHoras, registroActividad, scanner);
				break;
				
			case "3":
				EliminarActividad(usuario, registroID, registroFecha, registroHoras, registroActividad, scanner);
				break;
				
			case "4":
				CambiarContraseña(usuario, usuarioID, usuarioContraseña, scanner);
				break;
				
			default:
				
				if(!Respuesta.equals("5")) {
					
					Print("\nOpción incorrecta, intente nuevamente\n");
					
				}
				
				break;
				
			}
			
			Print("Ingrese otra opción: ");
			
		}
		
	}


	//1
	
	/*Método generado para registrar actividad preguntado cual desea agregar
	 *para luego colocarla en el txt */
	private static void RegistrarActividad(String string, Scanner scanner, String usuario, String[] registroID, String[] registroFecha, int[] registroHoras, String[] registroActividad) {
		
		try {
			
			Print("\nIngrese la nueva actividad (formato: Fecha;Horas;Actividad)\n");
			
			String datos = scanner.nextLine();
		    String[] partes = datos.split(";");
		    
		    if(partes.length < 3) {
		    	
	            Print("Formato incorrecto, use: Fecha;Horas;Actividad\n");
	            return;
	            
		    } else if(partes[0].split("/").length != 3) {
	        	
	        	Print("Formato incorrecto fecha , use: dia/mes/año\n");
	        	return;
		    }
		    
		    try {
		    	
		    	if(Integer.valueOf(partes[1]) < 0) {
		    		
		    		Print("Las horas deben ser un número positivo\n");
		    		return;
		    		
		    	}
		    	
		    }catch(NumberFormatException e) {
	        	
	        	Print("Las horas deben ser un número\n");
	        	return;
	        	
	        }
		    
		    String Fecha = partes[0];
	        int Horas = Integer.valueOf(partes[1]);
	        String Actividad = partes[2];
	        
	        try {
	        	
	        	String[] partesFecha = Fecha.split("/");
	        	
	        	int x = Integer.valueOf(partesFecha[0]);
	        	int y = Integer.valueOf(partesFecha[1]);
	        	int z = Integer.valueOf(partesFecha[2]);
	        	
	        	if(x < 0 || y < 0 || z < 0) {

	        		Print("La fecha solo puede contener números positivos\n");
	        		return;
	        		
	        	} else if(x > 31 || y > 12) {
	        		
	        		Print("Ingrese una fecha posible\n");
	        		return;
	        	}
	        	
	        }catch(NumberFormatException e) {
	        	
	        	Print("La fecha solo debe contener números\n");
	        	return;
	        	
	        }

	        
	        int Libre = -1;
	        for(int a = 0; a < registroID.length; a++) {
	            if(registroID[a] == null) {
	                Libre = a;
	                break;
	            }
	        }
	        
	        if(Libre == -1) {
	            Print("No queda espacio \n");
	            return;
	        }
	        
	        registroID[Libre] = usuario;
	        registroFecha[Libre] = Fecha;
	        registroHoras[Libre] = Horas;
	        registroActividad[Libre] = Actividad;
			
			BufferedWriter Escritor = new BufferedWriter(new FileWriter(string, true));
			Escritor.write(usuario + ";" + Fecha + ";" + Horas + ";" + Actividad);
			Escritor.newLine();
			Escritor.close();
			
			Print("\nActividad agregada :D\n");
			
		}
		
		catch(Exception e) {
			
			Print("\nError al agregar la actividad\n");
			
		}
		
	}
	
	
	//2
	
	//Método generado para modificar alguna actividad generada por el usuario, 
	private static void ModificarActividad(String usuario, String[] registroID, String[] registroFecha,
			int[] registroHoras, String[] registroActividad, Scanner scanner) {
		
		Print("Cual actividad deseas modificar: \n");

		int Posición = 0;
		
		Print(Posición + ") Regresar." );
		
		for(int a = 0; a < registroActividad.length; a++) {
			
			if(registroActividad[a] != null && registroID[a].equals(usuario)) {
				
				Posición++;
				Print(Posición + ") " + registroID[a] + ";" + registroFecha[a] + ";" + registroHoras[a] + ";" + registroActividad[a]);
				
			}
			
		}
		
		if(Posición == 0) {
			
			Print("No hay actividades que modificar\n");
			return;
			
		}
		
		Print("\n¿Qué numero de actividad deseas modificar?: ");
		
		try {

			int ActividadAModificar = scanner.nextInt();
			scanner.nextLine();
			
			if(ActividadAModificar < 0 || ActividadAModificar > Posición) {
				
				Print("El valor no está dentro del rango de valores\n");
				return;
				
			} else if (ActividadAModificar == 0) {
				
				Print("Regresando\n");
				return;
				
			}
			
			Print("\n¿Qué deseas modificar?: \n");
			Print("0) Regresar.\r\n"
				+ "1) Fecha\r\n"
				+ "2) Duracion\r\n"
				+ "3) Tipo de actividad\n");
		
			String Modificar = scanner.nextLine();
			
			if (!Modificar.equals("0") && !Modificar.equals("1") && !Modificar.equals("2") && !Modificar.equals("3")) {
				Print("Opción no válida\n");
				return;
			}
			
			if(Modificar.equals("0")) {
				Print("Regresando...\n");
				return;
			}
			else {
				BuscarYModificar(ActividadAModificar, Modificar, usuario, registroID, registroFecha, registroHoras, registroActividad, scanner);
			}
			
		}catch(Exception e) {
			
			Print("La actividad a modificar debe ser un número\n");
			return;
			
		}

	}
	
	
	/*Método generado para gestionar la actividad cambiada*/
	private static void BuscarYModificar(int actividadAModificar, String modificar, String usuario,
			String[] registroID, String[] registroFecha, int[] registroHoras, String[] registroActividad, Scanner scanner) {
		
		int Posición = 0;
		String[] NuevoTexto = new String[registroActividad.length];
		
		Print("Ingrese el valor por el cual cambiara su elección: \n");
		
		try {
		String Cambiar = scanner.nextLine();
		
		for(int a = 0; a < registroActividad.length; a++) {
			
			if(registroActividad[a] != null && registroID[a].equals(usuario)) {
				
				Posición++;
				
				if(actividadAModificar == Posición) {
				
					switch(modificar) {
					
					case "1":
						
						try {
				        	
				        	String[] partesFecha = Cambiar.split("/");
				        	
				        	int x = Integer.valueOf(partesFecha[0]);
				        	int y = Integer.valueOf(partesFecha[1]);
				        	int z = Integer.valueOf(partesFecha[2]);
				        	
				        	if(x < 0 || y < 0 || z < 0) {

				        		Print("La fecha solo puede contener números positivos\n");
				        		return;
				        		
				        	} else if(x > 31 || y > 12 ) {
				        		
				        		Print("Ingrese una fecha posible\n");
				        		return;
				        	}
				        	
				        }catch(NumberFormatException e) {
				        	
				        	Print("La fecha solo debe contener números\n");
				        	return;
				        	
				        }
						
						registroFecha[a] = Cambiar;
						
						break;
						
					case "2":
						
						try {
					    	
					    	if(Integer.valueOf(Cambiar) < 0) {
					    		
					    		Print("Las horas deben ser un número positivo\n");
					    		return;
					    		
					    	}
					    	
					    }catch(NumberFormatException e) {
				        	
				        	Print("Las horas deben ser un número\n");
				        	return;
				        	
				        }
						
						registroHoras[a] = Integer.valueOf(Cambiar);
						
						break;
						
					case "3":
						registroActividad[a] = Cambiar;
						break;
						
					}
					
					break;
				
				}
				
			}	
			
		}
		
		int Indice = 0;

		for(int a = 0; a < registroID.length;a++) {
			
			if(registroID[a] != null) {
				
				NuevoTexto[Indice] = registroID[a] + ";" + registroFecha[a] + ";" + registroHoras[a] + ";" + registroActividad[a];
				Indice++;
			}
			
		}
		
		ReescribirTexto(NuevoTexto, "Registros.txt");
		Print("Actividad modificada \n");
		
		} catch(Exception e) {
			
			Print("El valor a cambiar debe ser distinto de vacio o ser numérico\n");
			return;
			
		}
		
	}
	
	
	//3
	
	/*Método generado para preguntar y borrar la actividad seleccionada*/
	private static void EliminarActividad(String usuario, String[] registroID, String[] registroFecha,
			int[] registroHoras, String[] registroActividad, Scanner scanner) {
		
		Print("Actividades: \n");
		
		int Posición = 0;
		int[] LugarAModificar = new int[registroActividad.length];
		
		for(int a = 0; a < registroActividad.length; a++) {
			
			if(registroActividad[a] != null && registroID[a].equals(usuario)) {
				
				LugarAModificar[Posición] = a;
				Posición++;
				Print(Posición + ") " + registroID[a] + ";" + registroFecha[a] + ";" + registroHoras[a] + ";" + registroActividad[a]);
				
			}
			
		}
		
		if(Posición == 0) {
			
			Print("No hay actividades que eliminar\n");
			return;
			
		}
		
		else {
			
			Print("¿Qué actividad deseas eliminar?: \n");
			
			try {
			
			int ActividadAEliminar = scanner.nextInt();
			scanner.nextLine();
			
			if(ActividadAEliminar < 1 || ActividadAEliminar > Posición) {
				
				Print("Posición fuera del rango\n");
				return;
				
			}
			
			int Indice = LugarAModificar[ActividadAEliminar - 1];
			
			registroID[Indice] = null;
			registroFecha[Indice] = null;
			registroHoras[Indice] = 0;
			registroActividad[Indice] = null;
			
			String[] NuevoTexto = new String[registroID.length];
			
			int Cambio = 0;
			
			for(int a = 0; a < registroID.length; a++) {
				
				if(registroID[a] != null) {
					
					NuevoTexto[Cambio] =  registroID[a] + ";" + registroFecha[a] + ";" + registroHoras[a] + ";" + registroActividad[a];
					Cambio++;
					
				}
				
			}
			
			ReescribirTexto(NuevoTexto, "Registros.txt");
			Print("Actividad eliminada\n ");
			
			} catch(Exception e) {
				
				Print("La posición debe ser un número\n");
				scanner.nextLine();
				return;
			}
			
		}
	}


	//4
	
	/*Método generado para pedir la contraseña que desea cambiar y reemplazarla en el txt*/
	private static void CambiarContraseña(String usuario, String[] usuarioID, String[] usuarioContraseña,
			Scanner scanner) {
		
		String[] NuevoTexto = new String[usuarioContraseña.length];
		
		Print("Ingrese su nueva contraseña: \n");
		String Contraseña = scanner.nextLine();
		
		for(int a = 0; a < usuarioID.length; a++) {
			
			if(usuarioID[a] != null) {
			
				if(usuario.equals(usuarioID[a])) {
				
					usuarioContraseña[a] = Contraseña;
					break;
					
				}
				
			}
			
		}
		
		for(int a = 0; a < usuarioID.length; a++) {
			
			if(usuarioID[a] != null) {
				
				NuevoTexto[a] = usuarioID[a] + ";" + usuarioContraseña[a];
				
			}
			
		}
		
		
		ReescribirTexto(NuevoTexto, "Usuarios.txt");
		Print("Contraseña cambiada :D\n");
		
		
	}

	
	//Más de 1 método usa este método
	
	/*Método generado para reescribir el archivo con su respectivo cambio*/
	private static void ReescribirTexto(String[] nuevoTexto, String archivo) {

		try {
			
			BufferedWriter Escritor = new BufferedWriter(new FileWriter(archivo, false));
			
			for (int a = 0; a < nuevoTexto.length; a++) {
				
				if(nuevoTexto[a] != null) {
					
					Escritor.write(nuevoTexto[a]);
					Escritor.newLine();
					
				}
				
			}
			
			Print("Texto modificado :D\n");
			Escritor.close();
			
		}
		
		catch(Exception e) {
			
			Print("Error al modificar la contraseña\n");
			
		}
		
	}



	/*--------------------------------------------------------------------------------------------------------*/
	
	
	
	
	
	
	/*------------------ Sección generada para la creación del menú de análisis ------------------*/
	
	/*Método generado para gestionar las distintas tareas pertenecientes al menú de análisis medienta
	 *preguntas de los scanner, ciclos y switch-case*/
	private static void MenuAnalisis(String[] usuarioID, String[] usuarioContraseña, String[] registroID,
			String[] registroFecha, int[] registroHoras, String[] registroActividad, Scanner scanner) {
		
		String Respuesta = "";
		
		while(!Respuesta.equals("5")) {
			
			Print("\nBienvenido al menu de analisis!\r\n"
				+ "\r\n"
				+ "Que deseas realizar?\r\n"
				+ "\r\n"
				+ "1) Actividad más realizada\r\n"
				+ "2) Actividad más realizada por cada usuario\r\n"
				+ "3) Usuario con mayor procastinacion\r\n"
				+ "4) Ver todas las actividades\r\n"
				+ "5) Salir\n");
			
			Respuesta = scanner.nextLine();
			
			String[] ActividadesUnicas = new String[300];
			ActividadesUnicas(ActividadesUnicas, registroActividad);
			
			switch(Respuesta) {
			
			case "1":
				ActividadMásRealizada(registroActividad, ActividadesUnicas, registroHoras);
				break;
				
			case "2":
				ActividadMásRealizadaPorUsuario(usuarioID, registroID, registroHoras, registroActividad, ActividadesUnicas);
				break;
				
			case "3":
				MásProcastinador(registroID, registroHoras, registroActividad, usuarioID);
				break;
				
			case "4":
				VerActividadesUnicas(ActividadesUnicas);
				break;
				
			default:
				if(!Respuesta.equals("5")) {
					Print("Opción incorrecta, intente nuevamente\n");
				}
				break;
			
			}
			
		}
		
		Print("\nSaliento del menú de Análisis .....\n");
		
	}
	
	
	//1
	
	/*Método el cual recorrerá Arreglos y almacenará el tiempo empleado por cada actividad para
	 *luego llamár al método del mayor e imprimir la actividad buscada*/
	private static void ActividadMásRealizada(String[] registroActividad, String[] actividadesUnicas,
			int[] registroHoras) {
		
		int[] ActividadMásRealizada = new int[actividadesUnicas.length];
		
		for(int a = 0; a < registroActividad.length; a++) {
			
			if(registroActividad[a] != null) {
				
				for(int b = 0; b < actividadesUnicas.length; b++) {
					
					if(actividadesUnicas[b] != null && actividadesUnicas[b].equals(registroActividad[a])) {
						
						ActividadMásRealizada[b] += registroHoras[a];
						break;
						
					}
					
				}
				
			}
			
		}
		
		int Posición = mayor(ActividadMásRealizada);
		Print("La actividad más realizada es: " +actividadesUnicas[Posición] + " con " + ActividadMásRealizada[Posición] + " Horas");
		
	}

	//2
	
	/*Método generado para mediante busqueda entre los arreglos de cada usuario, se encontrará
	 *la actividad más repetida por cada usuario empleando algoritmo del mayor y renovación de arreglos*/
	private static void ActividadMásRealizadaPorUsuario(String[] usuarioID, String[] registroID, int[] registroHoras,
			String[] registroActividad, String[] actividadesUnicas) {
		
		for(int a = 0; a < usuarioID.length; a++) {
			
			if(usuarioID[a] != null){

			int[] TiempoEnActividadFavorita = new int[registroActividad.length];
			
				for(int b = 0; b < registroActividad.length; b++) {
					
					if(registroActividad[b] != null && registroID[b] != null && registroID[b].equals(usuarioID[a])) {

						for(int c = 0; c < actividadesUnicas.length; c++) {
							
							if(actividadesUnicas[c] != null && actividadesUnicas[c].equals(registroActividad[b])) {
								
								TiempoEnActividadFavorita[c] += registroHoras[b];
								break;
								
							}
							
						}
						
					}
					
					
				}
				
				int Posición = mayor(TiempoEnActividadFavorita);
				Print("El usuario " + usuarioID[a] + " ha realizado la actividad " + actividadesUnicas[Posición] + "un total de " + TiempoEnActividadFavorita[Posición] + " horas");
			
			}
			
		}
		
	}

	//3
	
	/*Método el cual por cada usuario disponible, calculará el tiempo ocupado en actividades
	 *diferentes al estudio y las acumulará para luego llamar otra función y que calcule el mayor
	 *y mediante las listas anidades imprimirá al usuario más procastinador*/
	private static void MásProcastinador(String[] registroID, int[] registroHoras, String[] registroActividad, String[] usuarioID) {
		
		int[] TiempoDeOcio = new int[registroID.length];
		
		for(int a = 0; a < usuarioID.length; a++) {
			
			if(usuarioID[a] != null) {
				
				for(int b = 0; b < registroActividad.length; b++) {
					
					if(registroActividad[b] != null){
					
						if(!registroActividad[b].equals("estudiar") && registroID[b].equals(usuarioID[a])) {
							
							TiempoDeOcio[a] += registroHoras[b];
							
						}
					
					}
					
				}
				
			}
			
		}
		
		int Posición = mayor(TiempoDeOcio);
		
		Print("El usuario con mayor tiempo de ocio es: " + usuarioID[Posición] + ""
				+ " con " + TiempoDeOcio[Posición] + " Horas de tiempo de ocio");
		
	}

	//4

	/*Método el cual leerá e imprimirá la lista que se le ingrese*/
	private static void VerActividadesUnicas(String[] actividadesUnicas) {
		
		for(int a = 0; a < actividadesUnicas.length; a++) {
			
			if(actividadesUnicas[a] != null) {
				
				Print((a + 1) + ") " + actividadesUnicas[a]);
				
			}
			
		}
		
	}
	
	/*Método generado para buscar todas las actividades sin que se repitan ninguna vez y almacenar estas
	 *en un arreglo para su posterior uso*/
	private static void ActividadesUnicas(String[] actividadesUnicas, String[] registroActividad) {
		
		int Contador = 0;
		
		for(int a = 0; a < registroActividad.length; a++) {
			
			String Actividad = registroActividad[a];
			
			Boolean Encontrado = false;
			
			if(Actividad != null) {
				
				for(int b = 0; b < a; b++) {
					
					if(Actividad.equals(registroActividad[b])) {
						
						Encontrado = true;
						break;
						
					}
					
				}
				
				if(Encontrado == false) {
					actividadesUnicas[Contador] = Actividad;
					Contador++;
				}
				
			}
			
		}
		
	}
	
	
	//Más de 1 método usa este método
	
	/*Método el cual aplicará el algoritmo del mayor y retornará la posición de este*/
	private static int mayor(int[] tiempoDeOcio) {
		
		int mayor = 0;
		int posición = 0;
		
		for(int a = 0; a < tiempoDeOcio.length; a++) {
			
			if(tiempoDeOcio[a] >= mayor) {
				
				mayor = tiempoDeOcio[a];
				posición = a;
				
			}
			
		}
		
		return posición;
	}

	/*--------------------------------------------------------------------------------------------------------*/
	
	
	
	
	
	/* ------------------ Sección generada para lectura y almacenamiento de los datos de los txt ------------------*/

	/*Método empleado para generar una lectura de archivo en base al archivo que se le entregue
	 *comunicandose con otros método para almacenar cada dato en su Arreglo correspondiente*/
	private static void RellenarArreglo(String archivo, String[] usuarioID, String[] usuarioContraseña, 
			String[] registroID, String[] registroFecha, int[] registroHoras, String[] registroActividad, int posición) {
		
		File file = new File(archivo);
		
		try {
			
			Scanner lector = new Scanner(file);
			
			while(lector.hasNextLine()) {
				
				String linea = lector.nextLine();
				
				if(archivo.equals("Usuarios.txt")) {
					
					AlmacenarUsuario(linea, usuarioID, usuarioContraseña, posición);
					posición++;
					
				}
				
				else if (archivo.equals("Registros.txt")) {
					
					AlmacenarRegistros(linea, registroID, registroFecha, registroHoras, registroActividad, posición);
					posición++;
					
				}
			}
			
				lector.close();
				
		}
	
		catch(Exception e){
			
			Print("\nArchivo no encontrado\n");
			
		}
	}
	
	
	/*Método el cual mediante la entrega de una linea de texto, desglozará esta
	 *y guardará su información en su arreglo correspondiente*/
	public static void AlmacenarUsuario(String linea, String[] usuarioID, String[] usuarioContraseña, int posición) {

		String[] Partes = linea.split(";");
		
		usuarioID[posición] = Partes[0];
		usuarioContraseña[posición] = Partes[1];
		
	}
	
	/*Método el cual mediante la entrega de una linea de texto, desglozará esta
	 *y guardará su información en su arreglo correspondiente*/
	private static void AlmacenarRegistros(String linea, String[] registroID, String[] registroFecha, 
			int[] registroHoras, String[] registroActividad, int posición) {
		
		String[] Partes = linea.split(";");
		
		registroID[posición] = Partes[0];
		registroFecha[posición] = Partes[1];
		registroHoras[posición] = Integer.valueOf(Partes[2]);
		registroActividad[posición] = Partes[3];
		
	}
	
	/*----------------------------------------------------------------------------------------------------------------------*/
	
	//Método generado para imprimir bonito :D
	public static void Print(String texto) {
		System.out.println(texto);
	}
	
}

